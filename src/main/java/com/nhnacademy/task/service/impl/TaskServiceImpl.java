package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.domain.dto.response.TaskResponseDTO;
import com.nhnacademy.task.domain.dto.task.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.TaskModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.TaskReadRequestDTO;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.exception.ProjectNotFoundException;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public TaskResponseDTO createTask(TaskCreateRequestDTO createRequestDTO) {
        Task task = Task.builder()
            .taskCreatedMemNum(createRequestDTO.getTaskCreatedMemNum())
            .taskName(createRequestDTO.getTaskName())
            .taskCreatedDt(createRequestDTO.getTaskCreatedDt())
            .taskContent(createRequestDTO.getTaskContent())
            .project(projectRepository.findById(createRequestDTO.getProjectNum()).orElseThrow(() -> new ProjectNotFoundException("해당 프로젝트가 존재하지 않습니다.")))
            .build();

        taskRepository.save(task);

        return taskRepository.queryByTaskNum(task.getTaskNum());
    }

    @Override
    public List<TaskResponseDTO> readAllTask() {
        return taskRepository.findAllBy();
    }

    @Override
    public TaskResponseDTO readTask(TaskReadRequestDTO readRequestDTO) {
        return taskRepository.queryByTaskNum(readRequestDTO.getTaskNum());
    }

    @Override
    public TaskResponseDTO updateTask(TaskModifyRequestDTO modifyRequestDto) {
        return null;
    }

    @Override
    public boolean deleteTask(Long taskNum) {
        return false;
    }
}
