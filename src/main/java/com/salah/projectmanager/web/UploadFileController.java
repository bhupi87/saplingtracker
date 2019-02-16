package com.salah.projectmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
 
import com.salah.projectmanager.service.FileStorage;

/**
 * Created by Bhupendra.
 */
 
@Controller
public class UploadFileController {
	
	@Autowired
	FileStorage fileStorage;
	
/*    @GetMapping("/")
    public String index() {
        return "multipartfile/form.html";
    } 
    
    @PostMapping("/")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		try {
			fileStorage.store(file);
			model.addAttribute("message", "File uploaded successfully! -> filename = " + file.getOriginalFilename());
		} catch (Exception e) {
			model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
		}
        return "multipartfile/form.html";
    } */
    
    
}
