package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.MapConstants;
import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Convenience class containing all the game modes availble in the game
 * 
 * @author massi
 *
 */
public class GameModes {
	
	/**
	 * get all the values in constants
	 * @return iterable
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(GameModes.class, DefineRefExpr.class);
	}

	public static final DefineRefExpr DEATH_MATCH = new DefineRefExpr("DEATH_MATCH"); 
	public static final DefineRefExpr REGICIDE = new DefineRefExpr("REGICIDE");
	/**
	 * (HD/DE only)
	 */
	public static final DefineRefExpr CAPTURE_THE_RELIC = new DefineRefExpr("CAPTURE_THE_RELIC");
	/**
	 * (UP only)
	 */
	public static final DefineRefExpr CAPTURE_RELICS = new DefineRefExpr("CAPTURE_RELICS");
	/**
	 * (UP/DE only)
	 */
	public static final DefineRefExpr RANDOM_MAP = new DefineRefExpr("RANDOM_MAP"); 
	/**
	 * (UP/DE only) (game mode: random map + the "turbo mode" tickbox)
	 */
	public static final DefineRefExpr TURBO_RANDOM_MAP = new DefineRefExpr("TURBO_RANDOM_MAP");
	/**
	 * (UP/DE only) (note that KING_OF_THE_HILL is a map type, and is always true!)
	 */
	public static final DefineRefExpr KING_OT_HILL = new DefineRefExpr("KING_OT_HILL");
	/**
	 * (UP/DE only)
	 */
	public static final DefineRefExpr WONDER_RACE = new DefineRefExpr("WONDER_RACE"); 
	/**
	 * (UP/DE only)
	 */
	public static final DefineRefExpr DEFEND_WONDER = new DefineRefExpr("DEFEND_WONDER");
	/**
	 * (DE only)
	 */
	public static final DefineRefExpr EMPIRE_WARS = new DefineRefExpr("EMPIRE_WARS"); 
	/**
	 * (DE only)
	 */
	public static final DefineRefExpr BATTLE_ROYALE = new DefineRefExpr("BATTLE_ROYALE");
	/**
	 * (DE only)
	 */
	public static final DefineRefExpr SUDDEN_DEATH = new DefineRefExpr("SUDDEN_DEATH"); 

}
