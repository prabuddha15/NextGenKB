package com.prabuddha.poc.spring.value_annotation;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:test.properties")
@ContextConfiguration
public class SpringValueAnnotationTest {

    @Value("#{systemProperties.topic}")
    private String topicName;

    @Value("#{environment['latest']}")
    private String latestValue;

    @Value("#{topicBean.name}")
    private String topicNameFromBean;

    @DateTimeFormat(pattern = "MM-d-yy")
    @Value("12-22-09")
    private Date dateUsingDateTimeAnnotation;

    @BeforeClass
    public static void configureSystemProperties() {
        System.setProperty("topic", "Spring");
    }

    @Test
    public void injectSysProperty() throws Exception {
        System.out.println("Value of System property 'topic': " + topicName);
    }

    @Test
    public void injectValueProperty() throws Exception {
        System.out.println("Value of key 'latest': " + latestValue);
    }

    @Test
    public void injectBeanProperty() throws Exception {
        System.out.println("Topic name from TopicBean: " + topicNameFromBean);
    }

    @Configuration
    static class Config {
        @Bean
        public FormattingConversionServiceFactoryBean conversionService() {
            return new FormattingConversionServiceFactoryBean();
        }
    }

    @Configuration("topicBean")
    public static class TopicBean {
        private String name = "Spring";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
