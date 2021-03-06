package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
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
public abstract class AbstractCreatePlayerLands extends AbstractRMSSingleRequiredDictArgumentCommand {

	protected AbstractCreatePlayerLands() {
		super(RMSNodeType.CREATE_PLAYER_LANDS);
	}
	
	@Override
	public String getArgumentName() {
		return "terrain specifics";
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.TERRAIN_TYPE, RMSNodeType.LAND_PERCENT, RMSNodeType.NUMBER_OF_TILES, RMSNodeType.BASE_SIZE, RMSNodeType.CIRCLE_RADIUS, RMSNodeType.CIRCLE_PLACEMENT, RMSNodeType.LEFT_BORDER, RMSNodeType.RIGHT_BORDER, RMSNodeType.TOP_BORDER, RMSNodeType.BOTTOM_BORDER, RMSNodeType.BORDER_FUZZINESS, RMSNodeType.CLUMPING_FACTOR, RMSNodeType.BASE_ELEVATION, RMSNodeType.ZONE, RMSNodeType.SET_ZONE_BY_TEAM, RMSNodeType.SET_ZONE_RANDOMLY, RMSNodeType.OTHER_ZONE_AVOIDANCE_DISTANCE);
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}
