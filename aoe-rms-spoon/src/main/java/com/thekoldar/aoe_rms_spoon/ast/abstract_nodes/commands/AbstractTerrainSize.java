package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
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
public abstract class AbstractTerrainSize extends AbstractRMSCommand {

	protected AbstractTerrainSize() {
		super(RMSNodeType.TERRAIN_SIZE);
	}
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.fixedSize.of(
				CommandFormalArgument.requiredInt("terrain_type", ""),
				CommandFormalArgument.optionalInt("radius", 1, ""),
				CommandFormalArgument.optionalInt("variance", 0, "")
		).toImmutable();
	}
	
	public LongSetPossible getTerrainType(SemanticCheckInput input) {
		return LongSetPossible.of(this.getArgumentAsInt(0, input));
	}

	public LongSetPossible getRadius(SemanticCheckInput input) {
		return LongSetPossible.of(this.getArgumentAsInt(1, input));
	}
	
	public LongSetPossible getVariance(SemanticCheckInput input) {
		return LongSetPossible.of(this.getArgumentAsInt(2, input));
	}

	@Override
	public String getComment() {
		return "When a connection passes through a tile of the specified terrain, the area within radius +/- variance will be subject to replace_terrain / default_terrain_replacement and terrains will be replaced accordingly.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		if (this.getRadius(input).contains(0L)) {
			result.addWarning(this, RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "Setting the radius to 0 will still generate a single-tile width path");
		}
		
		if (this.getVariance(input).areAtLeastOneGreaterThanAnyOf(this.getRadius(input))) {
			result.addWarning(this, RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "Setting the variance to be greater than the radius will avoid generating some parts of the road");
		}
		
		return result;
	}
	
	

}
