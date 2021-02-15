package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSOptionalIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractHeightLimits extends AbstractRMSOptionalIntOptionalIntArgumentCommand{

	protected AbstractHeightLimits() {
		super(RMSNodeType.HEIGHT_LIMITS);
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public String getArgument1Name() {
		return "min";
	}

	@Override
	public Object getArgument1DefaultValue() {
		return "none";
	}

	@Override
	public String getArgument1Comment() {
		return "";
	}

	@Override
	public String getArgument2Name() {
		return "max";
	}

	@Override
	public Object getArgument2DefaultValue() {
		return  "none";
	}

	@Override
	public String getArgument2Comment() {
		return "none";
	}

}
