package redflower.schema.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.core.enums.VarScope;

@Data
public class PutVarResultSchema {
	
	@JsonProperty("scope")
	private VarScope scope;
	
	@JsonProperty("name")
	private String name;
	
}
