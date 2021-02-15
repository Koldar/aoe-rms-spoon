package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
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
public abstract class AbstractRightBorder extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractRightBorder() {
		super(RMSNodeType.RIGHT_BORDER);
	}

	@Override
	public String getArgumentName() {
		return "percentage";
	}

	@Override
	public Object getDefaultValue() {
		return 0;
	}

	@Override
	public String getArgumentComment() {
		return "top is north-east";
	}

	@Override
	public String getComment() {
		return "Specify a percentage of map width for land placement and growth to stay away from a given border.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 97, true, true);
		
		this.infoCmd("Using a borders. This means that the genrated lands will not be square like but octagon like");
		
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)) {
			this.infoCmd("The norders will shift the circle that is used to place players on the map");
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}
