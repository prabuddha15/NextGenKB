package com.prabuddha.poc.ejb;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
 
@ApplicationPath("/hello")
public class HelloRestApp extends Application {
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(HelloJAXRSWebService.class);
        return classes;
    }
	
}
