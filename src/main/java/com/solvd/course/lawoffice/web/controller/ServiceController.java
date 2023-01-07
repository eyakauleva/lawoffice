package com.solvd.course.lawoffice.web.controller;

import com.solvd.course.lawoffice.domain.Service;
import com.solvd.course.lawoffice.service.ServiceService;
import com.solvd.course.lawoffice.web.dto.ServiceDto;
import com.solvd.course.lawoffice.web.mapper.ServiceMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    private final ServiceMapper serviceMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> findById(@PathVariable("id") Long id) {
        Service service = serviceService.findById(id);
        ServiceDto serviceDto = serviceMapper.domainToDto(service);
        return new ResponseEntity<>(serviceDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ServiceDto>> findAll() {
        List<Service> services = serviceService.findAll();
        List<ServiceDto> serviceDtos = services.stream()
                .map(serviceMapper::domainToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviceDtos, HttpStatus.OK);
    }
}
