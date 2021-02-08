package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSOptionalIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

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