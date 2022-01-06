package com.nagarro.registration.service;

import java.util.List;
import com.nagarro.registration.models.Product;
import com.nagarro.registration.models.Review;

public interface ReviewService {
	
	public List<Review> getAllReviews(Product prod);
	
	public Review addReview(Product product, Review review);

}
