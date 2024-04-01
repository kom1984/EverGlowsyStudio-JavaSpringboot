package com.everglowsy.projectfinal.repository;

import com.everglowsy.projectfinal.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<CategoryModel,Long> {

}
