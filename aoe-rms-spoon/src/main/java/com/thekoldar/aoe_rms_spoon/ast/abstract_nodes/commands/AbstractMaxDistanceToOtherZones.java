package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
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
public abstract class AbstractMaxDistanceToOtherZones extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractMaxDistanceToOtherZones() {
		super(RMSNodeType.MAX_DISTANCE_TO_OTHER_ZONES);
	}
	

	@Override
	public Object getDefaultValue() {
		return 0;
	}



	@Override
	public String getArgumentName() {
		return "distance";
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Minimum (NOT maximum), distance in tiles that objects will stay away from terrains that they are restricted from being placed on.";
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
		result.ensureArgumentGreaterThan0(this.getArgument(0));
		
		if (!this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_GROUPS).isEmpty()) {
			this.infoCmd("The minimum distance to other zones refers to the center of the group, not to the individual elements");
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}
