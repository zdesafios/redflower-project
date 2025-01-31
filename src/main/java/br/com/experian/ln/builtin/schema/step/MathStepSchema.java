package br.com.experian.ln.builtin.schema.step;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.experian.ln.builtin.schema.OperationSchema;
import br.com.experian.ln.builtin.schema.StepSchema;
import lombok.Data;

@Data
public class MathStepSchema extends StepSchema {
	
	@JsonProperty("operations")
	List<OperationSchema> operations;
	
	
	
	

}
