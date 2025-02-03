package redflower.schema.core.enums;

public enum VarScope {
	GLOBAL,
	LOCAL;

	public boolean isGlobal() {
		return this.equals(GLOBAL);
	}
}
