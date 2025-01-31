package br.com.experian.ln.builtin.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.experian.ln.builtin.schema.OperationSchema;
import lombok.Data;

@Data
public class NumberMultiplyOperationSchema extends OperationSchema {
	@JsonProperty("right")
	NumberVarOperation right;
	
	@JsonProperty("left")
	NumberVarOperation left;
	
	@JsonProperty("assignment")
	AssignmentResultOperation assignment;
}
