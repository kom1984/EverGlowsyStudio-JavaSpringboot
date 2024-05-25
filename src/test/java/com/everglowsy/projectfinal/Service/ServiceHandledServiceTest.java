package com.everglowsy.projectfinal.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.everglowsy.projectfinal.model.CategoryModel;
import com.everglowsy.projectfinal.model.ServiceHandledModel;
import com.everglowsy.projectfinal.repository.ServiceHandledRepository;
import com.everglowsy.projectfinal.service.ServiceHandledService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ServiceHandledServiceTest {

    @Mock
    private ServiceHandledRepository serviceHandledRepository;

    @InjectMocks
    private ServiceHandledService serviceHandledService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetAllServices() {
        // Mocking behavior of the repository
        when(serviceHandledRepository.findAll()).thenReturn(Arrays.asList(
                new ServiceHandledModel(1L, "Manucure", "Description 1", "Image 1", "Time 1", 10.0, null, null),
                new ServiceHandledModel(2L, "Pose Résine", "Description 2", "Image 2", "Time 2", 20.0, null, null)
        ));

        // Calling the method under test
        List<ServiceHandledModel> services = serviceHandledService.getAllServices();

        // Verifying the result
        assertEquals(2, services.size());
    }

    @Test
    void testGetServiceById() {
        // Mocking behavior of the repository
        ServiceHandledModel serviceToReturn = new ServiceHandledModel(1L, "Manucure", "Description 1", "Image 1", "Time 1", 10.0, null, null);
        when(serviceHandledRepository.findById(1L)).thenReturn(Optional.of(serviceToReturn));

        // Calling the method under test
        ServiceHandledModel service = serviceHandledService.getServiceById(1L);

        // Verifying the result
        assertNotNull(service);
        assertEquals("Manucure", service.getName_service());
    }

    @Test
    void testSaveService() {
        // Create a service object to save
        ServiceHandledModel serviceToSave = new ServiceHandledModel();
        serviceToSave.setName_service("Manucure");
        serviceToSave.setDescription("Description");
        serviceToSave.setImage("Image");
        serviceToSave.setTime_service("10:30");
        serviceToSave.setPrice(10.0);

        // Mocking behavior of the repository
        when(serviceHandledRepository.save(serviceToSave)).thenReturn(serviceToSave);

        // Calling the method under test
        serviceHandledService.saveService(serviceToSave);

        // Verifying that the save method was called with the correct service
        verify(serviceHandledRepository, times(1)).save(serviceToSave);
    }

    @Test
    void testDeleteService() {
        // Calling the method under test
        serviceHandledService.deleteService(1L);

        // Verifying that the delete method was called with the correct ID
        verify(serviceHandledRepository, times(1)).deleteById(1L);
    }

}
