package com.nhnacademy.task.service;

import com.nhnacademy.task.domain.dto.response.TaskResponseDTO;
import com.nhnacademy.task.domain.dto.task.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.TaskModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.TaskReadRequestDTO;
import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskCreateRequestDTO createRequestDTO);

    List<TaskResponseDTO> readAllTask();

    TaskResponseDTO readTask(TaskReadRequestDTO readRequestDTO);

    TaskResponseDTO updateTask(TaskModifyRequestDTO modifyRequestDto);

    boolean deleteTask(Long taskNum);
}
