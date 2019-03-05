package org.github.davidcana.jcrud.core.optionsFiles.javaParsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.github.davidcana.jcrud.core.optionsFiles.OptionsFile;
import org.github.davidcana.jcrud.core.utils.CoreUtils;

public class JavaParser {
	
	private ASTParser parser = buildASTParser();
	
	public JavaParser(){}
	
	public List<OptionsFile> parseFolder(final String folderPath, final boolean print) throws IOException {
		
		List<OptionsFile> result = new ArrayList<>();
		
	    File path = new File(folderPath);
	    File[] files = path.listFiles();
	    for (int i = 0; i < files.length; i++){
	        File file = files[i];
	        
			if (file.isFile()){ 
	        	String fileAbsolutePath = file.getAbsolutePath();
				String converted = CoreUtils.getInstance().getStringFromFullPath(fileAbsolutePath);
				OptionsFile optionsFile = this.parseFile(converted, print);
				
				if (print){
					System.out.print(
							"OptionsFile:\n" + optionsFile
					);
				}
	        	result.add(optionsFile);
	        	
	        } else if (file.isDirectory()) {
	        	result.addAll(
	        			this.parseFolder(
	        					file.getAbsolutePath(), print
	        			)
	        	);
	        }
	    }
		
		return result;
	}
	
	private OptionsFile parseFile(final String str, final boolean print) {
		
		this.parser.setSource(str.toCharArray());
		final CompilationUnit cu = (CompilationUnit) this.parser.createAST(null);

		CommentVisitor visitor = new CommentVisitor(cu, str, print);
		cu.accept(visitor);
		
		for (Comment comment : (List<Comment>) cu.getCommentList()) {
			comment.accept(visitor);
		}
		
		return visitor.getOptionsFile();
	}

	private static ASTParser buildASTParser() {
		
		ASTParser parser = ASTParser.newParser(AST.JLS3);		
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		return parser;
	}
}
