package redflower.schema.step;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import redflower.schema.core.operation.OperationSchema;
import redflower.schema.core.step.StepSchema;

@Data
public class GenericStepSchema extends StepSchema {
	
	@JsonProperty("operations")
	List<OperationSchema> operations;
	
}
