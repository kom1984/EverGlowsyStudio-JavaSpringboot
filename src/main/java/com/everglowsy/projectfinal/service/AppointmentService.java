package com.everglowsy.projectfinal.service;

import com.everglowsy.projectfinal.model.AppointmentModel;
import com.everglowsy.projectfinal.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<AppointmentModel> getAllAppointment()
    {

        return appointmentRepository.findAll();
    }
    public AppointmentModel getAppointmentById(Long id)
    {

        return appointmentRepository.findById(id).get();
    }
    public void saveAppointment(AppointmentModel saveappointment)
    {
        appointmentRepository.save(saveappointment);
    }
    public void deleteAppointment(Long id)
    {
        appointmentRepository.deleteById(id);
    }
}


