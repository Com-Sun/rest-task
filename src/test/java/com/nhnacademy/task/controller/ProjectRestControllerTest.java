package com.nhnacademy.task.controller;


import static org.hamcrest.Matchers.equalTo;
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
import com.nhnacademy.task.domain.dto.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectModifyRequestDTO;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.repository.ProjectRepository;
import java.util.Optional;
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
class ProjectRestControllerTest {

    @SpyBean
    private ProjectRepository projectRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @DisplayName("API - 프로젝트 생성 테스트")
    @Test
    void createProjectTest() throws Exception {
        ProjectCreateRequestDTO requestDTO = ProjectCreateRequestDTO.builder()
            .projectName("Project")
            .build();

        String requestBody = mapper.writeValueAsString(requestDTO);
        this.mvc.perform(
                post("/projects")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
            ).andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.projectName", equalTo("Project")));

    }

    @DisplayName("API - 프로젝트 전체 조회 테스트")
    @Test
    void readAllProjectsTest() throws Exception {
        this.mvc.perform(
                get("/projects"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("API - 프로젝트 단건 조회 테스트")
    @Test
    void readProjectTest() throws Exception {
        this.mvc.perform(
                get("/projects/{projectNum}", 1L)
            ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("API - 프로젝트 수정 테스트")
    @Test
    void modifyProjectTest() throws Exception {
        given(projectRepository.findById(1L))
            .willReturn(Optional.of(Project.builder()
                .projectName("가나다라")
                .build()));

        ProjectModifyRequestDTO projectModifyRequestDTO = ProjectModifyRequestDTO.builder()
            .projectName("이걸로바꾸자")
            .build();

        String requestBody = mapper.writeValueAsString(projectModifyRequestDTO);

        this.mvc.perform(
                put("/projects/{projectNum}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
            ).andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.projectName", equalTo("이걸로바꾸자")));
    }

    @DisplayName("API - 프로젝트 삭제 테스트")
    @Test
    void deleteProjectTest() throws Exception {
        this.mvc.perform(
            delete("/projects/{projectNum}", 1L)
        ).andExpect(status().isOk());
    }

}