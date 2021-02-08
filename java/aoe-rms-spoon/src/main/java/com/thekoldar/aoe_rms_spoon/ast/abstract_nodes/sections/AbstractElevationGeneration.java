package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddStandard;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;

public abstract class AbstractElevationGeneration extends AbstractRMSSection implements IAddStandard<AbstractElevationGeneration>{


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
