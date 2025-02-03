package redflower.api.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import redflower.api.listener.ProjectStartedEvent;
import redflower.api.service.ProjectService;
import redflower.pipeline.core.Context;
import redflower.pipeline.core.PipelineExecutor;
import redflower.schema.core.PipelineSchema;
import redflower.schema.core.ProjectSchema;

@RestController
@RequestMapping("/start/project")
@RequiredArgsConstructor
public class ProjectStarterController {
	
	private final ProjectService projectService;

	private final PipelineExecutor pipelineExecutor;
	
	private final Context context;
	
	@PostMapping(path = "/{id}")
	public Object postStart(@PathVariable("id") String id) {
		ProjectSchema projectSchema = projectService.getById(id);
		PipelineSchema pipelineSchema = projectSchema.getPipelineSchema();
		context.setJsonData(pipelineSchema.getData());
		context.publish(new ProjectStartedEvent(projectSchema));
		return pipelineExecutor.execute(pipelineSchema, context);
	}

	
}
