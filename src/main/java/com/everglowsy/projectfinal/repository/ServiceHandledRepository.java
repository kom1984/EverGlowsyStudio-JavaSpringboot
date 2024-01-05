package com.everglowsy.projectfinal.repository;

import com.everglowsy.projectfinal.model.CategoryModel;
import com.everglowsy.projectfinal.model.ServiceHandledModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceHandledRepository extends JpaRepository<ServiceHandledModel,Long> {
    //@Query("select DISTINCT category as id_category from service")
    //public List<ServiceHandledModel> getAllIdServiceCategory();
}


