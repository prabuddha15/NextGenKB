package com.prabuddha.spl.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.prabuddha.spl.service.SPLDataService;
import com.prabuddha.spl.spark.action.SPLSoccerAction;

public class ApplicationClient {
	public static void main(String[] args) {
		//CustomerService service = new CustomerServiceImpl();
		
		@SuppressWarnings("resource")
		ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
		SPLDataService service = context.getBean("splDataService", SPLDataService.class); //Setter Injection
		
		service.getAllLeagues();
		
		SPLSoccerAction action=new SPLSoccerAction();
		action.execute();
}
}
