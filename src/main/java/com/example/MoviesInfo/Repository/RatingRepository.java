package com.example.MoviesInfo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MoviesInfo.Entity.MovieEntity;
import com.example.MoviesInfo.Entity.RatingEntity;
import com.example.MoviesInfo.Entity.UserEntity;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity,Integer>{
	public List<RatingEntity> findByMovie(MovieEntity movieEntity);
	public List<RatingEntity> findByUser(UserEntity userEntity);
	public RatingEntity findByMovieAndUser(MovieEntity movie,UserEntity user);
} 
