package com.everglowsy.projectfinal.repository;

import com.everglowsy.projectfinal.model.AppointmentModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {

    @EntityGraph(attributePaths = {"userModel", "serviceHandledModel"})
    List<AppointmentModel> findAll();
}
