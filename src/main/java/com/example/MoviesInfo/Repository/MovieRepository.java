package com.example.MoviesInfo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MoviesInfo.Entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer>{
}
