package com.project.ElectronicStore.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException()
	{
		super("User your trying doesnot exist");
	}
	public ResourceNotFoundException(String message)
	{
		super(message);
	}

}
