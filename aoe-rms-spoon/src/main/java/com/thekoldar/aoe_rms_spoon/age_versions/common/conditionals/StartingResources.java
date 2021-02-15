package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Convenience class containing all the resources type available in the lobby
 * @author massi
 *
 */
public class StartingResources {
	
	/**
	 * get all the values in constants
	 * @return iterable
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(StartingResources.class, DefineRefExpr.class);
	}

	public static final DefineRefExpr HIGH_RESOURCES = new DefineRefExpr("HIGH_RESOURCES"); 
	public static final DefineRefExpr MEDIUM_RESOURCES = new DefineRefExpr("MEDIUM_RESOURCES");
	public static final DefineRefExpr LOW_RESOURCES = new DefineRefExpr("LOW_RESOURCES");
	public static final DefineRefExpr DEFAULT_RESOURCES  = new DefineRefExpr("DEFAULT_RESOURCES");
	public static final DefineRefExpr INFINITE_RESOURCES  = new DefineRefExpr("INFINITE_RESOURCES"); 
	public static final DefineRefExpr RANDOM_RESOURCES = new DefineRefExpr("RANDOM_RESOURCES");
	
}
