package com.studyplanner.study_planner.repository;

import com.studyplanner.study_planner.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

// Repository interface for Subject collection
public interface SubjectRepository extends MongoRepository<Subject, String> {
    List<Subject> findByUserId(String userId);
}
