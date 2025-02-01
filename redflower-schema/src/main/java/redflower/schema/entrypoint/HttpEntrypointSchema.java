package redflower.schema.entrypoint;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.EntrypointSchema;
import redflower.schema.entrypoint.enums.EntrypointMethod;

@Data
public class HttpEntrypointSchema extends EntrypointSchema {
	
	@JsonProperty("path")
	private String path;
	
	@JsonProperty("method")
	private EntrypointMethod method;
	
}
