package com.salah.projectmanager.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salah.projectmanager.domain.TaskState;
import com.salah.projectmanager.service.CollaboratorService;
import com.salah.projectmanager.service.ManagerService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
    @Autowired
    private CollaboratorService collaboratorService;
    
    @Autowired
    private ManagerService managerService;
    
    
    @RequestMapping(value = "{id}/task/{idT}/view", method = RequestMethod.GET)
    public String viewTaskGet(Model model, @PathVariable String id, @PathVariable String idT, Principal principal) {
        model.addAttribute("title", "Edit task");
        model.addAttribute("task", managerService.getTask(Integer.parseInt(idT),Integer.parseInt(id),principal.getName()));
        model.addAttribute("taskState", TaskState.values());
        model.addAttribute("projectId", id);
        model.addAttribute("users", managerService.getCollaboratorsList());
        return "comment/taskdetail";
    }
    
    @RequestMapping(value = "{id}/task/{idT}/createcomment", method = RequestMethod.GET)
    public String createComment(Model model, @PathVariable String id, @PathVariable String idT, Principal principal) {
        model.addAttribute("title", "Comments");
        model.addAttribute("task", managerService.getTask(Integer.parseInt(idT),Integer.parseInt(id),principal.getName()));
        model.addAttribute("taskState", TaskState.values());
        model.addAttribute("projectId", id);
        model.addAttribute("users", managerService.getCollaboratorsList());
        model.addAttribute("comment", managerService.getComments(Integer.parseInt(idT)));
        return "comment/showcomment";
    }
	

}
