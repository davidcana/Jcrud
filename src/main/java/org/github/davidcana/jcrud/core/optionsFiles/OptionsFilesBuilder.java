package org.github.davidcana.jcrud.core.optionsFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.github.davidcana.jcrud.core.Constants;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.optionsFiles.javaParsing.JavaParser;
import org.github.davidcana.jcrud.core.utils.CoreUtils;
import freemarker.template.TemplateException;

public class OptionsFilesBuilder {
	
	static private OptionsFilesBuilder instance;
	static private final boolean STANDARD_OUT_MODE = false;
	static private final String SRC_TEST_JAVA_MODEL = "src/test/java/org/github/davidcana/jcrud/core/model/";
	
	public OptionsFilesBuilder(){}
	
	public static void main(String[] args) throws IOException, TemplateException, ClassNotFoundException {
		
		System.out.println(
				"Running run method of OptionsFilesBuilder class to generate javascript files of model classes..."
		);
		
		OptionsFilesBuilder instance = new OptionsFilesBuilder();
		instance.run(
				Boolean.parseBoolean(args[0])
		);
		
		System.out.println(
				"Javascript files of model classes generated successfully."
		);
	}
	
	public List<OptionsFile> run(boolean debugJavaParser) throws IOException, TemplateException, ClassNotFoundException {
		
		// Build the list of instances of OptionsFile
		JavaParser javaParser = new JavaParser();
		List<OptionsFile> optionsFiles = javaParser.parseFolder(
				CoreUtils.getInstance().getProjectFullPath() 
					+ File.separator 
					+ SRC_TEST_JAVA_MODEL,
				debugJavaParser
		);
		
		// Iterate optionsFiles and build the js files
		for (OptionsFile optionsFile : optionsFiles){
			System.out.println(
					"Building options file for " + optionsFile.getClassName() + " class..."
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
					"Options file for " + optionsFile.getClassName() + " class built succesfully."
			);
		}
		
		return optionsFiles;
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
	
	static public OptionsFilesBuilder getInstance(){
		
		if ( instance == null ){
			instance = new OptionsFilesBuilder();
		}
		
		return instance;
	}

}
