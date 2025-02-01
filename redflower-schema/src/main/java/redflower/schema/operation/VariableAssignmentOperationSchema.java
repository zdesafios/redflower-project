package redflower.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.OperationSchema;

@Data
public class VariableAssignmentOperationSchema extends OperationSchema {

	@JsonProperty("final")
	Boolean makeFinal = Boolean.FALSE;
	
	@JsonProperty("scope")
	String scope;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("source_type")
	String sourceType;
	
	@JsonProperty("source")
	String source;
	
	@JsonProperty("value")
	String value;
}
