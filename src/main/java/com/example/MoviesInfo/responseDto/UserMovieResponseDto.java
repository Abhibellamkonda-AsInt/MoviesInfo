package com.example.MoviesInfo.responseDto;

import java.util.List;

import lombok.Data;

@Data
public class UserMovieResponseDto {
	private int NumberOfMoviesRated;
	private List<RatingResponseDto> ratingResponseDto;
}
