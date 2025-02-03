package redflower.pipeline.operation;

import org.springframework.stereotype.Component;

import redflower.pipeline.core.Context;
import redflower.pipeline.core.DefaultOperation;
import redflower.pipeline.core.Step;
import redflower.schema.OperationSchema;

@Component("if")
public class IfOperation extends DefaultOperation {


	@Override
	public void onRun(Step current, OperationSchema schema, Context context) {
		
	}


}
