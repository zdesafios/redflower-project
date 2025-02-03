package redflower.schema.entrypoint.enums;

public enum EntrypointType {
	HTTP,
	QUEUE_RABBIMQ,
	QUEUE_KAFKA;

	public boolean isHttp() {
		return this.equals(HTTP);
	}
}
