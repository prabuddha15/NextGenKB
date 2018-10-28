package com.prabuddha.poc.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.Date;

@Stateless(name = "HelloSessionBean", mappedName = "ejb/HelloSessionBean")
@Local( HelloSessionBeanLocal.class  )
@Remote( HelloSessionBeanRemote.class )
@Path("/timews")
public class HelloSessionBean implements HelloSessionBeanLocal, HelloSessionBeanRemote  {
    
	@GET
	@Produces("text/plain")
	public String getTime() {
        return new Date().toString();
    }
}