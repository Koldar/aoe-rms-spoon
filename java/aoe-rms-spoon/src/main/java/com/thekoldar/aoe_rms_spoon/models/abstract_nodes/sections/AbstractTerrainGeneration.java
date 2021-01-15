package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddComment;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DictExpr;

public abstract class AbstractTerrainGeneration extends AbstractRMSSection implements AddComment, AddDirectives{


	public AbstractTerrainGeneration() {
		super(RMSNodeType.TERRAIN_GENERATION);
	}

	public AbstractTerrainGeneration colorCorrection(AbstractExpressionNode expr) {
		this.addStatement(this.getAgeVersion().colorCorrection().addArgument(expr));
		return this;
	}
	
	public AbstractTerrainGeneration createTerrain(AbstractExpressionNode terrainType, DictExpr attributes) {
		this.addStatement(this.getAgeVersion().createTerrain()
			.addArgument(terrainType)
			.addArgument(attributes)
		);
		return this;
	}

}
