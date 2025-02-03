package redflower.pipeline.core.operation;

import redflower.pipeline.core.Context;
import redflower.pipeline.core.step.Step;
import redflower.schema.core.operation.OperationSchema;

public interface Operation {

	void run(Step current, OperationSchema operationSchema, Context context);
	
}
