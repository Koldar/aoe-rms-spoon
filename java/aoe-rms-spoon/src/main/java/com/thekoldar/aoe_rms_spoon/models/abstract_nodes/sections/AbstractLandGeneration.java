package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBaseTerrain;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddComment;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DictExpr;

public abstract class AbstractLandGeneration extends AbstractRMSSection implements AddDirectives<AbstractLandGeneration> {


	public AbstractLandGeneration() {
		super(RMSNodeType.LAND_GENERATION);
	}

	public AbstractLandGeneration baseTerrain(AbstractExpressionNode expr) {
		this.addStatement(
				this.getAgeVersion().baseTerrain()
					.setOrAddArgument(0, expr)
		);
		return this;
	}
	
	public AbstractLandGeneration createPlayerLands(DictExpr d) {
		this.addStatement(
				this.getAgeVersion().createPlayerLands()
					.setOrAddArgument(0, d)
		);
		return this;
	}
	
	public AbstractLandGeneration createLand(DictExpr d) {
		this.addStatement(
				this.getAgeVersion().createLand()
					.setOrAddArgument(0, d)
		);
		return this;
	}
	
	public AbstractLandGeneration enableWaves(AbstractExpressionNode showWaves) {
		this.addStatement(
				this.getAgeVersion().enableWaves()
					.setOrAddArgument(0, showWaves)
		);
		return this;
	}
	
	public AbstractLandGeneration baseLayer(AbstractExpressionNode terrainType) {
		this.addStatement(
				this.getAgeVersion().baseLayer()
					.setOrAddArgument(0, terrainType)
		);
		return this;
	}

}
