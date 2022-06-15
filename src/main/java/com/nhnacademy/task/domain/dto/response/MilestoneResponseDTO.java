package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import java.time.LocalDate;

public interface MilestoneResponseDTO {

    Milestone.Pk getPk();
    Project getProject();
    Task getTask();
    String getMilestoneStatus();
    LocalDate getMilestoneStartDate();
    LocalDate getMilestoneEndDate();
}
