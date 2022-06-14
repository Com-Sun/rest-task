package com.nhnacademy.task.controller;

import com.nhnacademy.task.domain.dto.task.request.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskReadRequestDTO;
import com.nhnacademy.task.domain.dto.task.response.TaskResponseDTO;
import com.nhnacademy.task.service.ProjectService;
import com.nhnacademy.task.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class TaskRestController {

    private final TaskService taskService;
    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/projects/{project-num}/tasks")
    public TaskResponseDTO createProject(@RequestBody TaskCreateRequestDTO requestDTO) {

        return taskService.createTask(requestDTO);
    }

    @GetMapping(value = "/projects/{project-num}/tasks")
    public List<TaskResponseDTO> readAllProjects() {

        return taskService.readAllTask();
    }

    @GetMapping(value = "/projects/{project-num}/tasks/{taskNum}")
    public TaskResponseDTO readProject(@PathVariable(name = "taskNum") Long taskNum) {

        return taskService.readTask(TaskReadRequestDTO.builder()
            .taskNum(taskNum)
            .build());
    }

    @PutMapping(value = "/projects/{project-num}/tasks/{taskNum}")
    public TaskResponseDTO modifyTask(@PathVariable(name = "taskNum") Long taskNum, @RequestBody
    TaskModifyRequestDTO requestDTO) {

        return taskService.updateTask(taskNum, requestDTO);
    }

    @DeleteMapping(value = "/projects/{project-num}/tasks/{taskNum}")
    public boolean deleteProject(@PathVariable(name = "taskNum") Long taskNum) {

        return taskService.deleteTask(taskNum);
    }
}
