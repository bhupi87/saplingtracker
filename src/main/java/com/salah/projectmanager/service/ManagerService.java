package com.salah.projectmanager.service;

import com.salah.projectmanager.domain.Client;
import com.salah.projectmanager.domain.Comment;
import com.salah.projectmanager.domain.FileInfo;
import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.Task;
import com.salah.projectmanager.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by bhupendra.
 */

public interface ManagerService {
    void addProject(Project project, String user);

    void updateProject(Project project, String user);

    void deleteProject(int idProject, String user);

    Task getTask(int idT, int idP, String user);

    void addTask(Task task, int idProject, String user);

    void updateTask(Task task, int idProject, String user);

    void deleteTask(int idTask, int idProject, String user);

    void approveTask(int idTask, String user);

    Map<String, Object> getStatistic(int idProject, String user);

    List<User> getCollaboratorsList();
    
    List<Comment> getComments(int idT);
    
    void addClient(Client client);
    
    void deleteClient(int idC);
    
    void updateClient(Client client);
    
}
