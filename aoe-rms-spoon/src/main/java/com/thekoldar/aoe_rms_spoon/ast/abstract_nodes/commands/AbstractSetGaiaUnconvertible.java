package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
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
public abstract class AbstractSetGaiaUnconvertible extends AbstractRMSNoArgumentCommand {

	protected AbstractSetGaiaUnconvertible() {
		super(RMSNodeType.SET_GAIA_UNCONVERTIBLE);
	}
	
	
	@Override
	public String getComment() {
		return "Use with any gaia object to make that object unrescuable by players and hostile towards them";
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
		if (this.getPreviousSiblingSatisfying((i, n) -> n.getNodeType().equals(RMSNodeType.SET_GAIA_OBJECT_ONLY)).isEmpty()) {
			result.addError(this, RMSErrorCode.INVALID_NODE_LOCATION, "%s needs to be placed after %s", this.getNodeType(), RMSNodeType.SET_GAIA_OBJECT_ONLY);
		}
		this.infoCmd("1646 indestructible markets you can use to trade with");
		
		return result.merge(this.semanticCheckChildren(input));
	}

}
