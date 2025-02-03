package redflower.schema.core.step;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonTypeInfo( use = JsonTypeInfo.Id.DEDUCTION )
public abstract class StepSchema {
	
	@NotNull
	@JsonProperty("type")
	String type;
	
	@NotNull
	@JsonProperty("name")
	String name;
	
	@NotNull
	@NotBlank
	@JsonProperty("next")
	private String next;

	public boolean hasNext() {
		return !next.equalsIgnoreCase(".end");
	}

}
