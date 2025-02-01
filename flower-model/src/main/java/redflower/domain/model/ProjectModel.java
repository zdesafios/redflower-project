package redflower.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectModel {
	
	@NotBlank
	@JsonProperty("name")
	private String name; 
	
	@NotNull
	@JsonProperty("pipeline")
	private PipelineModel pipelineSchema;
}
