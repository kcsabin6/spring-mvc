package com.rab3tech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//here only this annotation can be used
@Controller 
public class FactController {

	@GetMapping("/auth")
	public String calFact(HttpServletRequest req){
		
		String num=req.getParameter("num");
		//int n=Integer.parseInt(num);
		
		int sum=1;
		for(int i=2; i<=Integer.parseInt(num);i++){
			sum=sum*i;
		}
		
		req.setAttribute("resulta", sum);
		return "fact";
		
	}
	
	
}
