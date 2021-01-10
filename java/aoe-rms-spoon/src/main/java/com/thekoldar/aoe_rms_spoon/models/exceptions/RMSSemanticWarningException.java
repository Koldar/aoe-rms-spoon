package com.thekoldar.aoe_rms_spoon.models.exceptions;

public class RMSSemanticWarningException extends  AbstractRMSException {

	private static final long serialVersionUID = 1L;

	public RMSSemanticWarningException(int errorCode) {
		super(errorCode);
	}

	public RMSSemanticWarningException(int errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	public RMSSemanticWarningException(int errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public RMSSemanticWarningException(int errorCode, String message) {
		super(errorCode, message);
	}

	public RMSSemanticWarningException(int errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
	

}
