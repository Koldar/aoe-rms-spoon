package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class GameVersions {

	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(GameVersions.class, DefineRefExpr.class);
	}
	
	/**
	 * UP 1.4+
	 */
	public static final DefineRefExpr UP_AVAILABLE = new DefineRefExpr("UP_AVAILABLE");
	/**
	 * UP 1.5
	 */
	public static final DefineRefExpr UP_EXTENSION = new DefineRefExpr("UP_EXTENSION");
	/**
	 * De
	 */
	public static final DefineRefExpr DE_AVAILABLE = new DefineRefExpr("DE_AVAILABLE");
	

}
