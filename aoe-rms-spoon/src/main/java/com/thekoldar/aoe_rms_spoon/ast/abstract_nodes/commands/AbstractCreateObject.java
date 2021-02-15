package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSRequiredIntRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public class AbstractCreateObject extends AbstractRMSCommand {

	protected AbstractCreateObject() {
		super(RMSNodeType.CREATE_OBJECT);
	}

	@Override
	public String getComment() {
		return "Place the specified object, according to the chosen attributes.";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.fixedSize.of(
				CommandFormalArgument.requiredInt("object_type", ""),
				CommandFormalArgument.requiredDict("attributes", "")
		).toImmutable();
	}
	
	/**
	 * the object that we need to create
	 * @param input
	 * @return
	 */
	public IPossibleValue<Long> getInvolvedObject(SemanticCheckInput input) {
		return this.getArgumentAsInt(0, input);
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, 
				RMSNodeType.NUMBER_OF_OBJECTS,
				RMSNodeType.NUMBER_OF_GROUPS,
				RMSNodeType.GROUP_VARIANCE,
				RMSNodeType.RESOURCE_DELTA,
				RMSNodeType.SECOND_OBJECT,
				RMSNodeType.SET_SCALING_TO_MAP_SIZE,
				RMSNodeType.SET_SCALING_TO_PLAYER_NUMBER,
				RMSNodeType.SET_PLACE_FOR_EVERY_PLAYER,
				RMSNodeType.PLACE_ON_SPECIFIC_LAND_ID,
				RMSNodeType.SET_GAIA_OBJECT_ONLY,
				RMSNodeType.SET_GAIA_UNCONVERTIBLE,
				RMSNodeType.GROUP_PLACEMENT_RADIUS,
				RMSNodeType.SET_TIGHT_GROUPING,
				RMSNodeType.SET_LOOSE_GROUPING,
				RMSNodeType.TERRAIN_TO_PLACE_ON,
				RMSNodeType.LAYER_TO_PLACE_ON,
				RMSNodeType.PLACE_ON_FOREST_ZONE,
				RMSNodeType.AVOID_FOREST_ZONE,
				RMSNodeType.AVOID_CLIFF_ZONE,
				RMSNodeType.MIN_DISTANCE_TO_PLAYERS,
				RMSNodeType.MAX_DISTANCE_TO_PLAYERS,
				RMSNodeType.MIN_DISTANCE_GROUP_PLACEMENT,
				RMSNodeType.TEMP_MIN_DISTANCE_GROUP_PLACEMENT,
				RMSNodeType.FIND_CLOSEST,
				RMSNodeType.FORCE_PLACEMENT,
				RMSNodeType.ACTOR_AREA,
				RMSNodeType.ACTOR_AREA_RADIUS,
				RMSNodeType.ACTOR_AREA_TO_PLACE_IN,
				RMSNodeType.AVOID_ACTOR_AREA,
				RMSNodeType.AVOID_ALL_ACTOR_AREAS
		);
		
		return result;
	}

}
