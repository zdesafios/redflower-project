package redflower.domain.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import redflower.domain.repository.ProjectRepository;
import redflower.schema.EntrypointSchema;
import redflower.schema.OperationSchema;
import redflower.schema.PipelineSchema;
import redflower.schema.ProjectSchema;
import redflower.schema.StepSchema;
import redflower.schema.GetVarSchema;
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
