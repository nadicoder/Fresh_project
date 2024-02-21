package com.project.ElectronicStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ElectronicStore.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
