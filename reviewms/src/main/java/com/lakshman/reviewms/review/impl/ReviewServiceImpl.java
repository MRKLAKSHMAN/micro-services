package com.lakshman.reviewms.review.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakshman.reviewms.review.ReviewService;
import com.lakshman.reviewms.review.entity.Review;
import com.lakshman.reviewms.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepository;
	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepository.findByCompanyId(companyId);
		
		return reviews;
	}
	@Override
	public String addReviews(Long companyId, Review review) {
		if(null != review && null != companyId) {
			review.setCompanyId(companyId);
			Review successMessage = reviewRepository.save(review);
			return null != successMessage ? "success":null;
		}
		return null;
	}
	@Override
	public Review getReviewOfACompnayById(Long reviewId) {
		if(null != reviewId) {
			return reviewRepository.findById(reviewId).orElseGet(null);
		}
		return null;
	}
	@Override
	public Review updateReviewOfACompnayById( Long reviewId, Review updatedReview) {
		if(null != reviewId && null != updatedReview) {
			Optional<Review> existingReview = reviewRepository.findById(reviewId);
			if(existingReview.isPresent()) {
				Review existingReviewObj = existingReview.get();
				existingReviewObj.setCompanyId(updatedReview.getCompanyId());
				existingReviewObj.setDescription(updatedReview.getDescription());
				existingReviewObj.setRating(updatedReview.getRating());
				existingReviewObj.setTitle(updatedReview.getTitle());
				return reviewRepository.save(existingReviewObj);
			}
		}
		return null;
	}
	@Override
	public Boolean deleteReviewOfACompnayById(Long reviewId) {
		if(null != reviewId && reviewRepository.existsById(reviewId)) {
			reviewRepository.deleteById(reviewId);
			return true;
		}
		return false;
	}

}
