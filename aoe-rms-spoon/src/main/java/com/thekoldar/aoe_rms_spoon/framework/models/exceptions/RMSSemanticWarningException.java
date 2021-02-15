package com.thekoldar.aoe_rms_spoon.framework.models.exceptions;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;

/**
 * A {@link AbstractRMSException} that represents a semantic warning. This means that the semantic analysis found somethign that, while it is not wrong, 
 * should be refactor in something more valid
 * 
 * @author massi
 *
 */
public class RMSSemanticWarningException extends  AbstractRMSException {

	private static final long serialVersionUID = 1L;

	/**
	 * a warning with a given error code
	 * 
	 * @param errorCode error code of the warning to build
	 */
	public RMSSemanticWarningException(RMSErrorCode errorCode) {
		super(errorCode);
	}

	/**
	 * a warning with a given error code
	 * 
	 * @param errorCode error code of the warning to build
	 * @param message a string describing the warning
	 * @param cause the cause of this exception
	 * @param enableSuppression exception required parameter
	 * @param writableStackTrace exception requried parameter
	 */
	public RMSSemanticWarningException(RMSErrorCode errorCode, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorCode, message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * a warning with a given error code
	 * 
	 * @param errorCode error code of the warning to build
	 * @param message a string describing the warning
	 * @param cause the cause of this exception
	 */
	public RMSSemanticWarningException(RMSErrorCode errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * a warning with a given error code
	 * 
	 * @param errorCode error code of the warning to build
	 * @param message a string describing the warning
	 */
	public RMSSemanticWarningException(RMSErrorCode errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * a warning with a given error code
	 * 
	 * @param errorCode error code of the warning to build
	 * @param cause the cause of this exception
	 */
	public RMSSemanticWarningException(RMSErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
	
	/**
	 * a warning with a given error code
	 * 
	 * @param errorCode error code of the warning to build
	 * @param message a string describing the warning. Contains "%" foramt specifiers
	 * @param formats object arguments of {@code message} string.
	 */
	public RMSSemanticWarningException(RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, String.format(message, formats));
	}
	
	/**
	 * a warning with a given error code. . This will print the path from the node to the root of the building AST RMS tree
	 * 
	 * @param nodeInvolved node that has caused this semantic warning
	 * @param errorCode error code of the warning to build
	 * @param message a string describing the warning. Contains "%" foramt specifiers
	 * @param formats object arguments of {@code message} string.
	 */
	public RMSSemanticWarningException(IRMSNode nodeInvolved, RMSErrorCode errorCode, String message, Object... formats) {
		super(errorCode, "Path: " + nodeInvolved.getPathToRoot().toList().asReversed().makeString(" -> ") + " " + String.format(message, formats));
	}

}
