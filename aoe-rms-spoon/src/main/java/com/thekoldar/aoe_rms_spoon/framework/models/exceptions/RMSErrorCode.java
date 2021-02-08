package com.thekoldar.aoe_rms_spoon.framework.models.exceptions;

public enum RMSErrorCode {

	WRONG_CHILDREN_NUMBER(0, true),
	REQUIRED_INCLUDE_NOT_INCLUDED(1, true),
	DEFINE_IS_NOT_DEFINED(2, true),
	DEFINE_IS_DEFINED(3, true),
	CONST_REDEFINED(4, false),
	DEFINE_REDEFINED(5, false),
	NODE_IS_NOT_ROOT(6, true),
	NODE_IS_ROOT(7, true),
	NODE_SHOULD_BE_UNDER_ROOT(8, true),
	INVALID_PARENT_TYPE(9, true),
	DUPLICATE_TYPE(10, true),
	NODE_NOT_FOUND(11, true),
	INVALID_EXPR_TYPE(12, true),
	CONST_UNDEFINED(13, false),
	COMMAND_DOES_NOTHING(14, false),
	EXPECTED_REQUIRED_PARAMETER(15, true),
	TOO_FEW_ARGUMENTS(16, true),
	TOO_MANY_ARGUMENTS(17, true),
	INVALID_ARGUMENT(18,true),
	NOT_A_COMMAND(19, true),
	INVALID_RANGE(20, true),
	GREATER_THAN(21, true),
	GREATER_OR_EQUAL_THAN(22, true),
	LESS_THAN(23, true),
	LESS_OR_EQUAL_THAN(24, true),
	IGNORE_VALUE(25, false),
	BEHAVIOR_ONE(26, false),
	BEHAVIOR_MAY_BE_ALTERED(27, false),
	DISABLE_VALUE(28, false),
	MAY_CRASH(29, false),
	CONFLICTING_COMMANDS(30, true),
	EXPECTED_REQUIRED_COMMAND(31, true),
	NOT_A_EXPR(32, true),
	INVALID_NODE_LOCATION(33, true)
	;
	
	public static RMSErrorCode from(int id) {
		for (var e : RMSErrorCode.values()) {
			if (e.id == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("cannot find error code with id " + id);
	}
	
	private int id;
	private boolean isError;
	
	private RMSErrorCode(int id, boolean isError) {
		this.id = id;
		this.isError = isError;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	
	/**
	 * Check if normally this error code represents a warning
	 * @return
	 */
	public boolean isNormallyWarning() {
		return this.isError == false;
	}
	
	/**
	 * Check if normally this error code represents an error
	 * @return
	 */
	public boolean isNormallyError() {
		return this.isError == true;
	}
}
