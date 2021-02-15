package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
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
public class AbstractTerrainMask extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractTerrainMask() {
		super(RMSNodeType.TERRAIN_MASK);
	}


	@Override
	public String getComment() {
		return "";
	}


	@Override
	public String getArgumentName() {
		return "terrain_mask";
	}


	@Override
	public Object getDefaultValue() {
		return 0;
	}


	@Override
	public String getArgumentComment() {
		return "";
	}

}
