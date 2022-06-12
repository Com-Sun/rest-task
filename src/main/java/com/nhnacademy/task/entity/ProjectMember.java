package com.nhnacademy.task.entity;

import java.io.Serializable;
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
@Table(name = "project_member")
@Entity
public class ProjectMember {
    @EmbeddedId
    private Pk pk;

    @MapsId("projectNum")
    @ManyToOne
    @JoinColumn(name = "project_num")
    private Project project;

    @Column(name = "project_auth")
    private String projectAuth;

    @Column(name = "member_name")
    private String memberName;

    @Builder(builderClassName = "ProjectMemberBuilder")
    private ProjectMember(Pk pk, Project project, String projectAuth, String memberName) {
        this.pk = pk;
        this.project = project;
        this.projectAuth = projectAuth;
        this.memberName = memberName;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    @EqualsAndHashCode
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "member_num")
        private Long memberNum;

        @Column(name = "project_num")
        private Long projectNum;
    }
}
