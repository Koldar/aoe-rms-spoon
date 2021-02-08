package com.thekoldar.aoe_rms_spoon.age_versions.common.nodes;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.ChangeLogEntry;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public class StandardRootRMSNode extends AbstractRootNode {

	public StandardRootRMSNode(AbstractAoEVersion version) {
		super(version);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();

		result.ensureNodeIsDirectlySpecifiedExactlyOnce(this, RMSNodeType.PLAYER_SETUP);
		result.ensureNodeIsDirectlySpecifiedExactlyOnce(this, RMSNodeType.LAND_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.CONNECTION_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.TERRAIN_GENERATION);
		result.ensureNodeIsDirectlySpecifiedExactlyOnce(this, RMSNodeType.OBJECTS_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.ELEVATION_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.CLIFF_GENERATION);

		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		return super.codeGeneration(input);
	}

}
