package com.lakshman.companyms.company.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.lakshman.companyms.company.CompanyService;
import com.lakshman.companyms.company.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {
	private final CompanyService companyService;
	
	public ReviewMessageConsumer(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@RabbitListener(queues = "companyRatingQueue")
	public void consumeMessage(ReviewMessage reviewMessage) {
		companyService.updateCompanyRating(reviewMessage);
	}

}
