package br.com.experian.ln.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/project")
@RequiredArgsConstructor
public class ProjectController {
	
	
	@PostMapping
	public void create(@RequestParam("project") MultipartFile project) {
		
	}

}
