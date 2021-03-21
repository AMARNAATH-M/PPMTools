package io.agileintellligence.fullstack.services;

import io.agileintellligence.fullstack.domain.Project;
import io.agileintellligence.fullstack.exceptions.ProjectIdException;
import io.agileintellligence.fullstack.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

public Project saveOrUpdateProject(Project project)
{
   try
   {
       project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
       return projectRepository.save(project);
   }catch(Exception e)
   {
       throw new ProjectIdException("PROJECT ID " + project.getProjectIdentifier().toUpperCase() + " already exists");
   }

}

}
