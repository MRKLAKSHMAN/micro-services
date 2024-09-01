package com.lakshman.companyms.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lakshman.companyms.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
