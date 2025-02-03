package redflower.pipeline.core;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redflower.schema.PipelineSchema;
import redflower.schema.StepSchema;

@Slf4j
@Service
@RequiredArgsConstructor
public class PipelineExecutor {
	
	private final BeanFactory beanFactory;
	
	public Object execute(PipelineSchema pipelineSchema, Context context) {
		context.publish(new PipelineStartedEvent(pipelineSchema));
		
		StepLocator stepLocator = new StepLocator(beanFactory, pipelineSchema);
		
		Step firstStep = stepLocator.getStep(pipelineSchema.getStart());
		StepSchema firstStepSchema = stepLocator.getStepSchema(pipelineSchema.getStart());
		
		firstStep.start(stepLocator, context, firstStepSchema);
		
		context.publish(new PipelineFinishEvent(pipelineSchema));
		
		return context.getVar("pipeline-result");
		
	}

}
