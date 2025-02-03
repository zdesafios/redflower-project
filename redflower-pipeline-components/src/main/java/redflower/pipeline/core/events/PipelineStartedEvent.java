package redflower.pipeline.core.events;

import org.springframework.context.ApplicationEvent;

import redflower.schema.core.PipelineSchema;

public class PipelineStartedEvent extends ApplicationEvent {

	public PipelineStartedEvent(PipelineSchema schema) {
		super(schema);
	}
	
	@Override
	public PipelineSchema getSource() {
		return (PipelineSchema) super.getSource();
	}

}
