package org.github.davidcana.jcrud.core.optionsFiles;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.github.davidcana.jcrud.core.Constants;
import org.github.davidcana.jcrud.core.optionsFiles.parsing.JavaParser;

import freemarker.template.TemplateException;

public class OptionsFileGeneratorBuilder {
	
	static private OptionsFileGeneratorBuilder instance;

	public OptionsFileGeneratorBuilder(){}
	
	public static void main(String[] args) throws IOException, TemplateException {
		
		OptionsFileGeneratorBuilder instance = new OptionsFileGeneratorBuilder();
		instance.run();
	}
	
	public void run() throws IOException, TemplateException {
		
		//
		JavaParser commentExtractor = new JavaParser();
		List<OptionsFile> optionsFiles = commentExtractor.parseJavaFolder(
				"/home/david/jcrud-core/src/test/java/org/github/davidcana/jcrud/core/model/"
		);
		
		//
		for (OptionsFile optionsFile : optionsFiles){
			TemplateParser.getInstance().parse(
					Constants.TEMPLATE_PATH, 
					optionsFile,
					this.buildOutputStreamWriter(optionsFile)
			);
		}
	}
	
	private OutputStreamWriter buildOutputStreamWriter(OptionsFile optionsFile){
		return new OutputStreamWriter(System.out);
	}
	
	static public OptionsFileGeneratorBuilder getInstance(){
		
		if ( instance == null ){
			instance = new OptionsFileGeneratorBuilder();
		}
		
		return instance;
	}

}
