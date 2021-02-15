package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Convenience class containing the available class constants  
 * @author massi
 *
 */
public class ClassConstants {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(ClassConstants.class, RMSConstSymbol.class);
	}

	public static final RMSConstSymbol VILLAGER = new RMSConstSymbol("VILLAGER_CLASS", 904);
	public static final RMSConstSymbol BUILDING = new RMSConstSymbol("BUILDER_CLASS", 903);
	public static final RMSConstSymbol OCEAN_FISH = new RMSConstSymbol("OCEAN_FISH_CLASS", 905);
	public static final RMSConstSymbol SHORE_FISH = new RMSConstSymbol("", 933);
	public static final RMSConstSymbol FARM = new RMSConstSymbol("FARM_CLASS", 949);
	public static final RMSConstSymbol TREE = new RMSConstSymbol("TREE_CLASS", 915);
	public static final RMSConstSymbol TOWER = new RMSConstSymbol("TOWER_CLASS", 952);
	public static final RMSConstSymbol WALL = new RMSConstSymbol("WALL_CLASS", 927);
	public static final RMSConstSymbol GATE = new RMSConstSymbol("GATE_CLASS", 939);
	public static final RMSConstSymbol KING = new RMSConstSymbol("KING_CLASS", 959);
	public static final RMSConstSymbol LIVESTOCK = new RMSConstSymbol("LIVESTOCK_CLASS", 958);
	public static final RMSConstSymbol INFANTRY = new RMSConstSymbol("INFANTRY_CLASS", 906);
	public static final RMSConstSymbol ARCHERY = new RMSConstSymbol("ARCHERY_CLASS", 900);
	public static final RMSConstSymbol ARCHERY_CANNON = new RMSConstSymbol("ARCHERY_CANNON_CLASS", 944);
	public static final RMSConstSymbol CAVARLY = new RMSConstSymbol("CAVALRY_CLASS", 912);
	public static final RMSConstSymbol CAVALRY_ARCHER = new RMSConstSymbol("CAVARLY_ARCHER_CLASS", 936);
	public static final RMSConstSymbol CAVARLY_CANNON = new RMSConstSymbol("CAVARLY_CANNON_CLASS", 923);
	public static final RMSConstSymbol MONASTERY = new RMSConstSymbol("MONASTERY_CLASS", 918);
	public static final RMSConstSymbol SIEGE_WEAPON = new RMSConstSymbol("SIEGE_WEAPON_CLASS", 913);
	public static final RMSConstSymbol SCORPION = new RMSConstSymbol("SCORPION_CLASS", 955);
	public static final RMSConstSymbol PACKED_TREBUCHET = new RMSConstSymbol("PACKED_TREBUCHET_CLASS", 951);
	public static final RMSConstSymbol UNPACKED_TREBUCHET = new RMSConstSymbol("UNPACKED_TREBUCHET_CLASS", 954);
	public static final RMSConstSymbol PETARD = new RMSConstSymbol("PETARD_CLASS", 935);
	public static final RMSConstSymbol WARSHIP = new RMSConstSymbol("WARSHIP_CLASS", 922);
	
}
