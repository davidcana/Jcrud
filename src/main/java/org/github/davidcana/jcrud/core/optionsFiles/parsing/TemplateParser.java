package org.github.davidcana.jcrud.core.optionsFiles.parsing;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

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
	
	public TemplateParser() {
		this.buildConfiguration();
	}
	
	public void parse(String templatePath, Object dataModel) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		Template template = this.configuration.getTemplate(templatePath);

		template.process(
				dataModel, 
				new OutputStreamWriter(System.out)
		);

	}

	private void buildConfiguration() {
		
		this.configuration = new Configuration(Configuration.VERSION_2_3_28);
		
        // Where do we load the templates from:
		//this.configuration.setClassForTemplateLoading(MainTest.class, "templates");

        // Some other recommended settings:
		//this.configuration.setIncompatibleImprovements(new Version(2, 3, 20));
		this.configuration.setDefaultEncoding("UTF-8");
		this.configuration.setLocale(Locale.US);
		this.configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	
	static public TemplateParser getInstance(){
		
		if ( instance == null ){
			instance = new TemplateParser();
		}
		
		return instance;
	}
}
