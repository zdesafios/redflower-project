package br.com.experian.ln.builtin.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NumberVarOperation {
	
	@JsonProperty("source_type")
	private String sourceType;
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("value")
	private String value;
}
