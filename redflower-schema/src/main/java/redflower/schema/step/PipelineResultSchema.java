package redflower.schema.step;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.core.step.StepSchema;

@Data
public class PipelineResultSchema extends StepSchema {
	
	@JsonProperty("vars")
	private List<String> vars;
}
