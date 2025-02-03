package redflower.schema.step;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.OperationSchema;
import redflower.schema.StepSchema;

@Data
public class GenericStepSchema extends StepSchema {
	
	@JsonProperty("operations")
	List<OperationSchema> operations;
	
}
