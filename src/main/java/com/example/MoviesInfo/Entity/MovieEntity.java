package com.example.MoviesInfo.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="movies")
public class MovieEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private int id;
	
	@Column(name="movie_name")
	private String name;
	
	@OneToMany(mappedBy="movie",cascade=CascadeType.ALL)
	private List<RatingEntity> ratingEntities;
	
}
