package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class PlayerInTeams {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(PlayerInTeams.class, DefineRefExpr.class);
	}

	public static final DefineRefExpr PLAYER1_TEAM0  = new DefineRefExpr("PLAYER1_TEAM0");
	public static final DefineRefExpr PLAYER1_TEAM1  = new DefineRefExpr("PLAYER1_TEAM1");
	public static final DefineRefExpr PLAYER1_TEAM2  = new DefineRefExpr("PLAYER1_TEAM2");
	public static final DefineRefExpr PLAYER1_TEAM3  = new DefineRefExpr("PLAYER1_TEAM3");
	public static final DefineRefExpr PLAYER1_TEAM4  = new DefineRefExpr("PLAYER1_TEAM4");
	
	public static final DefineRefExpr PLAYER2_TEAM0  = new DefineRefExpr("PLAYER2_TEAM0");
	public static final DefineRefExpr PLAYER2_TEAM1  = new DefineRefExpr("PLAYER2_TEAM1");
	public static final DefineRefExpr PLAYER2_TEAM2  = new DefineRefExpr("PLAYER2_TEAM2");
	public static final DefineRefExpr PLAYER2_TEAM3  = new DefineRefExpr("PLAYER2_TEAM3");
	public static final DefineRefExpr PLAYER2_TEAM4  = new DefineRefExpr("PLAYER2_TEAM4");
	
	public static final DefineRefExpr PLAYER3_TEAM0  = new DefineRefExpr("PLAYER3_TEAM0");
	public static final DefineRefExpr PLAYER3_TEAM1  = new DefineRefExpr("PLAYER3_TEAM1");
	public static final DefineRefExpr PLAYER3_TEAM2  = new DefineRefExpr("PLAYER3_TEAM2");
	public static final DefineRefExpr PLAYER3_TEAM3  = new DefineRefExpr("PLAYER3_TEAM3");
	public static final DefineRefExpr PLAYER3_TEAM4  = new DefineRefExpr("PLAYER3_TEAM4");
	
	public static final DefineRefExpr PLAYER4_TEAM0  = new DefineRefExpr("PLAYER4_TEAM0");
	public static final DefineRefExpr PLAYER4_TEAM1  = new DefineRefExpr("PLAYER4_TEAM1");
	public static final DefineRefExpr PLAYER4_TEAM2  = new DefineRefExpr("PLAYER4_TEAM2");
	public static final DefineRefExpr PLAYER4_TEAM3  = new DefineRefExpr("PLAYER4_TEAM3");
	public static final DefineRefExpr PLAYER4_TEAM4  = new DefineRefExpr("PLAYER4_TEAM4");
	
	public static final DefineRefExpr PLAYER5_TEAM0  = new DefineRefExpr("PLAYER5_TEAM0");
	public static final DefineRefExpr PLAYER5_TEAM1  = new DefineRefExpr("PLAYER5_TEAM1");
	public static final DefineRefExpr PLAYER5_TEAM2  = new DefineRefExpr("PLAYER5_TEAM2");
	public static final DefineRefExpr PLAYER5_TEAM3  = new DefineRefExpr("PLAYER5_TEAM3");
	public static final DefineRefExpr PLAYER5_TEAM4  = new DefineRefExpr("PLAYER5_TEAM4");
	
	public static final DefineRefExpr PLAYER6_TEAM0  = new DefineRefExpr("PLAYER6_TEAM0");
	public static final DefineRefExpr PLAYER6_TEAM1  = new DefineRefExpr("PLAYER6_TEAM1");
	public static final DefineRefExpr PLAYER6_TEAM2  = new DefineRefExpr("PLAYER6_TEAM2");
	public static final DefineRefExpr PLAYER6_TEAM3  = new DefineRefExpr("PLAYER6_TEAM3");
	public static final DefineRefExpr PLAYER6_TEAM4  = new DefineRefExpr("PLAYER6_TEAM4");
	
	public static final DefineRefExpr PLAYER7_TEAM0  = new DefineRefExpr("PLAYER7_TEAM0");
	public static final DefineRefExpr PLAYER7_TEAM1  = new DefineRefExpr("PLAYER7_TEAM1");
	public static final DefineRefExpr PLAYER7_TEAM2  = new DefineRefExpr("PLAYER7_TEAM2");
	public static final DefineRefExpr PLAYER7_TEAM3  = new DefineRefExpr("PLAYER7_TEAM3");
	public static final DefineRefExpr PLAYER7_TEAM4  = new DefineRefExpr("PLAYER7_TEAM4");
	
	public static final DefineRefExpr PLAYER8_TEAM0  = new DefineRefExpr("PLAYER8_TEAM0");
	public static final DefineRefExpr PLAYER8_TEAM1  = new DefineRefExpr("PLAYER8_TEAM1");
	public static final DefineRefExpr PLAYER8_TEAM2  = new DefineRefExpr("PLAYER8_TEAM2");
	public static final DefineRefExpr PLAYER8_TEAM3  = new DefineRefExpr("PLAYER8_TEAM3");
	public static final DefineRefExpr PLAYER8_TEAM4  = new DefineRefExpr("PLAYER8_TEAM4");
	
}
