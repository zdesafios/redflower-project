package redflower.pipeline.operation;

import org.springframework.stereotype.Component;

import redflower.pipeline.core.Context;
import redflower.pipeline.core.DefaultOperation;
import redflower.pipeline.core.Step;
import redflower.schema.GetVarSchema;
import redflower.schema.OperationSchema;
import redflower.schema.operation.VariableAssignmentOperationSchema;

@Component("variableAssignment")
public class VariableAssignmentOperation extends DefaultOperation {
	

	@Override
	public void onRun(Step current, OperationSchema operationSchema, Context context) {
		VariableAssignmentOperationSchema schema = operationSchema.impl();
		
		if(schema.getScope().isGlobal()) {
			context.createVar(schema.getName(), getValue(current, schema, context));
		} else {
			current.createVar(schema.getName(), getValue(current, schema, context));
		}
	}
	
	private Object getValue(Step current, VariableAssignmentOperationSchema schema, Context context) {
		Object value = null;
		if(schema.getSourceType().isJsonData()) {
			value = getFromJsonData(current, schema, context);
		} else if(schema.getSourceType().isLiteral()) {
			value = schema.getValue();
			
		}
		
		return value;
	}
	
	private Object getFromJsonData(Step current, VariableAssignmentOperationSchema schema, Context context) {
		if(!schema.getScope().isGlobal()) {
			throw new RuntimeException("JSON DATA exists only in GLOBAL scope");
		}
		Object value = context.getVarFromJsonData(schema.getExpression());
		
		return value;
	}

}
