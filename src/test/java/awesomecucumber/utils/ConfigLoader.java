package awesomecucumber.utils;

import awesomecucumber.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        String env =System.getProperty("env",String.valueOf(EnvType.Stage));
        switch (EnvType.valueOf(env)){
            case Stage:
                properties = PropertyUtils.propertyLoader("src/test/resources/stage_config.properties");
                break;
            case Prod:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            default:
                 throw new IllegalStateException("Illegal state exception"+env);

        }

    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop!= null)
            return prop;
        else
            throw new RuntimeException("property baseUrl is not specifies in stage_config.properties");
    }
}
