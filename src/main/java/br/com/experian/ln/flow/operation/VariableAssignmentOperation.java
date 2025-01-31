package br.com.experian.ln.flow.operation;

import br.com.experian.ln.Context;
import br.com.experian.ln.flow.operation.model.VariableAssignmentModel;
import br.com.experian.ln.flow.step.Step;

@ComponentOperation("variable_assignment")
public class VariableAssignmentOperation extends DefaultOperation<VariableAssignmentModel> {

	@Override
	public void onRun(Step current, Context context, VariableAssignmentModel model) {
		if(model.getScope().isGlobal()) {
			context.createVar(model.getName(), getValue(model));
		} else {
			current.createVar(model.getName(), getValue(model));
		}
	}
	
	private Object getValue(VariableAssignmentModel model) {
		Object value = null;
		if(model.getSource().isDataJson()) {
			// obter do json
			
		} else if(model.getSource().isLiteral()) {
			// obter do proprio model
			value = model.getValue();
			
		} else if(model.getSource().isHttp()) {
			// obter do http
			String request = model.getValue();
			value = "";
			
		} else if(model.getSource().isDatabase()) {
			String query = model.getValue();
			value = "";
			
		}
		
		return value;
	}

}
