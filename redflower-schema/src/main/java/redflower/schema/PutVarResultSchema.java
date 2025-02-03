package redflower.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.enums.VarScope;

@Data
public class PutVarResultSchema {
	
	@JsonProperty("scope")
	private VarScope scope;
	
	@JsonProperty("name")
	private String name;
	
}
