package redflower.api.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redflower.pipeline.core.PipelineFinishEvent;
import redflower.pipeline.core.PipelineStartedEvent;
import redflower.pipeline.core.StepStartedEvent;

@Slf4j
@Component
public class PipelineEventListener {

	@EventListener(classes = {StepStartedEvent.class})
	public void stepStartedEvent(StepStartedEvent startedEvent) {
		log.info("[Started Step]: {} foi iniciado: ", startedEvent.getSource().getName());
	}
	
	@EventListener(classes = {PipelineStartedEvent.class})
	public void pipelineStartedEvent(PipelineStartedEvent startedEvent) {
		log.info("[Started Pipeline]: foi iniciada: ");
	}
	
	@EventListener(classes = {PipelineFinishEvent.class})
	public void pipelineFinishEvent(PipelineFinishEvent startedEvent) {
		log.info("[Finish Pipeline]: foi concluído: ");
	}
	
	@EventListener(classes = {ProjectStartedEvent.class})
	public void projectStartedEvent(ProjectStartedEvent startedEvent) {
		log.info("[Start Project]: {} foi iniciado ", startedEvent.getSource().getName());
	}
	
}
