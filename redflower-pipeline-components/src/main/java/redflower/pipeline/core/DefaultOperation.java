package redflower.pipeline.core;

import redflower.schema.OperationSchema;

public abstract class DefaultOperation implements Operation {
	
	@Override
	public void run(Step current, OperationSchema schema, Context context) {
		onRun(current, schema, context);
	}
	
	public abstract void onRun(Step current, OperationSchema schema, Context context);

}
