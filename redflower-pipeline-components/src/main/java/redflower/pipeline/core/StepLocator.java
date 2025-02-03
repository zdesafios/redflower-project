package redflower.pipeline.core;

import org.springframework.beans.factory.BeanFactory;

import lombok.RequiredArgsConstructor;
import redflower.pipeline.core.step.Step;
import redflower.schema.core.PipelineSchema;
import redflower.schema.core.step.StepSchema;

@RequiredArgsConstructor
public class StepLocator {
	
	private final BeanFactory beanFactory;
	private final PipelineSchema pipelineSchema;

	public Step getStep(String name) {
		StepSchema schema = getStepSchema(name);
		return beanFactory.getBean(schema.getType(), Step.class);
	}

	public StepSchema getStepSchema(String name) {
		return pipelineSchema.getSchemaForName(name);
	}

	public StepSchema getStartStepSchema() {
		return getStepSchema(pipelineSchema.getStart());
	}

	public Step getStartStep() {
		return getStep(pipelineSchema.getStart());
	}

}
