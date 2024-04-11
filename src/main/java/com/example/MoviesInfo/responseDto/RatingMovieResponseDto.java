package com.example.MoviesInfo.responseDto;

import java.util.List;

import lombok.Data;

@Data
public class RatingMovieResponseDto {
	private Double avgRating;
	private List<RatingResponseDto> ratingResponseDtos;
}
