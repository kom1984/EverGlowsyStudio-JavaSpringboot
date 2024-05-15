package com.everglowsy.projectfinal.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        // Mock list of services
        List<ServiceHandledModel> services = new ArrayList<>();
        services.add(new ServiceHandledModel(1L, "Manucure", "Description 1", "Image 1", "Time 1", 10.0, null, null));
        services.add(new ServiceHandledModel(2L, "Pose Résine", "Description 2", "Image 2", "Time 2", 15.0, null, null));

        // Mock repository behavior
        when(serviceHandledRepository.findAll()).thenReturn(services);

        // Perform getting all services
        List<ServiceHandledModel> retrievedServices = serviceHandledService.getAllServices();

        // Verify that the correct list of services is returned
        assertNotNull(retrievedServices);
        assertEquals(2, retrievedServices.size());
    }

    @Test
    void testGetServiceById() {
        // Mock service
        ServiceHandledModel service = new ServiceHandledModel(1L, "Manucure", "Description 1", "Image 1", "Time 1", 10.0, null, null);

        // Mock repository behavior
        when(serviceHandledRepository.findById(1L)).thenReturn(Optional.of(service));

        // Perform getting service by id
        ServiceHandledModel retrievedService = serviceHandledService.getServiceById(1L);

        // Verify that the correct service is returned
        assertNotNull(retrievedService);
        assertEquals(service, retrievedService);
    }

    @Test
    void testSaveService() {
        // Mock service
        ServiceHandledModel service = new ServiceHandledModel(1L, "Manucure", "Description 1", "Image 1", "Time 1", 10.0, null, null);

        // Perform saving service
        serviceHandledService.saveService(service);

        // Verify that repository save method was called with the service
        verify(serviceHandledRepository, times(1)).save(service);
    }

    @Test
    void testDeleteService() {
        // Perform deleting service by id
        serviceHandledService.deleteService(1L);

        // Verify that repository deleteById method was called with the correct id
        verify(serviceHandledRepository, times(1)).deleteById(1L);
    }

    // Add more test methods as needed...
}
