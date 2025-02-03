package redflower.api.web.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import redflower.domain.service.ProjectService;

@RestController
@RequestMapping(path = "/project")
@RequiredArgsConstructor
public class ProjectController {
	
	private final ProjectService projectService;
	
	@PostMapping
	public void createByFile(@RequestParam("file") MultipartFile file) throws IOException {
		projectService.createProjectWithJsonFile(file.getBytes());
	}
}
