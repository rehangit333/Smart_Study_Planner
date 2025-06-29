package com.studyplanner.study_planner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntry {
    private String subjectName;
    private int hoursAllocated;
}

