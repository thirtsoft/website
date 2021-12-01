package com.website.services;

import com.website.dtos.ServiceDto;

import java.math.BigDecimal;
import java.util.List;

public interface ServiceService {

    ServiceDto save(ServiceDto serviceDto);

    ServiceDto update(Long id, ServiceDto serviceDto);

    BigDecimal countNumberOfService();

    ServiceDto findById(Long id);

    List<ServiceDto> findAll();

    List<ServiceDto> findTop12ByOrderByCreateDateDesc();

    List<ServiceDto> findAllServicesOrderDesc();

    void delete(Long id);

}
