package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddStandard;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractCliffGeneration extends AbstractRMSSection implements IAddStandard<AbstractCliffGeneration> {


	public AbstractCliffGeneration() {
		super(RMSNodeType.CLIFF_GENERATION);
	}
	
	public AbstractCliffGeneration minNumberOfCliffs(int amount) {
		return this.minNumberOfCliffs(RMSExprs.intVal(amount));
	}

	public AbstractCliffGeneration minNumberOfCliffs(IRMSNode amount) {
		this.addStatement(this.getAgeVersion().minNumberOfCliffs().addArgument(amount));
		return this;
	}
	
	public AbstractCliffGeneration maxNumberOfCliffs(int amount) {
		return this.maxNumberOfCliffs(RMSExprs.intVal(amount));
	}
	
	public AbstractCliffGeneration maxNumberOfCliffs(IRMSNode amount) {
		this.addStatement(this.getAgeVersion().maxNumberOfCliffs().addArgument(amount));
		return this;
	}
	
	public AbstractCliffGeneration minLengthOfCliff(int length) {
		return this.minLengthOfCliff(RMSExprs.intVal(length));
	}
	
	public AbstractCliffGeneration minLengthOfCliff(IRMSNode length) {
		this.addStatement(this.getAgeVersion().minLengthOfCliff().addArgument(length));
		return this;
	}
	
	public AbstractCliffGeneration maxLengthOfCliff(int length) {
		return this.maxLengthOfCliff(RMSExprs.intVal(length));
	}
	
	public AbstractCliffGeneration maxLengthOfCliff(IRMSNode length) {
		this.addStatement(this.getAgeVersion().maxLengthOfCliff().addArgument(length));
		return this;
	}
	
	public AbstractCliffGeneration cliffCurliness(int percentage) {
		return this.cliffCurliness(RMSExprs.intVal(percentage));
	}
	
	public AbstractCliffGeneration cliffCurliness(IRMSNode percentage) {
		this.addStatement(this.getAgeVersion().cliffCurliness().addArgument(percentage));
		return this;
	}
	
	public AbstractCliffGeneration minDistanceCliffs(int distance) {
		return this.minDistanceCliffs(RMSExprs.intVal(distance));
	}
	
	public AbstractCliffGeneration minDistanceCliffs(IRMSNode distance) {
		this.addStatement(this.getAgeVersion().minDistanceCliffs().addArgument(distance));
		return this;
	}
	
	public AbstractCliffGeneration minTerrainDistance(int distance) {
		return this.minTerrainDistance(RMSExprs.intVal(distance));
	}
	
	public AbstractCliffGeneration minTerrainDistance(IRMSNode distance) {
		this.addStatement(this.getAgeVersion().minTerrainDistance().addArgument(distance));
		return this;
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.MIN_NUMBER_OF_CLIFFS, RMSNodeType.MAX_NUMBER_OF_CLIFFS, RMSNodeType.MIN_LENGTH_OF_CLIFF, RMSNodeType.MAX_LENGTH_OF_CLIFF, RMSNodeType.CLIFF_CURLINESS, RMSNodeType.MIN_DISTANCE_CLIFFS, RMSNodeType.MIN_TERRAIN_DISTANCE);
		
		return result.merge(this.semanticCheck(input));
	}

	
	
	
}
