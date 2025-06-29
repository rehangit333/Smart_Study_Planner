package com.studyplanner.study_planner.service;

import com.studyplanner.study_planner.model.Subject;
import com.studyplanner.study_planner.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubjectsByUserId(String userId) {
        return subjectRepository.findByUserId(userId);
    }

    public Optional<Subject> getSubjectById(String id) {
        return subjectRepository.findById(id);
    }

    public void deleteSubjectById(String id) {
        subjectRepository.deleteById(id);
    }
}
