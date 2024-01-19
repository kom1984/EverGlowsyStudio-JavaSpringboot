package com.everglowsy.projectfinal.model;

import jakarta.persistence.*;
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
    @Column(name="ID_Category")
    private Long id_category;

    // Le Nom de category ne doit pas être nul ou vide
    // le nom de category doit comporter au moins 2 caractères
    //@NotEmpty
    //@Size(min = 2,
           // message = "le nom de category doit comporter au moins 2 caractères")
    @Column(name="NAME_CATEGORY")
    private String name_category;

    /*@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<ServiceHandledModel> serviceHandledModel;*/


}