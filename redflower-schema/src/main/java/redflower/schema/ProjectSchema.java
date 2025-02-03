package redflower.schema;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document(collection = "projects")
public class ProjectSchema {
	
	@Id
	private String id;
	
	@NotBlank
	@JsonProperty("name")
	private String name; 
	
	@NotNull
	@JsonProperty("pipeline")
	private PipelineSchema pipelineSchema;
}
