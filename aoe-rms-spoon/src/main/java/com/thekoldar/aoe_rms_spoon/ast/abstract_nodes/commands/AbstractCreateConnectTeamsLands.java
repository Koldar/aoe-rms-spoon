package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
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
public class AbstractCreateConnectTeamsLands extends AbstractRMSSingleRequiredDictArgumentCommand {

	protected AbstractCreateConnectTeamsLands() {
		super(RMSNodeType.CREATE_CONNECT_TEAMS_LANDS);
	}

	@Override
	public String getArgumentName() {
		return "specifics";
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Connections will be generated between the origins of player lands belonging to members of the same team.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.DEFAULT_TERRAIN_REPLACEMENT, RMSNodeType.REPLACE_TERRAIN, RMSNodeType.TERRAIN_COST, RMSNodeType.TERRAIN_SIZE);
		
		return result.merge(this.semanticCheckChildren(input));
	}

}
