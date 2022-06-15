package com.nhnacademy.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "project")
@Entity
@DynamicInsert
public class Project {

    @Id
    @Column(name = "project_num")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long projectNum;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_status")
    private String projectStatus;

    @Builder(builderClassName = "ProjectBuilder")
    private Project (String projectName) {
        this.projectName = projectName;
    }
}
