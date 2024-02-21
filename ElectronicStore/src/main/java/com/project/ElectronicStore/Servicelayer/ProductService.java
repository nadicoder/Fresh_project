package com.project.ElectronicStore.Servicelayer;

import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Dto.ProductDto;

public interface ProductService {
	
	
	//create
	
	ProductDto create(ProductDto productDto);
	
	
	//delete
	
	void delete(String id);
	
	//update
	
	ProductDto update(String id,ProductDto productDto);
	//get
	
	ProductDto getSingle(String id);
	//getall
	
	PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
	//live
	
	PageableResponse<ProductDto> getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	//getby title;
	
	PageableResponse<ProductDto> getByTitle(String title,int pageNumber,int pageSize,String sortBy,String sortDir);
	

}
