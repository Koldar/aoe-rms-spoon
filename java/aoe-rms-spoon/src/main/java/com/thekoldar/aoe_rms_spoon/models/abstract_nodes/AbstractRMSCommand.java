package com.thekoldar.aoe_rms_spoon.models.abstract_nodes;

import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.IRMSNode;
import com.thekoldar.aoe_rms_spoon.models.RMSExprs;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

/**
 * a generic RMS command
 * @author massi
 *
 * @param <TAOE>
 */
public abstract class AbstractRMSCommand extends AbstractRMSNode {
	
	protected AbstractRMSCommand(RMSNodeType type) {
		super(type);
	}

	/**
	 * name of the command, as put in the RMS file
	 * @return
	 */
	public String getName() {
		return this.getNodeType().getRmsName();
	}

	/**
	 * what the command does?
	 * @return
	 */
	public abstract String getComment();
	
	/**
	 * Arguments of the command
	 * @return
	 */
	public abstract ImmutableList<CommandFormalArgument> getFormatArguments();
	
	
	public int getMinArity() {
		return this.getFormatArguments().count((arg) -> arg.isRequired());
	}
	
	public int getMaxArity() {
		return this.getFormatArguments().size();
	}
	
	public AbstractRMSCommand setOrAddArgument(int index, @Nullable AbstractExpressionNode node) {
		if (node == null) {
			//ignore if the node is empty
			return this;
		}
		if (index < this.children.size()) {
			node.setParent(this);
			this.children.set(index, node);
		} else if (index == this.children.size()) {
			node.setParent(this);
			this.children.add(node);
		} else {
			node.setParent(this);
			this.children.add(node);
		}
		return this;
	}
	
	public AbstractRMSCommand setOrAddArgument(int index, int value) {
		return this.setOrAddArgument(index, RMSExprs.intVal(value));
	}
	
	public AbstractRMSCommand setOrAddArgument(int index, boolean value) {
		return this.setOrAddArgument(index, RMSExprs.boolVal(value));
	}
	
	public AbstractRMSCommand setOrAddArgument(int index, String value) {
		return this.setOrAddArgument(index, RMSExprs.constVal(value));
	}
	
	public AbstractRMSCommand setOrAddArgument(int index, float value) {
		return this.setOrAddArgument(index, RMSExprs.floatVal(value));
	}
	
	public AbstractRMSCommand addArgument(@Nullable AbstractExpressionNode node) {
		return this.setOrAddArgument(this.children.size(), node);
	}
	
	public AbstractRMSCommand addArgument(int value) {
		return this.addArgument(RMSExprs.intVal(value));
	}
	
	public AbstractRMSCommand addArgument(boolean value) {
		return this.addArgument(RMSExprs.boolVal(value));
	}
	
	public AbstractRMSCommand addArgument(String value) {
		return this.addArgument(RMSExprs.constVal(value));
	}
	
	public AbstractRMSCommand addArgument(float value) {
		return this.addArgument(RMSExprs.floatVal(value));
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		
		result.addLine(this.getName());
		for (var c: this.getChildren()) {
			var output = c.codeGeneration(input);
			result.mergeIntoStringAfterLastLine(output);
		}
		result.addLine();
		
		return result;
	}

	

}
