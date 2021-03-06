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

/**
 * A RMS expression that specifies a string type
 * @author massi
 *
 */
public class StringExpr extends AbstractExpressionNode {

	/**
	 * Value fo the expression
	 */
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
	public Optional<ExprType> getType() {
		return Optional.of(ExprType.STRING);
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
	public IPossibleValue<Long> getAsLong(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

	@Override
	public IPossibleValue<Boolean> getAsBool(SemanticCheckInput input) {
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
