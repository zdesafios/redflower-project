package br.com.experian.ln.builtin.schema;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PipelineSchema {

	@NotNull
	@JsonProperty("type")
	private PipelineType type;
	
	@JsonProperty("data_json")
	private Object data;
	
	@NotNull
	@NotEmpty
	@JsonProperty("steps")
	private List<StepSchema> steps;
	
}
