package com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals;


import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.models.MapSize;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Available map sizes
 * @author massi
 *
 */
public class MapSizes {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<MapSize> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(MapSizes.class, MapSize.class);
	}

	public static final MapSize TINY_MAP = new MapSize("Tiny", 2, "TINY_MAP", 120); 
	public static final MapSize SMALL_MAP = new MapSize("Small", 3, "SMALL_MAP", 144);
	public static final MapSize MEDIUM_MAP = new MapSize("Medium", 4, "MEDIUM_MAP", 168);
	public static final MapSize LARGE_MAP = new MapSize("Normal", 6, "LARGE_MAP", 200);
	public static final MapSize HUGE_MAP  = new MapSize("Large", 8, "HUGE_MAP", 220);
	public static final MapSize GIGANTIC_MAP = new MapSize("Giant", 8, "GIGANTIC_MAP", 240);
	public static final MapSize LUDIKRIS_MAP = new MapSize("Ludicrous", 8, "LUDIKRIS_MAP", 480);
	
	
	
}
