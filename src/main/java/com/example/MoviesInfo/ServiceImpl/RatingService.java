package com.example.MoviesInfo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MoviesInfo.Entity.RatingEntity;
import com.example.MoviesInfo.Repository.RatingRepository;
import com.example.MoviesInfo.responseDto.RatingResponseDto;
@Service
public class RatingService {
	@Autowired
	private RatingRepository ratingrepository;
	
	public RatingResponseDto addRating(RatingEntity rating) {	
		RatingEntity rate = ratingrepository.save(rating);
		RatingResponseDto resp = new RatingResponseDto();
		resp.setUserName(rate.getUser().getName());
		resp.setMovieName(rate.getMovie().getName());
		resp.setRating(rate.getRating());
		
		resp.setReview(rate.getReview());
		return resp;
	}
	
	public List<RatingEntity> getRating(){
		return ratingrepository.findAll();
	}
	

	public RatingResponseDto entityToResponseDto(RatingEntity ratingEntity)
	{
		RatingResponseDto ratingResponse = new RatingResponseDto();
		ratingResponse.setUserName(ratingEntity.getUser().getName());
		ratingResponse.setMovieName(ratingEntity.getMovie().getName());
		ratingResponse.setRating(ratingEntity.getRating());
		ratingResponse.setReview(ratingEntity.getReview());
		return ratingResponse;
	}
}
