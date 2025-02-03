package redflower.api.web.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import redflower.api.service.ProjectService;

@RestController
@RequestMapping(path = "/project")
@RequiredArgsConstructor
public class ProjectController {
	
	private final ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<Map<String, String>> createByFile(@RequestParam("file") MultipartFile file) throws IOException {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProjectWithJsonFile(file.getBytes()));
	}
}
