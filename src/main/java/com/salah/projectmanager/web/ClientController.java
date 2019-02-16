package com.salah.projectmanager.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salah.projectmanager.domain.Client;
import com.salah.projectmanager.domain.FileInfo;
import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.ProjectState;
import com.salah.projectmanager.service.CollaboratorService;
import com.salah.projectmanager.service.ManagerService;

/**
 * Created by bhupendra.
 */
@Controller
@RequestMapping(value = "/client")
public class ClientController {
	
    @Autowired
    private CollaboratorService collaboratorService;
    
    @Autowired
    private ManagerService managerService;
	
	
	   @RequestMapping(value = "", method = RequestMethod.GET)
	    public String index(Model model, Principal principal) {
	        model.addAttribute("title", "Clients");
	        model.addAttribute("clients", collaboratorService.getAllClients());
	        return "client/index";
	    }

	    @RequestMapping(value = "new", method = RequestMethod.GET)
	    public String newGet(Model model) {
	        model.addAttribute("title", "Add Client");
	        model.addAttribute("client", new Client());	       
	        return "client/form";
	    }
	    
	    @RequestMapping(value = "new", method = RequestMethod.POST)
	    public String newPost(Model model, @ModelAttribute @Valid Client client, Errors errors) {
	        if (errors.hasErrors()) {
	            model.addAttribute("title", "Add client");
	            model.addAttribute("error", "error");	           
	            return "client/form";
	        }	        
	        managerService.addClient(client);   	        
	        return "redirect:/client/new?success";
	    }
	    
	    @RequestMapping(value = "{id}/delete")
	    private String delete(@PathVariable String id, Principal principal) {
	        managerService.deleteClient(Integer.parseInt(id));
	        return "redirect:/client";
	    }

}
