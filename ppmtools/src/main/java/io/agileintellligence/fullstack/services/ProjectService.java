package io.agileintellligence.fullstack.services;

import io.agileintellligence.fullstack.domain.Project;
import io.agileintellligence.fullstack.exceptions.ProjectIdException;
import io.agileintellligence.fullstack.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public Project findByProjectIdentifier(String projectId)
{
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    if(project == null)
    {
        throw new ProjectIdException("Project ID " + projectId + " Does not exist");
    }
    return project;
}
public Iterable<Project> findAllProjects()
{
    return  projectRepository.findAll();
}
public void deleteProjectByProjectIdentifier(String projectId)
{
    Project project = projectRepository.findByProjectIdentifier(projectId);
    if(project == null)
    {
        throw new ProjectIdException("Project ID " + projectId + " does not exist");
    }
    projectRepository.delete(project);
//    return "Project " + projectId + "has been deleted SuccessFully";
}

}
