/**
 * 
 */
package com.test.testsys.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properties文件属性操作类
 * @author Julia
 *
 */
public class PropertiesUtil{
	
	private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

	private static InputStream fileInput;
	
	private static Properties properties;
	
	static {
		loadPropertyFile();
	}		
	
	private static void loadPropertyFile() {		
		try {
			fileInput = PropertiesUtil.class.getResourceAsStream("/config.properties");
			properties = new Properties();
			properties.load(fileInput);			
		} catch (IOException e) {
			log.error("properties file loader failed",e);
		} finally {
			try {
				if(fileInput != null) {
					fileInput.close();
				}
			} catch (IOException e1) {
				log.error("inputstream close failed", e1);
			}			
		}
	}
	
	public static String getProperty(String propertyName) {
		log.info("input param propertyName : " + propertyName);
		if(propertyName == null || "".equals(propertyName)) {
			return null;
		}
		if(properties == null) {
			loadPropertyFile();
		}
		return properties.getProperty(propertyName);
	}
}
