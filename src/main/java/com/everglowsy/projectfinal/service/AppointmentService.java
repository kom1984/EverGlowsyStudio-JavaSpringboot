package com.everglowsy.projectfinal.service;

import com.everglowsy.projectfinal.model.AppointmentModel;
import com.everglowsy.projectfinal.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    private static final Logger loggers = LoggerFactory.getLogger(AppointmentService.class);

    public List<AppointmentModel> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public AppointmentModel getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

    @Transactional
    public AppointmentModel saveAppointment(AppointmentModel appointmentModel) {
        loggers.debug("Saving appointment: {}", appointmentModel);
        return appointmentRepository.save(appointmentModel);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}

 /*List<AppointmentModel> appointments = appointmentRepository.findAll();
        for (AppointmentModel appointment : appointments) {
            loggers.info("Appointment: {}", appointment);
            if (appointment.getUserModel() != null) {
                loggers.info("User: {}", appointment.getUserModel().getFirstName());
            } else {
                loggers.warn("User is null for appointment: {}", appointment.getId_appointment());
            }
            if (appointment.getServiceHandledModel() != null) {
                loggers.info("Service: {}", appointment.getServiceHandledModel().getName_service());
            } else {
                loggers.warn("Service is null for appointment: {}", appointment.getId_appointment());
            }
        }
        return appointments;*/
