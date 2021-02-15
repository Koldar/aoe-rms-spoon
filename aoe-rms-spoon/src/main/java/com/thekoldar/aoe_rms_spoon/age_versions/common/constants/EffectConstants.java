package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Convenience class containing the available effect constants  
 * @author massi
 *
 */
public class EffectConstants {
	
	/**
	 * get all the values in constants
	 * @return iterable
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(EffectConstants.class, RMSConstSymbol.class);
	}

	public static final RMSConstSymbol SET_ATTRIBUTE = new RMSConstSymbol("SET_ATTRIBUTE", 0);
	public static final RMSConstSymbol ADD_ATTRIBUTE = new RMSConstSymbol("ADD_ATTRIBUTE", 4);
	public static final RMSConstSymbol MUL_ATTRIBUTE = new RMSConstSymbol("MUL_ATTRIBUTE", 5);
	public static final RMSConstSymbol MOD_RESOURCE = new RMSConstSymbol("MOD_RESOURCE", 1);
	public static final RMSConstSymbol MUL_RESOURCE = new RMSConstSymbol("MUL_RESOURCE", 6);
	public static final RMSConstSymbol SET_TECH_COST = new RMSConstSymbol("SET_TECH_COST", 100);
	public static final RMSConstSymbol ADD_TECH_COST = new RMSConstSymbol("ADD_TECH_COST", 101);
	public static final RMSConstSymbol MOD_TECH_TIME = new RMSConstSymbol("MOD_TECH_TIME", 103);
	public static final RMSConstSymbol ENABLE_OBJECT = new RMSConstSymbol("ENABLE_OBJECT", 2);
	public static final RMSConstSymbol UPGRADE_UNIT = new RMSConstSymbol("UPGRADE_UNIT", 3);
	public static final RMSConstSymbol DISABLE_TECH = new RMSConstSymbol("DISABLE_TECH", 102);
	public static final RMSConstSymbol ENABLE_TECH = new RMSConstSymbol("ENABLE_TECH", 7);
	public static final RMSConstSymbol MODIFY_TECH = new RMSConstSymbol("MODIFY_TECH", 8);
	public static final RMSConstSymbol SET_PLAYER_DATA = new RMSConstSymbol("SET_PLAYER_DATA", 9);

	public static final RMSConstSymbol GAIA_SET_ATTRIBUTE = new RMSConstSymbol("GAIA_SET_ATTRIBUTE", -1);
	public static final RMSConstSymbol GAIA_ADD_ATTRIBUTE = new RMSConstSymbol("GAIA_ADD_ATTRIBUTE", -5);
	public static final RMSConstSymbol GAIA_MUL_ATTRIBUTE = new RMSConstSymbol("GAIA_MUL_ATTRIBUTE", -6);
	public static final RMSConstSymbol GAIA_MOD_RESOURCE = new RMSConstSymbol("GAIA_MOD_RESOURCE", -2);
	public static final RMSConstSymbol GAIA_MUL_RESOURCE = new RMSConstSymbol("GAIA_MUL_RESOURCE", -7);
	public static final RMSConstSymbol GAIA_SET_TECH_COST = new RMSConstSymbol("GAIA_SET_TECH_COST", -101);
	public static final RMSConstSymbol GAIA_ADD_TECH_COST = new RMSConstSymbol("GAIA_ADD_TECH_COST", -102);
	public static final RMSConstSymbol GAIA_MOD_TECH_TIME = new RMSConstSymbol("GAIA_MOD_TECH_TIME", -104);
	public static final RMSConstSymbol GAIA_ENABLE_OBJECT = new RMSConstSymbol("GAIA_ENABLE_OBJECT", -3);
	public static final RMSConstSymbol GAIA_UPGRADE_UNIT = new RMSConstSymbol("GAIA_UPGRADE_UNIT", -4);
	public static final RMSConstSymbol GAIA_DISABLE_TECH = new RMSConstSymbol("GAIA_DISABLE_TECH", -103);
	public static final RMSConstSymbol GAIA_ENABLE_TECH = new RMSConstSymbol("GAIA_ENABLE_TECH", -8);
	public static final RMSConstSymbol GAIA_MODIFY_TECH = new RMSConstSymbol("GAIA_MODIFY_TECH", -9);
	public static final RMSConstSymbol GAIA_SET_PLAYER_DATA = new RMSConstSymbol("GAIA_SET_PLAYER_DATA", -10);

}
