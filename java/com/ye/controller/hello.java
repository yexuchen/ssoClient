package com.yexc.controller;


import java.beans.ConstructorProperties;





import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.yexc.model.goods;
import com.yexc.common.*;





@Controller
public class hello {
	
	
	@RequestMapping("/hello.html")
	public String helloTest(String id){
		return "redirect:/views/hello.jsp";//以重定向的方式发送数据
	}
	
	
	@RequestMapping("/paramTest1")//传递参数测试
	public String paramTest1(ModelMap model){
		model.put("name","paramTest1");
		model.put("name1","叶");
		return "test";
	}
	
	@RequestMapping("/paramTest2")//传递参数测试
	public ModelAndView paramTest2(){
		Map<String,Object> data = new HashMap<String,Object>();  
	    data.put("name","paramTest2");  
		return new ModelAndView("test",data);
	}
	
	
	
	
	
	@RequestMapping("/erroTest")//错误抛出测试
	public void erroTest() throws NullNameExpection{
		throw new NullNameExpection("错误");
	}
	
	
	@RequestMapping("/jsonTest")
	public ModelAndView jsonTest(){
		   goods gd=new goods(110,"可乐",3,4);
		   goods gd1=new goods(111,"蛋糕",3,4);
		   goods gd2=new goods(112,"薯片",3,4);
		   //将对象封装到列表当中     
		   ArrayList<goods> al=new ArrayList<goods>();
		   al.add(gd);
		   al.add(gd1);
		   al.add(gd2);
		   //通过方法将对象转换为json
		   JSONArray json=JSONArray.fromObject(al);
		   Map<String,Object> data = new HashMap<String,Object>();  
		   data.put("name",json.toString());  
		   return new ModelAndView("jsonTest",data);
	}
	
	
	
	//生成验证码
	@RequestMapping("/validateCode")
	@ResponseBody
	public String  validateCode(HttpServletResponse response,HttpServletRequest request){
		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
          
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);  
        //删除以前的
        session.removeAttribute("verifyCode");
        session.setAttribute("verifyCode",verifyCode);
        //生成图片  
        int w = 100, h = 30; //设定高度和长度 
        try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return "";
	}
	
	//检验验证码
	@RequestMapping("/checkCode")
	@ResponseBody
	public String checkCode(HttpServletResponse response,HttpServletRequest request){
		String NowCode=(String) request.getSession().getAttribute("verifyCode");
		return NowCode;
	}
	
	
	
	
	
	
	
	
	
}
