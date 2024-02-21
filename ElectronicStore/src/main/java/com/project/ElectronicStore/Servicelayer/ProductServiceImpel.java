package com.project.ElectronicStore.Servicelayer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ElectronicStore.Dto.PageableResponse;
import com.project.ElectronicStore.Dto.ProductDto;
import com.project.ElectronicStore.Entities.Product;
import com.project.ElectronicStore.Exceptions.ResourceNotFoundException;
import com.project.ElectronicStore.Helper.Helper;
import com.project.ElectronicStore.Repository.ProductRepository;



@Service
public class ProductServiceImpel implements ProductService {

	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ProductDto create(ProductDto productDto) {
		// TODO Auto-generated method stub
		
		Product product=mapper.map(productDto, Product.class);
		Product productSaved=productRepository.save(product);
		
		return mapper.map(productSaved, ProductDto.class);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
		Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
		productRepository.delete(product);
	}

	@Override
	public ProductDto update(String id, ProductDto productDto) {
		// TODO Auto-generated method stub
		
		Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Your product not found"));
		product.setTitle(productDto.getTitle());
		product.setDescription(productDto.getDescription());
		product.setAddedDate(productDto.getAddedDate());
		product.setDiscountedPrice(productDto.getDiscountedPrice());
		product.setQuantity(productDto.getQuantity());
		product.setLive(productDto.isLive());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.isStock());
		
		Product productSaved=productRepository.save(product);
		
		return mapper.map(productSaved, ProductDto.class);
	}

	@Override
	public ProductDto getSingle(String id) {
		// TODO Auto-generated method stub
		Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("your product is not found"));
		
		
		
		return mapper.map(product, ProductDto.class);
	}

	@Override
	public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
		PageRequest page=PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> products=productRepository.findAll(page);
		PageableResponse<ProductDto> response=Helper.getPageableResponse(products, ProductDto.class);
		
		return response;
	}

	@Override
	public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort=(sortDir.equalsIgnoreCase("dsc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
		PageRequest page=PageRequest.of(pageNumber, pageSize,sort);
		Page<Product> products=productRepository.findByLiveTrue(page);
		
		PageableResponse<ProductDto> response=Helper.getPageableResponse(products, ProductDto.class);
		
		return response;
	}

	@Override
	public PageableResponse<ProductDto> getByTitle(String title, int pageNumber, int pageSize, String sortBy,
			String sortDir) {
		// TODO Auto-generated method stub
		Sort sort=(sortDir.equalsIgnoreCase("dsc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
		PageRequest page=PageRequest.of(pageNumber, pageSize,sort);
		Page<Product> products=productRepository.findByTitleContaining(title, page);
		PageableResponse<ProductDto> response=Helper.getPageableResponse(products, ProductDto.class);
		
		
		
		
		return response;
	}

}
