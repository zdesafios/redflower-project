package redflower.pipeline.core.step;

import lombok.RequiredArgsConstructor;
import redflower.pipeline.core.Context;
import redflower.pipeline.core.StepLocator;
import redflower.pipeline.core.events.StepStartedEvent;
import redflower.schema.core.step.StepSchema;

@RequiredArgsConstructor
public abstract class DefaultStep<T extends StepSchema> implements Step<T> {
	
	@Override
	public void start(StepLocator stepLocator, Context context, T schema) {
		context.publish(new StepStartedEvent(schema));
		onStart(context, schema);
		if(schema.hasNext()) {
			Step nextStep = stepLocator.getStep(schema.getNext());
			StepSchema nextStepSchema = stepLocator.getStepSchema(schema.getNext());
			nextStep.start(stepLocator, context, nextStepSchema);
		}
	}
	
	public abstract void onStart(Context context, T schema);
	
	@Override
	public Object createVar(String name, Object value) {
		return null;
	}
	
	@Override
	public Object getVar(String expression) {
		return null;
	}
	
	@Override
	public Object getVarFromJsonData(String expression) {
		return null;
	}
	
	
	
}
