package org.github.davidcana.jcrud.core;

import org.apache.log4j.Logger;

public class Constants {
	
	// Path and file names
	public static final String TOMCAT_HOME = "/usr/local/tomcat";
	public static final String WEB_APPS_DIR = "webapps";
	public static final String WEB_APP_ID = "jcrud";
    public static final String WEBINF_DIR = "WEB-INF";
    public static final String CONF_DIR = WEBINF_DIR;
    public static final String LOG4J_CONF_DIR = CONF_DIR;
    public static final String LOG4J_CONF_FILE = "log4j.properties";
    public static final String LOG4J_CONF_PATH = LOG4J_CONF_DIR + "/" + LOG4J_CONF_FILE;
    public static final String LOG4J_CONF_FULL_PATH = TOMCAT_HOME+ "/" + WEB_APPS_DIR + "/" + WEB_APP_ID +"/" + LOG4J_CONF_PATH;
    public static final String LOG4J_TEST_CONF_FILE = "log4j-test.properties";
    //public static final String LOG4J_TEST_CONF_PATH = LOG4J_CONF_DIR + "/" + LOG4J_TEST_CONF_FILE;
	//public static final String DEV_HOME = "/home/david/" + WEB_APP_ID;
	//public static final String DEV_WEBINF = DEV_HOME + "/src/main/webapp/WEB-INF";
    //public static final String LOG4J_TEST_CONF_FULL_PATH = DEV_WEBINF +"/" + LOG4J_TEST_CONF_FILE;
	
    // Loggers
    public static final Logger ADMIN_LOGGER = Logger.getLogger("adminLogger");
    public static final Logger TEST_LOGGER = ADMIN_LOGGER;
    
    // Java files
    public static final String MAIN_JAVA_PATH = "/src/main/";
    public static final String TEMPLATE_FOLDER = MAIN_JAVA_PATH + "/resources/";
    
	// URL parameters keys
	public static final String COMMAND_URL_PARAMETER = "cmd";
	public static final String TABLE_URL_PARAMETER = "table";
	public static final String KEY_URL_PARAMETER = "key";
	public static final String FILE_FIELD_URL_PARAMETER = "field";
	
    // URL parameters values: commands
	public static final String COMMAND_LIST_URL_PARAMETER = "LIST";
	public static final String COMMAND_GET_URL_PARAMETER = "GET";
	public static final String COMMAND_UPDATE_URL_PARAMETER = "BATCH_UPDATE";
    
    // Special comments for building of javascript files
	public static final String COMMENT_START = "/*jcrud";
	public static final String CLASS_COMMENT_START = COMMENT_START + "-class";
	public static final String FIELD_COMMENT_START = COMMENT_START + "-field";
	
    // Misc
    public static final String ZCRUD_RECORDS_SUFFIX = "ZCrudRecords";
    public static final String TEMPLATE_FILE_NAME = "options.js.ftl";
    
}
