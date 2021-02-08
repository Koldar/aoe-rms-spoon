package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.MapConstants;
import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class AdditionalLobbySettings {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(AdditionalLobbySettings.class, DefineRefExpr.class);
	}

	public static final DefineRefExpr FIXED_POSITIONS  = new DefineRefExpr("FIXED_POSITIONS"); 
	public static final DefineRefExpr TURBO_MODE  = new DefineRefExpr("TURBO_MODE");
	public static final DefineRefExpr TEAM_POSITIONS  = new DefineRefExpr("TEAM_POSITIONS");
	public static final DefineRefExpr FULL_TECH_TREE  = new DefineRefExpr("FULL_TECH_TREE");
	public static final DefineRefExpr AI_PLAYERS   = new DefineRefExpr("AI_PLAYERS"); 
}
