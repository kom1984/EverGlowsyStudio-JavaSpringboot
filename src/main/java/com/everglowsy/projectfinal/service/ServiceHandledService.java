package com.everglowsy.projectfinal.service;

import com.everglowsy.projectfinal.model.ServiceHandledModel;
import com.everglowsy.projectfinal.repository.ServiceHandledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ServiceHandledService {
    @Autowired
    private ServiceHandledRepository serviceHandledRepository;

    public List<ServiceHandledModel> getAllServices()
    {

        return serviceHandledRepository.findAll();
    }
    /*public List<ServiceHandledModel> getServiceCategory()
    {
        return serviceHandledRepository.getAllIdServiceCategory();
    }*/

    public ServiceHandledModel getServiceById(Long id)
    {
        return serviceHandledRepository.findById(id).get();
    }
    public void saveService(ServiceHandledModel saveServices) {
        serviceHandledRepository.save(saveServices);
    }
    public void deleteService(Long id) {
        serviceHandledRepository.deleteById(id);
    }
}

