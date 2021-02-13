package com.thekoldar.aoe_rms_spoon.framework.models;

import com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals.MapSizes;
import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSDefineSymbol;

/**
 * size of a map
 * @author massi
 *
 */
public class MapSize {
	
	private String name;
	private int suggestedPlayers;
	private String conditionalName;
	private int tilesPerSide;
	
	public MapSize(String name, int suggestedPlayers, String conditionalName, int tilesPerSide) {
		super();
		this.name = name;
		this.suggestedPlayers = suggestedPlayers;
		this.conditionalName = conditionalName;
		this.tilesPerSide = tilesPerSide;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getSuggestedPlayers() {
		return this.suggestedPlayers;
	}
	
	public String getConditionalName() {
		return this.conditionalName;
	}
	
	public int getTilesPerSide() {
		return this.tilesPerSide;
	}
	
	/**
	 * get number of tiles as a percentage of the number of tiles per map side
	 * 
	 * @param percentage a percentage from 0 to 100. The ratio is <b>between doubles</b>
	 * @return number of tiles representing the percentage of map side
	 */
	public int getTileFromPercentage(double percentage) {
		return (int)((percentage / 100.) * this.tilesPerSide);
	}
	
	
	/**
	 * get define reference
	 * @return
	 */
	public DefineRefExpr getDefineRef() {
		return new DefineRefExpr(this.conditionalName);
	}
	
	/**
	 * get the define symbol
	 * @return
	 */
	public RMSDefineSymbol getDefine() {
		return new RMSDefineSymbol(this.conditionalName);
	}
	
	/**
	 * get total tioles in the whole map
	 * @return
	 */
	public int getTotalTiles() {
		return this.tilesPerSide * this.tilesPerSide;
	}
	
	/**
	 * get the ratio between {@link #getTotalTiles()} and 1e6
	 * @return
	 */
	public double getTilesPer100x100() {
		return this.getTotalTiles()/(100.*100.);
	}
	
	/**
	 * ratio between this map size and {@link MapSizes#TINY_MAP}
	 * @return values near 1.0 represents a map whose side si similar to TINY.  
	 */
	public double getPercentageRelativeToTiny() {
		return (this.getTilesPerSide() * 1.0) /(MapSizes.TINY_MAP.getTilesPerSide() * 1.0);
	}
	


}
