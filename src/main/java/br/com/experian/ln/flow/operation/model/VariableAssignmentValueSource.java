package br.com.experian.ln.flow.operation.model;

public enum VariableAssignmentValueSource {
	DATA_JSON,
	LITERAL,
	REST,
	WWEBSERVICE;

	public boolean isDataJson() {
		return false;
	}

	public boolean isLiteral() {
		return false;
	}

	public boolean isHttp() {
		return false;
	}

	public boolean isDatabase() {
		return false;
	}
}
