package com.rab3tech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.rab3tech.controller.dto.ProfileDTO;
import com.rab3tech.controller.utils.Utils;
import com.rab3tech.dao.ProfileDao;



@Controller
public class CustomerController {

	@Autowired // dependency injection of ProfileDao class thus no need to
				// create ProfileDao object separately
	private ProfileDao profileDao;

	// <form action="signup" method="post">

	@PostMapping("/signup") // data is sent to the database so use @PostMapping
							// annotation
	public String signUp(HttpServletRequest req) {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String qualification = req.getParameter("qualification");
		String mobile = req.getParameter("mobile");
		String gender = req.getParameter("gender");
		String photo = req.getParameter("photo");
		String password = Utils.generateRandomPassword(5);
		String username = email;

		ProfileDTO profileDTO = new ProfileDTO(username, password, name, email, mobile, gender, photo, qualification);
		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		// ProfileDao profileDao = new ProfileDaoImpl();
		profileDao.createSignup(profileDTO);
		req.setAttribute("hmmmm", "Hi , " + name + " , you have done signup successfully!!!!!!!!!!!");
		return "login";

	}

	@PostMapping("/auth") // data is sent to the database so use @PostMapping
							// annotation
	public String authUser(HttpServletRequest req) {
		String pusername = req.getParameter("username");
		String ppassword = req.getParameter("password");

		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		ProfileDTO profileDTO = profileDao.authUser(pusername, ppassword);
		// adding profileDTO object inside request scope with name magic
		if (profileDTO != null) {
			// page->request->session->application
			HttpSession session = req.getSession(true);// makes new session
			session.setAttribute("userData", profileDTO);

			// req.setAttribute("magic",profileDTO);
			return "dashboard";

		} else {// user is not there
			req.setAttribute("hmmmm", "Sorry, username and password are not correct");
			return "login";
		}
	}

	@GetMapping("/deleteProfile") // data is retrieved from the database so use
									// @GetMapping annotation
	public String deleteProfile(HttpServletRequest req) {
		String pusername = req.getParameter("username");
		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		profileDao.deleteByUsername(pusername);
		return "profiles";

	}

	@GetMapping("/editProfile") // data is retrieved from the database so use
								// @GetMapping annotation
	public String editProfile(HttpServletRequest req) {
		String pusername = req.getParameter("username"); // <a
															// href="editProfile?username=${profileDTO.username}">

		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		ProfileDTO profileDTO = profileDao.findByUsername(pusername);
		req.setAttribute("profileDTO", profileDTO);
		return "esignup";

	}

	@GetMapping("/filterProfile") // data is retrieved from the database so use
									// @GetMapping annotation
	public String filterProfile(HttpServletRequest req) {
		String filterText = req.getParameter("filterText");
		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		List<ProfileDTO> profileDTOs = null;
		if (!"Select".equalsIgnoreCase(filterText)) {
			profileDTOs = profileDao.filterProfiles(filterText);
		} else {
			profileDTOs = profileDao.findAll();
		}
		// adding profileDTO object inside request scope with namemagic

		req.setAttribute("listoptions", profileDao.findAllQualification());

		req.setAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@GetMapping("/LoggedUser") // data is retrieved from the database so use
								// @GetMapping annotation
	public String loggedUser(HttpServletRequest req) {
		Set<ProfileDTO> loggedUsers = ProfileDTO.loggedInUser();
		req.setAttribute("profileDTOs", loggedUsers);
		return "loggedUsers";

	}

	@GetMapping("/logout") // data is retrieved from the database so use
							// @GetMapping annotation
	public String logout(HttpServletRequest req) {
		// This code invalidating the session
		HttpSession session = req.getSession(false);// avoid making new session
		if (session != null)
			session.invalidate();

		// req.getSession().invalidate();
		req.setAttribute("hmmmm", "You have logged out successfully!!");
		return "login";
	}

	@GetMapping("/profiles") // data is retrieved from the database so use
								// @GetMapping annotation
	public String profiles(HttpServletRequest req) {
		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		List<ProfileDTO> profileDTOs = profileDao.findAll();
		// adding profileDTO object inside request scope with namemagic
		req.setAttribute("profileDTOs", profileDTOs);
		req.setAttribute("listoptions", profileDao.findAllQualification());
		return "profiles";
	}

	@GetMapping("/searchProfile") // data is retrieved from the database so use
									// @GetMapping annotation
	public String searchProfile(HttpServletRequest req) {
		String search = req.getParameter("search");
		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		List<ProfileDTO> profileDTOs = profileDao.searchProfiles(search);

		req.setAttribute("listoptions", profileDao.findAllQualification());
		req.setAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@GetMapping("/sortProfile") // data is retrieved from the database so use
								// @GetMapping annotation
	public String sortProfile(HttpServletRequest req) {
		String sort = req.getParameter("sort");

		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		List<ProfileDTO> profileDTOs = profileDao.sortProfiles(sort);

		// adding profileDTO object inside request scope with namemagic
		req.setAttribute("listoptions", profileDao.findAllQualification());
		req.setAttribute("profileDTOs", profileDTOs);
		return "profiles";
	}

	@PostMapping("/usignup") // data is retrieved from the database so use
							// @GetMapping annotation
	public String usignup(HttpServletRequest req) {
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String qualification = req.getParameter("qualification");
		String mobile = req.getParameter("mobile");
		String gender = req.getParameter("gender");
		String photo = req.getParameter("photo");

		ProfileDTO profileDTO = new ProfileDTO(username, "", name, email, mobile, gender, photo, qualification);

		// ProfileDao profileDao = SpringContainerService.getProfileDao();
		profileDao.updateSignup(profileDTO);
		return "redirect:/profiles";

	}
}
