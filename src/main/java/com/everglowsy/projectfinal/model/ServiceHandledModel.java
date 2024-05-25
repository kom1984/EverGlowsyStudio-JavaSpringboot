package com.everglowsy.projectfinal.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
    @NotBlank(message = "Le nom de service est obligatoire")
    @NotEmpty
    @Size(min = 2,
     message = "le nom de service doit comporter au moins 2 caractères")
    @Column(name="NAME_SERVICE")
    private String name_service;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="IMAGE")
    private String image;

    @NotBlank(message = "Le temp de service est obligatoire")
    @NotEmpty
    @Column(name="TIME_SERVICE")
    private String time_service;

    @Column(name="PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName="id_category",insertable = true, updatable = true )
    private CategoryModel category;

    @OneToMany(mappedBy = "serviceHandledModel", fetch = FetchType.LAZY)
    private Set<AppointmentModel> appointmentModelList;

    public Long getId_service() {
        return id_service;
    }

    public void setId_service(Long id_service) {
        this.id_service = id_service;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime_service() {
        return time_service;
    }

    public void setTime_service(String time_service) {
        this.time_service = time_service;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public Set<AppointmentModel> getAppointmentModelList() {
        return appointmentModelList;
    }

    public void setAppointmentModelList(Set<AppointmentModel> appointmentModelList) {
        this.appointmentModelList = appointmentModelList;
    }


}
