package com.nagarro.registration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagarro.registration.models.Review;

public interface ReviewDao extends JpaRepository<Review, Long>{

}
