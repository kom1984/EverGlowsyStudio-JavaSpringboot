package com.everglowsy.projectfinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name="Category")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Category")
    private Long id_category;
    @Column(name = "NAME_CATEGORY")
    private String name_category;
}

    
    /*@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<ServiceHandledModel> serviceHandledModel;*/


