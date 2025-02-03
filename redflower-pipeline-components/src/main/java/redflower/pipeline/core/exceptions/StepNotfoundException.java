package redflower.pipeline.core.exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StepNotfoundException extends RuntimeException {
	private final String project;
	private final String stepForStart;
	
}
