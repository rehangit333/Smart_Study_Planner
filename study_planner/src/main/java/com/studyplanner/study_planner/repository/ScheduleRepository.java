package com.studyplanner.study_planner.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.studyplanner.study_planner.model.Schedule;

// Repository interface for Schedule collection
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByUserId(String userId);
    Optional<Schedule> findByUserIdAndDate(String userId, LocalDate date);
    void deleteByUserId(String userId);
}

