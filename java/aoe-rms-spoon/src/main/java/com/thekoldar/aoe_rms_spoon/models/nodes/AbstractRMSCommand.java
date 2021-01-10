package com.thekoldar.aoe_rms_spoon.models.nodes;

import java.util.List;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
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
public abstract class AbstractRMSCommand<TAOE> extends AbstractRMSNode<TAOE> {
	
	protected AbstractRMSCommand(RMSNodeType type) {
		super(type);
	}

	/**
	 * name of the command, as put in the RMS file
	 * @return
	 */
	public abstract String getName();

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
			result.merge(output);
		}
		result.addLine();
		
		return result;
	}

	

}
