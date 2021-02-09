package com.thekoldar.aoe_rms_spoon.ast.functions;

import java.util.Optional;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * Specifies a random choice. Each children repersents a percent_chance element within the random
 * 
 * each of this percent needs to be independently semantically checked 
 * @author massi
 *
 */
public class RandomNode extends AbstractRMSNode {
	
	private static final Logger LOG = LoggerFactory.getLogger(RandomNode.class);

	public RandomNode() {
		super(RMSNodeType.RANDOM);
	}
	
	@Override
	public Optional<ExprType> getType() {
		return this.getChildren().get(0).getType();
	}


	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		//make sure the children are all percent_chance
		
		result.ensureDirectChildrenAreOnlyOf(this, RMSNodeType.PERCENT_CHANCE);
		
		//make sure the children the sum of the percent_change is at most 100
		var total = this.getChildren().collect(c -> (PercentChance)c).collectInt(pc -> pc.getPercentvalue(input)).sum();
		if (total > 100) {
			result.addError(RMSErrorCode.INVALID_ARGUMENT, "percent_chance percentages surpasses the 100%%!");
		} else if (total < 100) {
			LOG.warn("percent_change sum at most {}%. There is {}% that none of the choices will be considered", total, 100 - total);
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		
		result.addLine("start_random");
		result.increaseTabNumber();
		
		for (var c : this.children) {
			result.merge(c.codeGeneration(input));
		}
		
		result.decreaseTabNumber();
		result.addLine("end_random");
		result.addLine();
		
		return result;
	}

}
