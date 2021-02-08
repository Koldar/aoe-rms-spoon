package com.thekoldar.aoe_rms_spoon.ast.expr;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public class BooleanExpr extends AbstractExpressionNode {

	private boolean value;
	
	public BooleanExpr(boolean value) {
		super();
		this.value = value;
	}
	
	public String getLabel() {
		return this.value ? "true" : "false";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		result.addLine(Integer.toString(this.value ? 1 : 0));
		return result;
	}

	@Override
	public ExprType getType() {
		return ExprType.BOOLEAN;
	}

	@Override
	public boolean canBeCastedToInt() {
		return true;
	}

	@Override
	public boolean canBeCastedToBoolean() {
		return true;
	}

	@Override
	public boolean canBeCastedToDict() {
		return false;
	}

	@Override
	public boolean canBeCastedToFloat() {
		return false;
	}

	@Override
	public boolean canBeCastedToString() {
		return false;
	}

	@Override
	public int getAsInt(SemanticCheckInput input) {
		return this.value ? 1 : 0;
	}

	@Override
	public boolean getAsBool(SemanticCheckInput input) {
		return this.value;
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
