package org.github.davidcana.jcrud.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.davidcana.jcrud.core.Constants;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageException;
import org.github.davidcana.jcrud.storages.StorageResolver;
import org.github.davidcana.jcrud.core.File;

@WebServlet("/downloadFile")
public class DownloadFileServlet extends HttpServlet {

    private static final long serialVersionUID = -610766579581607142L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get parameters from Request
		String table = request.getParameter(Constants.TABLE_URL_PARAMETER);
		String key = request.getParameter(Constants.KEY_URL_PARAMETER);
		String field = request.getParameter(Constants.FILE_FIELD_URL_PARAMETER);
		
		// Get the file from the storage
        File file = this.getFileFromStorage(table, key, field, response);
        if (file == null) {
        	return;
        }
        
        // Download that file
        this.downloadFile(file, response);
    }

	private File getFileFromStorage(String table, String key, String field, HttpServletResponse response) throws IOException {
		
		try {
			// Resolve the storage
			@SuppressWarnings("rawtypes")
			Storage storage = StorageResolver.getInstance().get(table);
			
			// Get the file from the Storage instance
			File file = storage.getFile(key, field);
			
			// Check dataURL contains something
	        String dataURL = file.getContents();
	        if (dataURL == null || dataURL.isEmpty()) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing data URL");
	            return null;
	        }
			
			return file;
			
		} catch (StorageException e) {
			//throw new ServletException(e);
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error trying to get file: " + e.getMessage());
			return null;
		}
	}

	private void downloadFile(File file, HttpServletResponse response) throws IOException {
		
		try {
            // Parse the data URL
            DataURLInfo dataInfo = this.parseDataURL(file.getContents());
            
            // Set response headers
            response.setContentType(dataInfo.mimeType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            //response.setHeader("Content-Disposition", "attachment; filename=\"downloaded_file\"");
            
            if (dataInfo.base64Encoded) {
                // Decode base64 content
                byte[] fileContent = Base64.getDecoder().decode(dataInfo.dataPart);
                
                // Write to output stream
                try (OutputStream out = response.getOutputStream()) {
                    out.write(fileContent);
                }
            } else {
                // For non-base64 encoded data (URL encoded)
                byte[] fileContent = dataInfo.dataPart.getBytes("UTF-8");
                
                try (OutputStream out = response.getOutputStream()) {
                    out.write(fileContent);
                }
            }
            
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Data URL format");
            
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing file download");
        }
	}
    
    // Helper class to store parsed data URL information
    private static class DataURLInfo {
        String mimeType;
        boolean base64Encoded;
        String dataPart;
    }
    
    // Method to parse data URL
    private DataURLInfo parseDataURL(String dataURL) throws IllegalArgumentException {
    	
        if (!dataURL.startsWith("data:")) {
            throw new IllegalArgumentException("Not a valid data URL");
        }
        
        DataURLInfo info = new DataURLInfo();
        
        // Remove the "data:" prefix
        String content = dataURL.substring(5);
        
        // Split into metadata and data parts
        int commaIndex = content.indexOf(',');
        if (commaIndex == -1) {
            throw new IllegalArgumentException("Invalid data URL format - no comma separator");
        }
        
        String metadata = content.substring(0, commaIndex);
        info.dataPart = content.substring(commaIndex + 1);
        
        // Parse metadata
        String[] metadataParts = metadata.split(";");
        
        // First part is MIME type (default to "application/octet-stream")
        info.mimeType = metadataParts.length > 0 && !metadataParts[0].isEmpty()? 
                        metadataParts[0]:
                        "application/octet-stream";
        
        // Check for base64 encoding
        info.base64Encoded = metadata.toLowerCase().contains(";base64");
        
        return info;
    }
}
