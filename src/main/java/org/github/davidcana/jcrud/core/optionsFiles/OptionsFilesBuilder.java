package org.github.davidcana.jcrud.core.optionsFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.atteo.classindex.ClassIndex;
import org.github.davidcana.jcrud.core.Constants;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.optionsFiles.javaParsing.JavaParser;
import org.github.davidcana.jcrud.core.utils.CoreUtils;
import freemarker.template.TemplateException;

public class OptionsFilesBuilder {
	
	static private OptionsFilesBuilder instance;
	static private final boolean STANDARD_OUT_MODE = false;
	private JavaParser javaParser = new JavaParser();
	
	public OptionsFilesBuilder(){}
	
	public static void main(String[] args) throws IOException, TemplateException, ClassNotFoundException {
		
		System.out.println(
				"Running run method of OptionsFilesBuilder class to generate javascript files of model classes..."
		);
		
		String rootFolder = args[0];
		boolean debugJavaParser = Boolean.parseBoolean(args[1]);
		
		OptionsFilesBuilder instance = new OptionsFilesBuilder();
		List<OptionsFile> optionsFiles = instance.run(rootFolder, debugJavaParser);
		
		System.out.println(
				optionsFiles.isEmpty()?
				"The list of options files to built is empty, so there is no javascript file to build.":
				optionsFiles.size() + " javascript files of model classes generated successfully."
		);
	}
	
	public List<OptionsFile> run(String rootFolder, boolean debugJavaParser) throws IOException, TemplateException, ClassNotFoundException {
		
		// Build the list of instances of OptionsFile
		List<OptionsFile> optionsFiles = this.buildOptionsFiles(rootFolder, debugJavaParser);
		
		// Iterate optionsFiles and build the js files
		for (OptionsFile optionsFile : optionsFiles){
			System.out.println(
					"Building options file for " 
							+ optionsFile.getClassName() 
							+ " class..."
			);
			Writer writer = STANDARD_OUT_MODE? 
					this.buildSystemOutWriter(optionsFile):
					this.buildFileWriter(optionsFile);
			TemplateParser.getInstance().parse(
					Constants.TEMPLATE_FILE_NAME, 
					optionsFile,
					writer
			);
			writer.close();
			System.out.println(
					"Options file for " 
							+ optionsFile.getClassName() 
							+ " class built succesfully in " 
							+ buildJsFileFullPath(optionsFile)
							+ "."
			);
		}
		
		return optionsFiles;
	}

	private List<OptionsFile> buildOptionsFiles(String rootFolder, boolean debugJavaParser) throws IOException {
		
		List<OptionsFile> optionsFiles = new ArrayList<>();
		
		Iterator<Class<?>> i = ClassIndex.getAnnotated(JCRUDEntity.class).iterator();
		while (i.hasNext()){
			Class<?> clazz = i.next();
			
			// Get the JCRUDEntity annotation
			JCRUDEntity jcrudEntity = clazz.getAnnotation(JCRUDEntity.class);
			
			// Get the jsFileFullPath
			if ("".equals(jcrudEntity.jsFilePath())){
				continue;
			}
			String jsFileFullPath = buildJsFileFullPath(jcrudEntity);
			
			// Get the javaFileFullPath
			String javaFileFullPath = CoreUtils.getInstance().getJavaFileFullPath(clazz, rootFolder);
			
			//  If java file is not newer than js file skip it
			if (!CoreUtils.getInstance().isNewerThan(javaFileFullPath, jsFileFullPath)){
				System.out.println(jsFileFullPath + " not built, skipped.");
				continue;
			}
			
			// Parse java file and add OptionsFile to the list
			OptionsFile optionsFile = this.javaParser.parseFile(javaFileFullPath, debugJavaParser);
			
			// Set var name if needed
			if (!"".equals(jcrudEntity.jsFileVarName())){
				optionsFile.setVarName(jcrudEntity.jsFileVarName());
			}
			
			optionsFiles.add(optionsFile);
		}

		return optionsFiles;
	}
	
	private Writer buildSystemOutWriter(OptionsFile optionsFile) {
		return new OutputStreamWriter(System.out);
	}
	
	private Writer buildFileWriter(OptionsFile optionsFile) throws IOException, ClassNotFoundException {

		// Build the full path of the new js file
		String jsFileFullPath = buildJsFileFullPath(optionsFile);
		
		// Make dirs
		this.mkdirsForFile(jsFileFullPath);
		
		// Instance the writer
		return new OutputStreamWriter(
				new FileOutputStream(jsFileFullPath)
		);
	}

	private boolean mkdirsForFile(String fileFullPath) {
		
		File file = new File(fileFullPath);
		return file.getParentFile().mkdirs();
	}

	static private String buildJsFileFullPath(OptionsFile optionsFile) throws ClassNotFoundException, IOException {
		
		Class<?> clazz = optionsFile.getClazz();
		JCRUDEntity jcrudEntity = clazz.getAnnotation(JCRUDEntity.class);
		
		return buildJsFileFullPath(jcrudEntity);
	}

	static private String buildJsFileFullPath(JCRUDEntity jcrudEntity) throws IOException {
		
		return CoreUtils.getInstance().getProjectFullPath() 
				+ File.separator 
				+ jcrudEntity.jsFilePath();
	}
	
	static public OptionsFilesBuilder getInstance(){
		
		if ( instance == null ){
			instance = new OptionsFilesBuilder();
		}
		
		return instance;
	}

}
