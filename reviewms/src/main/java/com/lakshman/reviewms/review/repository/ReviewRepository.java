package com.lakshman.reviewms.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lakshman.reviewms.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	List<Review> findByCompanyId(Long companyId);

}
