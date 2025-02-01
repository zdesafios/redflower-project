package redflower.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectSchema {
	
	@NotBlank
	@JsonProperty("name")
	private String name; 
	
	@NotNull
	@JsonProperty("pipeline")
	private PipelineSchema pipelineSchema;
}
