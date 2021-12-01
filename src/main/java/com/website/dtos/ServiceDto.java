package com.website.dtos;


import com.website.models.Service;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Date createdDate;

    public static ServiceDto fromEntityToDto(Service service) {
        if (service == null) {
            return null;
        }
        return ServiceDto.builder()
                .id(service.getId())
                .code(service.getCode())
                .name(service.getName())
                .description(service.getDescription())
                .createdDate(service.getCreatedDate())
                .build();
    }

    public static Service fromDtoToEntity(ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }

        Service service = new Service();
        service.setId(serviceDto.getId());
        service.setCode(serviceDto.getCode());
        service.setName(serviceDto.getName());
        service.setDescription(serviceDto.getDescription());
        service.setCreatedDate(serviceDto.getCreatedDate());

        return service;
    }
}
