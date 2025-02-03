package redflower.pipeline.step;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import redflower.pipeline.core.Context;
import redflower.pipeline.core.step.DefaultStep;
import redflower.schema.step.PipelineResultSchema;

@Component("pipelineResultStep")
public class PipelineResultStep extends DefaultStep<PipelineResultSchema> {

	@Override
	public void onStart(Context context, PipelineResultSchema schema) {
		
		Map<String, Object> result = new HashMap<>();
		
		schema.getVars().stream().forEach(name-> result.put(name, context.getVar(name)));
		
		context.createVar("pipeline-result", result);
	}

}
