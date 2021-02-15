package com.thekoldar.aoe_rms_spoon.framework.models.exceptions;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;

/**
 * A {@link AbstractRMSException} that represents a semantic error. This means that the semantic analysis found something that is without any doubt wrong and need to be fixed
 * 
 * @author massi
 *
 */
public class RMSSemanticErrorException extends  AbstractRMSException {

	private static final long serialVersionUID = 1L;

	/**
	 * an error with a given error code
	 * 
	 * @param errorCode error code of the error to build
	 */
	public RMSSemanticErrorException(RMSErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * an error with a given error code
	 * 
	 * @param cause cause generating the error
	 */
	public RMSSemanticErrorException(AbstractRMSException cause) {
		this(cause.getErrorCode(),cause.getMessage(),  cause);
	}

	/**
	 * an error with a given error code
	 * 
	 * @param errorCode error code of the error to build
	 * @param message a string describing the error
	 * @param cause the cause of this exception
	 * @param enableSuppression exception required parameter
	 * @param writableStackTrace exception requried parameter
	 */
	public RMSSemanticErrorException(RMSErrorCode errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * an error with a given error code
	 * 
	 * @param errorCode error code of the error to build
	 * @param message a string describing the error
	 * @param cause the cause of this exception
	 */
	public RMSSemanticErrorException(RMSErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * an error with a given error code
	 * 
	 * @param errorCode error code of the error to build
	 * @param message a string describing the error
	 */
	public RMSSemanticErrorException(RMSErrorCode errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * an error with a given error code
	 * 
	 * @param errorCode error code of the error to build
	 * @param cause the cause of this exception
	 */
	public RMSSemanticErrorException(RMSErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
	/**
	 * an error with a given error code
	 * 
	 * @param errorCode error code of the error to build
	 * @param message a string describing the warning. Contains "%" foramt specifiers
	 * @param formats object arguments of {@code message} string.
	 */
	public RMSSemanticErrorException(RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, String.format(message, formats));
	}
	
	/**
	 * an error with a given error code. This will print the path from the node to the root of the building AST RMS tree
	 * 
	 * @param nodeInvolved node that has caused this semantic warning
	 * @param errorCode error code of the error to build
	 * @param message a string describing the warning. Contains "%" foramt specifiers
	 * @param formats object arguments of {@code message} string.
	 */
	public RMSSemanticErrorException(IRMSNode nodeInvolved, RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, "Path: " + nodeInvolved.getPathToRoot().toList().asReversed().makeString(" -> ") + " " + String.format(message, formats));
	}
	
	

}
