package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.response.TaskResponseDto;
import com.nhnacademy.task.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Task.Pk> {

    List<TaskResponseDto> findByPk_ProjectNum(Long projectNum);
}
