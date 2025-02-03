package redflower.pipeline.core.events;

import org.springframework.context.ApplicationEvent;

import redflower.schema.core.step.StepSchema;

public class StepStartedEvent extends ApplicationEvent {

	public StepStartedEvent(StepSchema schema) {
		super(schema);
	}

	@Override
	public StepSchema getSource() {
		return (StepSchema) super.getSource();
	}

}
