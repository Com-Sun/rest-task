package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.response.MilestoneResponseDTO;
import com.nhnacademy.task.entity.Milestone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Milestone.Pk> {

    List<MilestoneResponseDTO> findByPk_ProjectNum(Long projectNum);
}
