package redflower.pipeline.operation;

import org.springframework.stereotype.Component;

import redflower.pipeline.core.Context;
import redflower.pipeline.core.operation.DefaultOperation;
import redflower.pipeline.core.step.Step;
import redflower.schema.core.operation.OperationSchema;
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
	
	// TODO Criar um componente pra acessar as variaveis locais, globais e json_data
	private Object getValue(Step current, VariableAssignmentOperationSchema schema, Context context) {
		Object value = null;
		if(schema.getSourceType().isJsonData()) {
			value = getFromJsonData(schema, context);
		} else if(schema.getSourceType().isLiteral()) {
			value = schema.getValue();
		} else if(schema.getSourceType().isVar()) {
			value = getFromVar(current, schema, context, schema.getName()); 
		}
		
		return value;
	}
	
	private Object getFromJsonData(VariableAssignmentOperationSchema schema, Context context) {
		if(!schema.getScope().isGlobal()) {
			throw new RuntimeException("JSON DATA exists only in GLOBAL scope");
		}
		return context.getVarFromJsonData(schema.getExpression());
	}
	
	private Object getFromVar(Step current, VariableAssignmentOperationSchema schema, Context context, String name) {
		Object value = null;
		if(schema.getScope().isGlobal()) {
			value = context.getVar(name);
		} else {
			value = current.getVar(name);
		}
		
		return value;
	}

}
