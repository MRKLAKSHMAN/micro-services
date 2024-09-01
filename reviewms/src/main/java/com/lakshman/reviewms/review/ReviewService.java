package com.lakshman.reviewms.review;

import java.util.List;

import com.lakshman.reviewms.review.entity.Review;

public interface ReviewService {
	List<Review> getAllReviews(Long companyId);

	String addReviews(Long companyId, Review review);

	Review getReviewOfACompnayById(Long reviewId);

	Review updateReviewOfACompnayById(Long reviewId, Review review);

	Boolean deleteReviewOfACompnayById(Long reviewId);
}
