package redflower.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.domain.model.enums.SourceType;
import redflower.domain.model.enums.VarScope;

@Data
public class GetVarModel {
	
	@JsonProperty("scope")
	private VarScope scope;
	
	@JsonProperty("source_type")
	private SourceType sourceType;
	
	@JsonProperty("name")
	private String name;
}
