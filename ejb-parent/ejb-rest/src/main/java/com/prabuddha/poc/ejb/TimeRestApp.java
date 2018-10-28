package com.prabuddha.poc.ejb;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/time")
public class TimeRestApp extends Application{
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(HelloSessionBean.class);
        return classes;
    }
	
}
