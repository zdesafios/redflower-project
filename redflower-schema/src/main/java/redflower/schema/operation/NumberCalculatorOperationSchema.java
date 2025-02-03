package redflower.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.core.GetVarSchema;
import redflower.schema.core.PutVarResultSchema;
import redflower.schema.core.operation.OperationSchema;

@Data
public class NumberCalculatorOperationSchema extends OperationSchema {
	
	@JsonProperty("right")
	private GetVarSchema right;
	
	@JsonProperty("left")
	private GetVarSchema left;
	
	@JsonProperty("result")
	private PutVarResultSchema result;
}
