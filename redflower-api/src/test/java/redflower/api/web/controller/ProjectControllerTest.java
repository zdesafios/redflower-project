package redflower.api.web.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import redflower.api.repository.ProjectRepository;
import redflower.api.service.ProjectService;
import redflower.schema.core.ProjectSchema;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={ProjectController.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class ProjectControllerTest {

	@InjectMocks
	ProjectController projectController;
	
	@MockitoBean
	ProjectService projectService;
	
	@MockitoBean
	ProjectRepository projectRepository;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
		ReflectionTestUtils.setField(projectService, "projectRepository", projectRepository);
		ReflectionTestUtils.setField(projectController, "projectService", projectService);
	}
	
	@Test
	void createWithError() throws Exception {
		
		ProjectSchema projectSchema = new ProjectSchema();
		projectSchema.setId(UUID.randomUUID().toString());
		
		when(projectRepository.save(Mockito.any())).thenReturn(projectSchema);
		
		when(projectService.createProjectWithJsonFile(Mockito.any(byte[].class))).thenCallRealMethod();
		
		Path resourceDirectory = Paths.get("src","test","resources", "calculos_project.json");
		byte[] bytes = Files.readAllBytes(resourceDirectory);
		
		mockMvc.perform(multipart("/project")
				.file("file",  bytes))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isCreated());
	}
	
}
