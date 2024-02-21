package com.project.ElectronicStore.controller;

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
import com.project.ElectronicStore.Dto.ProductDto;
import com.project.ElectronicStore.Servicelayer.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	
	@PostMapping
	ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto)
	{
		
		
		ProductDto productDtogot=productService.create(productDto);
		
		return new ResponseEntity<ProductDto>(productDtogot,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/{id}")
	ResponseEntity<ProductDto> getSingle(@PathVariable String id)
	{
		ProductDto productDto=productService.getSingle(id);
		return new ResponseEntity(productDto,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	ResponseEntity<ProductDto> update(@PathVariable String id,@RequestBody ProductDto productDto)
	{
		ProductDto productDtoUpdated=productService.update(id, productDto);
		return new ResponseEntity(productDtoUpdated,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	ResponseEntity<ApiResponseMessage> delete(@PathVariable String id)
	{
		productService.delete(id);
		
		ApiResponseMessage message=new ApiResponseMessage();
		message.setMessage("Hence Product got deleted");
		message.setStatus(HttpStatus.OK);
		message.setSuccess(true);
		
		
		
		
		return new ResponseEntity(message,HttpStatus.OK);
		
		
	}
	
	
	
	
	@GetMapping
	ResponseEntity<PageableResponse<ProductDto>> getAll(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="0",required=false) int pageSize,
	@RequestParam(value="sortBy",defaultValue = "0",required=false) String sortBy,
	@RequestParam(value="sortDir",defaultValue = "0",required=false) String sortDir)
	{
		
		PageableResponse<ProductDto> response=productService.getAll(pageNumber, pageSize, sortBy, sortDir);
		
	return new ResponseEntity(response,HttpStatus.OK);
	
	}
	
	
	
	
	@GetMapping("/live")
	ResponseEntity<PageableResponse<ProductDto>> getAllLive(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="0",required=false) int pageSize,
	@RequestParam(value="sortBy",defaultValue = "0",required=false) String sortBy,
	@RequestParam(value="sortDir",defaultValue = "0",required=false) String sortDir)
	{
		
		PageableResponse<ProductDto> response=productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity(response,HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/title/{title}")
	
	ResponseEntity<PageableResponse<ProductDto>> getByTitle(
			@RequestParam(value="pageNumber",defaultValue="0",required=false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="0",required=false) int pageSize,
	@RequestParam(value="sortBy",defaultValue = "0",required=false) String sortBy,
	@RequestParam(value="sortDir",defaultValue = "0",required=false) String sortDir,
	@PathVariable String title)
	{
		
		PageableResponse<ProductDto> response=productService.getByTitle(title,pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity(response,HttpStatus.OK);
		
		
	}
	
	
	
	
	
	
	
	
	

}
