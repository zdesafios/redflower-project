package redflower.pipeline.core;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redflower.pipeline.core.events.PipelineFinishEvent;
import redflower.pipeline.core.events.PipelineStartedEvent;
import redflower.pipeline.core.step.Step;
import redflower.schema.core.PipelineSchema;
import redflower.schema.core.step.StepSchema;

@Slf4j
@Service
@RequiredArgsConstructor
public class PipelineExecutor {
	
	private final BeanFactory beanFactory;
	
	public Object execute(PipelineSchema pipelineSchema, Context context) {
		context.publish(new PipelineStartedEvent(pipelineSchema));
		
		StepLocator stepLocator = new StepLocator(beanFactory, pipelineSchema);
		
		StepSchema firstStepSchema = stepLocator.getStartStepSchema();
		Step firstStep = stepLocator.getStartStep();
		
		firstStep.start(stepLocator, context, firstStepSchema);
		
		context.publish(new PipelineFinishEvent(pipelineSchema));
		
		return context.getVar("pipeline-result");
		
	}

}
