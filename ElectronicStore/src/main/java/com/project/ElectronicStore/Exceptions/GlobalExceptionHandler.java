package com.project.ElectronicStore.Exceptions;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.ElectronicStore.Dto.ApiResponseMessage;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> resourceNotFoundException(ResourceNotFoundException resource)
	{
		ApiResponseMessage message=new ApiResponseMessage();
	
	message.setMessage("User not found");
	message.setStatus(HttpStatus.NOT_FOUND);
	message.setSuccess(true);
	
	return new ResponseEntity<>(message,HttpStatus.OK);
	
	}
	
	
	//trial that i can also handle exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> methodArgumentNotFound(MethodArgumentNotValidException resource)
	{
		
	Map<String,Object> message=new HashMap<>();
		List<ObjectError> allErrors=resource.getBindingResult().getAllErrors();
		allErrors.stream().forEach(i->{
			
			String message2=i.getDefaultMessage();
			message.put(message2, "error found");
		
		});
		
		return new ResponseEntity<>(message,HttpStatus.OK);
		
	}
		
//	}
	
	
	
	

}
