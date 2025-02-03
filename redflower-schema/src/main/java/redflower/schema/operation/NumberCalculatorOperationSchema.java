package redflower.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.PutVarResultSchema;
import redflower.schema.OperationSchema;
import redflower.schema.GetVarSchema;

@Data
public class NumberCalculatorOperationSchema extends OperationSchema {
	
	@JsonProperty("right")
	private GetVarSchema right;
	
	@JsonProperty("left")
	private GetVarSchema left;
	
	@JsonProperty("result")
	private PutVarResultSchema result;
}
