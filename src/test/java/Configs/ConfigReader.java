package Configs;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.log4testng.Logger;


public class ConfigReader {
	private static final Logger logger = Logger.getLogger(ConfigReader.class);
	private final Properties baseURI = new Properties();
	private final Properties endPoints = new Properties();
	
	private ConfigReader()
	{
		logger.debug("read all properties from file");
		try
		{
			FileInputStream configBase = getFileInputstream(System.getProperty("user.dir")+"/properties/baseURI.properties");
			if(configBase!=null)
			{
				baseURI.load(configBase);
			}
			logger.debug("baseURI loaded");
			FileInputStream endBase = getFileInputstream(System.getProperty("user.dir")+"/properties/endpoints.properties");
			if(endBase!=null)
			{
				endPoints.load(endBase);
			}
			logger.debug("Endpoints loaded");
			
		} 
		catch(Exception e)
		{
			logger.error("ConfigReader() exception message: " + e.getMessage());
		}
	}
	
	public static class LazyHolder
	{
		private static final ConfigReader INSTANCE = new ConfigReader();
	}
	public static ConfigReader getInstance()
	{
		return LazyHolder.INSTANCE;
	}
	public String getBaseURI(String key)
	{
		return baseURI.getProperty(key);
	}
	public String getEndpoint(String key)
	{
		return endPoints.getProperty(key);
	}
	public FileInputStream getFileInputstream(String filepath)
	{
		FileInputStream fstream = null;
		try
		{
			fstream = new FileInputStream(filepath);
		} 
		catch(Exception e)
		{
			logger.error("GetFileInputStream() exception message: " + e.getMessage());
		}
		return fstream;
	}
}
