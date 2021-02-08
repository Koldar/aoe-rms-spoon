package com.thekoldar.aoe_rms_spoon.framework.models.exceptions;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;

public class RMSSemanticErrorException extends  AbstractRMSException {

	private static final long serialVersionUID = 1L;

	public RMSSemanticErrorException(RMSErrorCode errorCode) {
		super(errorCode);
	}
	
	public RMSSemanticErrorException(AbstractRMSException cause) {
		this(cause.getErrorCode(),cause.getMessage(),  cause);
	}

	public RMSSemanticErrorException(RMSErrorCode errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	public RMSSemanticErrorException(RMSErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public RMSSemanticErrorException(RMSErrorCode errorCode, String message) {
		super(errorCode, message);
	}

	public RMSSemanticErrorException(RMSErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
	public RMSSemanticErrorException(RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, String.format(message, formats));
	}
	
	public RMSSemanticErrorException(IRMSNode nodeInvolved, RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, "Path: " + nodeInvolved.getPathToRoot().toList().asReversed().makeString(" -> ") + " " + String.format(message, formats));
	}
	
	

}
