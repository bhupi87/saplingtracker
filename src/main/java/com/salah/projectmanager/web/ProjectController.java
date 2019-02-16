package com.salah.projectmanager.web;

import com.salah.projectmanager.domain.Client;
import com.salah.projectmanager.domain.FileInfo;
import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.ProjectState;
import com.salah.projectmanager.repo.ClientRepository;
import com.salah.projectmanager.service.CollaboratorService;
import com.salah.projectmanager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.salah.projectmanager.service.FileStorage;

/**
 * Created by bhupendra.
 */
@Controller
@RequestMapping(value = "/projects")
public class ProjectController {

    @Autowired
    private CollaboratorService collaboratorService;
    
	@Autowired
	FileStorage fileStorage;

    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {
        model.addAttribute("title", "Projects");
        model.addAttribute("projects", collaboratorService.getAllProjects(principal.getName()));
        return "projects/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newGet(Model model) {
        model.addAttribute("title", "Add project");
        model.addAttribute("project", new Project());
        model.addAttribute("projectState", ProjectState.values());
        model.addAttribute("clients", clientRepository.findAll());
        return "projects/form";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newPost(@RequestParam("file") MultipartFile file,Model model, @ModelAttribute @Valid Project project, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add project");
            model.addAttribute("error", "error");
            model.addAttribute("projectState", ProjectState.values());             
            return "projects/form";
        }
        project.setCover("cover.jpg");
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilename(file.getOriginalFilename().toString());        
        List<FileInfo> fileInfos = new ArrayList<>();
        fileInfos.add(fileInfo);
        project.setFileInfo(fileInfos);
        managerService.addProject(project ,principal.getName());   
        fileStorage.store(file);
        return "redirect:/projects/new?success";
    }

    @RequestMapping(value = "{id}/delete")
    private String delete(@PathVariable String id, Principal principal) {
        managerService.deleteProject(Integer.parseInt(id), principal.getName());
        return "redirect:/projects";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    private String updateGet(Model model, @PathVariable String id, Principal principal){
        model.addAttribute("title", "Add project");
        model.addAttribute("project", collaboratorService.getProjectWithTasks(Integer.parseInt(id), principal.getName()));
        model.addAttribute("projectState", ProjectState.values());
        model.addAttribute("clients",collaboratorService.getProjectWithTasks(Integer.parseInt(id), principal.getName()).getClient());
        return "projects/form";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.POST)
    public String updatePost(@RequestParam("file") MultipartFile file,Model model, @PathVariable String id, @ModelAttribute @Valid Project project, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit project");
            model.addAttribute("error", "error");
            return "projects/form";
        }
        project.setCover("cover.jpg");
        project.setId(Integer.parseInt(id));
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilename(file.getOriginalFilename().toString());        
        List<FileInfo> fileInfos = new ArrayList<>();
        fileInfos.add(fileInfo);
        project.setFileInfo(fileInfos);
        managerService.updateProject(project ,principal.getName());
        fileStorage.store(file);
        return "redirect:/projects/new?successEdit";
    }

    @RequestMapping(value = "{id}/statistic", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> statistic(@PathVariable String id, Principal principal) {
        return managerService.getStatistic(Integer.parseInt(id), principal.getName());
    }

    @RequestMapping(value = "{id}")
    public String home (@PathVariable String id, Principal principal, Model model) {
        Project p = collaboratorService.getProjectWithTasks(Integer.parseInt(id), principal.getName());
        model.addAttribute("title", "Project Manager | "+p.getName());
        model.addAttribute("project", p);
        return "projects/home";
    }
}
