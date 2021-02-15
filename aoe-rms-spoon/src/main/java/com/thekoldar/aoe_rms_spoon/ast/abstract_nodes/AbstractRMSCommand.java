package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticWarningException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SetPossibleValue;

/**
 * a generic RMS command
 * @author massi
 *
 */
public abstract class AbstractRMSCommand extends AbstractRMSNode {
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractRMSCommand.class);
	
	protected AbstractRMSCommand(RMSNodeType type) {
		super(type);
	}
	
	/**
	 * Log an information message relative to a given command.
	 * @param message message to plot, specific accoridng {@link Logger} syntax
	 * @param args arguments within the message
	 */
	public void infoCmd(String message, Object... args) {
		var objs = new Object[args.length + 1];
		objs[0] = this.getName();
		for (int i=0; i<args.length; ++i) {
			objs[1+i] = args[i];
		}
		var logEntry = "{}: " + message;
		LOG.info(logEntry, objs);
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
	
	public AbstractRMSCommand setOrAddArgument(int index, @Nullable IRMSNode node) {
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
	
	public AbstractRMSCommand addArgument(@Nullable IRMSNode node) {
		return this.setOrAddArgument(this.children.size(), node);
	}
	
	/**
	 * Adds another argument in the command, which is an int
	 * @param value parameter actual value
	 * @return this
	 */
	public AbstractRMSCommand addArgument(int value) {
		return this.addArgument(RMSExprs.intVal(value));
	}
	
	/**
	 * Adds another argument in the command, which is an boolean
	 * @param value parameter actual value
	 * @return this
	 */
	public AbstractRMSCommand addArgument(boolean value) {
		return this.addArgument(RMSExprs.boolVal(value));
	}
	
	/**
	 * Adds another argument in the command, which is a const defined
	 * @param value parameter actual value
	 * @return this
	 */
	public AbstractRMSCommand addArgument(String value) {
		return this.addArgument(RMSExprs.constVal(value));
	}
	
	/**
	 * Adds another argument in the command, which is a float
	 * @param value parameter actual value
	 * @return this
	 */
	public AbstractRMSCommand addArgument(float value) {
		return this.addArgument(RMSExprs.floatVal(value));
	}
	
	/**
	 * Add 2 arguments in the command, both const defined
	 * @param value first parameter actual value
	 * @param value2 second parameter actual value
	 * @return this
	 */
	public AbstractRMSCommand addArguments(String value, String value2) {
		this.addArgument(RMSExprs.constVal(value));
		this.addArgument(RMSExprs.constVal(value2));
		return this;
	}
	
	/**
	 * Add 2 arguments in the command, one is a const defined, the other is an int
	 * @param value first parameter actual value
	 * @param value2 second parameter actual value
	 * @return this
	 */
	public AbstractRMSCommand addArguments(String value, int value2) {
		this.addArgument(RMSExprs.constVal(value));
		this.addArgument(RMSExprs.intVal(value2));
		return this;
	}
	
	/**
	 * Add 3 arguments in the command, one is a const defined, the second and the third are integers
	 * @param value first parameter actual value
	 * @param value2 second parameter actual value
	 * @param value3 third parameter actual value
	 * @return this
	 */
	public AbstractRMSCommand addArguments(String value, int value2, int value3) {
		this.addArgument(RMSExprs.constVal(value));
		this.addArgument(RMSExprs.intVal(value2));
		this.addArgument(RMSExprs.intVal(value3));
		return this;
	}
	
	/**
	 * get i-th argument
	 * @param index index of the argument to fetch
	 * @return
	 */
	public AbstractExpressionNode getArgument(int index) {
		return (AbstractExpressionNode)this.getChildren().get(index);
	}
	
	
	/**
	 * get the int representation of the the -i-th argument
	 * @param index argument we need to convert
	 * @param input semantic analysis input
	 * @return int rerpesentation of the i-th argument
	 */
	public LongSetPossible getArgumentAsInt(int index, SemanticCheckInput input) {
		return LongSetPossible.of(this.getArgument(index).getAsLong(input));
	}

	/**
	 * we check if the format argument type match the actual ones. If the actual ones are less than the formal ones
	 */
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		var formalArguments = this.getFormatArguments();
		
		if (this.getChildren().size() < this.getMinArity()) {
			result.add(new RMSSemanticErrorException(RMSErrorCode.TOO_FEW_ARGUMENTS, "Command %s requires at least %d paraemters", this.getName(), this.getMinArity()));
		}
		
		if (this.getChildren().size() > this.getMaxArity()) {
			result.add(new RMSSemanticErrorException(RMSErrorCode.TOO_MANY_ARGUMENTS, "Command %s requires at most %d paraemters", this.getName(), this.getMaxArity()));
		}
		
		for (int i=0; i<formalArguments.size(); ++i) {
			var formalArgument = formalArguments.get(i);
			var expectedExprType = formalArgument.getType();
			
			IRMSNode actualArgument = null;
			ExprType actualType = null;
			if (i < this.getChildren().size()) {
				//there is an actual argument
				actualArgument = this.getChildren().get(i);
				if (actualArgument.getType().isEmpty()) {
					result.addError(this, RMSErrorCode.INVALID_NODE_LOCATION, "Command %s expected as its %d-th argument a node that has a type. However, we got %s", this.getName(), i, actualArgument);
				}
				actualType = actualArgument.getType().get();
				
				if (!actualArgument.canBeCastedTo(expectedExprType)) {
					result.addError(this, RMSErrorCode.INVALID_EXPR_TYPE, "Command %s required as its %d-th argument (named %s) an expression of type %s, but got %s (which cannot be converted into %s)!", this.getName(), i, formalArgument.getName(), expectedExprType, actualType, expectedExprType);
				} else {
					
					if (!actualType.equals(expectedExprType)) {
						result.addWarning(this, RMSErrorCode.INVALID_EXPR_TYPE, "Command %s requires as its %d-th argument (named %s) an expression of type %s, but got %s. This can still be performed since %s can be casted into %s. Still, consider using the formal type.", this.getName(), i, formalArgument.getName(), expectedExprType, actualType, actualType, expectedExprType);
					}
					//the type is actually the same, so no implicit cast is needed
				}
				
				//call the semantic check of the underlying expression
				result.merge(actualArgument.semanticCheck(input));
			} else {
				
				if (!formalArgument.isOptional()) {
					result.addError(this, RMSErrorCode.EXPECTED_REQUIRED_PARAMETER, "Command %s requires as its %d-th paraemeters a required one", this.getName(), i);
				}
				
				//there is not an actual argument. Use the default one
				actualType = formalArgument.getType();
			}
			
		}
		
		return result;
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
