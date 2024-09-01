package com.lakshman.jobms.job.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakshman.jobms.job.JobService;
import com.lakshman.jobms.job.client.CompanyClient;
import com.lakshman.jobms.job.client.ReviewClient;
import com.lakshman.jobms.job.dto.JobDTO;
import com.lakshman.jobms.job.entity.Job;
import com.lakshman.jobms.job.external.Company;
import com.lakshman.jobms.job.external.Review;
import com.lakshman.jobms.job.mapper.JobMapper;
import com.lakshman.jobms.job.repository.JobRepository;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private CompanyClient companyClient;
	
	@Autowired
	private ReviewClient reviewClient;
	
	int attempt=0;
	
	@Override
	@RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
//	@Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
//	@CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
	public List<JobDTO> findAll() {
		System.out.println("Attempt " + ++attempt);
		List<Job> jobs = jobRepository.findAll();
		return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<String> companyBreakerFallback(Exception e){
		return Arrays.asList("1","2","3");
	}
	private JobDTO convertToDto(Job job) {
//		Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/job-app/company/" + job.getCompanyId(), Company.class);
		Company company = companyClient.getCompany(job.getCompanyId());
		if(null != company) {
//			ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(), HttpMethod.GET, null, 
//					new ParameterizedTypeReference<List<Review>>() {});
//			List<Review> reviews = reviewResponse.getBody();
			List<Review> reviews = reviewClient.getReviews(job.getCompanyId());
			return JobMapper.mapTheJobWithCompanyDto(job, company, reviews);
		}
		return null;
	}

	@Override
	public Job createJob(Job job) {
		return jobRepository.save(job);
	}

	@Override
	public JobDTO findById(Long id) {
		if(null != id) {
			Job job = jobRepository.findById(id).orElse(null);
			if(null != job) {
				return convertToDto(job);
			}
		}
		return null;

	}

	@Override
	public boolean deleteJobById(Long id) {
		try {
			if(jobRepository.existsById(id)) {
				jobRepository.deleteById(id);
				return true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	@Override
	public Job updateJob(Long id, Job job) {
		Optional<Job> jobOptional = jobRepository.findById(id);
		if(jobOptional.isPresent()) {
			Job existingJob = jobOptional.get();
			existingJob.setDescription(job.getDescription());
			existingJob.setLocation(job.getLocation());
			existingJob.setMinSalary(job.getMinSalary());
			existingJob.setMaxSalary(job.getMaxSalary());
			existingJob.setTitle(job.getTitle());
			jobRepository.save(existingJob);
			return existingJob;

		}
		return null;
	}

}
