package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.expr.ConstRefExpr;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * List of all the constants names provided by F_seasons.inc.
 * We have introduced this class since some names are not in english
 * @author massi
 *
 */
public class SeasonProvidedConstants {
	
	/**
	 * get all the const name that F_seasons.inc generates
	 * @return
	 */
	public static RichIterable<String> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(SeasonProvidedConstants.class, String.class);
	}

	public static final String LAYER_MAIN = "LAYER_A";
	public static final String LAYER_2 = "LAYER_B";
	public static final String LAYER_3 = "LAYER_C";
	public static final String LAYER_4 = "LAYER_E";
	public static final String LAYER_5 = "LAYER_F";
	
	public static final String FOREST_MAIN = "WOODIES";
	public static final String FOREST_2 = "WOODIES_B";
	public static final String FOREST_3 = "WOODIES_C";
	public static final String STRAGGLER = "STRAGGLER";
	
	public static final String WATER = "VODA";
	public static final String SHALLOWS = "MELCINA";
	
	public static final String ROAD = "CESTA";
	
	public static final String DEER = "HUNTABLE";
	
	public static final String SHEEP1 = "HERDABLE_A";
	public static final String SHEEP2 = "HERDABLE_B";
	
	public static final String BOAR1 = "LURABLE_A";
	public static final String BOAR2 = "LURABLE_B";
	
	public static final String WOLF1 = "PREDATOR_A";
	public static final String WOLF2 = "PREDATOR_B";
	
	public static final String HAWK1 = "BIRDS_A";
	public static final String HAWK2 = "BIRDS_B";
	
	public static final String OCEAN_FISH1 = "FISH_A";
	public static final String OCEAN_FISH2 = "FISH_B";
	
	public static final String SHORE_FISH = "MELKARYBA";
	
	public static final String BERRIES = "KERICEK";
	 
	
}
