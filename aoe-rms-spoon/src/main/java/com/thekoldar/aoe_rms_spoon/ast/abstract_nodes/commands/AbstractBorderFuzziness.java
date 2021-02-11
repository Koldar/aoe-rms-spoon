package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticWarningException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractBorderFuzziness extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractBorderFuzziness() {
		super(RMSNodeType.BORDER_FUZZINESS);
	}

	@Override
	public String getArgumentName() {
		return "percentage";
	}

	@Override
	public Object getDefaultValue() {
		return 8;
	}

	@Override
	public String getArgumentComment() {
		return "Low values allow lands to exceed specified borders";
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureIsCommand(this);
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentIsPercentage(this.getArgument(0));
		if (this.getArgumentAsInt(0, input).contains(0L) && this.hasAtLeastOneSiblingOfTypes(RMSNodeType.TOP_BORDER, RMSNodeType.BOTTOM_BORDER, RMSNodeType.LEFT_BORDER, RMSNodeType.RIGHT_BORDER)) {
			result.add(new RMSSemanticWarningException(RMSErrorCode.IGNORE_VALUE, "borders are ignore when %s is set to 0", this.getName()));
		}
		if (this.getArgumentAsInt(0, input).containsOnly(100L)) {
			this.infoCmd("Borders are fully respected, resulting in perfectly straight lands along borders");
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
}
