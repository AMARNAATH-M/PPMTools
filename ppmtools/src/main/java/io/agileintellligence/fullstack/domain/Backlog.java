package io.agileintellligence.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;
    @Column(insertable = false,updatable=false)
    private int project_id;

    public Backlog() {
    }

    //OnetoOne with Project
    @OneToOne(fetch=FetchType.EAGER)
   @JoinColumn(name ="project_id",nullable=false)
    @JsonIgnore
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    //OnetoMany with ProjectTask

}
