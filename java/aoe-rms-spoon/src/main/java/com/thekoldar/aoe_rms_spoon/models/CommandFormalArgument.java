package com.thekoldar.aoe_rms_spoon.models;

/**
 * An argument for the command
 * @author massi
 *
 */
public class CommandFormalArgument {

	private String name;
	private ExprType type;
	private String comment;
	private Object defaultValue;
	
	public CommandFormalArgument(String name, ExprType type, String comment, Object defaultValue) {
		super();
		this.name = name;
		this.type = type;
		this.comment = comment;
		this.defaultValue = defaultValue;
	}
	
	public static CommandFormalArgument requiredInt(String name, String comment) {
		return new CommandFormalArgument(name, ExprType.INT, comment, null);
	}
	
	public static CommandFormalArgument optionalInt(String name, Object defaultValue, String comment) {
		return new CommandFormalArgument(name, ExprType.INT, comment, defaultValue);
	}
	
	public static CommandFormalArgument requiredFloat(String name, String comment) {
		return new CommandFormalArgument(name, ExprType.FLOAT, comment, null);
	}
	
	public static CommandFormalArgument optionalFloat(String name, Object defaultValue, String comment) {
		return new CommandFormalArgument(name, ExprType.FLOAT, comment, defaultValue);
	}
	
	public static CommandFormalArgument requiredBoolean(String name, String comment) {
		return new CommandFormalArgument(name, ExprType.BOOLEAN, comment, null);
	}
	
	public static CommandFormalArgument optionalBoolean(String name, Object defaultValue, String comment) {
		return new CommandFormalArgument(name, ExprType.BOOLEAN, comment, defaultValue);
	}
	
	public static CommandFormalArgument requiredDict(String name, String comment) {
		return new CommandFormalArgument(name, ExprType.DICT, comment, null);
	}
	
	public static CommandFormalArgument optionalDict(String name, Object defaultValue, String comment) {
		return new CommandFormalArgument(name, ExprType.DICT, comment, defaultValue);
	}
	

	public String getName() {
		return name;
	}

	public ExprType getType() {
		return type;
	}

	public String getComment() {
		return comment;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}
	
	public boolean isRequired() {
		return this.defaultValue == null;
	}
	
	public boolean isOptional() {
		return this.defaultValue != null;
	}
	
	
	
	
}
