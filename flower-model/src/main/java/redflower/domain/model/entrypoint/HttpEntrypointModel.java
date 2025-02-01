package redflower.domain.model.entrypoint;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.domain.model.EntrypointModel;
import redflower.domain.model.entrypoint.enums.EntrypointMethod;

@Data
public class HttpEntrypointModel extends EntrypointModel {
	
	@JsonProperty("path")
	private String path;
	
	@JsonProperty("method")
	private EntrypointMethod method;
	
}
