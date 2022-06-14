package com.nhnacademy.task.service;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.nhnacademy.task.domain.dto.task.request.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskReadRequestDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TaskServiceTest {

    @MockBean
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;

    @MockBean
    private ProjectRepository projectRepository;

    private Task task;
    private Project project;

    @BeforeEach
    void setUp() {
        project= Project.builder()
            .projectName("This is Project!")
            .build();

        task = Task.builder()
            .project(project)
            .taskContent("일할 시간이다")
            .taskName("현진아")
            .taskCreatedDt(now())
            .taskCreatedMemNum(1L)
            .build();
    }

    @Test
    void createTask() {
        given(projectRepository.findById(any()))
            .willReturn(Optional.of(project));

        TaskCreateRequestDTO createRequestDTO = TaskCreateRequestDTO.builder()
            .taskContent("일할 시간이다")
            .taskName("현진아")
            .taskCreatedMemNum(1L)
            .projectNum(1L)
            .build();

        taskService.createTask(createRequestDTO);

        verify(taskRepository, atLeastOnce()).save(any());
        verify(taskRepository, atLeastOnce()).queryByTaskNum(any());
    }

    @Test
    void readAllTask() {
        taskService.readAllTask();
        verify(taskRepository, atLeastOnce()).findAllBy();
    }

    @Test
    void readTask() {
        given(taskRepository.findById(any()))
            .willReturn(Optional.ofNullable(task));

        TaskReadRequestDTO taskReadRequestDTO = TaskReadRequestDTO.builder()
            .taskNum(1L)
            .build();

        taskService.readTask(taskReadRequestDTO);
        verify(taskRepository, atLeastOnce()).queryByTaskNum(any());
    }

    @Test
    void updateTask() {

        given(taskRepository.findById(any()))
            .willReturn(Optional.of(task));

        TaskModifyRequestDTO taskModifyRequestDTO = TaskModifyRequestDTO.builder()
            .taskName("수정맨")
            .projectNum(1L)
            .taskContent("수정요")
            .build();

        taskService.updateTask(1L, taskModifyRequestDTO);
        verify(taskRepository, atLeastOnce()).queryByTaskNum(any());
        verify(taskRepository, atLeastOnce()).save(any());
        verify(taskRepository, atLeastOnce()).findById(any());
    }

    @Test
    void deleteTask() {
        given(taskRepository.findById(any()))
            .willReturn(Optional.of(task));

        boolean isTrue = taskService.deleteTask(task.getTaskNum());

        assertThat(isTrue).isTrue();
        verify(taskRepository, atLeastOnce()).delete(any());
    }
}