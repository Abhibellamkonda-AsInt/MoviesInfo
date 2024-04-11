package com.example.MoviesInfo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="ratings")
public class RatingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rating_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_Id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="movie_Id")
	private MovieEntity movie;
	
	@Column(nullable=false)
	private Double rating;
	
	@Column(nullable=false)
	private String review;
	
	
}
