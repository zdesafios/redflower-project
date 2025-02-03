package redflower.pipeline.operation;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import redflower.pipeline.core.CalculateStepDalegate;
import redflower.pipeline.core.CalulatorStrategy;
import redflower.pipeline.core.Context;
import redflower.pipeline.core.DefaultOperation;
import redflower.pipeline.core.Step;
import redflower.schema.OperationSchema;
import redflower.schema.PutVarResultSchema;
import redflower.schema.operation.NumberCalculatorOperationSchema;

@Component("numberMultiply")
@RequiredArgsConstructor
public final class NumberMultiplyOperation extends DefaultOperation {
	
	private final CalculateStepDalegate calculateDelegate;

	@Override
	public void onRun(Step stepCurrent, OperationSchema operationSchema, Context context) {
		Number result = calculateDelegate.calculate(stepCurrent, operationSchema, context, new MultiplyCalculatorStrategy());
		
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

 class MultiplyCalculatorStrategy implements CalulatorStrategy {

	@Override
	public Number calculate(Number right, Number left) {
		if(null == right || null == left) {
			return null;
		}
		
		return right.doubleValue() * left.doubleValue();
	}
	 
 }
