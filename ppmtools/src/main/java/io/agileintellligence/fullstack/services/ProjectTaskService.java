package io.agileintellligence.fullstack.services;

import io.agileintellligence.fullstack.domain.Backlog;
import io.agileintellligence.fullstack.domain.Project;
import io.agileintellligence.fullstack.domain.ProjectTask;
import io.agileintellligence.fullstack.exceptions.ProjectNotFoundException;
import io.agileintellligence.fullstack.repositories.BacklogRepository;
import io.agileintellligence.fullstack.repositories.ProjectRepository;
import io.agileintellligence.fullstack.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {


    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        //Exceptions: Project not found
        try {

            //PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            //set the bl to pt
            projectTask.setBacklog(backlog);
            //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
            Integer BacklogSequence = backlog.getPTSequence();
            // Update the BL SEQUENCE
            BacklogSequence++;
            backlog.setPTSequence(BacklogSequence);
            //Add Sequence to Project Task
            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            //INITIAL priority when priority null
            if (projectTask.getPriority()==0||projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            //INITIAL status when status is null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project Not Found");
        }
    }

    public Iterable<ProjectTask> findByBacklogId(String backlog_id) {
        Project project = projectRepository.findByProjectIdentifier(backlog_id);
        if (project == null) {
            throw new ProjectNotFoundException("Project with id:" + backlog_id + " does not exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findByProjectSequence(String backlog_id, String sequence) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project Task with id:" + backlog_id + " does not exists");
        }
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task id:" + sequence + " does not exist");
        }
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project Task id:" + sequence + " does not exist in the project" + backlog_id);
        }
        return projectTask;
    }


    public ProjectTask updateProject(ProjectTask updatedProject, String backlog_id, String sequence) {
        ProjectTask projectTask1 = findByProjectSequence(backlog_id, sequence);

        projectTask1 = updatedProject;
        return projectTaskRepository.save(projectTask1);
    }
    public  void deleteProject(String backlog_id, String sequence)
    {
        ProjectTask projectTask1 = findByProjectSequence(backlog_id, sequence);
        projectTaskRepository.delete(projectTask1);
    }

}