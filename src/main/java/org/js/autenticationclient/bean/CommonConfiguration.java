package org.js.autenticationclient.bean;


import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.Logger;
import org.js.autenticationclient.core.impl.CommonAuthenticationCoreImpl;

import javax.swing.*;
import java.util.Map;
import java.util.Properties;

public class CommonConfiguration {

    final static Logger logger = Logger.getLogger(CommonConfiguration.class);

    private static CommonConfiguration instance;

    private Configuration config;

    private CommonConfiguration()  {
        try {
            logger.info("Initilze common Configuration");
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName("config.properties"));
            config = builder.getConfiguration();
        } catch ( ConfigurationException e) {
            logger.error("UNABLE TO LOAD CONFIGURATION");
            logger.error(e.getMessage(),e);
        } catch ( Exception e) {
            logger.error("UNABLE TO LOAD CONFIGURATION");
            logger.error(e.getMessage(),e);
        }
    }

    public static CommonConfiguration getInstance()  {
        if(instance == null){
            synchronized (CommonConfiguration.class) {
                if(instance == null){
                    instance = new CommonConfiguration();
                }
            }
        }
        return instance;
    }

    public static String getProperties(String key)   {
        if(instance == null){
            getInstance();
        }
        return instance.config.getString(key);
    }

    public static void addNewProperties(String key,String value)  {
        try {
            logger.info("Update common Configuration");
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName("config.properties"));
            Configuration config = builder.getConfiguration();
            config.setProperty(key,value);
            builder.save();

            getInstance();
        } catch ( ConfigurationException e) {
            logger.error("UNABLE TO SAVE CONFIGURATION");
            logger.error(e.getMessage(),e);
        } catch ( Exception e) {
            logger.error("UNABLE TO SAVE CONFIGURATION");
            logger.error(e.getMessage(),e);
        }

    }
}
