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
	public static final String DEV_HOME = "/home/david/" + WEB_APP_ID;
	public static final String DEV_WEBINF = DEV_HOME + "/src/main/webapp/WEB-INF";
    public static final String LOG4J_TEST_CONF_FULL_PATH = DEV_WEBINF +"/" + LOG4J_TEST_CONF_FILE;
	
    // Loggers
    public static final Logger ADMIN_LOGGER = Logger.getLogger("adminLogger");
    public static final Logger TEST_LOGGER = ADMIN_LOGGER;
    
    // DB
    public static final String DB_HOST = "localhost";
    public static final int DB_PORT = 5432;
    public static final String DB_NAME = "jcrud";
    public static final String DB_USER = "jcrud_user";
    public static final String DB_PASSWORD = "emkv4df2lp";
    
    /*
    // URLs
	public static final String HOME_URL = "http://localhost:8080/" + WEB_APP_ID + "/";
	public static final String ACTION_PAGE = "action.html";
	public static final String ACTION_URL = HOME_URL + ACTION_PAGE;
	public static final String CLIENT_PAGE = "client.html";
	public static final String CLIENT_URL = HOME_URL + CLIENT_PAGE;
	public static final String ADMIN_PAGE = "admin.html";
	public static final String ADMIN_URL = HOME_URL + ADMIN_PAGE;
	
	*/
}
