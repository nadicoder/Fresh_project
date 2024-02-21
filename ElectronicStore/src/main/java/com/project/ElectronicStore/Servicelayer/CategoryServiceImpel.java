package com.project.ElectronicStore.Servicelayer;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ElectronicStore.Dto.CategoryDto;
import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Entities.Category;
import com.project.ElectronicStore.Exceptions.ResourceNotFoundException;
import com.project.ElectronicStore.Helper.Helper;
import com.project.ElectronicStore.Repository.CategoryRepository;


@Service
public class CategoryServiceImpel implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	
	Logger logger=LoggerFactory.getLogger(CategoryServiceImpel.class);
	@Override
	public CategoryDto create(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		String id=UUID.randomUUID().toString();
		categoryDto.setCategoryId(id);
		Category category=mapper.map(categoryDto,Category.class);
		
		logger.info(" "+category);
		Category savedCategory=categoryRepository.save(category);
		logger.info("  "+savedCategory);
		return mapper.map(savedCategory, CategoryDto.class);
	}
	
	@Override
	public CategoryDto update(String id, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category Which you are trying does not exist"));
		category.setCategoryName(categoryDto.getCategoryName());
		category.setDescription(categoryDto.getDescription());
		category.setCoverImage(categoryDto.getCoverImage());
		Category categorySaved=categoryRepository.save(category);
		
		
		return mapper.map(category, CategoryDto.class);
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
		Category category=categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Requested categoy not found"));
		categoryRepository.delete(category);
		
	}
	@Override
	public CategoryDto getSingle(String id) {
		// TODO Auto-generated method stub
		
		Category category=categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category doesnot Exist"));
		
		
		
		return mapper.map(category, CategoryDto.class);
	}
	@Override
	public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		
		
		
		Sort sort=(sortDir.equalsIgnoreCase("asc")?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending()));
		Pageable page=PageRequest.of(pageNumber, pageSize,sort);
		Page<Category> pageResult=categoryRepository.findAll(page);
		
		PageableResponse<CategoryDto> pageableResponse=Helper.getPageableResponse(pageResult, CategoryDto.class);
		return pageableResponse;
	}
	

}
