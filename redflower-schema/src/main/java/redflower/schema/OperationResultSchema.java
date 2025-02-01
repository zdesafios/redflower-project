package redflower.schema;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Data
public class OperationResultSchema {
	
	@JsonUnwrapped
	private GetVarSchema varSchema;
	
}
