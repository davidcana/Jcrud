package org.github.davidcana.jcrud.core.optionsFiles.parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.github.davidcana.jcrud.core.optionsFiles.OptionsFile;

public class JavaParser {
	
	private ASTParser parser = buildParser();
	
	public static void main(String[] args) throws IOException {
		
		JavaParser commentExtractor = new JavaParser();
		commentExtractor.parseJavaFolder("/home/david/jcrud-core/src/test/java/org/github/davidcana/jcrud/core/model/");
	}
	
	/*
	public void run() throws IOException {
		
		String str = "/home/david/jcrud-core/src/test/java/org/github/davidcana/jcrud/core/model/Simple.java";
		String converted = readFileToString(str);
		
		OptionsFile optionsFile = this.parseJavaFile(converted);
		System.out.print(
				"OptionsFile:\n" + optionsFile
		);
	}
	*/
	public List<OptionsFile> parseJavaFolder(final String folderPath) throws IOException {
		
		List<OptionsFile> result = new ArrayList<>();
		
	    File path = new File(folderPath);
	    File[] files = path.listFiles();
	    for (int i = 0; i < files.length; i++){
	        File file = files[i];
	        
			if (file.isFile()){ 
	        	String fileAbsolutePath = file.getAbsolutePath();
				String converted = readFileToString(fileAbsolutePath);
				OptionsFile optionsFile = this.parseJavaFile(converted);
				System.out.print(
						"OptionsFile:\n" + optionsFile
				);
	        	result.add(optionsFile);
	        	
	        } else if (file.isDirectory()) {
	        	result.addAll(
	        			this.parseJavaFolder(file.getAbsolutePath())
	        	);
	        }
	    }
		
		return result;
	}
	
	private OptionsFile parseJavaFile(final String str) {
		
		this.parser.setSource(str.toCharArray());
		final CompilationUnit cu = (CompilationUnit) this.parser.createAST(null);

		CommentVisitor visitor = new CommentVisitor(cu, str);
		cu.accept(visitor);
		
		for (Comment comment : (List<Comment>) cu.getCommentList()) {
			comment.accept(visitor);
		}
		
		return visitor.getOptionsFile();
	}

	private static ASTParser buildParser() {
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);		
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		return parser;
	}
	
	private static String readFileToString(String filePath) throws IOException {
		
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
		return fileData.toString();
	}
}
