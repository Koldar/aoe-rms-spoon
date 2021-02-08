package com.thekoldar.aoe_rms_spoon.framework;

import java.awt.Color;
import java.nio.file.Path;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;

/**
 * Represents a const that refers to a particular terrain
 * 
 * @author massi
 *
 */
public interface ITerrain {
	
	/**
	 * A path representing where the texture file of this terrain can be found on the filesystem.
	 * The path is relative to the age of empires version you are considering
	 * @return
	 */
	public Path getTextureFile();
	
	/**
	 * Color of the terrain
	 */
	public Color getColor();
	
	/**
	 * name of the terrain that you should use in the RMS file
	 * @return
	 */
	public String getConstName();
	
	/**
	 * value of the terrain associated to the RMS const
	 * 
	 * @return
	 */
	public int getConstValue();
	
	/**
	 * get the const value of this terrain
	 * @return
	 */
	public default RMSConstSymbol getConstSymbol() {
		return new RMSConstSymbol(this.getConstName(), this.getConstValue());
	}
	
	/**
	 * if true, with the terrain comes also a forest 
	 * @return
	 */
	public boolean isForest();
	
	/**
	 * if true, the terrain generated can be walked by land units
	 * @return
	 */
	public boolean isLandWalkable();
	
	/**
	 * if true, the terrain generated can be walked by ship units
	 * @return
	 */
	public boolean isShipWalkable();
	
	/**
	 * if true, a player can build buildings (e.g., barracks) on it
	 * @return
	 */
	public boolean isBuildable();
	
	/**
	 * if true, a player can build a dock on it
	 * @return
	 */
	public boolean isDockable();
	
	/**
	 * if true, a player can build walls on the terrain
	 * @return
	 */
	public boolean isWallable();
	
	/**
	 * if true, the game can place natural resources on the terrain
	 * @return
	 */
	public boolean canNaturalResourcesBePutOnIt();
	
}
