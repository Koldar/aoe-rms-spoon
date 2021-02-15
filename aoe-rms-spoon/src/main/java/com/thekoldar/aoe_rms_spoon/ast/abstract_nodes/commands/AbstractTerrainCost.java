package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSRequiredIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSRequiredIntRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
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
public abstract class AbstractTerrainCost extends AbstractRMSRequiredIntOptionalIntArgumentCommand {

	protected AbstractTerrainCost() {
		super(RMSNodeType.TERRAIN_COST);
	}
	
	@Override
	public String getArgument1Name() {
		return "terrain_type";
	}

	@Override
	public String getArgument1Comment() {
		return "";
	}

	@Override
	public String getArgument2Name() {
		return "cost";
	}
	
	@Override
	public Object getArgument2DefaultValue() {
		return 1;
	}

	@Override
	public String getArgument2Comment() {
		return "";
	}

	@Override
	public String getComment() {
		return "The cost of having the connection run through the specified terrain";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureArgumentIsBetween(this.getArgument(0), 0L, 4294967296L, true, true);
		
		if (this.getArgument(0).getAsLong(input).contains(0L)) {
			this.infoCmd("setting terrain_cost to 0 means that the terrain will be avoided at all costs by the connection algorithm");
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}
