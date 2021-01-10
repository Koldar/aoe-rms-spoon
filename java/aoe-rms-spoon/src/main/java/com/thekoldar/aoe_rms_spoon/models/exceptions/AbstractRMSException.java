package com.thekoldar.aoe_rms_spoon.models.exceptions;

public abstract class AbstractRMSException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	
	public int getErrorCode() {
		return this.errorCode;
	}

	public AbstractRMSException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public AbstractRMSException(int errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
	}

	public AbstractRMSException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public AbstractRMSException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public AbstractRMSException(int errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	
	
}
