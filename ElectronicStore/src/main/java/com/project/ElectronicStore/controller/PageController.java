package com.project.ElectronicStore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.project.ElectronicStore.Dto.CategoryDto;
import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Dto.UserDto;
import com.project.ElectronicStore.Servicelayer.CategoryService;

@RestController
@RequestMapping("/category")
public class PageController {
	
	@Autowired
	private CategoryService categoryService;
	
	private Logger logger=LoggerFactory.getLogger(PageController.class);
	
//create
	
	@PostMapping
	ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto)
	{
		
		logger.info("entering inside");
		CategoryDto categoryDtosaved=categoryService.create(categoryDto);
	
		
		return new ResponseEntity(categoryDtosaved,HttpStatus.CREATED);
	}
	
	
	
	//update
	@PutMapping("/{id}")
	ResponseEntity<CategoryDto> update(@PathVariable String id,@RequestBody CategoryDto categoryDto)
	{
		
		CategoryDto categoryDtosaved=categoryService.update(id, categoryDto);
		
		
		return new ResponseEntity(categoryDtosaved,HttpStatus.OK);
	}
	
	
	
	//delete
	
	
	
	@DeleteMapping("/{id}")
	ResponseEntity<ApiResponseMessage> delete(@PathVariable String id)
	{
		
		logger.info("inside delete");
		ApiResponseMessage message=new ApiResponseMessage();
		
		message.setMessage("Category deleted");
		message.setStatus(HttpStatus.OK);
		message.setSuccess(true);
		categoryService.delete(id);
	return new ResponseEntity(message,HttpStatus.OK);	
	}
	//getSingle
	
	
	
	@GetMapping("/{id}")
	ResponseEntity<CategoryDto> getSingle(@PathVariable String id)
	{
		CategoryDto categoryDto=categoryService.getSingle(id);
		
		return new ResponseEntity(categoryDto,HttpStatus.OK);
	}
	
	
	
	
	//getAll
	@GetMapping
	public ResponseEntity<PageableResponse<UserDto>> getAll(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="0", required=false) int pageSize,
	        @RequestParam(value="sortBy",defaultValue="0",required=false) String sortBy,
	        @RequestParam(value="sortDir",defaultValue="0",required=false) String sortDir)
			
	{
		PageableResponse<CategoryDto> categoryDtos=categoryService.getAll(pageNumber,pageSize,sortBy,sortDir);
	return new ResponseEntity(categoryDtos,HttpStatus.OK);
	
	}
			
	
	
}
