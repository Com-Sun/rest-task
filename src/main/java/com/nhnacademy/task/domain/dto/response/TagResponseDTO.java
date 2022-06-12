package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;

public interface TagResponseDTO {

    Tag.Pk getPk();
    Project getProject();
    Long getTaskNum();
}
