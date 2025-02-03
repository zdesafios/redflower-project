package redflower.pipeline.step;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import redflower.pipeline.core.Context;
import redflower.pipeline.core.operation.Operation;
import redflower.pipeline.core.step.DefaultStep;
import redflower.schema.core.operation.OperationSchema;
import redflower.schema.step.GenericStepSchema;

@Component("genericStep")
@RequiredArgsConstructor
public class GenericStep extends DefaultStep<GenericStepSchema> {
	
	private final BeanFactory beanFactory;
	
	@Override
	public void onStart(Context context, GenericStepSchema schema) {
		List<OperationSchema> operationsSchemas = schema.getOperations();
		operationsSchemas.stream().forEach(op-> {
			Operation operation = beanFactory.getBean(op.getOperation(), Operation.class);
			if(null == operation) {
				throw new RuntimeException("Operação %s não definida".formatted(op.getOperation()));
			}
			operation.run(GenericStep.this, op, context);
		});
	}

}
