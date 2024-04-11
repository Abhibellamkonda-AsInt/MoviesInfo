package com.example.MoviesInfo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MoviesInfo.Entity.RatingEntity;
import com.example.MoviesInfo.Repository.RatingRepository;
import com.example.MoviesInfo.ServiceImpl.MovieServiceImpl;
import com.example.MoviesInfo.ServiceImpl.RatingService;
import com.example.MoviesInfo.ServiceImpl.UserServiceImpl;
import com.example.MoviesInfo.responseDto.RatingMovieResponseDto;
import com.example.MoviesInfo.responseDto.RatingRequestDto;
import com.example.MoviesInfo.responseDto.RatingResponseDto;
import com.example.MoviesInfo.responseDto.UserMovieResponseDto;

@RestController
public class RatingController {
	@Autowired
	private RatingService ratingService;
	@Autowired
	private MovieServiceImpl movieService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private RatingRepository ratingRepo;
	@PostMapping("/addRating/{id}")
	public ResponseEntity<Object> addRating(@RequestBody RatingRequestDto ratingRequestDto,@PathVariable int id){
		if((ratingRepo.findByMovieAndUser(movieService.getMovieById(id).get(), userService.getUserById(ratingRequestDto.getUserId()).get())) != null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User has already rated the movie!");
		}
		RatingEntity ratingEntity= new RatingEntity();
		ratingEntity.setUser(userService.getUserById(ratingRequestDto.getUserId()).get());
		ratingEntity.setMovie(movieService.getMovieById(id).get());
		ratingEntity.setRating(ratingRequestDto.getRating());
		ratingEntity.setReview(ratingRequestDto.getReview());
		RatingResponseDto resp = ratingService.addRating(ratingEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		
	}
	
	@PutMapping("/updateRating/{id}")
	public ResponseEntity<Object> updateRating(@RequestBody RatingRequestDto ratingRequestDto,@PathVariable int id){
		RatingEntity ratingEntity= ratingRepo.findByMovieAndUser(movieService.getMovieById(id).get(), userService.getUserById(ratingRequestDto.getUserId()).get());
		if(ratingEntity==null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("user haven't rated the movie yet!!");
		}
		ratingEntity.setUser(userService.getUserById(ratingRequestDto.getUserId()).get());
		ratingEntity.setMovie(movieService.getMovieById(id).get());
		ratingEntity.setRating(ratingRequestDto.getRating());
		ratingEntity.setReview(ratingRequestDto.getReview());
		RatingResponseDto resp = ratingService.addRating(ratingEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		
	}
	
	@GetMapping("/movieId/{id}")
	public ResponseEntity<Object> getRating(@PathVariable int id){
		List<RatingEntity> ratings = ratingRepo.findByMovie(movieService.getMovieById(id).get());
		List<RatingResponseDto> ratingResponse = ratings.stream().map(ratingService::entityToResponseDto).toList();
		RatingMovieResponseDto response = new RatingMovieResponseDto();
		response.setAvgRating((ratings.stream().map(rate -> rate.getRating()).toList().stream().mapToDouble(Double::doubleValue).sum())/ratings.size());
		response.setRatingResponseDtos(ratingResponse);
		return ResponseEntity.status(HttpStatus.FOUND).body(response);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUserRatings(@PathVariable Long userId){
		List<RatingEntity> Ratings=ratingRepo.findByUser(userService.getUserById(userId).get());
		List<RatingResponseDto> RatingResponse=Ratings.stream().map(ratingService::entityToResponseDto).toList();
		UserMovieResponseDto Resp= new UserMovieResponseDto();
		Resp.setNumberOfMoviesRated(Ratings.size());
		Resp.setRatingResponseDto(RatingResponse);
		return ResponseEntity.status(HttpStatus.FOUND).body(Resp);
	}
}
