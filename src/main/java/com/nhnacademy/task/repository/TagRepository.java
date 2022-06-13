package com.nhnacademy.task.repository;

import com.nhnacademy.task.domain.dto.response.TagResponseDTO;
import com.nhnacademy.task.entity.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Tag.Pk> {

    List<TagResponseDTO> findByPk_ProjectNum(Long projectNum);
}
