package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import java.time.ZonedDateTime;
import java.util.Collection;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import com.github.zafarkhaja.semver.Version;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.StandardRootRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractCliffGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractConnectionGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractElevationGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractLandGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractObjectsGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractPlayerSetup;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractTerrainGeneration;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddComments;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddStandard;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.ChangeLogEntry;
import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractRootNode extends AbstractRMSNode implements IAddStandard<AbstractRootNode> {

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
		var result = input.createOutput();
		
		result.merge(this.semanticCheckChildren(input));
		
		result.ensureNodeIsDirectlySpecifiedExactlyOnce(this, RMSNodeType.PLAYER_SETUP);
		result.ensureNodeIsDirectlySpecifiedExactlyOnce(this, RMSNodeType.LAND_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.CONNECTION_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.TERRAIN_GENERATION);
		result.ensureNodeIsDirectlySpecifiedExactlyOnce(this, RMSNodeType.OBJECTS_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.ELEVATION_GENERATION);
		result.ensureNodeIsDirectlySpecifiedAtMostOnce(this, RMSNodeType.CLIFF_GENERATION);
		
		result.ensureWeHaveIncludedFile(this, "GeneratingObjects.inc", "This file is imporant if you want to create players in the positions specified by create_player_lands!");
		result.ensureIsDefined("GNR_NORMALTC", "The define is required if you want to position players in the positions specified by creatE_player_lands");
		result.ensureIsDefined("GNR_STARTVILLS", "The define is required if you want to position players in the positions specified by creatE_player_lands");
		result.ensureIsDefined("GNR_CLASSICSCOUT", "The define is required if you want to position players in the positions specified by creatE_player_lands");
		
		return result;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		return this.codeGenerationChildren(input);
	}

	
	public IRMSNode addFileInfo(String author, String description, Version version, RichIterable<ChangeLogEntry> changelogs) {
		return this.adds.addFileInfo(author, description, version, changelogs);
	}
	
	public AbstractPlayerSetup playerSetup() {
		return this.adds.playerSetup();
	}
	
	public AbstractLandGeneration landGeneration() {
		return this.adds.landGeneration();
	}
	
	public AbstractCliffGeneration cliffGeneration() {
		return this.adds.cliffGeneration();
	}
	
	public AbstractTerrainGeneration terrainGeneration() {
		return this.adds.terrainGeneration();
	}
	
	public AbstractElevationGeneration elevationGeneration() {
		return this.adds.elevationGeneration();
	}
	
	public AbstractConnectionGeneration connectionGeneration() {
		return this.adds.connectionGeneration();
	}
	
	public AbstractObjectsGeneration objectsGeneration() {
		return this.adds.objectsGeneration();
	}
}
