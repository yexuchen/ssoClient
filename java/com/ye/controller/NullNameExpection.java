package com.yexc.controller;

public class NullNameExpection extends Exception {
	
	public NullNameExpection (){};
	
	public NullNameExpection(String message){
		super(message);
	}
	
	
	public NullNameExpection(Throwable cause){
		super(cause);
	}
	
	public NullNameExpection(String message,Throwable cause){
		super(message,cause);
	}
	
	

}
