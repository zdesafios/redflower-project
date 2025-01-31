package br.com.experian.ln.builtin.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Choose {
	
	@JsonProperty("case")
	private String chooseCase;
	
	@JsonProperty("next")
	private String next;
}
