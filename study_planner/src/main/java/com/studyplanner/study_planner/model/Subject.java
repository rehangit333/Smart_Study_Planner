package com.studyplanner.study_planner.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "subjects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    private String id;
    private String userId;
    private String name;
    private int priority;       // Higher number means higher priority
    private LocalDate examDate; // Exam date
    private int totalHours;     // Total study hours required

    public Subject copy() {
        return new Subject(id, userId, name, priority, examDate, totalHours);
    }

}
