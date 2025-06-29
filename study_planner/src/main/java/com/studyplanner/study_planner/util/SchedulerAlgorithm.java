package com.studyplanner.study_planner.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.studyplanner.study_planner.model.ScheduleEntry;
import com.studyplanner.study_planner.model.Subject;

public class SchedulerAlgorithm {

    public static Map<LocalDate, List<ScheduleEntry>> generateSchedule(List<Subject> subjects, LocalDate startDate, int dailyStudyHours) {

        Map<LocalDate, List<ScheduleEntry>> schedule = new LinkedHashMap<>();
        List<Subject> clonedSubjects = new ArrayList<>();
        for (Subject s : subjects) {
            clonedSubjects.add(s.copy());
        }
        // Sort subjects by exam date and priority
        subjects.sort((a, b) -> {
            long daysA = ChronoUnit.DAYS.between(startDate, a.getExamDate());
            long daysB = ChronoUnit.DAYS.between(startDate, b.getExamDate());
            if (daysA == daysB) {
                return Integer.compare(b.getPriority(), a.getPriority());
            }
            return Long.compare(daysA, daysB);
        });

        // Find the last exam date
        LocalDate endDate = subjects.stream()
                .map(Subject::getExamDate)
                .max(LocalDate::compareTo)
                .orElse(startDate);

        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;

        // For each day
        for (int i = 0; i < totalDays; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            int remainingHoursToday = dailyStudyHours;

            // Always clear and initialize today's schedule
            schedule.put(currentDate, new ArrayList<>());

            // Sort again daily to dynamically prioritize unfinished subjects
            clonedSubjects.sort((a, b) -> {
                long daysLeftA = ChronoUnit.DAYS.between(currentDate, a.getExamDate());
                long daysLeftB = ChronoUnit.DAYS.between(currentDate, b.getExamDate());
                if (daysLeftA == daysLeftB) {
                    return Integer.compare(b.getPriority(), a.getPriority());
                }
                return Long.compare(daysLeftA, daysLeftB);
            });

            for (Subject subject : clonedSubjects) {
                if (remainingHoursToday == 0) break;

                long daysLeft = ChronoUnit.DAYS.between(currentDate, subject.getExamDate());
                if (daysLeft < 0 || subject.getTotalHours() == 0) continue;

                int hoursToAllocate = (int) Math.ceil((double) subject.getTotalHours() / (daysLeft + 1));
                hoursToAllocate = Math.min(hoursToAllocate, remainingHoursToday);
                hoursToAllocate = Math.min(hoursToAllocate, subject.getTotalHours());

                if (hoursToAllocate > 0) {
                    schedule.get(currentDate).add(new ScheduleEntry(subject.getName(), hoursToAllocate));
                    subject.setTotalHours(subject.getTotalHours() - hoursToAllocate);
                    remainingHoursToday -= hoursToAllocate;
                }
            }
        }

        return schedule;
    }
}
