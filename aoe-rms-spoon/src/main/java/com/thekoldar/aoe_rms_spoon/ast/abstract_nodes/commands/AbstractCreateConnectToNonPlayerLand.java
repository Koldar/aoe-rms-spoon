package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.impl.factory.Sets;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;
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
public class AbstractCreateConnectToNonPlayerLand extends AbstractRMSSingleRequiredDictArgumentCommand {

	protected AbstractCreateConnectToNonPlayerLand() {
		super(RMSNodeType.CREATE_CONNECT_SAME_LAND_ZONES);
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
		return "Connect all player lands to all neutral lands, but do not directly generate connections between individual players.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.DEFAULT_TERRAIN_REPLACEMENT, RMSNodeType.REPLACE_TERRAIN, RMSNodeType.TERRAIN_COST, RMSNodeType.TERRAIN_SIZE);
		
		var s = Sets.mutable.of(RMSNodeType.CREATE_CONNECT_ALL_LANDS, RMSNodeType.CREATE_CONNECT_ALL_PLAYERS_LAND, RMSNodeType.CREATE_CONNECT_SAME_LAND_ZONES, RMSNodeType.CREATE_CONNECT_TEAMS_LANDS);
		var nextCreateConnections = this.getNextSiblingSatisfying((i, n) -> s.contains(n.getNodeType())).toList();
		if (!nextCreateConnections.isEmpty()) {
			result.addError(this, RMSErrorCode.CONFLICTING_COMMANDS, "Due to a DE bug, calls to %s will block all the next connection generations (in this case %s). ", this.getNodeType(), nextCreateConnections.collect(n -> n.getTwo().getNodeType()).makeString());
		}
		if (!this.getPreviousSiblingSatisfying((i, n) -> n.getNodeType().equals(RMSNodeType.CREATE_CONNECT_TEAMS_LANDS)).isEmpty()) {
			//this command is after create_copnnect_teams_lands
			var nextCreateTeamConnections = this.getNextSiblingSatisfying((i, n) -> n.getNodeType().equals(RMSNodeType.CREATE_CONNECT_TEAMS_LANDS)).toList();
			if (!nextCreateTeamConnections.isEmpty()) {
				//there is some team connection after this command
				result.addError(this, RMSErrorCode.CONFLICTING_COMMANDS, "Due to a DE bug, calls to %s will block all the next team connection generations (excepts thos involving player 1) (in this case %s) when used after %s. ", this.getNodeType(), nextCreateConnections.collect(n -> n.getTwo().getNodeType()).makeString(), RMSNodeType.CREATE_CONNECT_TEAMS_LANDS);
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}
