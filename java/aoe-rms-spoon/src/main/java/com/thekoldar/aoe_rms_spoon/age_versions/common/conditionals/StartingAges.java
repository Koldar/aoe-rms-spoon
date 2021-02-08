package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class StartingAges {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(StartingAges.class, DefineRefExpr.class);
	}

	public static final DefineRefExpr DARK_AGE_START = new DefineRefExpr("DARK_AGE_START"); 
	public static final DefineRefExpr FEUDAL_AGE_START = new DefineRefExpr("FEUDAL_AGE_START");
	public static final DefineRefExpr CASTLE_AGE_START = new DefineRefExpr("CASTLE_AGE_START");
	public static final DefineRefExpr IMPERIAL_AGE_START  = new DefineRefExpr("IMPERIAL_AGE_START");
	public static final DefineRefExpr POST_IMPERIAL_AGE_START  = new DefineRefExpr("POST_IMPERIAL_AGE_START"); 
}
