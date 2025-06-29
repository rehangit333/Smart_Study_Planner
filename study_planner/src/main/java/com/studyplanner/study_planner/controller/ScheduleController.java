package com.studyplanner.study_planner.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyplanner.study_planner.model.Schedule;
import com.studyplanner.study_planner.model.ScheduleEntry;
import com.studyplanner.study_planner.model.Subject;
import com.studyplanner.study_planner.repository.SubjectRepository;
import com.studyplanner.study_planner.service.ScheduleService;
import com.studyplanner.study_planner.util.SchedulerAlgorithm;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SubjectRepository subjectRepository;

    @PostMapping("/generate/{userId}")
    public ResponseEntity<String> generateSchedule(
            @PathVariable String userId,
            @RequestParam int dailyStudyHours,
            @RequestParam String startDate  // <-- startDate as string input
    ) {
        List<Subject> subjects = subjectRepository.findByUserId(userId);
        if (subjects.isEmpty()) {
            return ResponseEntity.badRequest().body("No subjects found for this user.");
        }

        LocalDate parsedStartDate = LocalDate.parse(startDate);
        Map<LocalDate, List<ScheduleEntry>> scheduleMap = SchedulerAlgorithm.generateSchedule(subjects, parsedStartDate, dailyStudyHours);
        scheduleService.saveGeneratedSchedule(userId, scheduleMap);
        return ResponseEntity.ok("Schedule generated successfully.");
    }

    @GetMapping("/user/{userId}")
    public List<Schedule> getUserSchedules(@PathVariable String userId) {
        return scheduleService.getSchedulesByUserId(userId);
    }
}
