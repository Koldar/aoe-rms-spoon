package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import java.lang.reflect.Modifier;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Convenience class containing all the const related to technologies
 * @author massi
 *
 */
public class TechnologyConstants {

	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(TechnologyConstants.class, RMSConstSymbol.class);
	}
	
	
}
