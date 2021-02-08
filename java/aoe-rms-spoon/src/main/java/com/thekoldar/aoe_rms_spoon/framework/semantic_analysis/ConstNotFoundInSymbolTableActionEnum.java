package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

/**
 * what to do if for some weird reason we requested a value from the symbol table but there is no such symbol
 * @author massi
 *
 */
public enum ConstNotFoundInSymbolTableActionEnum {
	/**
	 * we should raise exception.This is a strict options but during development it may be too much 
	 */
	RAISE_EXCEPTION,
	/**
	 * we assume the const value to be 0.  This may allows us to continue the semantic analysis
	 */
	ASSUME_0,
}
