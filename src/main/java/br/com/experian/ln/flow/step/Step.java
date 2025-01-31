package br.com.experian.ln.flow.step;

import br.com.experian.ln.Context;

public interface Step {
	
	void start(Context context);

	void createVar(String name, Object value);
}
