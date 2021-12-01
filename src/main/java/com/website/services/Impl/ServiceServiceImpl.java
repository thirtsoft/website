package com.website.services.Impl;

import com.website.dtos.ServiceDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.repository.ServiceRepository;
import com.website.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    @Override
    public ServiceDto save(ServiceDto serviceDto) {
        ServiceDto serviceDtoResult = serviceDto;
        serviceDtoResult.setCreatedDate(new Date());

        return ServiceDto.fromEntityToDto(
                serviceRepository.save(
                        ServiceDto.fromDtoToEntity(serviceDtoResult)
                )
        );
    }

    @Override
    public ServiceDto update(Long id, ServiceDto serviceDto) {
        if (!serviceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Service not found");
        }

        Optional<com.website.models.Service> service = serviceRepository.findById(id);

        if (!service.isPresent()) {
            throw new ResourceNotFoundException("Service not found");
        }

        ServiceDto serviceDtoResult = ServiceDto.fromEntityToDto(service.get());
        serviceDtoResult.setCode(serviceDto.getCode());
        serviceDtoResult.setName(serviceDto.getName());
        serviceDtoResult.setDescription(serviceDto.getDescription());
        serviceDtoResult.setCreatedDate(new Date());

        return ServiceDto.fromEntityToDto(
                serviceRepository.save(
                        ServiceDto.fromDtoToEntity(serviceDtoResult)
                )
        );
    }

    @Override
    public BigDecimal countNumberOfService() {
        return serviceRepository.countNumberOfService();
    }

    @Override
    public ServiceDto findById(Long id) {
        if (id == null) {
            return null;
        }

        Optional<com.website.models.Service> service = serviceRepository.findById(id);

        return Optional.of(ServiceDto.fromEntityToDto(service.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<ServiceDto> findAll() {
        return serviceRepository.findAll().stream()
                .map(ServiceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> findTop12ByOrderByCreateDateDesc() {
        return serviceRepository.findTop12ByOrderByCreatedDateDesc().stream()
                .map(ServiceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceDto> findAllServicesOrderDesc() {
        return serviceRepository.findByOrderByIdDesc().stream()
                .map(ServiceDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        serviceRepository.deleteById(id);
    }
}
