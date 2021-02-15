package com.thekoldar.aoe_rms_spoon.framework.models.exceptions;

/**
 * A generic exception that spoon may generates
 * @author massi
 *
 */
public abstract class AbstractRMSException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private RMSErrorCode errorCode;
	
	public RMSErrorCode getErrorCode() {
		return this.errorCode;
	}

	public AbstractRMSException(RMSErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public AbstractRMSException(RMSErrorCode errorCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
	}

	public AbstractRMSException(RMSErrorCode errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public AbstractRMSException(RMSErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public AbstractRMSException(RMSErrorCode errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	
}
