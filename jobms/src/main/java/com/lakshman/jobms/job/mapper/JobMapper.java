package com.lakshman.jobms.job.mapper;

import java.util.List;

import com.lakshman.jobms.job.dto.JobDTO;
import com.lakshman.jobms.job.entity.Job;
import com.lakshman.jobms.job.external.Company;
import com.lakshman.jobms.job.external.Review;

public class JobMapper {
	
	public static JobDTO mapTheJobWithCompanyDto(Job job, Company company,List<Review> reviews) {
		JobDTO jobDTO = new JobDTO();
		jobDTO.setLocation(job.getLocation());
		jobDTO.setDescription(job.getDescription());
		jobDTO.setId(job.getId());
		jobDTO.setCompany(company);
		jobDTO.setMaxSalary(job.getMaxSalary());
		jobDTO.setMinSalary(job.getMinSalary());
		jobDTO.setTitle(job.getTitle());
		jobDTO.setReviews(reviews);
		return jobDTO;
	}

}
