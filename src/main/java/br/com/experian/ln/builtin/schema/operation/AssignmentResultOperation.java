package br.com.experian.ln.builtin.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AssignmentResultOperation {
	
	@JsonProperty("type")
	String type;
	
	@JsonProperty("name")
	String name;
	
}
