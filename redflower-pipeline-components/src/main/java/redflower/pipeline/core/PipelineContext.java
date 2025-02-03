package redflower.pipeline.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.jayway.jsonpath.JsonPath;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PipelineContext implements Context {
	private Map<String, Object> vars = new HashMap<>();
	
	private String jsonData;
	
	// Encontrar um melhor local para esse componente, semanticamente nao faz muito sentido
	@Deprecated
	private final ApplicationEventPublisher publisher;
	
	@Override
	public void createVar(String name, Object value) {
		Assert.notNull(name, "Name nao pode ser nulo");
		
		if(vars == null) {
			vars = new HashMap<>();
		}
		
		vars.put(name, value);
	}

	@Override
	public Object getVar(String name) {
		if(vars == null) {
			return null;
		}
		
		return vars.get(name);
	}

	@Override
	public Object getVarFromJsonData(String expression) {
		return JsonPath.read(jsonData, expression);
	}

	//TODO remover esse método
	@Deprecated
	@Override
	public void publish(ApplicationEvent event) {
		publisher.publishEvent(event);
	}

	//TODO encontrar uma melhor forma de injetar essa dependencia.
	@Deprecated
	@Override
	public void setJsonData(String jsonData) {
		if(null != jsonData)
			this.jsonData = jsonData;
	}

}
