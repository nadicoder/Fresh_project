package com.project.ElectronicStore.Servicelayer;

import com.project.ElectronicStore.Dto.CategoryDto;
import com.project.ElectronicStore.Dto.PageableResponse;

public interface CategoryService {
	
	
	
	
	//create
	
	CategoryDto create(CategoryDto categoryDto);
	//update
	
	CategoryDto update(String id,CategoryDto categoryDto);
	//delete
	
	void delete(String id);
	//get
	
	CategoryDto getSingle(String id);
	
	//getall
	
	
	
	PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);

}
