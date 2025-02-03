package redflower.schema.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.core.enums.SourceType;
import redflower.schema.core.enums.VarScope;

@Data
public class GetVarSchema {
	
	@JsonProperty("scope")
	private VarScope scope;
	
	@JsonProperty("source_type")
	private SourceType sourceType;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("value")
	private Object value;
	
	@JsonProperty("expression")
	private String expression;
}
