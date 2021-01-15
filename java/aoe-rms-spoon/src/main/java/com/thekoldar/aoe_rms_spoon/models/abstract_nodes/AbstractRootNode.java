package com.thekoldar.aoe_rms_spoon.models.abstract_nodes;

import java.time.ZonedDateTime;
import java.util.Collection;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import com.github.zafarkhaja.semver.Version;
import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;
import com.thekoldar.aoe_rms_spoon.models.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.models.ChangeLogEntry;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDefine;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddFileInfo;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddRMSSections;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.StandardRootRMSNode;

public abstract class AbstractRootNode extends AbstractRMSNode implements AddFileInfo, AddDirectives, AddRMSSections {

	private AbstractAoEVersion ageVersion;
	
	protected AbstractRootNode(AbstractAoEVersion version) {
		super(RMSNodeType.ROOT);
		this.ageVersion = version;
	}
	
	@Override
	public AbstractAoEVersion getAgeVersion() {
		return this.ageVersion;
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		//check single
		this.ensureThereIsAtLeastOneNodeThat(RMS)
		
		return this.semanticCheckChildren(input);
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		return this.codeGenerationChildren(input);
	}

}
