package redflower.pipeline.core.operation;

import redflower.pipeline.core.Context;
import redflower.pipeline.core.step.Step;
import redflower.schema.core.operation.OperationSchema;

public abstract class DefaultOperation implements Operation {
	
	@Override
	public void run(Step current, OperationSchema schema, Context context) {
		onRun(current, schema, context);
	}
	
	public abstract void onRun(Step current, OperationSchema schema, Context context);

}
