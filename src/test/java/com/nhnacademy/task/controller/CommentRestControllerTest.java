package com.nhnacademy.task.controller;

import static java.time.LocalDateTime.now;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.task.domain.dto.comment.request.CommentCreateRequestDTO;
import com.nhnacademy.task.domain.dto.comment.request.CommentModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskModifyRequestDTO;
import com.nhnacademy.task.entity.Comment;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.repository.CommentRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import java.util.Optional;
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
class CommentRestControllerTest {

    @SpyBean
    private CommentRepository commentRepository;
    @SpyBean
    private TaskRepository taskRepository;
    @SpyBean
    private ProjectRepository projectRepository;

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    private Task task;
    private Comment comment;
    private Project project;


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

        comment = Comment.builder()
            .commentContent("으악")
            .memberName("현진")
            .commentCreatedDt(now())
            .task(task)
            .build();

    }

    @DisplayName("API - comment 생성 테스트")
    @Test
    void createCommentTest() throws Exception {

        CommentCreateRequestDTO requestDTO = CommentCreateRequestDTO.builder()
            .commentContent("으악")
            .memberName("현진")
            .commentCreatedDt(now())
            .taskNum(1L)
            .build();

        String requestBody = mapper.writeValueAsString(requestDTO);
        this.mvc.perform(
                post("/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
            ).andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.commentContent", equalTo("으악")));
    }

    @DisplayName("API - comment 전체 조회 테스트")
    @Test
    void readAllCommentsTest() throws Exception {
        this.mvc.perform(
                get("/comments"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("API - comment 단건 조회 테스트")
    @Test
    void readCommentTest() throws Exception {
        this.mvc.perform(
                get("/comments/{commentNum}", 1L)
            ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("API - comment 수정 테스트")
    @Test
    void modifyCommentTest() throws Exception {

        CommentModifyRequestDTO requestDTO = CommentModifyRequestDTO.builder()
            .commentNum(1L)
            .commentContent("gk")
            .commentModifiedDt(now())
            .commentContent("aa")
            .build();

        String requestBody = mapper.writeValueAsString(requestDTO);

        this.mvc.perform(
                put("/comments/{commentNum}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
            ).andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.commentContent", equalTo("aa")));
    }

    @DisplayName("API - comment 삭제 테스트")
    @Test
    void deleteCommentTest() throws Exception {
        this.mvc.perform(
            delete("/comments/{commentNum}", 1L)
        ).andExpect(status().isOk());
    }
}