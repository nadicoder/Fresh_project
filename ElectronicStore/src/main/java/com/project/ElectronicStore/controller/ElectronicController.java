package com.project.ElectronicStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ElectronicStore.Dto.ApiResponseMessage;
import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Dto.UserDto;
import com.project.ElectronicStore.Servicelayer.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class ElectronicController {
	
	@Autowired
	private UserService userService;
	//create
	@PostMapping
	public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto)
	{
		
		UserDto userDtor=userService.create(userDto);
		
	return new ResponseEntity(userDtor,HttpStatus.CREATED);
	}
	
	
	
	//update
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable String userId)
	{
		
		UserDto userDtor=userService.update(userId, userDto);
	return new ResponseEntity(userDtor,HttpStatus.OK);
	
	}
	
	//delete
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponseMessage> delete(@PathVariable String userId)
	{
		userService.deleteUser(userId);
		ApiResponseMessage api=new ApiResponseMessage();
		api.setMessage("userdeleted");
		api.setSuccess(true);
		api.setStatus(HttpStatus.OK);
		return new ResponseEntity<>(api,HttpStatus.OK);
		
	}
	
	
	
	//getSingle user
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingle(@PathVariable String userId)
	{
		UserDto userDtor=userService.getSingleUser(userId);
	return new ResponseEntity(userDtor,HttpStatus.OK);
	}
	//getAll user
	
	
	@GetMapping
	public ResponseEntity<PageableResponse<UserDto>> getAll(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="0", required=false) int pageSize,
	        @RequestParam(value="sortBy",defaultValue="0",required=false) String sortBy,
	        @RequestParam(value="sortDir",defaultValue="0",required=false) String sortDir)
			
	{
		PageableResponse<UserDto> userDtos=userService.getAll(pageNumber,pageSize,sortBy,sortDir);
	return new ResponseEntity<>(userDtos,HttpStatus.OK);
	
	}
	
	
	
	
	//get By email
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserDto> getByEmail(@PathVariable String email)
	{
		UserDto userDto=userService.searchByEmail(email);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	
	//get By keyword
	
	@GetMapping("/keyword/{keyword}")
	public ResponseEntity<List<UserDto>> getByKeyword(@PathVariable String keyword)
	{
		
		List<UserDto> userDtos=userService.searchByKeyword(keyword);
		return new ResponseEntity<>(userDtos,HttpStatus.OK);
	}
	
	

}
