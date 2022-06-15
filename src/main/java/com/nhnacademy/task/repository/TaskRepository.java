package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.task.response.TaskResponseDTO;
import com.nhnacademy.task.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<TaskResponseDTO> findByProject_ProjectNum(Long projectNum);

    TaskResponseDTO queryByTaskNum(Long taskNum);

    List<TaskResponseDTO> findAllBy();
}
