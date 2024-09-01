package com.lakshman.reviewms.review.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lakshman.reviewms.review.ReviewService;
import com.lakshman.reviewms.review.entity.Review;
import com.lakshman.reviewms.review.messaging.ReviewMessageProducer;

@RestController
@RequestMapping("/job-app/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewMessageProducer reviewMessageProducer;

	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
		return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> addReviews(@RequestParam Long companyId, @RequestBody Review review){
		String successMessage = reviewService.addReviews(companyId,review);
		if(null != successMessage) {
			reviewMessageProducer.sendMessage(review);
			return new ResponseEntity<String>(successMessage, HttpStatus.CREATED);
		}
		else
			return new ResponseEntity<String>("Could not save the Review", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<Review> getReviewOfACompnayById(@PathVariable Long reviewId){
		Review review = reviewService.getReviewOfACompnayById(reviewId);
		return null != review ? new ResponseEntity<>(review, HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<Review> updateReviewOfACompnayById(@PathVariable Long reviewId, @RequestBody Review review){
		Review updatedReview = reviewService.updateReviewOfACompnayById(reviewId, review);
		return null != review ? new ResponseEntity<>(updatedReview, HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReviewOfACompnayById(@PathVariable Long reviewId){
		Boolean isReviewDeleted = reviewService.deleteReviewOfACompnayById(reviewId);
		return isReviewDeleted ? new ResponseEntity<>("Review deleted successfully", HttpStatus.OK):new ResponseEntity<>("Review not deleted",HttpStatus.NOT_FOUND);
	}

	@GetMapping("/averageRating")
	public Double getAverageReviewRating(@RequestParam Long companyId) {
		List<Review> reviewList = reviewService.getAllReviews(companyId);
		return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
	}
}
