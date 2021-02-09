package com.thekoldar.aoe_rms_spoon.ast.functions;

import java.util.Optional;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * If node. first child of the the node is the condition.
 * Then each pair of children are the pair fo condition and body of an else if  block. The last child specifies the else body.
 * @author massi
 *
 */
public class IfNode extends AbstractRMSNode {

	public IfNode() {
		super(RMSNodeType.IF);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	/**
	 * get the expression representing the condition of the if
	 * @return
	 */
	public IRMSNode getCondition() {
		return this.getChildren().get(0);
	}
	
	/**
	 * get the expression representing the then block
	 * @return
	 */
	public IRMSNode getThenBlock() {
		return this.getChildren().get(1);
	}
	
	/**
	 * 
	 * @return if true, this if node has an else block
	 */
	public boolean hasElse() {
		return this.children.size() % 2 != 0;
	}
	
	public int getElseIndex() {
		return this.children.size() - 1;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		 
		//first i condition, last is else, the middles ones are elseif
		var condition = this.getChildren().get(0);
		var firstBody = this.getChildren().get(1);
		
		//condition 1; then body: 1; elseif: 2 per node; else: 1
		var hasElse = this.hasElse(); 
		result.addLine("if");
		
		//merge condition and then body
		result.mergeIntoLastLine(condition.codeGeneration(input), " ");
		result.increaseTabNumber();
		result.merge(firstBody.codeGeneration(input));
		result.decreaseTabNumber();
		
		var lastElseIfBody = this.children.size() - (hasElse ? 1 : 0);
		for (int i=2; i<lastElseIfBody; i+=2) {
			result.addLine("elseif");
			//condition
			result.mergeIntoLastLine(this.getChildren().get(i).codeGeneration(input), " ");
			//block
			result.increaseTabNumber();
			result.merge(this.getChildren().get(i+1).codeGeneration(input));
			result.decreaseTabNumber();
		}
		
		if (hasElse) {
			result.addLine("else ");
			result.increaseTabNumber();
			result.merge(this.getChildren().getLast().codeGeneration(input));
			result.decreaseTabNumber();
		}
		result.addLine("endif ");
		result.addLine();
		
		return result;
	}

	@Override
	public Optional<ExprType> getType() {
		return this.getThenBlock().getType();
	}

}
