package redflower.domain.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Data
public class OperationResultOperationModel {
	
	@JsonUnwrapped
	private GetVarModel varSchema;
	
}
