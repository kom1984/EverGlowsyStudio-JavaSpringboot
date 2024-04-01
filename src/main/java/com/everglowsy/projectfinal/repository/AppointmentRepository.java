package com.everglowsy.projectfinal.repository;

import com.everglowsy.projectfinal.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentModel,Long> {
}
