package com.thekoldar.aoe_rms_spoon.ast.functions;

import java.util.Optional;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * A percent_change element in the AST.
 * 
 * It contains only 2 children. The first is the eprcentage (which is an int) while the second child represents the body of the random element. 
 * 
 * @author massi
 *
 */
public class PercentChance extends AbstractRMSNode {

	public PercentChance() {
		super(RMSNodeType.PERCENT_CHANCE);
	}
	
	/**
	 * get the percent value this node has
	 * @param input
	 * @return 
	 */
	public IPossibleValue<Long> getPercentValue(SemanticCheckInput input) {
		return this.getPercentExpr().getAsLong(input);
	}
	
	/**
	 * get the expression repersenting the number of the percentage
	 * @return
	 */
	public AbstractExpressionNode getPercentExpr() {
		return (AbstractExpressionNode) this.getChildren().get(0);
	}
	
	public IRMSNode getPercentBlock() {
		return this.getChildren().get(1);
	}
	
	@Override
	public Optional<ExprType> getType() {
		return this.getPercentBlock().getType();
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		//percent_chance needs to be in (0, 100]
		result.ensureArgumentIsBetween(this.getPercentExpr(), 0, 100, false, true);
		
		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		
		var percent = this.getChildren().get(0).codeGeneration(input);
		var code = this.getChildren().get(1).codeGeneration(input);
		
		result.addLine("percent_chance");
		result.mergeIntoLastLine(percent, " ");
		result.mergeIntoLastLine(code, " ");
		
		return result;
	}

}
