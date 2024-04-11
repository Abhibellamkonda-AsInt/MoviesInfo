package com.example.MoviesInfo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MoviesInfo.Entity.MovieEntity;
import com.example.MoviesInfo.ServiceImpl.MovieServiceImpl;
import com.example.MoviesInfo.responseDto.MovieRequestDto;
import com.example.MoviesInfo.responseDto.MovieResponse;

@RestController
@RequestMapping("/movie")
public class Moviecontroller {
	@Autowired
	private MovieServiceImpl movieservice;
	
	@PostMapping
	public MovieResponse addMovie(@RequestBody MovieRequestDto movie) {
		MovieEntity Movie= new MovieEntity();
		Movie.setName(movie.getName());
		movieservice.addMovie(Movie);
		MovieResponse movieres= new MovieResponse();
		movieres.setId(Movie.getId());
		movieres.setName(Movie.getName());
		return movieres;
	}
	
	@GetMapping
	public List<MovieEntity> getMovies(){
		return movieservice.getMovies();
	}
	
	@GetMapping("/{id}")
	public Optional<MovieEntity> getMovieById(@PathVariable int id) {
		return movieservice.getMovieById(id);
	}
	

	@DeleteMapping("/delete/{id}")
	public String deleteMovie(@PathVariable int id) {
		return movieservice.deleteMovie(id);
	}
	
	@PutMapping
	public MovieEntity updateMovie(@RequestBody MovieEntity movie) {
		return movieservice.updateMovie(movie);
	}
	
}
