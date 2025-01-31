package br.com.experian.ln.flow.operation;

import br.com.experian.ln.Context;
import br.com.experian.ln.flow.operation.model.OperationModel;
import br.com.experian.ln.flow.step.Step;

public interface Operation<T extends OperationModel> {

	void run(Step current, Context context, T model);
	
}
