package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.response.ProjectResponseDTO;
import com.nhnacademy.task.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    ProjectResponseDTO findByProjectName(String projectName);
}
