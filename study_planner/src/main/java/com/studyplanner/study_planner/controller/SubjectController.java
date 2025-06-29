package com.studyplanner.study_planner.controller;

import com.studyplanner.study_planner.model.Subject;
import com.studyplanner.study_planner.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @GetMapping("/user/{userId}")
    public List<Subject> getSubjectsByUserId(@PathVariable String userId) {
        return subjectService.getSubjectsByUserId(userId);
    }

    @GetMapping("/{id}")
    public Optional<Subject> getSubjectById(@PathVariable String id) {
        return subjectService.getSubjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubjectById(@PathVariable String id) {
        subjectService.deleteSubjectById(id);
    }
}

