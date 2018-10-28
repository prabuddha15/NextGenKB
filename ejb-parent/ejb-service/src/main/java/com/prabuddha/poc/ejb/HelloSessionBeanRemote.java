package com.prabuddha.poc.ejb;

import java.util.Date;
import javax.ejb.Remote;

@Remote
public interface HelloSessionBeanRemote {
	String getTime();
}
