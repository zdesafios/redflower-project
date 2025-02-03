package redflower.api.listener;

import org.springframework.context.ApplicationEvent;

import redflower.schema.ProjectSchema;

public class ProjectStartedEvent extends ApplicationEvent {

	public ProjectStartedEvent(ProjectSchema schema) {
		super(schema);
	}
	
	@Override
	public ProjectSchema getSource() {
		return (ProjectSchema) super.getSource();
	}

}
