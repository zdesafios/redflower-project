package br.com.experian.ln;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redflower.schema.OperationSchema;
import redflower.schema.PipelineSchema;
import redflower.schema.ProjectSchema;
import redflower.schema.StepSchema;
import redflower.schema.operation.NumberMultiplyOperationSchema;
import redflower.schema.operation.VariableAssignmentOperationSchema;
import redflower.schema.step.FlowDecisionStepSchema;
import redflower.schema.step.MathStepSchema;

@SpringBootApplication
public class ExperianLnApplication {

	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		//SpringApplication.run(ExperianLnApplication.class, args);
		
		aaa();
	}
	
	public static void aaa() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerSubtypes(PipelineSchema.class, StepSchema.class, MathStepSchema.class,
				OperationSchema.class, VariableAssignmentOperationSchema.class, NumberMultiplyOperationSchema.class,
				FlowDecisionStepSchema.class);
		
		ClassLoader classLoader = ExperianLnApplication.class.getClassLoader();
		File file = new File(classLoader.getResource("calculo_x.json").getFile());
		ProjectSchema json = objectMapper.readValue(file, ProjectSchema.class);
		System.out.println(json);
	}

}
