package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Service;
import com.solvd.course.lawoffice.service.ServiceService;
import com.solvd.course.lawoffice.web.dto.ServiceDto;
import com.solvd.course.lawoffice.web.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/services")
public class ServiceController {
    //TODO Просмотреть предоставляемые услуги
    //TODO Получить информацию по определенной услуге
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceController(ServiceService serviceService, ServiceMapper serviceMapper) {
        this.serviceService = serviceService;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        List<Service> services = serviceService.getAllServices();
        List<ServiceDto> serviceDtos = services.stream().map(serviceMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviceDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable("id") Long id) {
        Service service = serviceService.getServiceById(id);
        ServiceDto serviceDto = serviceMapper.domainToDto(service);
        return new ResponseEntity<>(serviceDto, HttpStatus.OK);
    }
}
