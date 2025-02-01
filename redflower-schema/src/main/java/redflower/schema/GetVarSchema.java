package redflower.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.enums.SourceType;
import redflower.schema.enums.VarScope;

@Data
public class GetVarSchema {
	
	@JsonProperty("scope")
	private VarScope scope;
	
	@JsonProperty("source_type")
	private SourceType sourceType;
	
	@JsonProperty("name")
	private String name;
}
