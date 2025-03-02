package redflower.schema.operation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.core.enums.SourceType;
import redflower.schema.core.enums.VarScope;
import redflower.schema.core.operation.OperationSchema;

@Data
public class VariableAssignmentOperationSchema extends OperationSchema {

	@JsonProperty("final")
	private Boolean makeFinal = Boolean.FALSE;
	
	@JsonProperty("scope")
	private VarScope scope;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("source_type")
	private SourceType sourceType;
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("expression")
	private String expression;
}
