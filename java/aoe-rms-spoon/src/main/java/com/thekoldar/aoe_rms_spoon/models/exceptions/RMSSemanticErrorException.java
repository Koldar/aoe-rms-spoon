package com.thekoldar.aoe_rms_spoon.models.exceptions;

public class RMSSemanticErrorException extends  AbstractRMSException {

	private static final long serialVersionUID = 1L;

	public RMSSemanticErrorException(int errorCode) {
		super(errorCode);
	}

	public RMSSemanticErrorException(int errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	public RMSSemanticErrorException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public RMSSemanticErrorException(int errorCode, String message) {
		super(errorCode, message);
	}

	public RMSSemanticErrorException(int errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
	

}
