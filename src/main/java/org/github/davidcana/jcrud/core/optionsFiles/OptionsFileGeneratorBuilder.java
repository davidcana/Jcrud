package org.github.davidcana.jcrud.core.optionsFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.github.davidcana.jcrud.core.Constants;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.optionsFiles.parsing.JavaParser;
import org.github.davidcana.jcrud.core.utils.CoreUtils;
import freemarker.template.TemplateException;

public class OptionsFileGeneratorBuilder {
	
	static private OptionsFileGeneratorBuilder instance;
	static private final boolean STANDARD_OUT_MODE = false;
	static private final String SRC_TEST_JAVA_MODEL = "src/test/java/org/github/davidcana/jcrud/core/model/";
	
	public OptionsFileGeneratorBuilder(){}
	
	public static void main(String[] args) throws IOException, TemplateException, ClassNotFoundException {
		
		//OptionsFileGeneratorBuilder instance = new OptionsFileGeneratorBuilder();
		//instance.run();
	}
	
	public void run() throws IOException, TemplateException, ClassNotFoundException {
		
		// Build the list of instances of OptionsFile
		JavaParser commentExtractor = new JavaParser();
		List<OptionsFile> optionsFiles = commentExtractor.parseJavaFolder(
				CoreUtils.getInstance().getProjectFullPath() 
				+ File.separator 
				+ SRC_TEST_JAVA_MODEL
		);
		
		// Iterate optionsFiles and build the js files
		for (OptionsFile optionsFile : optionsFiles){
			Writer writer = STANDARD_OUT_MODE? 
					this.buildSystemOutWriter(optionsFile):
					this.buildFileWriter(optionsFile);
			TemplateParser.getInstance().parse(
					Constants.TEMPLATE_FILE_NAME, 
					optionsFile,
					writer
			);
			writer.close();
		}
	}
	

	private Writer buildSystemOutWriter(OptionsFile optionsFile) {
		return new OutputStreamWriter(System.out);
	}
	
	private Writer buildFileWriter(OptionsFile optionsFile) throws IOException, ClassNotFoundException {

		// Build the full path of the new js file
		Class<?> clazz = optionsFile.getClazz();
		JCRUDEntity jcrudEntity = clazz.getAnnotation(JCRUDEntity.class);
		String jsFileFullPath = CoreUtils.getInstance().getProjectFullPath() 
				+ File.separator 
				+ jcrudEntity.jsFilePath();
		
		// Instance the writer
		return new OutputStreamWriter(
				new FileOutputStream(jsFileFullPath)
		);
	}
	
	static public OptionsFileGeneratorBuilder getInstance(){
		
		if ( instance == null ){
			instance = new OptionsFileGeneratorBuilder();
		}
		
		return instance;
	}

}
