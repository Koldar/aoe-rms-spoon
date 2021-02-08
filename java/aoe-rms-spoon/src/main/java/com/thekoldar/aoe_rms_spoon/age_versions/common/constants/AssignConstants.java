package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractAssignTo;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * constants used in {@link AbstractAssignTo}
 * @author massi
 *
 */
public class AssignConstants {

	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(AssignConstants.class, RMSConstSymbol.class);
	}
	
	public static final RMSConstSymbol AT_PLAYER  = new RMSConstSymbol("AT_PLAYER", 0);
	public static final RMSConstSymbol AT_COLOR  = new RMSConstSymbol("AT_COLOR", 1);
	public static final RMSConstSymbol AT_TEAM  = new RMSConstSymbol("AT_TEAM", 2);
	
}
