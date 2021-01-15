package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddComment;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;

public abstract class AbstractCliffGeneration extends AbstractRMSSection implements AddComment, AddDirectives{


	public AbstractCliffGeneration() {
		super(RMSNodeType.CLIFF_GENERATION);
	}

	public AbstractCliffGeneration minNumberOfCliffs(AbstractExpressionNode amount) {
		this.addStatement(this.getAgeVersion().minNumberOfCliffs().addArgument(amount));
		return this;
	}
	
	public AbstractCliffGeneration maxNumberOfCliffs(AbstractExpressionNode amount) {
		this.addStatement(this.getAgeVersion().maxNumberOfCliffs().addArgument(amount));
		return this;
	}
	
	public AbstractCliffGeneration minLengthOfCliff(AbstractExpressionNode length) {
		this.addStatement(this.getAgeVersion().minLengthOfCliff().addArgument(length));
		return this;
	}
	
	public AbstractCliffGeneration maxLengthOfCliff(AbstractExpressionNode length) {
		this.addStatement(this.getAgeVersion().maxLengthOfCliff().addArgument(length));
		return this;
	}
	
	public AbstractCliffGeneration cliffCurliness(AbstractExpressionNode percentage) {
		this.addStatement(this.getAgeVersion().cliffCurliness().addArgument(percentage));
		return this;
	}
	
	public AbstractCliffGeneration minDistanceCliffs(AbstractExpressionNode distance) {
		this.addStatement(this.getAgeVersion().minDistanceCliffs().addArgument(distance));
		return this;
	}
	
	public AbstractCliffGeneration minTerrainDistance(AbstractExpressionNode distance) {
		this.addStatement(this.getAgeVersion().minTerrainDistance().addArgument(distance));
		return this;
	}

}
