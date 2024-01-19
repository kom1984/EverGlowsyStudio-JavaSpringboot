package com.everglowsy.projectfinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Entity
@Table(name="Service")
@NoArgsConstructor
@AllArgsConstructor

public class ServiceHandledModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_SERVICE")
    private Long id_service;

    // Le Nom de service ne doit pas être nul ou vide
    // le nom de service doit comporter au moins 2 caractères
    //@NotEmpty
    //@Size(min = 2,
    // message = "le nom de service doit comporter au moins 2 caractères")
    @Column(name="NAME_SERVICE")
    private String name_service;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="IMAGE")
    private String image;
    @Column(name="TIME_SERVICE")
    private Time time_service;
    @Column(name="PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName="id_category",insertable = true, updatable = true )
    private CategoryModel category;

}
