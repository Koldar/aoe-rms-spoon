package com.thekoldar.aoe_rms_spoon.ast.expr;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public class StringExpr extends AbstractExpressionNode {

	private String value;
	
	public StringExpr(String value) {
		super();
		this.value = value;
	}
	
	public String getLabel() {
		return this.value;
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		result.addLine(this.value);
		return result;
	}

	@Override
	public ExprType getType() {
		return ExprType.STRING;
	}

	@Override
	public boolean canBeCastedToInt() {
		return false;
	}

	@Override
	public boolean canBeCastedToBoolean() {
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
		return this.value;
	}

	@Override
	public DictExpr getAsDict(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

}
