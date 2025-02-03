package redflower.pipeline.core;

import org.springframework.stereotype.Component;

import redflower.schema.GetVarSchema;
import redflower.schema.OperationSchema;
import redflower.schema.operation.NumberCalculatorOperationSchema;

@Component
public class CalculateStepDalegate {
	
	public Number calculate(Step stepCurrent, OperationSchema operationSchema, Context context, CalulatorStrategy strategy) {
		NumberCalculatorOperationSchema schema = operationSchema.impl();
		GetVarSchema right = schema.getRight();
		GetVarSchema left = schema.getLeft();
		
		Number valueRight = getValue(stepCurrent, right, context);
		Number valueLeft = getValue(stepCurrent, left, context);
		
		return strategy.calculate(valueRight, valueLeft);
		
	}
	
	private Number getValueAsNumber(Object value) {
		return switch (value) {
			case Integer v -> v;
			case Double v -> v;
			default -> null;
		};
	}
	
	private Number getValue(Step current, GetVarSchema schema, Context context) {
		Object value = null;
		if(schema.getSourceType().isJsonData()) {
			value = getFromJsonData(current, schema, context, schema.getExpression());
		} else if(schema.getSourceType().isLiteral()) {
			
		} else if(schema.getSourceType().isVar()) {
			value = getFromVar(current, schema, context, schema.getName());
		}
		
		return getValueAsNumber(value);
	}
	
	//TODO melhorar o reuso, pois na operação VariableAssingnm também usa
	private Object getFromJsonData(Step current, GetVarSchema schema, Context context, String expression) {
		if(!schema.getScope().isGlobal()) {
			throw new RuntimeException("JSON DATA exists only in GLOBAL scope");
		}
		Object value = context.getVarFromJsonData(expression);
		
		return value;
	}
	
	private Object getFromVar(Step current, GetVarSchema schema, Context context, String name) {
		Object value = null;
		if(schema.getScope().isGlobal()) {
			value = context.getVar(name);
		} else {
			value = current.getVar(name);
		}
		
		return value;
	}
}
