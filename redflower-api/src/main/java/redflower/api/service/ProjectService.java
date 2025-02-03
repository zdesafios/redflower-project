package redflower.api.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import redflower.api.repository.ProjectRepository;
import redflower.pipeline.core.exceptions.ProjectNotfoundException;
import redflower.schema.core.EntrypointSchema;
import redflower.schema.core.GetVarSchema;
import redflower.schema.core.PipelineSchema;
import redflower.schema.core.ProjectSchema;
import redflower.schema.core.operation.OperationSchema;
import redflower.schema.core.step.StepSchema;
import redflower.schema.entrypoint.HttpEntrypointSchema;
import redflower.schema.operation.NumberCalculatorOperationSchema;
import redflower.schema.operation.VariableAssignmentOperationSchema;
import redflower.schema.step.FlowDecisionStepSchema;
import redflower.schema.step.GenericStepSchema;
import redflower.schema.step.PipelineResultSchema;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectRepository projectRepository;

	public String createProjectWithJsonFile(byte[] jsonContent) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerSubtypes(PipelineSchema.class, StepSchema.class, GenericStepSchema.class,
				OperationSchema.class, VariableAssignmentOperationSchema.class, NumberCalculatorOperationSchema.class,
				FlowDecisionStepSchema.class, EntrypointSchema.class, HttpEntrypointSchema.class, GetVarSchema.class, PipelineResultSchema.class);
		try {
			ProjectSchema projectSchema = objectMapper.readValue(jsonContent, ProjectSchema.class);
			projectRepository.save(projectSchema);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ProjectSchema getById(String id) {
		return projectRepository.findById(id).orElseThrow(()-> new ProjectNotfoundException(id));
	}

}
