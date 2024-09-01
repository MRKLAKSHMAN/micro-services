package com.lakshman.jobms.job.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lakshman.jobms.job.external.Company;

@FeignClient(name = "COMPANY-SERVICE",
url = "${company-service.url}")
public interface CompanyClient {
	
	@GetMapping("/companies/{companyId}")
	Company getCompany(@PathVariable Long companyId);
}
