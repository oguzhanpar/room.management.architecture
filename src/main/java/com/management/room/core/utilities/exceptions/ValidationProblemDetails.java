package com.management.room.core.utilities.exceptions;


import java.util.Map;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationProblemDetails extends ProblemDetails{
	
	
	Map<String, String> validationErrors;

}