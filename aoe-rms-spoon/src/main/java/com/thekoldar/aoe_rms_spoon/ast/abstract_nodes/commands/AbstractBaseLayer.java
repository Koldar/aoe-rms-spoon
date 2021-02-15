package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
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
public abstract class AbstractBaseLayer extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractBaseLayer() {
		super(RMSNodeType.BASE_LAYER);
	}

	@Override
	public String getArgumentName() {
		return "terrain_type";
	}

	@Override
	public Object getDefaultValue() {
		return "no layered terrain";
	}

	@Override
	public String getArgumentComment() {
		return "layer to use to cover";
	}

	@Override
	public String getComment() {
		return "Use this attribute in addition to base_terrain if (and only if) you specified a layer for the map base terrain at the beginning of <LAND_GENERATION>";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		
		if (this.isUnderNodeWithTypes(RMSNodeType.ELEVATION_GENERATION)) {
			//fetch the base_terrain in LAND_GENERATION section
			var isBaseTerrainInLandGenerationIsPresent = !this.getNodesOfTypes(RMSNodeType.BASE_TERRAIN).select(n -> n.isUnderNodeWithTypes(RMSNodeType.LAND_GENERATION)).isEmpty();
			if (!isBaseTerrainInLandGenerationIsPresent) {
				result.addError(RMSErrorCode.EXPECTED_REQUIRED_COMMAND, "When using %s it is required thant %s is present in %s as well!", this, RMSNodeType.BASE_TERRAIN, RMSNodeType.LAND_GENERATION);
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}
