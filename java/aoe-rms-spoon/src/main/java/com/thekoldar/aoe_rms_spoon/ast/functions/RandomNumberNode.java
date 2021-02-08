package com.thekoldar.aoe_rms_spoon.ast.functions;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * one ofthe few rms functions. allows to generate a number between 2 others
 * @author massi
 *
 */
public class RandomNumberNode extends AbstractExpressionNode {

	public RandomNumberNode() {
		super();
	}
	
	
	

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureNChildren(this, 2);
		
		var min = this.getChildren().get(0);
		var max = this.getChildren().get(1);
		result.ensureIsExpression(min);
		result.ensureIsExpression(max);
		
		if (((AbstractExpressionNode)min).getAsInt(input) == 0) {
			result.addWarning(RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "min of rnd is set to 0. upperbound of rns (i.e., max) is exclusive, not inclusive!");
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		
		var min = this.children.get(0);
		var max = this.children.get(1);
		
		result.addLine("rnd(");
		
		result.mergeIntoLastLine(min.codeGeneration(input), "");
		result.mergeIntoLastLine(max.codeGeneration(input), ",");
		
		result.addStringToLastLine("", "", ")");
		
		return result;
	}

	@Override
	public ExprType getType() {
		return ExprType.INT;
	}

	@Override
	public boolean canBeCastedToInt() {
		return true;
	}

	@Override
	public boolean canBeCastedToBoolean() {
		return false;
	}

	@Override
	public boolean canBeCastedToFloat() {
		return true;
	}

	@Override
	public boolean canBeCastedToString() {
		return false;
	}

	@Override
	public boolean canBeCastedToDict() {
		return false;
	}

	@Override
	public int getAsInt(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

	@Override
	public boolean getAsBool(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

	@Override
	public String getAsString(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

	@Override
	public DictExpr getAsDict(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

}
