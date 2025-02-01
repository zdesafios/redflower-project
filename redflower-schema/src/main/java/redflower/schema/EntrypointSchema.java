package redflower.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import redflower.schema.entrypoint.enums.EntrypointType;

@JsonTypeInfo( use = JsonTypeInfo.Id.DEDUCTION )
public abstract class EntrypointSchema {
	
	@JsonProperty("type")
	private EntrypointType type;
	
}
