package com.studyplanner.study_planner.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyplanner.study_planner.model.Schedule;
import com.studyplanner.study_planner.model.ScheduleEntry;
import com.studyplanner.study_planner.repository.ScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void saveGeneratedSchedule(String userId, Map<LocalDate, List<ScheduleEntry>> scheduleMap) {
        for (Map.Entry<LocalDate, List<ScheduleEntry>> entry : scheduleMap.entrySet()) {
            LocalDate date = entry.getKey();
            List<ScheduleEntry> entries = entry.getValue();

            Optional<Schedule> existingSchedule = scheduleRepository.findByUserIdAndDate(userId, date);

            Schedule schedule = existingSchedule.orElse(new Schedule());
            schedule.setUserId(userId);
            schedule.setDate(date);

            //if (existingSchedule.isPresent()) {
                //schedule.setEntries(entries);  // merge new entries
           // } else {
                //schedule.setEntries(entries);
            //}
            schedule.setEntries(entries);

            scheduleRepository.save(schedule);
        }
    }

    public List<Schedule> getSchedulesByUserId(String userId) {
        return scheduleRepository.findByUserId(userId);
    }
}
