package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSRequiredIntRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
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
public abstract class AbstractReplaceTerrain extends AbstractRMSRequiredIntRequiredIntArgumentCommand {

	protected AbstractReplaceTerrain() {
		super(RMSNodeType.REPLACE_TERRAIN);
	}
	
	@Override
	public String getArgument1Name() {
		return "terrain_type_old";
	}

	@Override
	public String getArgument1Comment() {
		return "if a connection goes on this terrain, replace it with terrain_type_new";
	}

	@Override
	public String getArgument2Name() {
		return "terrain_type_new";
	}

	@Override
	public String getArgument2Comment() {
		return "replace terrain";
	}

	@Override
	public String getComment() {
		return "If the specified terrain is part of the connection, replace it with the new terrain specified. Connections can pass through terrains even if they are not specified";
	}
	
	public AbstractExpressionNode getOldTerrain() {
		return (AbstractExpressionNode)this.getArgument(0);
	}
	
	public AbstractExpressionNode getNewTerrain() {
		return (AbstractExpressionNode)this.getArgument(1);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		var oldTerrain = this.getOldTerrain().getAsLong(input);
		
		//fetch a replace terain occuring before this one that replace a terrain into oldTerrain (since probably the developers thinks they will concatenate
		var previousReplaceTerrain = this
				.getPreviousSiblings()
				.select(pair -> pair.getTwo().getNodeType().equals(RMSNodeType.REPLACE_TERRAIN))
				.collect(pair -> (AbstractReplaceTerrain)pair.getTwo())
				.select(n -> !LongSetPossible.of(n.getNewTerrain().getAsLong(input)).intersect(oldTerrain).isEmpty())
				.toList()
			;
		if (!previousReplaceTerrain.isEmpty()) {
			var previousOldTerrain = ((AbstractReplaceTerrain)previousReplaceTerrain.getFirst()).getOldTerrain().getAsLong(input);
			var previousNewTerrain = ((AbstractReplaceTerrain)previousReplaceTerrain.getFirst()).getNewTerrain().getAsLong(input);
			var newTerrain = this.getNewTerrain().getAsLong(input);
			result.addWarning(this, RMSErrorCode.CONFLICTING_COMMANDS, "DE BUG: Note that the %s command consider only the original terrain before any replacement. So this concatenation of %s from %s to %s and from %s to %s will not work", RMSNodeType.REPLACE_TERRAIN, RMSNodeType.REPLACE_TERRAIN, previousOldTerrain, previousNewTerrain, oldTerrain, newTerrain);
		}
		
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}
