package com.vti.controller;

import java.util.List;

import com.vti.dto.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.feignclient.DepartmentFeignClient;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountFilterForm;
import com.vti.service.IAccountService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "api/v1/accounts")
public class AccountController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAccountService service;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DepartmentFeignClient departmentFeignClient;

	@GetMapping()
	public Page<AccountDTO> getAllAccounts(
			Pageable pageable, 
			@RequestParam(value = "search", required = false) String search,
			AccountFilterForm filterForm) {

		Page<Account> entityPages = service.getAllAccounts(pageable, search, filterForm);

		// convert entities --> dtos
		List<AccountDTO> dtos = modelMapper.map(
				entityPages.getContent(), 
				new TypeToken<List<AccountDTO>>() {}.getType());

		Page<AccountDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

		return dtoPages;
	}


	@GetMapping(value = "/department/{id}")
	public DepartmentDTO getDepartmentByID(@PathVariable(name = "id") int id) {


//		DepartmentDTO dto =  restTemplate.getForObject("http://localhost:8082/api/v1/departments/" + id, DepartmentDTO.class);
		DepartmentDTO dto = departmentFeignClient.getDepartmentByID(id).getBody();
		return dto;
	}

}
