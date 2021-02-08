package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import java.lang.reflect.Modifier;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Map types
 * @author massi
 *
 */
public class MapConstants {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(MapConstants.class, RMSConstSymbol.class);
	}

	public static final RMSConstSymbol ARABIA = new RMSConstSymbol("ARABIA", 9);
	public static final RMSConstSymbol ARCHIPELAGO = new RMSConstSymbol("ARCHIPELAGO", 10);
	public static final RMSConstSymbol ARENA = new RMSConstSymbol("ARENA", 29);
	public static final RMSConstSymbol BALTIC = new RMSConstSymbol("BALTIC", 11);
	public static final RMSConstSymbol BLACK_FOREST = new RMSConstSymbol("BLACK_FOREST", 12);
	public static final RMSConstSymbol COASTAL = new RMSConstSymbol("COASTAL", 13);
	public static final RMSConstSymbol CONTINENTAL = new RMSConstSymbol("CONTINENTAL", 14);
	public static final RMSConstSymbol CRATER_LAKE = new RMSConstSymbol("CRATER_LAKE", 15);
	public static final RMSConstSymbol FORTRESS = new RMSConstSymbol("FORTRESS", 16);
	public static final RMSConstSymbol GHOST_LAKE = new RMSConstSymbol("GHOST_LAKE", 32); 
	public static final RMSConstSymbol GOLD_RUSH = new RMSConstSymbol("GOLD_RUSH", 17);
	public static final RMSConstSymbol HIGHLAND = new RMSConstSymbol("HIGHLAND", 18);
	public static final RMSConstSymbol ISLANDS = new RMSConstSymbol("ISLANDS", 19);
	public static final RMSConstSymbol KING_OF_THE_HILL = new RMSConstSymbol("KING_OF_THE_HILL", 30);
	public static final RMSConstSymbol MEDITERRANEAN = new RMSConstSymbol("MEDITERRANEAN", 20);
	public static final RMSConstSymbol MIGRATION = new RMSConstSymbol("MIGRATION", 21);
	public static final RMSConstSymbol MONGOLIA = new RMSConstSymbol("MONGOLIA", 26);
	public static final RMSConstSymbol NOMAD = new RMSConstSymbol("NOMAD", 33);
	public static final RMSConstSymbol OASIS = new RMSConstSymbol("OASIS", 31);
	public static final RMSConstSymbol RIVERS = new RMSConstSymbol("RIVERS", 22);
	public static final RMSConstSymbol SALT_MARSH = new RMSConstSymbol("SALT_MARSH", 28);
	public static final RMSConstSymbol SCANDANAVIA = new RMSConstSymbol("SCANDANAVIA", 25);
	public static final RMSConstSymbol TEAM_ISLANDS = new RMSConstSymbol("TEAM_ISLANDS", 23);
	public static final RMSConstSymbol YUCATAN = new RMSConstSymbol("YUCATAN", 27);
	
	public static final RMSConstSymbol STEPPE = new RMSConstSymbol("STEPPE", 34);
	public static final RMSConstSymbol BUDAPEST = new RMSConstSymbol("BUDAPEST", 35);
	public static final RMSConstSymbol VALLEY = new RMSConstSymbol("VALLEY", 36);
	public static final RMSConstSymbol ATLANTIC = new RMSConstSymbol("ATLANTIC", 37);
	public static final RMSConstSymbol LAND_OF_LAKES = new RMSConstSymbol("LAND_OF_LAKES", 38);
	public static final RMSConstSymbol LAND_NOMAD = new RMSConstSymbol("LAND_NOMAD", 39);
	public static final RMSConstSymbol CENOTES = new RMSConstSymbol("CENOTES", 40);
	public static final RMSConstSymbol GOLDEN_HILL = new RMSConstSymbol("GOLDEN_HILL", 41);
	public static final RMSConstSymbol MEGARANDOM = new RMSConstSymbol("MEGARANDOM", 42);
	public static final RMSConstSymbol MICHI = new RMSConstSymbol("MICHI", 43);
	public static final RMSConstSymbol AMBUSH = new RMSConstSymbol("AMBUSH", 44);
	public static final RMSConstSymbol CUSTOM = new RMSConstSymbol("CUSTOM", 45);
	public static final RMSConstSymbol NILE_DELTA = new RMSConstSymbol("NILE_DELTA", 46);
	public static final RMSConstSymbol MOUNTAIN_PASS = new RMSConstSymbol("MOUNTAIN_PASS", 47);
	public static final RMSConstSymbol SERENGETI = new RMSConstSymbol("SERENGETI", 48);
	public static final RMSConstSymbol SOCOTRA = new RMSConstSymbol("SOCOTRA", 49);
	public static final RMSConstSymbol KILIMANJARO = new RMSConstSymbol("KILIMANJARO", 50);

}
