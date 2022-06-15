package com.nhnacademy.task.repository;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.task.domain.dto.response.MilestoneResponseDTO;
import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import java.time.LocalDateTime;
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
    private ProjectRepository projectRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;

    @Autowired
    private TaskRepository taskRepository;

    private Project project;
    private Task task;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .build();

        task = Task.builder()
            .project(project)
            .taskContent("일할시간")
            .taskCreatedDt(LocalDateTime.now())
            .taskName("현진아")
            .taskCreatedMemNum(1L)
            .build();
    }

    @Test
    void findByPk_ProjectNumTest() {
        projectRepository.saveAndFlush(project);
        taskRepository.saveAndFlush(task);

        Milestone milestone = Milestone.builder()
            .pk(new Milestone.Pk("안녕", project.getProjectNum()))
            .project(project)
            .milestoneStatus("활성")
            .task(task)
            .milestoneStartDate(now())
            .milestoneEndDate(now())
            .build();

        milestoneRepository.save(milestone);

        List<MilestoneResponseDTO> responseDtoList = milestoneRepository.findByPk_ProjectNum(project.getProjectNum());
        assertThat(responseDtoList).hasSize(1);
    }

}