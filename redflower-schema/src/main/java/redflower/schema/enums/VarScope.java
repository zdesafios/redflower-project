package redflower.schema.enums;

public enum VarScope {
	GLOBAL,
	LOCAL;

	public boolean isGlobal() {
		return this.equals(GLOBAL);
	}
}
