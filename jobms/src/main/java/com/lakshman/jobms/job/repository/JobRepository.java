package com.lakshman.jobms.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lakshman.jobms.job.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

}
