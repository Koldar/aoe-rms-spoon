package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddComment;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DictExpr;

public abstract class AbstractElevationGeneration extends AbstractRMSSection implements AddComment, AddDirectives{


	public AbstractElevationGeneration() {
		super(RMSNodeType.ELEVATION_GENERATION);
	}

	@Override
	public String getSectionName() {
		return "ELEVATION_GENERATION";
	}
	
	public AbstractElevationGeneration createElevation(AbstractExpressionNode maxHeight, DictExpr attributes) {
		this.addStatement(this.getAgeVersion().createElevation()
				.addArgument(maxHeight)
				.addArgument(attributes)
		);
		return this;
	}

}
