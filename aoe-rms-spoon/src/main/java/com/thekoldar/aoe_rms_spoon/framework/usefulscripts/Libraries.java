package com.thekoldar.aoe_rms_spoon.framework.usefulscripts;

import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.AssignConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.AttributesConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.ClassConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.EffectConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.MapConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.ResourcesConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.SeasonConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.TechnologyConstants;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractAssignTo;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;

/**
 * Static lcass containing lines fo code implementing some common AST you may in several of your scripts
 * @author massi
 *
 */
public class Libraries {

	/**
	 * Defined {@code TRUE} and {@code FALSE} constants.
	 * @param aoe age of empires version
	 * @return node representing the stdbool library
	 */
	public static IRMSNode stdBool(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
		.addStatement(aoe.comment("***** STD BOOL *****"))
		.addStatement(aoe.constant("TRUE", 1))
		.addStatement(aoe.constant("FALSE", 0))
		.addStatement(aoe.comment("***** END STD BOOL *****"))
		;
	}
	
	/**
	 * Add all the constants that we know may be useful for the developer. If you add this field you should not need to define other stuff by yourself
	 * @param aoe age of empires version
	 * @return node repersenting standard const and defines that will be added to the RMS script
	 */
	public static IRMSNode setStandardConstAndDefine(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
				.addStatement(Libraries.addAssignConstants(aoe))
				.addStatement(Libraries.addAttributesConstants(aoe))
				.addStatement(Libraries.addClassConstants(aoe))
				.addStatement(Libraries.addEffectsConstants(aoe))
				.addStatement(Libraries.addMapConstants(aoe))
				.addStatement(Libraries.addResourcesConstants(aoe))
				.addStatement(Libraries.addSeasonConstants(aoe))
				.addStatement(Libraries.addTechnologyConstants(aoe))
				;
	}
	
	/**
	 * Add to your script all the constants related to {@link AbstractAssignTo} command
	 * 
	 * @param aoe age of empires version
	 * @return a node that needs to be addeed yto your script representing all the constants defined
	 */
	public static IRMSNode addAssignConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** ASSIGN *****"))
			.addStatements(AssignConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END ASSIGN *****"))
		;
	}
	
	/**
	 * Add to your script all the constants relative to the attributes
	 * 
	 * @param aoe age of empires version
	 * @return a node representing the constants define. Needs to be added to your AST
	 */
	public static IRMSNode addAttributesConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** ATTRIBUTES *****"))
			.addStatements(AttributesConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END ATTRIBUTES *****"))
		;
	}
	
	/**
	 * Add to your script all the constants relative to the class constants
	 * 
	 * @param aoe age of empires version
	 * @return a node representing the constants define. Needs to be added to your AST
	 */
	public static IRMSNode addClassConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** CLASS *****"))
			.addStatements(ClassConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END CLASS *****"))
		;
	}
	
	/**
	 * Add to your script all the constants relative to the effect constants
	 * 
	 * @param aoe age of empires version
	 * @return a node representing the constants define. Needs to be added to your AST
	 */
	public static IRMSNode addEffectsConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** EFFECT *****"))
			.addStatements(EffectConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END EFFECT*****"))
		;
	}
	
	/**
	 * Add to your script all the constants relative to the map constants
	 * 
	 * @param aoe age of empires version
	 * @return a node representing the constants define. Needs to be added to your AST
	 */
	public static IRMSNode addMapConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** MAP *****"))
			.addStatements(MapConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END MAP *****"))
		;
	}
	
	/**
	 * Add to your script all the constants relative to the resources constants
	 * 
	 * @param aoe age of empires version
	 * @return a node representing the constants define. Needs to be added to your AST
	 */
	public static IRMSNode addResourcesConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** RESOURCES *****"))
			.addStatements(ResourcesConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END RESOURCES *****"))
		;
	}
	
	/**
	 * Add to your script all the constants relative to the seasons constants
	 * 
	 * @param aoe age of empires version
	 * @return a node representing the constants define. Needs to be added to your AST
	 */
	public static IRMSNode addSeasonConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** SEASON *****"))
			.addStatements(SeasonConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END SEASON*****"))
		;
	}
	
	/**
	 * Add to your script all the constants relative to the technology constants
	 * 
	 * @param aoe age of empires version
	 * @return a node representing the constants define. Needs to be added to your AST
	 */
	public static IRMSNode addTechnologyConstants(AbstractAoEVersion aoe) {
		return aoe.multiplexer()
			.addStatement(aoe.comment("***** TECHNOLOGY *****"))
			.addStatements(TechnologyConstants.all().collect(c -> aoe.constant(c)))
			.addStatement(aoe.comment("***** END TECHNOLOGY *****"))
		;
	}
}
