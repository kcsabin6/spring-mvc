package com.rab3tech.dao;

import java.util.List;

import com.rab3tech.controller.dto.ProfileDTO;


public interface ProfileDao {

	void show();

	String createSignup(ProfileDTO profileDTO);

	ProfileDTO findByEmail(String pemail);

	List<ProfileDTO> sortProfiles(String sort);

	List<ProfileDTO> searchProfiles(String search);

	List<ProfileDTO> findAll();

	List<ProfileDTO> filterProfiles(String filterText);

	List<String> findAllQualification();

	ProfileDTO findByUsername(String pusername);

	String updateSignup(ProfileDTO profileDTO);

	void deleteByUsername(String pusername);

	ProfileDTO authUser(String pusername, String ppassword);
	
	
	

}
