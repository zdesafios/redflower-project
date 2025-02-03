package redflower.schema.core;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import redflower.schema.core.enums.PipelineType;
import redflower.schema.core.step.StepSchema;

@Data
public class PipelineSchema {

	@Valid
	@NotNull(message = "")
	@JsonProperty("type")
	private PipelineType type;
	
	@JsonProperty("data_json")
	private String data;
	
	@NotNull
	@NotEmpty
	@JsonProperty("start")
	private String start;
	
	@NotNull(message = "")
	@NotEmpty(message = "")
	@JsonProperty("steps")
	private List<redflower.schema.core.step.StepSchema> steps;

	public StepSchema getSchemaForStart() {
		return getSchemaForName(start);
	}
	
	public StepSchema getSchemaForName(String name) {
		return steps.stream()
				.filter(s-> s.getName().equals(name))
				.findFirst()
				.orElseThrow(()-> new RuntimeException("step %s not defined".formatted(start)));
	}
	
}
