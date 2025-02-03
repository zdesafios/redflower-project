package redflower.pipeline.core.exceptions;

public class ProjectNotfoundException extends RuntimeException {

	public ProjectNotfoundException(String id) {
		super(id);
	}

}
