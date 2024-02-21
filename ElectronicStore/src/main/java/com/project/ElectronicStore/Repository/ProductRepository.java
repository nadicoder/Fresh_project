package com.project.ElectronicStore.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ElectronicStore.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	
	
	Page<Product> findByTitleContaining(String title,Pageable page);
	Page<Product> findByLiveTrue(Pageable page);

}
