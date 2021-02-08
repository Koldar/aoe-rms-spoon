package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class TeamCounts {

	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(TeamCounts.class, DefineRefExpr.class);
	}
	
	public static final DefineRefExpr TEAM_GAME_1  = new DefineRefExpr("TEAM_GAME_1"); 
	public static final DefineRefExpr TEAM_GAME_2  = new DefineRefExpr("TEAM_GAME_2");
	public static final DefineRefExpr TEAM_GAME_3  = new DefineRefExpr("TEAM_GAME_3");
	public static final DefineRefExpr TEAM_GAME_4  = new DefineRefExpr("TEAM_GAME_4");
	public static final DefineRefExpr TEAM_GAME_0  = new DefineRefExpr("TEAM_GAME_0");
	
	
	 
}
