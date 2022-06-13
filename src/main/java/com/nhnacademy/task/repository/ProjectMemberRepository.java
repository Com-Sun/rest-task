package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.response.ProjectMemberResponseDTO;
import com.nhnacademy.task.entity.ProjectMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMember.Pk> {
    List<ProjectMemberResponseDTO> findByPk_ProjectNum(Long projectNum);
}
