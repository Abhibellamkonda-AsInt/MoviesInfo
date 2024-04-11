package com.example.MoviesInfo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MoviesInfo.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
}
