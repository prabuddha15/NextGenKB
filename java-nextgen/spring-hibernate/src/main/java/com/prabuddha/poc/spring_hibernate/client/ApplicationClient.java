package com.prabuddha.poc.spring_hibernate.client;

import com.prabuddha.poc.spring_hibernate.config.AppConfig;
import com.prabuddha.poc.spring_hibernate.model.VendorDefectId;
import com.prabuddha.poc.spring_hibernate.model.VendorDefectMapper;
import com.prabuddha.poc.spring_hibernate.service.DataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;


public class ApplicationClient {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
                ApplicationContext appContext=new AnnotationConfigApplicationContext(AppConfig.class);
        DataService service = appContext.getBean("dataService",DataService.class); // Setter Injection

        VendorDefectMapper data=new VendorDefectMapper(new VendorDefectId("FIRESTONE","22221"),"ACTIVE","Prabuddha",new Timestamp(System.currentTimeMillis()));

        //service.addData(data);
        service.deleteData(data.getVendorDefectId().getVendorLookupId(),data.getVendorDefectId().getDefectCode());
    }
}