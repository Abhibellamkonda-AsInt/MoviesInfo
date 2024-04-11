package com.example.MoviesInfo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MoviesInfo.Entity.MovieEntity;
import com.example.MoviesInfo.Repository.MovieRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class MovieServiceImpl {
	@Autowired
	private MovieRepository movierepository;
	
	public MovieEntity addMovie(MovieEntity movie) {
		int id =movie.getId();
		Optional<MovieEntity> found= movierepository.findById(id);
		if(found.isPresent()) {
			throw new IllegalStateException(id+"already exits");
		}
		return movierepository.save(movie);
	}
	
	public List<MovieEntity> getMovies(){
		return movierepository.findAll();
	}
	
	public Optional<MovieEntity> getMovieById(int id ){
		return movierepository.findById(id);
	}
	
	public String deleteMovie(int id) {
		MovieEntity movie=movierepository.findById(id).orElse(null);
		if(movie!=null) {
			movierepository.delete(movie);
			return "movie deleted";
		}
		return "No Movie Found";
		
	}
	
	public MovieEntity updateMovie(MovieEntity movie) {
		int id =movie.getId();
		Optional<MovieEntity> found=movierepository.findById(id);
		if(found.isPresent()) {
			throw new RuntimeException(id+" cannot be updated");
		}
		else {
			return movierepository.save(movie);
		}
	}
	
	
}
