package io.agileintellligence.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Project Name Required")
    private String projectName;
    @NotBlank(message="Project Identifier Required")
    @Size(min=4,max=5,message="Please USe 4-5 Characters")
    @Column(updatable = false,unique = true)
    private String projectIdentifier;
    @NotBlank(message="Project Description Required")
    private String description;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date startDate;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date endDate;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-mm-dd")
    @Column(updatable =false)
    private Date created_At;
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updated_At;

    @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="project")
    private Backlog backlog;

    public Project() {
    }
}
