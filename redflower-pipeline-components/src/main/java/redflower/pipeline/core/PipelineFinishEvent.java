package redflower.pipeline.core;

import org.springframework.context.ApplicationEvent;

import redflower.schema.PipelineSchema;

public class PipelineFinishEvent extends ApplicationEvent {

	public PipelineFinishEvent(PipelineSchema schema) {
		super(schema);
	}
	
	@Override
	public PipelineSchema getSource() {
		return (PipelineSchema) super.getSource();
	}

}
