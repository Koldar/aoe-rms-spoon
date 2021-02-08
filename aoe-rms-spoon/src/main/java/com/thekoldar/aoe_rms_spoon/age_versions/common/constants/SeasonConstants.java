package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import java.lang.reflect.Modifier;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * avaialble season types. Available only in DE
 * 
 * Altering this value will alter the default herdable, predators, hawks
 * 
 * @author massi
 *
 */
public class SeasonConstants {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(SeasonConstants.class, RMSConstSymbol.class);
	}

	public static final RMSConstSymbol AUTUMN  = new RMSConstSymbol("CC_AUTUMN", 1);
	public static final RMSConstSymbol WINTER  = new RMSConstSymbol("CC_WINTER", 2);
	public static final RMSConstSymbol JUNGLE  = new RMSConstSymbol("CC_JUNGLE", 3);
	public static final RMSConstSymbol DESERT  = new RMSConstSymbol("CC_DESERT", 4);
}
