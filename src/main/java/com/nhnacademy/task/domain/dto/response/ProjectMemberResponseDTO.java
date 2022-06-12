package com.nhnacademy.task.domain.dto.response;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectMember;

public interface ProjectMemberResponseDTO {

    ProjectMember.Pk getPk();

    Project getProject();

    String getProjectAuth();

    String getMemberName();
}
