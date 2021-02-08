package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections;

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

public abstract class AbstractConnectionGeneration extends AbstractRMSSection implements IAddStandard<AbstractConnectionGeneration> {


	public AbstractConnectionGeneration() {
		super(RMSNodeType.CONNECTION_GENERATION);
	}
	
	public AbstractConnectionGeneration createConnectAllPlayersLand(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectAllPlayersLand().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectTeamsLands(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectTeamLands().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectTeamsLands(IRMSNode... attributes) {
		return this.createConnectTeamsLands(RMSExprs.dict(attributes));
	}
	
	public AbstractConnectionGeneration createConnectAllLands(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectAllLands().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectAllLands(IRMSNode... attributes) {
		return this.createConnectAllLands(RMSExprs.dict(attributes));
	}
	
	public AbstractConnectionGeneration createConnectSameLandZones(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectSameLandZones().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectSameLandZones(IRMSNode... attributes) {
		return this.createConnectSameLandZones(RMSExprs.dict(attributes));
	}
	
	public AbstractConnectionGeneration createConnectToNonPlayerLand(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectToNonPlayerLand().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectToNonPlayerLand(IRMSNode... attributes) {
		return this.createConnectToNonPlayerLand(RMSExprs.dict(attributes));
	}

	
	
}
