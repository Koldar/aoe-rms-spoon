package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections;

import java.util.function.Consumer;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddStandard;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * The abstract version of the associated RMS section. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractConnectionGeneration extends AbstractRMSSection implements IAddStandard<AbstractConnectionGeneration> {


	public AbstractConnectionGeneration() {
		super(RMSNodeType.CONNECTION_GENERATION);
	}
	
	
	
	// createConnectAllPlayersLand
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.CREATE_CONNECT_ALL_PLAYERS_LAND, RMSNodeType.CREATE_CONNECT_TEAMS_LANDS, RMSNodeType.CREATE_CONNECT_ALL_LANDS, RMSNodeType.CREATE_CONNECT_SAME_LAND_ZONES, RMSNodeType.CREATE_CONNECT_TO_NONPLAYER_LAND);
		
		return result.merge(this.semanticCheckChildren(input));
	}



	public AbstractConnectionGeneration createConnectAllPlayersLand(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectAllPlayersLand().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectAllPlayersLand(Consumer<DictExpr> dict) {
		var d = RMSExprs.dict();
		this.createConnectAllPlayersLand(d);
		dict.accept(d);
		return this;
	}
	
	// createConnectTeamsLands
	
	public AbstractConnectionGeneration createConnectTeamsLands(Consumer<DictExpr> dict) {
		var d = RMSExprs.dict();
		this.createConnectTeamsLands(d);
		dict.accept(d);
		return this;
	}
	
	public AbstractConnectionGeneration createConnectTeamsLands(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectTeamLands().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectTeamsLands(IRMSNode... attributes) {
		return this.createConnectTeamsLands(RMSExprs.dict(attributes));
	}
	
	// createConnectAllLands
	
	public AbstractConnectionGeneration createConnectAllLands(Consumer<DictExpr> dict) {
		var d = RMSExprs.dict();
		this.createConnectAllLands(d);
		dict.accept(d);
		return this;
	}
	
	public AbstractConnectionGeneration createConnectAllLands(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectAllLands().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectAllLands(IRMSNode... attributes) {
		return this.createConnectAllLands(RMSExprs.dict(attributes));
	}
	
	// createConnectSameLandZones
	
	public AbstractConnectionGeneration createConnectSameLandZones(Consumer<DictExpr> dict) {
		var d = RMSExprs.dict();
		this.createConnectSameLandZones(d);
		dict.accept(d);
		return this;
	}
	
	public AbstractConnectionGeneration createConnectSameLandZones(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectSameLandZones().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectSameLandZones(IRMSNode... attributes) {
		return this.createConnectSameLandZones(RMSExprs.dict(attributes));
	}
	
	// createConnectToNonPlayerLand
	
	public AbstractConnectionGeneration createConnectToNonPlayerLand(Consumer<DictExpr> dict) {
		var d = RMSExprs.dict();
		this.createConnectToNonPlayerLand(d);
		dict.accept(d);
		return this;
	}
	
	public AbstractConnectionGeneration createConnectToNonPlayerLand(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectToNonPlayerLand().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectToNonPlayerLand(IRMSNode... attributes) {
		return this.createConnectToNonPlayerLand(RMSExprs.dict(attributes));
	}

	
	
}
