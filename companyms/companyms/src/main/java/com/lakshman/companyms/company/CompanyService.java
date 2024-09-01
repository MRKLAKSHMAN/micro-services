package com.lakshman.companyms.company;

import java.util.List;

import com.lakshman.companyms.company.dto.ReviewMessage;
import com.lakshman.companyms.company.entity.Company;

public interface CompanyService {
	List<Company> getAllCompanies();
	Company updateCompany(Long companyId, Company company);
	Company createCompany(Company company);
	boolean deleteCompanyById(Long companyId);
	Company getCompanyById(Long companyId);
	public void updateCompanyRating(ReviewMessage reviewMessage);
}
