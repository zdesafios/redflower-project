package redflower.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import redflower.domain.model.enums.PipelineType;

@Data
public class PipelineModel {

	@Valid
	@NotNull(message = "")
	@JsonProperty("type")
	private PipelineType type;
	
	@JsonProperty("data_json")
	private Object data;
	
	@Valid
	@NotNull
	@JsonProperty("entrypoint")
	private EntrypointModel entrypoint;
	
	@NotNull
	@NotEmpty
	@JsonProperty("start")
	private String start;
	
	@NotNull(message = "")
	@NotEmpty(message = "")
	@JsonProperty("steps")
	private List<@Valid StepModel> steps;
	
}
