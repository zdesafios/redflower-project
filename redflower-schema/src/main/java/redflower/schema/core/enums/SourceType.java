package redflower.schema.core.enums;

public enum SourceType {
	VAR,
	JSON_DATA,
	LITERAL;

	public boolean isJsonData() {
		return this.equals(JSON_DATA);
	}

	public boolean isLiteral() {
		return this.equals(LITERAL);
	}

	public boolean isVar() {
		return this.equals(VAR);
	}
}
