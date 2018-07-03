package com.prabuddha.spl.spark.action;

import java.io.Serializable;

public abstract class BaseAction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public final void execute(){
		preprocess();
		process();
		postprocess();
	}

	protected void preprocess() {
		
	}

	protected abstract void process();
	
	protected void postprocess() {
		
	}
	
}
