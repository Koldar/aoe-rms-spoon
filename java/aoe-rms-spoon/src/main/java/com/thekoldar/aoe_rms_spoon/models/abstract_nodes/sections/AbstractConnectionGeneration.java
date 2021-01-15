package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddComment;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DictExpr;

public abstract class AbstractConnectionGeneration extends AbstractRMSSection implements AddComment, AddDirectives{


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
	
	public AbstractConnectionGeneration createConnectAllLands(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectAllLands().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectSameLandZones(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectSameLandZones().addArgument(dict));
		return this;
	}
	
	public AbstractConnectionGeneration createConnectToNonPlayerLand(DictExpr dict) {
		this.addStatement(this.getAgeVersion().createConnectToNonPlayerLand().addArgument(dict));
		return this;
	}

}
