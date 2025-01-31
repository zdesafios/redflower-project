package br.com.experian.ln.builtin.schema.step;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.experian.ln.builtin.schema.StepSchema;
import br.com.experian.ln.builtin.schema.operation.Choose;
import lombok.Data;

@Data
public class FlowDecisionStepSchema extends StepSchema {
	
	@JsonProperty("source_type")
	String sourceType;
	
	@JsonProperty("source")
	String source;
	
	@JsonProperty("choose")
	Choose[] choose;
}
