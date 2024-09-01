package com.lakshman.jobms.job;

import java.util.List;

import com.lakshman.jobms.job.dto.JobDTO;
import com.lakshman.jobms.job.entity.Job;

public interface JobService {

	List<JobDTO> findAll();
	Job createJob(Job job);
	JobDTO findById(Long id);
	boolean deleteJobById(Long id);
	Job updateJob(Long id, Job job);
}
