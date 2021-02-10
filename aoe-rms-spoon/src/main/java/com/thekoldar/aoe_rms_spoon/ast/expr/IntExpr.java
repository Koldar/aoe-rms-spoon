package com.thekoldar.aoe_rms_spoon.ast.expr;

import java.util.Optional;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SetPossibleValue;

public class IntExpr extends AbstractExpressionNode {

	private int value;
	
	public IntExpr(int value) {
		super();
		this.value = value;
	}
	
	public String getLabel() {
		return Integer.toString(this.value);
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		result.addLine(Integer.toString(this.value));
		return result;
	}

	@Override
	public Optional<ExprType> getType() {
		return Optional.of(ExprType.INT);
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
	public IPossibleValue<Integer> getAsInt(SemanticCheckInput input) {
		return new SetPossibleValue<Integer>(this.value);
	}

	@Override
	public IPossibleValue<Boolean> getAsBool(SemanticCheckInput input) {
		return new SetPossibleValue<Boolean>(this.value != 0);
	}

	@Override
	public String getAsString(SemanticCheckInput input) {
		return Integer.toString(this.value);
	}

	@Override
	public DictExpr getAsDict(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

}
