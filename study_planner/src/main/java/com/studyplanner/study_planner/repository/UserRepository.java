package com.studyplanner.study_planner.repository;

import com.studyplanner.study_planner.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

// Repository interface for User collection
public interface UserRepository extends MongoRepository<User, String> { }