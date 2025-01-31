package br.com.experian.ln.flow.operation;

import br.com.experian.ln.Context;
import br.com.experian.ln.flow.operation.model.OperationModel;
import br.com.experian.ln.flow.step.Step;

public abstract class DefaultOperation<T extends OperationModel> implements Operation<T> {

	@Override
	public void run(Step current, Context context, T model) {
		onRun(current, context, model);
	}
	
	public abstract void onRun(Step current, Context context, T model);

}
