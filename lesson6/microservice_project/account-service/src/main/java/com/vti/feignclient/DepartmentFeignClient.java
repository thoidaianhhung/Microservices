package com.vti.feignclient;

import com.vti.dto.DepartmentDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service",path = "api/v1")
@RibbonClient(name = "department-service")
public interface DepartmentFeignClient {
    @GetMapping(value = "departments/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentByID(@PathVariable("id") int id);
}
