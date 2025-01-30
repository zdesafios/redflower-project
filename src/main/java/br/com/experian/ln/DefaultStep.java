package br.com.experian.ln;

public abstract class DefaultStep implements Step {
	
	@Override
	public void start(StepContext context) {
		onStart(context);
	}
	
	public abstract void onStart(StepContext context);
	
}
