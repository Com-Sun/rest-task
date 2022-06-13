package com.nhnacademy.task.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "milestone")
@Entity
public class Milestone {

    @EmbeddedId
    private Pk pk;

    @MapsId("projectNum")
    @ManyToOne
    @JoinColumn(name = "project_num")
    private Project project;

    @Column(name = "task_num")
    private Long taskNum;

    @Column(name = "milestone_status")
    private String milestoneStatus;

    @Column(name = "milestone_start_date")
    private LocalDate milestoneStartDate;

    @Column(name = "milestone_end_date")
    private LocalDate milestoneEndDate;

    @Builder(builderClassName = "MilestoneBuilder")
    private Milestone(Pk pk, Project project, Long taskNum, String milestoneStatus, LocalDate milestoneStartDate, LocalDate milestoneEndDate) {
        this.pk = pk;
        this.project = project;
        this.taskNum = taskNum;
        this.milestoneStatus = milestoneStatus;
        this.milestoneStartDate = milestoneStartDate;
        this.milestoneEndDate = milestoneEndDate;
    }

    @Embeddable
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pk implements Serializable {

        @Column(name = "milestone_name")
        private String milestoneName;
        @Column(name = "project_num")
        private Long projectNum;
    }
}
