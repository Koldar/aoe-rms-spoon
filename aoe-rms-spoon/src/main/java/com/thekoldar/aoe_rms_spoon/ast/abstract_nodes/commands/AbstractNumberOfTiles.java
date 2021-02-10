package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractNumberOfTiles extends AbstractRMSSingleOptionalIntArgumentCommand {
	
	protected AbstractNumberOfTiles() {
		super(RMSNodeType.NUMBER_OF_TILES);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return 100;
	}

	@Override
	public String getArgumentComment() {
		return "Fixed number of tiles that the land should grow by";
	}

	@Override
	public String getComment() {
		return "Total size of the land is number_of_tiles in addition to the square origin specified by base_size";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentGreaterThan(this.getArgument(0), 0);

		var behaviour = this.getSiblings()
			.select(n -> n.getNodeType().equals(RMSNodeType.BEHAVIOR_VERSION))
			.collect(n -> (AbstractRMSCommand)n)
			.select(n -> n.getArgumentAsInt(0, input).contains(1))
			.isEmpty();
		
		if (behaviour) {
			this.infoCmd("When using this command alongside behavior_version set to 1, the square origin is included in the total number_of tiles, resulting in smaller lands");
		}
		
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)) {
			this.infoCmd("the amount specified in number_of_tiles is given to each player");
		}
		
		return this.semanticCheckChildren(input);
	}

}
