package com.thekoldar.aoe_rms_spoon.models.conditionals;

import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DefineRefExpr;

public class StartingResources {

	public static final DefineRefExpr HIGH_RESOURCES = new DefineRefExpr("HIGH_RESOURCES"); 
	public static final DefineRefExpr MEDIUM_RESOURCES = new DefineRefExpr("MEDIUM_RESOURCES");
	public static final DefineRefExpr LOW_RESOURCES = new DefineRefExpr("LOW_RESOURCES");
	public static final DefineRefExpr DEFAULT_RESOURCES  = new DefineRefExpr("DEFAULT_RESOURCES");
	public static final DefineRefExpr INFINITE_RESOURCES  = new DefineRefExpr("INFINITE_RESOURCES"); 
	public static final DefineRefExpr RANDOM_RESOURCES = new DefineRefExpr("RANDOM_RESOURCES");
}
