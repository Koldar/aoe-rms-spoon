package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractGroupPlacementRadius extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractGroupPlacementRadius() {
		super(RMSNodeType.GROUP_PLACEMENT_RADIUS);
	}

	@Override
	public String getArgumentName() {
		return "radius";
	}

	@Override
	public Object getDefaultValue() {
		return 3;
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Specify the number of tiles out from the central tile that objects belonging to the same group may spawn.";
	}
	
	/**
	 * compute the involve objhect in this create_object
	 * @param input
	 * @return
	 */
	public IPossibleValue<Long> getInvolvedObject(SemanticCheckInput input) {
		var createObject = (AbstractCreateObject)this.getFirstNodeFromPathSatisfying(n -> n.getNodeType().equals(RMSNodeType.CREATE_OBJECT)).getAny();
		return createObject.getInvolvedObject(input);
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItIsOnlyInstructionOfTypeInDict(this);
		result.ensureArgumentGreaterThan(this.getArgument(0), 0);
		
		var numberOfObjects = ((AbstractRMSCommand)this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_OBJECTS).getAny()).getArgumentAsInt(0, input);
		var availableTiles = this.getArgumentAsInt(0, input).collect(u -> u * u);
		
		if (numberOfObjects.areAtLeastOneGreaterThanAnyOf(availableTiles)) {
			result.addWarning(this, RMSErrorCode.INVALID_ARGUMENT, "There is a scenario where the number of objects surpasses the available tiles set by %s. This will create a square filled of resources", this.getNodeType());
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}
