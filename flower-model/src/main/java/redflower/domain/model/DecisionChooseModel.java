package redflower.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DecisionChooseModel {
	
	@JsonProperty("case")
	private String chooseCase;
	
	@JsonProperty("next")
	private String next;
}
