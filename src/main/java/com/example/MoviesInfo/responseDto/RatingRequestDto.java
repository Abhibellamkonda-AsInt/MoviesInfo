package com.example.MoviesInfo.responseDto;

import lombok.Data;

@Data
public class RatingRequestDto {
	private Long userId;
	private Double rating;
	private String review;
	
}
