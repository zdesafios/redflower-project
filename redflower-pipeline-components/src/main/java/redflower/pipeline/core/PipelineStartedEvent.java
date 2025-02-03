package redflower.pipeline.core;

import org.springframework.context.ApplicationEvent;

import redflower.schema.PipelineSchema;

public class PipelineStartedEvent extends ApplicationEvent {

	public PipelineStartedEvent(PipelineSchema schema) {
		super(schema);
	}
	
	@Override
	public PipelineSchema getSource() {
		return (PipelineSchema) super.getSource();
	}

}
