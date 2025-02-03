package redflower.pipeline.core;

import redflower.schema.OperationSchema;

public interface Operation {

	void run(Step current, OperationSchema operationSchema, Context context);
	
}
