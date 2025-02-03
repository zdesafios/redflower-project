package redflower.schema.step;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.core.step.StepSchema;

@Data
public class FlowDecisionStepSchema extends StepSchema {
	
	@JsonProperty("source_type")
	private String sourceType;
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("choose")
	private DecisionChooseSchema[] choose;
}
