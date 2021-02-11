package com.thekoldar.aoe_rms_spoon.framework;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;

/**
 * represents an object in the Age of Empires game data
 * @author massi
 *
 */
public interface IAoeObject {

	/**
	 * Id associated to the Game object
	 * @return
	 */
	public int getValue();
	
	/**
	 * Name of the constant representing the game object
	 * @return
	 */
	public String getName();
	
	/**
	 * a {@link RMSConstSymbol} objects representing this game object
	 * @return
	 */
	public default RMSConstSymbol getConst() {
		return new RMSConstSymbol(getName(), getValue());
	}
	
	/**
	 * get a description of this object
	 * @return
	 */
	public String getDescription();
}
