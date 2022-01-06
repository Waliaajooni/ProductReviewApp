package com.nagarro.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.registration.dao.ProductDao;
import com.nagarro.registration.dao.ReviewDao;
import com.nagarro.registration.models.Product;
import com.nagarro.registration.models.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ReviewDao reviewDao;

	@Override
	public List<Review> getAllReviews(Product prod) {
		List<Review> revs = prod.getReviews();
		return revs;
	}

	@Override
	public Review addReview(Product product, Review review) {
		review.setProduct(product);
		reviewDao.save(review);
		return review;
	}
}
