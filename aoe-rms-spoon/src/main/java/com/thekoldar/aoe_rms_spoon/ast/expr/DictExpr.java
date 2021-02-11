package com.thekoldar.aoe_rms_spoon.ast.expr;

import java.util.Optional;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public class DictExpr extends AbstractExpressionNode {
	
	public DictExpr() {
		super();
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	public String getLabel() {
		return "dict";
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		result.addStringToLastLine("{");
		result.increaseTabNumber();
		for (var c : this.children) {
			result.merge(c.codeGeneration(input));
		}
		result.decreaseTabNumber();
		result.addLine("}");
		return result;
	}
	
	/**
	 * cast the add statement to Dictionary
	 */
	public DictExpr addStatement(IRMSNode node) {
		super.addStatement(node);
		return this;
	}
	
	/**
	 * cast the add statement to Dictionary
	 */
	public DictExpr addStatements(IRMSNode... nodes) {
		super.addStatements(nodes);
		return this;
	}

	@Override
	public Optional<ExprType> getType() {
		return Optional.of(ExprType.DICT);
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
		return true;
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
		throw new IllegalArgumentException();
	}

	@Override
	public DictExpr getAsDict(SemanticCheckInput input) {
		return this;
	}

	

}
