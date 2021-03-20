package io.agileintellligence.fullstack.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;
    @CreationTimestamp
    private Date created_At;
    @UpdateTimestamp
    private Date updated_At;

    public Project() {
    }
}
