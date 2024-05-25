package com.everglowsy.projectfinal.Service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.everglowsy.projectfinal.model.AppointmentModel;
import com.everglowsy.projectfinal.repository.AppointmentRepository;
import com.everglowsy.projectfinal.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAppointments() {
        List<AppointmentModel> appointments = appointmentService.getAllAppointment();
        assertNotNull(appointments);
        for (AppointmentModel appointment : appointments) {
            assertNotNull(appointment.getUserModel());
            assertNotNull(appointment.getServiceHandledModel());
        }
    }
    @Test
    void testGetAppointmentById() {
        // Mocking behavior of the repository
        AppointmentModel appointmentToReturn = new AppointmentModel(1L, new Date(), "09:00", "Confirmed", null, null);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentToReturn));

        // Calling the method under test
        AppointmentModel appointment = appointmentService.getAppointmentById(1L);

        // Verifying the result
        assertNotNull(appointment);
        assertEquals("09:00", appointment.getTime_appointment());
    }

    @Test
    void testSaveAppointment() {
        // Create an appointment object to save
        AppointmentModel appointmentToSave = new AppointmentModel();
        appointmentToSave.setDate_appointment(new Date());
        appointmentToSave.setTime_appointment("09:00");
        appointmentToSave.setStatus_rdv("Confirmed");

        // Mocking behavior of the repository
        when(appointmentRepository.save(appointmentToSave)).thenReturn(appointmentToSave);

        // Calling the method under test
        AppointmentModel savedAppointment = appointmentService.saveAppointment(appointmentToSave);

        // Verifying that the save method was called with the correct appointment
        verify(appointmentRepository, times(1)).save(appointmentToSave);
        assertNotNull(savedAppointment);
    }

    @Test
    void testDeleteAppointment() {
        // Calling the method under test
        appointmentService.deleteAppointment(1L);

        // Verifying that the delete method was called with the correct ID
        verify(appointmentRepository, times(1)).deleteById(1L);
    }
}
