package redflower.schema.core.operation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@JsonTypeInfo( use = JsonTypeInfo.Id.DEDUCTION )
@JsonInclude(value = Include.NON_NULL)
public class OperationSchema {
	
	@JsonProperty("operation")
	private String operation;

	public <T extends OperationSchema>  T impl() {
		return (T) this;
	} 
	
}
