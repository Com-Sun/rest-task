package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;

public interface TagResponseDTO {

    Tag.Pk getPk();
    Project getProject();
    Task getTask();
}
