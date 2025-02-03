package redflower.pipeline.core;

import org.springframework.context.ApplicationEvent;

public interface Context {
	void createVar(String name, Object value);
	Object getVar(String name);
	void setJsonData(String jsonData);
	Object getVarFromJsonData(String expression);
	void publish(ApplicationEvent event);
}
