package com.lakshman.companyms.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakshman.companyms.company.CompanyService;
import com.lakshman.companyms.company.client.ReviewClient;
import com.lakshman.companyms.company.dto.ReviewMessage;
import com.lakshman.companyms.company.entity.Company;
import com.lakshman.companyms.company.repository.CompanyRepository;

import jakarta.ws.rs.NotFoundException;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ReviewClient reviewClient;
	
	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}
	@Override
	public Company updateCompany(Long companyId, Company company) {
		Optional<Company> companyOptional = companyRepository.findById(companyId);
		if(companyOptional.isPresent()) {
			Company existingCompany = companyOptional.get();
			existingCompany.setDescription(company.getDescription());
			existingCompany.setName(company.getName());
			companyRepository.save(existingCompany);
			return existingCompany;

		}
		return null;
	}
	@Override
	public Company createCompany(Company company) {
		return companyRepository.save(company);
	}
	@Override
	public boolean deleteCompanyById(Long companyId) {
		try {
			if(companyRepository.existsById(companyId)) {
				companyRepository.deleteById(companyId);
				return true;
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	@Override
	public Company getCompanyById(Long companyId) {
		return companyRepository.findById(companyId).orElse(null);
	}
	@Override
	public void updateCompanyRating(ReviewMessage reviewMessage) {
		System.out.println("CompanyServiceImpl.updateCompanyRating() " + reviewMessage.getDescription());
		Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow((() -> new NotFoundException("Company not found " + reviewMessage.getCompanyId())));
		Double averageRatingOfACompany = reviewClient.getAverageRatingForACompany(reviewMessage.getCompanyId());
		company.setRating(averageRatingOfACompany);
		companyRepository.save(company);
	}

}
