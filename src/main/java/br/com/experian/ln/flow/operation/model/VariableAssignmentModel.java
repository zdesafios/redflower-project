package br.com.experian.ln.flow.operation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VariableAssignmentModel implements OperationModel {
	private VariableAssignmentScopeType scope;
	private String name;
	private VariableAssignmentValueSource source;
	private String value;
}
