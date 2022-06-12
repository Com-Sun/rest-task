package com.nhnacademy.task.repository;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.task.domain.dto.response.TaskResponseDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ProjectRepository projectRepository;

    private Project project;
    private Task task;
    private Task task2;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .projectStatus("활성")
            .build();




    }

    @Test
    void findByProjectNumTest() {
        projectRepository.saveAndFlush(project);

        task = Task.builder()
            .pk(new Task.Pk(1L, project.getProjectNum()))
            .project(project)
            .taskContent("일할시간")
            .taskCreatedDt(now())
            .taskName("현진아")
            .taskCreatedMemNum(1L)
            .build();

        task2 = Task.builder()
            .pk(new Task.Pk(2L, project.getProjectNum()))
            .project(project)
            .taskContent("일할시간")
            .taskCreatedDt(now())
            .taskName("현진아")
            .taskCreatedMemNum(1L)
            .build();

        taskRepository.saveAndFlush(task);
        taskRepository.saveAndFlush(task2);

        List<TaskResponseDto> dtoList =  taskRepository.findByPk_ProjectNum(project.getProjectNum());
        assertThat(dtoList).hasSize(2);
    }

}