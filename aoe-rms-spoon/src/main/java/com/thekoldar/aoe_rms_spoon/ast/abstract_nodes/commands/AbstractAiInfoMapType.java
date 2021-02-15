package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractAiInfoMapType extends AbstractRMSCommand {

	protected AbstractAiInfoMapType() {
		super(RMSNodeType.AI_INFO_MAP_TYPE);
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.mutable.of(
				CommandFormalArgument.optionalInt("map_type", 0, ""),
				CommandFormalArgument.optionalBoolean("is_nomad", false, ""),
				CommandFormalArgument.optionalBoolean("is_michi", false, ""),
				CommandFormalArgument.optionalBoolean("showtype", false, "")
		).toImmutable();
	}
	
	
	

}
