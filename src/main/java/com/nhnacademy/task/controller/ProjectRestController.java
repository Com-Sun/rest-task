package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.dto.project.request.ProjectCreateRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectModifyRequestDTO;
import com.nhnacademy.task.domain.dto.project.request.ProjectReadRequestDTO;
import com.nhnacademy.task.domain.dto.project.response.ProjectResponseDTO;
import com.nhnacademy.task.service.ProjectService;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ProjectRestController {

    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/projects")
    public ProjectResponseDTO createProject(@RequestBody ProjectCreateRequestDTO requestDTO) {
        return projectService.createProject(requestDTO);
    }

    @GetMapping(value = "/projects")
    public List<ProjectResponseDTO> readAllProjects() {
        return projectService.readAllProjects();
    }

    @GetMapping(value = "/projects/{projectNum}")
    public ProjectResponseDTO readProject(@PathVariable(name = "projectNum") Long projectNum) {

        return projectService.readProject(ProjectReadRequestDTO.builder()
                .ProjectNum(projectNum)
            .build());
    }

    @PutMapping(value = "/projects/{projectNum}")
    public ProjectResponseDTO modifyProject(@PathVariable(name = "projectNum") Long projectNum, @RequestBody
                                            ProjectModifyRequestDTO requestDTO) {

        return projectService.updateProject(requestDTO);
    }

    @DeleteMapping(value = "/projects/{projectNum}")
    public boolean deleteProject(@PathVariable(name = "projectNum") Long projectNum) {
        return projectService.deleteProject(projectNum);
    }


}
