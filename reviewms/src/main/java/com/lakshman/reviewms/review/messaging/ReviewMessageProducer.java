package com.lakshman.reviewms.review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.lakshman.reviewms.review.dto.ReviewMessage;
import com.lakshman.reviewms.review.entity.Review;

@Service
public class ReviewMessageProducer {
	private final RabbitTemplate rabbitTemplate;
	
	public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	

	public void sendMessage(Review review) {
		ReviewMessage reviewMessage = new ReviewMessage();
		reviewMessage.setCompanyId(review.getCompanyId());
		reviewMessage.setDescription(review.getDescription());
		reviewMessage.setId(review.getId());
		reviewMessage.setRating(review.getRating());
		reviewMessage.setTitle(review.getTitle());
		rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
	}
}
