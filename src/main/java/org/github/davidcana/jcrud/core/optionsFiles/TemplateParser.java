package org.github.davidcana.jcrud.core.optionsFiles;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import freemarker.core.JavaScriptOutputFormat;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class TemplateParser {
	
	static private TemplateParser instance;
	private Configuration configuration;
	
	public TemplateParser() throws IOException {
		this.buildConfiguration();
	}
	
	public void parse(String templatePath, Object dataModel, OutputStreamWriter outputStreamWriter) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		Template template = this.configuration.getTemplate(templatePath);
		template.process(dataModel, outputStreamWriter);

	}

	private void buildConfiguration() throws IOException {
		
		this.configuration = new Configuration(Configuration.VERSION_2_3_28);
		
        // Where do we load the templates from:
		this.configuration.setDirectoryForTemplateLoading(
				new File(
						this.getClass().getResource("/").getFile()
				)
		);

		// Don't escape anything
		this.configuration.setOutputFormat(JavaScriptOutputFormat.INSTANCE);
		
        // Some other recommended settings:
		this.configuration.setDefaultEncoding("UTF-8");
		//this.configuration.setLocale(Locale.ES);
		this.configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	
	static public TemplateParser getInstance() throws IOException{
		
		if ( instance == null ){
			instance = new TemplateParser();
		}
		
		return instance;
	}
}
