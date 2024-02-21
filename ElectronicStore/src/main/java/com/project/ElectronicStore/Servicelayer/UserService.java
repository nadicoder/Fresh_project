package com.project.ElectronicStore.Servicelayer;

import java.util.List;

import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Dto.UserDto;

public interface UserService {
	
	//create
	
	UserDto create(UserDto userDto);
	//update
	
	UserDto update(String userId,UserDto userDto);
	//single
	
	UserDto getSingleUser(String userId);
	//all
	
	PageableResponse<UserDto> getAll(int pageNumber, int pageSize,String sortBy,String sortDir);
	//delete
	
	void deleteUser(String userId);
	//byemail
	UserDto searchByEmail(String email);
	
	//searchkeyword
	
	List<UserDto> searchByKeyword(String keyword);
	

}
