package com.website.controllers;

import com.website.controllers.api.ServiceApi;
import com.website.dtos.ServiceDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class ServiceController implements ServiceApi {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public ResponseEntity<ServiceDto> createService(ServiceDto serviceDto) {
        ServiceDto serviceDtoResult = serviceService.save(serviceDto);

        return new ResponseEntity<>(serviceDtoResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ServiceDto> updateService(Long Id, ServiceDto serviceDto) {
        serviceDto.setId(Id);
        ServiceDto serviceDtoResult = serviceService.save(serviceDto);

        return new ResponseEntity<>(serviceDtoResult, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberTotalOfServices() {
        return serviceService.countNumberOfService();
    }

    @Override
    public ResponseEntity<ServiceDto> getServiceById(Long id) throws ResourceNotFoundException {
        ServiceDto serviceDtoResult = serviceService.findById(id);

        return new ResponseEntity<>(serviceDtoResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        List<ServiceDto> serviceDtoList = serviceService.findAll();

        return new ResponseEntity<>(serviceDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ServiceDto>> getAllServicesOrderDesc() {
        List<ServiceDto> serviceDtoList = serviceService.findAllServicesOrderDesc();

        return new ResponseEntity<>(serviceDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteService(Long id) {
        serviceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
