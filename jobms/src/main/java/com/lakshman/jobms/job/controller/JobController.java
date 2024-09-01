package com.lakshman.jobms.job.controller;

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

import com.lakshman.jobms.job.JobService;
import com.lakshman.jobms.job.dto.JobDTO;
import com.lakshman.jobms.job.entity.Job;

@RestController
@RequestMapping("/job-app/jobs")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping("/{id}")
	public ResponseEntity<JobDTO> findById(@PathVariable Long id){
		JobDTO jobDTO = jobService.findById(id);
		if(null != jobDTO)
			return new ResponseEntity<>(jobDTO,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<JobDTO>> findAll(){
		List<JobDTO> jobs = jobService.findAll();
		return jobs.size() > 0 ? new ResponseEntity<>(jobs,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<Job> createJob(@RequestBody Job job){
		return new ResponseEntity<>(jobService.createJob(job), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteJob(@PathVariable Long id){
		return jobService.deleteJobById(id) ? new ResponseEntity<>("Job deleted successfully", HttpStatus.OK) : new ResponseEntity<>("Job not found successfully", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job){
		Job updateJobSuccess = jobService.updateJob(id, job);
		return null != updateJobSuccess ? new ResponseEntity<>(updateJobSuccess, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
