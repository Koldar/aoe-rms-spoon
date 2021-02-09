package com.thekoldar.aoe_rms_spoon.framework.usefulscripts;

import com.thekoldar.aoe_rms_spoon.framework.models.MapSize;

/**
 * different methods to convert into various units used by age of empires.
 * 
 * The convertion methods allows you to seamlessly convert the following units:
 * <ul>
 * 	<li>tiles</li>
 *  <li>percentage (0-100) of map side (requires to input the size of the map)</li>
 *  <li>cliff segment</li>
 *  <li>cliff units</li>
 * </ul>
 * 
 * @author massi
 *
 */
public class Conversions {

	/**
	 * convert the number of tiles into a percentage of map side
	 * @param mp
	 * @return 0 - 100
	 */
	public static int tiles_to_MapSidePercentage(int tiles, MapSize mp) {
		return (int)(100 * ((1.0 * tiles) / mp.getTilesPerSide()));
	}
	
	/**
	 * convert a map side percentage into the corresponding tiles
	 * @param percentage 0-100
	 * @param mp
	 * @return
	 */
	public static int mapSidePercentage_to_tiles(int percentage, MapSize mp) {
		return (int)((percentage / 100.) * mp.getTilesPerSide());
	}
	
	/**
	 * convert a cliff segment unit into the corresponding tiles number
	 * @param cliffSegment value. Must be greater than 2.
	 * @return
	 */
	public static int cliffSegment_to_tiles(int cliffSegment) {
		if (cliffSegment < 3) {
			throw new IllegalArgumentException("cliff segment minimum value is 3!");
		}
		return 12 + 3 * (cliffSegment - 3 );
	}
	
	/**
	 * convert the number of tiles into the corresponding cliff segment
	 * @param tiles tiles amount
	 * @return cliff segment. Always greater than 2
	 */
	public static int tiles_to_cliffSegment(int tiles) {
		var cliff = (int)((tiles - 3)/3.);
		if (cliff < 3) {
			throw new IllegalArgumentException("cliff segment minimum value is 3!");
		}
		return cliff;
	}
	
	public static int tiles_to_cliff(int tiles) {
		return tiles / 2;
	}
	
	public static int cliff_to_tiles(int cliff) {
		return 2 * cliff;
	}

	// DERIVED CONVERSIONS
	
	public static int cliffSegment_to_mapSidePercentage(int cliffSegment, MapSize mp) {
		return tiles_to_MapSidePercentage(cliffSegment_to_tiles(cliffSegment), mp);
	}
	
	public static int mapSidePercentage_to_cliffSegment(int percentage, MapSize mp) {
		return tiles_to_cliffSegment(mapSidePercentage_to_tiles(percentage, mp));
	}
	
	public static int cliff_to_cliffSegment(int cliff) {
		return tiles_to_cliffSegment(cliff_to_tiles(cliff));
	}
	
	public static int cliffSegment_to_cliff(int cliffSegment) {
		return tiles_to_cliff(cliffSegment_to_tiles(cliffSegment));
	}
	
	public int cliff_to_mapSidePercentage(int cliff, MapSize mp) {
		return tiles_to_MapSidePercentage(cliff_to_tiles(cliff), mp);
	}
	
	public int mapSidePercentage_to_cliff(int percentage, MapSize mp) {
		return tiles_to_cliff(mapSidePercentage_to_tiles(percentage, mp));
	}
}
