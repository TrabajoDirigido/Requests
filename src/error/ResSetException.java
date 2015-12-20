package error;

import variables.ResSet;

public class ResSetException extends Exception{

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	
	public ResSetException(String message) { super(message); }
	
	public ResSetException(String className, ResSet type) { 
		super(className + " wrong data input, current: "+type.getDataType());
	}

}
