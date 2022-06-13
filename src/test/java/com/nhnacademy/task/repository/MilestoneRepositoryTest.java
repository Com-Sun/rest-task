package com.nhnacademy.task.repository;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.task.domain.dto.response.MilestoneResponseDTO;
import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MilestoneRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    MilestoneRepository milestoneRepository;
    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .projectNum(1L)
            .build();
    }

    @Test
    void findByPk_ProjectNumTest() {
        projectRepository.saveAndFlush(project);

        Milestone milestone = Milestone.builder()
            .pk(new Milestone.Pk("안녕", project.getProjectNum()))
            .project(project)
            .milestoneStatus("활성")
            .taskNum(1L)
            .milestoneStartDate(now())
            .milestoneEndDate(now())
            .build();

        milestoneRepository.save(milestone);

        List<MilestoneResponseDTO> responseDtoList = milestoneRepository.findByPk_ProjectNum(project.getProjectNum());
        assertThat(responseDtoList).hasSize(1);
    }

}