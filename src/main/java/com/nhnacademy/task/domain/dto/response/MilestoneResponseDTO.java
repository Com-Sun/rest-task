package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import java.time.LocalDate;

public interface MilestoneResponseDTO {

    Milestone.Pk getPk();
    Project getProject();
    Long getTaskNum();
    String getMilestoneStatus();
    LocalDate getMilestoneStartDate();
    LocalDate getMilestoneEndDate();
}
