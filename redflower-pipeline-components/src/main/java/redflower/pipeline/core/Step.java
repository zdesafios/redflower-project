package redflower.pipeline.core;

import redflower.schema.StepSchema;

public interface Step<T extends StepSchema> {
	
	void start(StepLocator steplocator, Context context, T schema);

	Object createVar(String name, Object value);

	Object getVarFromJsonData(String expression);

	Object getVar(String expression);
}
