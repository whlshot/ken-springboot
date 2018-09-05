package com.ken.other.el;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

@Configuration
//@PropertySource("classpath:com/ken/test.properties")//需要指定特殊的配置文件时使用，否则使用默认的配置文件。
public class ElConfig {

    @Value("com.ken")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random()*100.0}")
    private double randomNumber;

    @Value("#{elOther.another}")
    private String fromAnother;

    @Value("classpath:testFile.txt")
    private Resource testFile;

    @Value("http://www.baidu.com")
    private Resource testUrl;

    @Value("${spring.redis.host}")
    private String redisHost_;

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public String outputResource() {
        StringBuilder builder = new StringBuilder();
        builder.append("normal: " + normal);
        builder.append("osName: " + osName);
        builder.append("randomNumber: " + randomNumber);
        builder.append("fromAnother:" + fromAnother);
        builder.append("testFile:" + testFile);
        builder.append("testUrl: " + testUrl);
        builder.append("redisHost_: " + redisHost_);
        builder.append("environment.getProperty: " + environment.getProperty("redisHost"));
        return builder.toString();
    }

}
