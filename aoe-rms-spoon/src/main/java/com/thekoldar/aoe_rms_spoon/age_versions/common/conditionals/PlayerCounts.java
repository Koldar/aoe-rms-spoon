package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Convenience class containing if a given player is actually playing the game or not
 * 
 * @author massi
 *
 */
public class PlayerCounts {
	
	/**
	 * get all the values in constants
	 * @return iterable
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(PlayerCounts.class, DefineRefExpr.class);
	}

	public static final DefineRefExpr PLAYER_GAME_1  = new DefineRefExpr("PLAYER_GAME_1"); 
	public static final DefineRefExpr PLAYER_GAME_2  = new DefineRefExpr("PLAYER_GAME_2");
	public static final DefineRefExpr PLAYER_GAME_3  = new DefineRefExpr("PLAYER_GAME_3");
	public static final DefineRefExpr PLAYER_GAME_4  = new DefineRefExpr("PLAYER_GAME_4");
	public static final DefineRefExpr PLAYER_GAME_5  = new DefineRefExpr("PLAYER_GAME_5");
	public static final DefineRefExpr PLAYER_GAME_6  = new DefineRefExpr("PLAYER_GAME_6");
	public static final DefineRefExpr PLAYER_GAME_7  = new DefineRefExpr("PLAYER_GAME_7");
	public static final DefineRefExpr PLAYER_GAME_8  = new DefineRefExpr("PLAYER_GAME_8");
	
	 
}
