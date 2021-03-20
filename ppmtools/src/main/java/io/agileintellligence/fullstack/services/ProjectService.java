package io.agileintellligence.fullstack.services;

import io.agileintellligence.fullstack.domain.Project;
import io.agileintellligence.fullstack.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

public Project saveOrUpdateProject(Project project)
{
   return projectRepository.save(project);
}

}
