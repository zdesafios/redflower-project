package br.com.experian.ln.flow.step;

import br.com.experian.ln.Context;

public abstract class DefaultStep implements Step {
	
	@Override
	public void start(Context context) {
		onStart(context);
	}
	
	public abstract void onStart(Context context);
	
	@Override
	public void createVar(String name, Object value) {
		
	}
	
}
