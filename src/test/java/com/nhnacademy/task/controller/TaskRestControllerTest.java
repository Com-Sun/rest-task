package com.nhnacademy.task.controller;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.dto.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskModifyRequestDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import java.util.Optional;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class TaskRestControllerTest {

    @SpyBean
    private ProjectRepository projectRepository;
    @SpyBean
    private TaskRepository taskRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private Project project;
    private Task task;

    @BeforeEach
    void setUp() {
        project = Project.builder()
            .projectName("Hyunjin Project")
            .build();
        projectRepository.save(project);

        task = Task.builder()
            .project(project)
            .taskContent("졸려")
            .taskName("더졸려")
            .taskCreatedMemNum(1L)
            .taskCreatedDt(now())
            .build();
    }

    @DisplayName("API - task 생성 테스트")
    @Test
    void createTaskTest() throws Exception {

        TaskCreateRequestDTO requestDTO = TaskCreateRequestDTO.builder()
            .projectNum(1L)
            .taskContent("졸려")
            .taskName("더졸려")
            .taskCreatedMemNum(1L)
            .taskCreatedDt(now())
            .build();

        String requestBody = mapper.writeValueAsString(requestDTO);
        this.mvc.perform(
                post("/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
            ).andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.taskName", equalTo("더졸려")));
    }

    @DisplayName("API - task 전체 조회 테스트")
    @Test
    void readAllTasksTest() throws Exception {
        this.mvc.perform(
                get("/tasks"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("API - task 단건 조회 테스트")
    @Test
    void readTaskTest() throws Exception {
        this.mvc.perform(
                get("/tasks/{taskNum}", 1L)
            ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("API - task 수정 테스트")
    @Test
    void modifyTaskTest() throws Exception {
        given(taskRepository.findById(1L))
            .willReturn(Optional.of(task));

        TaskModifyRequestDTO requestDTO = TaskModifyRequestDTO.builder()
            .taskModifiedDt(now())
            .taskContent("졸려잉")
            .projectNum(1L)
            .taskNum(1L)
            .taskName("흐흐")
            .build();

        String requestBody = mapper.writeValueAsString(requestDTO);

        this.mvc.perform(
                put("/tasks/{taskNm}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
            ).andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.taskName", equalTo("흐흐")));
    }

    @DisplayName("API - task 삭제 테스트")
    @Test
    void deleteProjectTest() throws Exception {
        this.mvc.perform(
            delete("/tasks/{taskNum}", 1L)
        ).andExpect(status().isOk());
    }
}