package com.prabuddha.poc.ejb;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface HelloSessionBeanLocal {
	String getTime();
}
