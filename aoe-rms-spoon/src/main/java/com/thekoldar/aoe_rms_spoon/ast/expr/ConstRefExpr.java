package com.thekoldar.aoe_rms_spoon.ast.expr;

import java.util.Optional;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticWarningException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * A reference to a const. The const itself may be defined or not
 * @author massi
 *
 */
public class ConstRefExpr extends AbstractExpressionNode {

	/**
	 * name of the referenced const
	 */
	private String name;
	
	public ConstRefExpr(String name) {
		this.name = name;
	}
	
	public String getLabel() {
		return this.name;
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		//check if the const was previously defined
		if (!input.isConstDefined(this.name)) {
			result.add(new RMSSemanticWarningException(RMSErrorCode.CONST_UNDEFINED, "Const \"%s\" is used but we could not detect if it was defined.", this.name));
		}
		
		return result;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		result.addLine(this.name);
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
		return true;
	}

	@Override
	public boolean canBeCastedToDict() {
		return false;
	}

	@Override
	public IPossibleValue<Long> getAsLong(SemanticCheckInput input) {
		return input.getConstValue(this.name);
	}

	@Override
	public IPossibleValue<Boolean> getAsBool(SemanticCheckInput input) {
		return this.getAsLong(input).collect(i -> i != 0);
	}

	@Override
	public String getAsString(SemanticCheckInput input) {
		return this.name;
	}

	@Override
	public DictExpr getAsDict(SemanticCheckInput input) {
		throw new IllegalArgumentException();
	}

}
