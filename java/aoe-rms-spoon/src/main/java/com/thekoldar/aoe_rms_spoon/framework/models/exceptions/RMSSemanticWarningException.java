package com.thekoldar.aoe_rms_spoon.framework.models.exceptions;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;

public class RMSSemanticWarningException extends  AbstractRMSException {

	private static final long serialVersionUID = 1L;

	public RMSSemanticWarningException(RMSErrorCode errorCode) {
		super(errorCode);
	}

	public RMSSemanticWarningException(RMSErrorCode errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	public RMSSemanticWarningException(RMSErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	public RMSSemanticWarningException(RMSErrorCode errorCode, String message) {
		super(errorCode, message);
	}

	public RMSSemanticWarningException(RMSErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
	public RMSSemanticWarningException(RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, String.format(message, formats));
	}
	
	public RMSSemanticWarningException(IRMSNode nodeInvolved, RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, "Path: " + nodeInvolved.getPathToRoot().toList().asReversed().makeString(" -> ") + " " + String.format(message, formats));
	}

}
