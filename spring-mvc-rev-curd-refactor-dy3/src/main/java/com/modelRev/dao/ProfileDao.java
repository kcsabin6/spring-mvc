package com.modelRev.dao;

import java.util.List;

import com.modelRev.dto.ProfileDTO;



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

	ProfileDTO validateUP(String pusername, String ppassword);
	
	
	

}
