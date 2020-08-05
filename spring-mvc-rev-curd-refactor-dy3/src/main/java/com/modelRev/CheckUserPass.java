package com.modelRev;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.modelRev.dao.ProfileDao;
import com.modelRev.dto.ProfileDTO;

@Controller
public class CheckUserPass {
	
	@Autowired
	private ProfileDao profileDao;
	
	@GetMapping("/fact")
	public String checkUpass(HttpServletRequest req){
		String pusername=req.getParameter("username");
		String ppassword=req.getParameter("password");
		System.out.println(pusername);
		
		ProfileDTO profileDTO=profileDao.validateUP(pusername, ppassword);
		if(profileDTO!=null) {
			req.setAttribute("magic", "Your data is present on the database");
		   
	  }else {  //user is not there
		  req.setAttribute("magic", "Sorry , username and password are not correct");
	  }
		System.out.println("Hey");
		return "display";
	  
		
	}
	
}
