package redflower.pipeline.core;

import org.springframework.context.ApplicationEvent;

import redflower.schema.StepSchema;

public class StepStartedEvent extends ApplicationEvent {

	public StepStartedEvent(StepSchema schema) {
		super(schema);
	}

	@Override
	public StepSchema getSource() {
		return (StepSchema) super.getSource();
	}

}
