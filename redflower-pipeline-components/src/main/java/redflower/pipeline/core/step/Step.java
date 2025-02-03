package redflower.pipeline.core.step;

import redflower.pipeline.core.Context;
import redflower.pipeline.core.StepLocator;
import redflower.schema.core.step.StepSchema;

public interface Step<T extends StepSchema> {
	
	void start(StepLocator steplocator, Context context, T schema);

	Object createVar(String name, Object value);

	Object getVarFromJsonData(String expression);

	Object getVar(String expression);
}
