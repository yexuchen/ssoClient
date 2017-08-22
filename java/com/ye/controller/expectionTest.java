package com.yexc.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



//错误处理
@Controller
public class expectionTest {
	
	
	@RequestMapping("/sqlExpection")
	public void sqlexpection() throws SQLException{
		throw new SQLException();
	}
	
	
	
	@RequestMapping("/RuntimeException")
	public void runimeException() throws RuntimeException{
		throw new RuntimeException();
	}
}
