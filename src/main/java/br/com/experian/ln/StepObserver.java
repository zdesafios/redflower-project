package br.com.experian.ln;

import br.com.experian.ln.flow.step.Step;

public interface StepObserver {
	public void notify(Step currentStep);
}
