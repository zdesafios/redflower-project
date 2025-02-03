package redflower.pipeline.operation;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import redflower.pipeline.core.NumberCalculateOperationDalegate;
import redflower.pipeline.core.CalulatorStrategy;
import redflower.pipeline.core.Context;
import redflower.pipeline.core.operation.DefaultOperation;
import redflower.pipeline.core.step.Step;
import redflower.schema.core.PutVarResultSchema;
import redflower.schema.core.operation.OperationSchema;
import redflower.schema.operation.NumberCalculatorOperationSchema;

@Component("numberSubtract")
@RequiredArgsConstructor
public final class NumberSubtractOperation extends DefaultOperation {
	
	private final NumberCalculateOperationDalegate calculateDelegate;

	@Override
	public void onRun(Step stepCurrent, OperationSchema operationSchema, Context context) {
		Number result = calculateDelegate.onRun(stepCurrent, operationSchema, context, new SubtractCalculatorStrategy());
		
		NumberCalculatorOperationSchema schema = operationSchema.impl();
		PutVarResultSchema resultSchema = schema.getResult();
		multiplyResult(stepCurrent, resultSchema, result, context);
	}
	
	private void multiplyResult(Step stepCurrent, PutVarResultSchema resultSchema, Number result, Context context) {
		if(resultSchema.getScope().isGlobal()) {
			context.createVar(resultSchema.getName(), result);
		} else {
			stepCurrent.createVar(resultSchema.getName(), result);
		}
	}
}

 class SubtractCalculatorStrategy implements CalulatorStrategy {

	@Override
	public Number calculate(Number right, Number left) {
		if(null == right || null == left) {
			return null;
		}
		
		return right.doubleValue() * left.doubleValue();
	}
	 
 }
