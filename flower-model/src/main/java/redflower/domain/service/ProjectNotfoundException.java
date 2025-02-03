package redflower.domain.service;

public class ProjectNotfoundException extends RuntimeException {

	public ProjectNotfoundException(String id) {
		super(id);
	}

}
