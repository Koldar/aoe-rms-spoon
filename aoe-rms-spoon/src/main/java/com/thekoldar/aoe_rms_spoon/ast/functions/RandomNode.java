package com.thekoldar.aoe_rms_spoon.ast.functions;

import java.util.Optional;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.tuple.Tuples;
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
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

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
		var setOfPossiblePercentValues = this.getChildren()
				.collect(c -> (PercentChance)c)
				.collect(pc -> pc.getPercentValue(input))
				.collect(i -> i.getPossibleValues().toList())
				.toList()
		;		
		var combinations = Utils.cartesianProduct(setOfPossiblePercentValues).toList();
		
		var totalsPossible = combinations
				.collect(c -> { return Tuples.pair(c, c.sumOfInt(i -> i.intValue())); } )
				.toList();
		
		var more100 = totalsPossible.select(p -> p.getTwo() > 100).getAny();
		if (more100 != null) {
			result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "percent_chance percentages surpasses the 100%%!");
		}
		
		var less100 = totalsPossible.select(p -> p.getTwo() < 100).getAny();
		if (less100 != null) {
			result.addWarning(this, RMSErrorCode.INVALID_ARGUMENT, "the combination %s sums up to %d%%. There is %d%% that none of the choices will be considered", less100.getOne().makeString(), less100.getTwo(), 100 - less100.getTwo().intValue());
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
