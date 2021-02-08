package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class SeasonDefines {

	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<DefineRefExpr> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(SeasonDefines.class, DefineRefExpr.class);
	}

	public static final DefineRefExpr PH_ALPINE = new DefineRefExpr("PH_ALPINE");
	public static final DefineRefExpr PH_ALPINE_B = new DefineRefExpr("PH_ALPINE_B");
	public static final DefineRefExpr PH_SPRING = new DefineRefExpr("PH_SPRING");
	public static final DefineRefExpr PH_SPRING_C = new DefineRefExpr("PH_SPRING_C");
	public static final DefineRefExpr PH_MEDISOUTH = new DefineRefExpr("PH_MEDISOUTH");
	public static final DefineRefExpr PH_SPRING_B = new DefineRefExpr("PH_SPRING_B");
	public static final DefineRefExpr PH_TROPHICALSOUTH = new DefineRefExpr("PH_TROPHICALSOUTH");
	public static final DefineRefExpr PH_TROPHICALSOUTH_B = new DefineRefExpr("PH_TROPHICALSOUTH_B");
	public static final DefineRefExpr PH_TROPHICALEAST = new DefineRefExpr("PH_TROPHICALEAST");
	public static final DefineRefExpr PH_DESERT = new DefineRefExpr("PH_DESERT");
	public static final DefineRefExpr PH_AFRICAN = new DefineRefExpr("PH_AFRICAN");
	public static final DefineRefExpr PH_ASIAN_B = new DefineRefExpr("PH_ASIAN_B"); 
	public static final DefineRefExpr PH_ASIAN = new DefineRefExpr("PH_ASIAN"); 
	public static final DefineRefExpr PH_AUTUMN = new DefineRefExpr("PH_AUTUMN");
	public static final DefineRefExpr PH_AUTUMN_B = new DefineRefExpr("PH_AUTUMN_B");
	public static final DefineRefExpr PH_FROZEN = new DefineRefExpr("PH_FROZEN");
	public static final DefineRefExpr PH_AFRICAN_B = new DefineRefExpr("PH_AFRICAN_B");
	public static final DefineRefExpr PH_AFRICAN_C = new DefineRefExpr("PH_AFRICAN_C");
	public static final DefineRefExpr PH_AFRICAN_D = new DefineRefExpr("PH_AFRICAN_D");
	public static final DefineRefExpr PH_AFRICAN_E = new DefineRefExpr("PH_AFRICAN_E");
}
