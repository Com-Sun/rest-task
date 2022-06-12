package com.nhnacademy.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "project")
@Entity
public class Project {
    @Id
    @Column(name = "project_num")
    private Long projectNum;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_status")
    private String projectStatus;

    @Builder(builderClassName = "ProjectBuilder")
    private Project (Long projectNum, String projectName, String projectStatus) {
        this.projectNum = projectNum;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
    }
}
