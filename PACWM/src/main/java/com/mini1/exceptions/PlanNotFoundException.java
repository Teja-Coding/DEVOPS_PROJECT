package com.mini1.exceptions;

public class PlanNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -197951885974822567L;
	
	public PlanNotFoundException(String msg)
	{
		super(msg);
	}
}
