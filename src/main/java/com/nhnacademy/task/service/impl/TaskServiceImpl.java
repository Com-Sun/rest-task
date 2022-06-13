package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.domain.dto.task.response.TaskResponseDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskCreateRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskModifyRequestDTO;
import com.nhnacademy.task.domain.dto.task.request.TaskReadRequestDTO;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.exception.ProjectNotFoundException;
import com.nhnacademy.task.exception.TaskNotFoundException;
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

        Task task = taskRepository.findById(modifyRequestDto.getTaskNum()).orElseThrow(() -> new TaskNotFoundException("해당 업무가 존재하지 않습니다."));
        task.setTaskContent(modifyRequestDto.getTaskContent());
        task.setTaskName(modifyRequestDto.getTaskName());
        task.setTaskModifiedDt(modifyRequestDto.getTaskModifiedDt());
        taskRepository.save(task);

        return taskRepository.queryByTaskNum(task.getTaskNum());
    }

    @Override
    public boolean deleteTask(Long taskNum) {
        taskRepository.delete(taskRepository.findById(taskNum).orElseThrow(() -> new TaskNotFoundException("해당 업무가 존재하지 않습니다.")));

        return true;
    }
}
