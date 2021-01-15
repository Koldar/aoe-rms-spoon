package com.thekoldar.aoe_rms_spoon.models.conditionals;

import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DefineRefExpr;

public class GameModes {

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
