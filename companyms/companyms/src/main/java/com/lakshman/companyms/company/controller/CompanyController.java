package com.lakshman.companyms.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lakshman.companyms.company.CompanyService;
import com.lakshman.companyms.company.entity.Company;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping
	public ResponseEntity<List<Company>> findAll(){
		return new ResponseEntity<>(companyService.getAllCompanies(),HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company){
		Company updatedCompany = companyService.updateCompany(id, company);
		return null != updatedCompany ? new ResponseEntity<>(updatedCompany,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Company> createCompany(@RequestBody Company company){
		Company createdCompany = companyService.createCompany(company);
		return null != createdCompany ? new ResponseEntity<>(createdCompany,HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable Long id){
		boolean isCompanyDeleted = companyService.deleteCompanyById(id);
		return isCompanyDeleted ? new ResponseEntity<>("Deleted company successfully",HttpStatus.OK) : new ResponseEntity<>("Company Not Found",HttpStatus.NOT_FOUND);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Company> findById(@PathVariable Long id){
		Company company = companyService.getCompanyById(id);
		return null != company ? new ResponseEntity<>(company,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
