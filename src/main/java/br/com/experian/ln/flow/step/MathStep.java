package br.com.experian.ln.flow.step;

import java.util.List;

import br.com.experian.ln.Context;
import br.com.experian.ln.flow.operation.Operation;

@ComponentStep("asas")
public class MathStep extends DefaultStep {
	
	@Override
	public void onStart(Context context) {
		List<Operation> operations = null;
		operations.stream().forEach(op->{
			op.run(this, context, null);
		});
	}

}
