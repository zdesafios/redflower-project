package redflower.schema.step;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DecisionChooseSchema {
	
	@JsonProperty("case")
	private String chooseCase;
	
	@JsonProperty("next")
	private String next;
}
