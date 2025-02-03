package redflower.pipeline.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.JsonPath;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PipelineContext implements Context {
	private Map<String, Object> vars = new HashMap<>();
	
	private String jsonData;
	
	private final ApplicationEventPublisher publisher;
	
	@Override
	public void createVar(String name, Object value) {
		vars.put(name, value);
	}

	@Override
	public Object getVar(String name) {
		return vars.get(name);
	}

	@Override
	public Object getVarFromJsonData(String expression) {
		return JsonPath.read(jsonData, expression);
	}

	@Override
	public void publish(ApplicationEvent event) {
		publisher.publishEvent(event);
	}

	@Override
	public void setJsonData(String jsonData) {
		if(null != jsonData)
			this.jsonData = jsonData;
	}

}
