package com.thekoldar.aoe_rms_spoon.age_versions.de;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.MapConstants;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.StandardComment;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.StandardRootRMSNode;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.cliff_generation.StandardCliffCurliness;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.cliff_generation.StandardMaxLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.cliff_generation.StandardMaxNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.cliff_generation.StandardMinDistanceCliffs;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.cliff_generation.StandardMinLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.cliff_generation.StandardMinNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.cliff_generation.StandardMinTerrainDistance;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardCreateConnectAllLands;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardCreateConnectAllPlayerLand;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardCreateConnectSameLandZones;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardCreateConnectTeamsLands;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardCreateConnectToNonPlayerLand;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardDefaultTerrainReplacement;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardReplaceTerrain;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardTerrainCost;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.connection_generation.StandardTerrainSize;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.elevation_generation.StandardCreateElevation;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.elevation_generation.StandardEnableBalancedElevation;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.elevation_generation.StandardNumberOfClumps;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.elevation_generation.StandardSetScaleByGroups;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.elevation_generation.StandardSetScaleBySize;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.elevation_generation.StandardSpacing;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardAssignTo;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardAssignToPlayer;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardBaseElevation;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardBaseLayer;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardBaseTerrain;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardBorderFuzziness;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardBottomBorder;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardCirclePlacement;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardClumpingFactor;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardCreateLand;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardCreatePlayerLands;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardEnableWaves;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardLandId;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardLeftBorder;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardMinPlacementDistance;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardOtherZoneAvoidanceDistance;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardRightBorder;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardSetZoneByTeam;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardSetZoneRandomly;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardTopBorder;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation.StandardZone;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardCreateObject;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardGroupPlacementRadius;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardGroupVariance;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardMaxDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardMinDistanceGroupPlacement;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardMinDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardNumberOfGroups;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardNumberOfObjects;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardPlaceOnSpecificLandId;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardResourceDelta;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardSecondObject;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardSetGaiaObjectOnly;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardSetGaiaUnconvertible;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardSetPlaceForEveryPlayer;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardSetScalingToMapSize;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.objects_generation.StandardSetScalingToPlayerNumber;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardAiInfoMapType;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardBaseSize;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardBehaviorVersion;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardCircleRadius;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardDirectPlacement;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardEffectAmount;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardEffectPercent;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardGroupedByTeam;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardGuardState;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardLandPercent;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardLandPosition;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardNomadResources;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardNumberOfTiles;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardRandomPlacement;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardSetGaiaCivilization;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardTerrainState;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardTerrainType;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.player_setup.StandardWeatherType;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.terrain_generation.StandardColorCorrection;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.terrain_generation.StandardCreateTerrain;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.terrain_generation.StandardHeightLimits;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.terrain_generation.StandardSetAvoidPlayerStartAreas;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.terrain_generation.StandardSetFlatTerrainOnly;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.terrain_generation.StandardSpacingToOtherTerrainTypes;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.terrain_generation.StandardTerrainMask;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections.StandardCliffGeneration;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections.StandardConnectionGeneration;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections.StandardElevationGeneration;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections.StandardLandGeneration;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections.StandardObjectsGeneration;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections.StandardPlayerSetup;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections.StandardTerrainGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractAiInfoMapType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractAssignTo;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractAssignToPlayer;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractBaseElevation;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractBaseLayer;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractBaseSize;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractBaseTerrain;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractBehaviorVersion;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractBorderFuzziness;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractBottomBorder;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCirclePlacement;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCircleRadius;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCliffCurliness;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractClumpingFactor;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractColorCorrection;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateConnectAllLands;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateConnectAllPlayerLand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateConnectSameLandZones;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateConnectTeamsLands;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateConnectToNonPlayerLand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateElevation;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateLand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateObject;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreatePlayerLands;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreateTerrain;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractDefaultTerrainReplacement;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractDirectPlacement;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractEffectAmount;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractEffectPercent;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractEnableBalancedElevation;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractEnableWaves;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractGroupPlacementRadius;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractGroupVariance;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractGroupedByTeam;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractGuardState;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractHeightLimits;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractLandId;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractLandPercent;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractLandPosition;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractLeftBorder;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMaxDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMaxLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMaxNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMinDistanceCliffs;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMinDistanceGroupPlacement;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMinDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMinLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMinNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMinPlacementDistance;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractMinTerrainDistance;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractNomadResources;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractNumberOfClumps;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractNumberOfGroups;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractNumberOfObjects;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractNumberOfTiles;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractOtherZoneAvoidanceDistance;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractPlaceOnSpecificLandId;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractRandomPlacement;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractReplaceTerrain;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractResourceDelta;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractRightBorder;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSecondObject;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetAvoidPlayerStartAreas;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetFlatTerrainOnly;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetGaiaCivilization;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetGaiaObjectOnly;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetGaiaUnconvertible;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetPlaceForEveryPlayer;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetScaleByGroups;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetScaleBySize;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetScalingToMapSize;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetScalingToPlayerNumber;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetZoneByTeam;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSetZoneRandomly;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSpacing;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractSpacingToOtherTerrainTypes;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractTerrainCost;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractTerrainMask;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractTerrainSize;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractTerrainState;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractTerrainType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractTopBorder;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractWeatherType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractZone;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractConst;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractDefine;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractInclude;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractIncludeDrs;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractCliffGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractConnectionGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractElevationGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractLandGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractObjectsGeneration;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractPlayerSetup;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractTerrainGeneration;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSDefineSymbol;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.ITerrain;
import com.thekoldar.aoe_rms_spoon.framework.models.StandardTerrain;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class DefinitiveEdition extends AbstractAoEVersion {

	private static final int CONST_ID = 0;
	private static final int RMS_CONST_NAME = 1;
	private static final int WK_CONST = 2;
	private static final int RMS_CONST_DE_SCENARIO_EDITOR = 3;
	private static final int RMS_CONST_HD_SCENARIO_EDITOR = 4;
	private static final int RMS_CONST_UP_1_5_SCENARIO_EDITOR = 5;
	private static final int RMS_CONST_UP_1_0_SCENARIO_EDITOR = 6;
	private static final int RMS_CONST_HD_TEXTURE = 7;
	private static final int RMS_CONST_DE_TEXTURE = 8;
	private static final int RMS_MINIMAP = 9;
	private static final int COMMENT = 10;
	
	private DefinitiveEditionImportantFiles importantFiles;
	private MutableList<ITerrain> usableTerrains;

	public DefinitiveEdition(Path installationFolder) {
		this.importantFiles = new DefinitiveEditionImportantFiles(installationFolder);
		this.usableTerrains = Lists.mutable.empty();
	}
	
	public DefinitiveEdition() {
		this(Paths.get("C:", "Program Files (x86)", "Steam", "steamapps", "commons", "AoE2DE"));
	}
	
	@Override
	public String getUserGameDirectoryName() {
		return "Age of Empires 2 DE";
	}


	public DefinitiveEditionImportantFiles getImportantFiles() {
		return this.importantFiles;
	}

	@Override
	public AbstractRootNode root() {
		return new StandardRootRMSNode(this);
	}

	@Override
	public String getName() {
		return "Definitive Edition"; 
	}

	@Override
	public AbstractPlayerSetup playerSetup() {
		return new StandardPlayerSetup();
	}

	@Override
	public AbstractLandGeneration landGeneration() {
		return new StandardLandGeneration();
	}

	@Override
	public AbstractElevationGeneration elevationGeneration() {
		return new StandardElevationGeneration();
	}

	@Override
	public AbstractCliffGeneration cliffGeneration() {
		return new StandardCliffGeneration();
	}

	@Override
	public AbstractTerrainGeneration terrainGeneration() {
		return new StandardTerrainGeneration();
	}

	@Override
	public AbstractConnectionGeneration connectionGeneration() {
		return new StandardConnectionGeneration();
	}

	@Override
	public AbstractObjectsGeneration objectsGeneration() {
		return new StandardObjectsGeneration();
	}

	@Override
	public AbstractCreatePlayerLands createPlayerLands() {
		return new StandardCreatePlayerLands();
	}

	@Override
	public AbstractRandomPlacement randomPlacement() {
		return new StandardRandomPlacement();
	}

	@Override
	public AbstractGroupedByTeam groupedByTeam() {
		return new StandardGroupedByTeam();
	}

	@Override
	public AbstractDirectPlacement directPlacement() {
		return new StandardDirectPlacement();
	}

	@Override
	public AbstractNomadResources nomadResources() {
		return new StandardNomadResources(); 
	}

	@Override
	public AbstractAiInfoMapType aiInfoMapType() {
		return new StandardAiInfoMapType(); 
	}

	@Override
	public AbstractWeatherType weatherType() {
		return new StandardWeatherType();
	}

	@Override
	public AbstractEffectAmount effectAmount() {
		return new StandardEffectAmount(); 
	}

	@Override
	public AbstractEffectPercent effectPercent() {
		return new StandardEffectPercent();
	}

	@Override
	public AbstractGuardState guardState() {
		return new StandardGuardState();
	}

	@Override
	public AbstractTerrainState terrainState() {
		return new StandardTerrainState(); 
	}

	@Override
	public AbstractSetGaiaCivilization setGaiaCivilization() {
		return new StandardSetGaiaCivilization(); 
	}

	@Override
	public AbstractBehaviorVersion behaviorVersion() {
		return new StandardBehaviorVersion();
	}

	@Override
	public AbstractBaseTerrain baseTerrain() {
		return new StandardBaseTerrain(); 
	}

	@Override
	public AbstractBaseLayer baseLayer() {
		return new StandardBaseLayer();
	}

	@Override
	public AbstractEnableWaves enableWaves() {
		return new StandardEnableWaves(); 
	}

	@Override
	public AbstractCreateLand createLand() {
		return new StandardCreateLand(); 
	}

	@Override
	public AbstractTerrainType terrainType() {
		return new StandardTerrainType();
	}

	@Override
	public AbstractLandPercent landPercent() {
		return new StandardLandPercent();
	}

	@Override
	public AbstractNumberOfTiles numberOfTiles() {
		return new StandardNumberOfTiles();
	}

	@Override
	public AbstractBaseSize baseSize() {
		return new StandardBaseSize(); 
	}

	@Override
	public AbstractLandPosition landPosition() {
		return new StandardLandPosition();
	}

	@Override
	public AbstractCircleRadius circleRadius() {
		return new StandardCircleRadius();
	}

	@Override
	public AbstractCirclePlacement circlePlacement() {
		return new StandardCirclePlacement();
	}

	@Override
	public AbstractLeftBorder leftBorder() {
		return new StandardLeftBorder();
	}

	@Override
	public AbstractRightBorder rightBorder() {
		return new StandardRightBorder();
	}

	@Override
	public AbstractTopBorder topBorder() {
		return new StandardTopBorder(); 
	}

	@Override
	public AbstractBottomBorder bottomBorder() {
		return new StandardBottomBorder();
	}

	@Override
	public AbstractBorderFuzziness borderFuzziness() {
		return new StandardBorderFuzziness();
	}

	@Override
	public AbstractClumpingFactor clumpingFactor() {
		return new StandardClumpingFactor();
	}

	@Override
	public AbstractBaseElevation baseElevation() {
		return new StandardBaseElevation();
	}

	@Override
	public AbstractAssignToPlayer assignToPlayer() {
		return new StandardAssignToPlayer();
	}

	@Override
	public AbstractAssignTo assignTo() {
		return new StandardAssignTo();
	}

	@Override
	public AbstractZone zone() {
		return new StandardZone();
	}

	@Override
	public AbstractSetZoneByTeam setZoneByTeam() {
		return new StandardSetZoneByTeam();
	}

	@Override
	public AbstractSetZoneRandomly setZoneRandomly() {
		return new StandardSetZoneRandomly();
	}

	@Override
	public AbstractOtherZoneAvoidanceDistance otherZoneAvoidanceDistance() {
		return new StandardOtherZoneAvoidanceDistance();
	}

	@Override
	public AbstractMinPlacementDistance minPlacementDistance() {
		return new StandardMinPlacementDistance();
	}

	@Override
	public AbstractLandId landId() {
		return new StandardLandId();
	}

	@Override
	public AbstractCreateElevation createElevation() {
		return new StandardCreateElevation();
	}

	@Override
	public AbstractNumberOfClumps numberOfClumps() {
		return new StandardNumberOfClumps();
	}

	@Override
	public AbstractSetScaleBySize setScaleBySize() {
		return new StandardSetScaleBySize();
	}

	@Override
	public AbstractSetScaleByGroups setScaleByGroups() {
		return new StandardSetScaleByGroups();
	}

	@Override
	public AbstractSpacing spacing() {
		return new StandardSpacing();
	}

	@Override
	public AbstractEnableBalancedElevation enableBalancedElevation() {
		return new StandardEnableBalancedElevation();
	}

	@Override
	public AbstractMinNumberOfCliffs minNumberOfCliffs() {
		return new StandardMinNumberOfCliffs();
	}

	@Override
	public AbstractMaxNumberOfCliffs maxNumberOfCliffs() {
		return new StandardMaxNumberOfCliffs();
	}

	@Override
	public AbstractMinLengthOfCliff minLengthOfCliff() {
		return new StandardMinLengthOfCliff();
	}

	@Override
	public AbstractMaxLengthOfCliff maxLengthOfCliff() {
		return new StandardMaxLengthOfCliff();
	}

	@Override
	public AbstractCliffCurliness cliffCurliness() {
		return new StandardCliffCurliness();
	}

	@Override
	public AbstractMinDistanceCliffs minDistanceCliffs() {
		return new StandardMinDistanceCliffs();
	}

	@Override
	public AbstractMinTerrainDistance minTerrainDistance() {
		return new StandardMinTerrainDistance();
	}

	@Override
	public AbstractColorCorrection colorCorrection() {
		return new  StandardColorCorrection();
	}

	@Override
	public AbstractCreateTerrain createTerrain() {
		return new StandardCreateTerrain();
	}

	@Override
	public AbstractTerrainMask terrainMask() {
		return new StandardTerrainMask();
	}

	@Override
	public AbstractSpacingToOtherTerrainTypes spacingToOtherTerrainTypes() {
		return new StandardSpacingToOtherTerrainTypes(); 
	}

	@Override
	public AbstractSetFlatTerrainOnly setFlatTerrainOnly() {
		return new StandardSetFlatTerrainOnly();
	}

	@Override
	public AbstractSetAvoidPlayerStartAreas setAvoidPlayerStartAreas() {
		return new StandardSetAvoidPlayerStartAreas();
	}

	@Override
	public AbstractHeightLimits heightLimits() {
		return new StandardHeightLimits();
	}

	@Override
	public AbstractCreateConnectAllPlayerLand createConnectAllPlayersLand() {
		return new StandardCreateConnectAllPlayerLand();
	}

	@Override
	public AbstractCreateConnectTeamsLands createConnectTeamLands() {
		return new StandardCreateConnectTeamsLands();
	}

	@Override
	public AbstractCreateConnectAllLands createConnectAllLands() {
		return new StandardCreateConnectAllLands();
	}

	@Override
	public AbstractCreateConnectSameLandZones createConnectSameLandZones() {
		return new StandardCreateConnectSameLandZones();
	}

	@Override
	public AbstractCreateConnectToNonPlayerLand createConnectToNonPlayerLand() {
		return new StandardCreateConnectToNonPlayerLand();
	}

	@Override
	public AbstractDefaultTerrainReplacement defaultTerrainReplacement() {
		return new StandardDefaultTerrainReplacement();
	}

	@Override
	public AbstractReplaceTerrain replaceTerrain() {
		return new StandardReplaceTerrain();
	}

	@Override
	public AbstractTerrainCost terrainCost() {
		return new StandardTerrainCost();
	}

	@Override
	public AbstractTerrainSize terrainSize() {
		return new StandardTerrainSize();
	}

	@Override
	public AbstractCreateObject createObject() {
		return new StandardCreateObject();
	}

	@Override
	public AbstractNumberOfObjects numberOfObjects() {
		return new StandardNumberOfObjects();
	}

	@Override
	public AbstractNumberOfGroups numberOfGroups() {
		return new StandardNumberOfGroups();
	}

	@Override
	public AbstractSetPlaceForEveryPlayer setPlaceForEveryPlayer() {
		return new StandardSetPlaceForEveryPlayer();
	}

	@Override
	public AbstractSetGaiaObjectOnly setGaiaObjectOnly() {
		return new StandardSetGaiaObjectOnly();
	}

	@Override
	public AbstractGroupPlacementRadius groupPlacementRadius() {
		return new StandardGroupPlacementRadius();
	}

	@Override
	public AbstractMinDistanceToPlayers minDistanceToPlayers() {
		return new StandardMinDistanceToPlayers();
	}

	@Override
	public AbstractMaxDistanceToPlayers maxDistanceToPlayers() {
		return new StandardMaxDistanceToPlayers();
	}

	@Override
	public AbstractMinDistanceGroupPlacement minDistanceGroupPlacement() {
		return new StandardMinDistanceGroupPlacement();
	}

	@Override
	public AbstractGroupVariance groupVariance() {
		return new StandardGroupVariance();
	}

	@Override
	public AbstractResourceDelta resourceDelta() {
		return new StandardResourceDelta();
	}

	@Override
	public AbstractSecondObject secondObject() {
		return new StandardSecondObject();
	}

	@Override
	public AbstractSetScalingToMapSize setScalingToMapSize() {
		return new StandardSetScalingToMapSize();
	}

	@Override
	public AbstractSetScalingToPlayerNumber setScalingToPlayerNumber() {
		return new StandardSetScalingToPlayerNumber();
	}

	@Override
	public AbstractPlaceOnSpecificLandId placeOnSpecificLandId() {
		return new StandardPlaceOnSpecificLandId();
	}

	@Override
	public AbstractSetGaiaUnconvertible setGaiaUnconvertible() {
		return new StandardSetGaiaUnconvertible();
	}
	
	/**
	 * check if the terrain implicitly contains a forest
	 * @param row
	 * @return
	 */
	private boolean isForest(String[] row) {
		//check if in DE scenario editor it is a forest
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "forest")) {
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "jungle")) {
			return true;
		}
		return false;
	}
	
	private boolean isLandWalkable( String[] row) {
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "not walkable")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "walkable")) {
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "forest")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "jungle")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "water")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Check if a ship can traverse this terrain.
	 * if the terrain does not say anything, we will assume the terrain is not navigable
	 * @param row
	 * @return
	 */
	private boolean isShipWalkable(String[] row) {
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "forest")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "not navigable")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "navigable")) {
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "water")) {
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "shallow")) {
			return true;
		}
		return false;
	}
	
	/**
	 * if true, you can build a generic buld on this terrain
	 * if the terrain does not say anything, we will assume the terrain is is buildable
	 * @param row
	 * @return
	 */
	private boolean isBuildable(String[] row) {
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "no building")) {
			return false;
		}
		if (row[DefinitiveEdition.RMS_CONST_NAME].equalsIgnoreCase("ice")) {
			//we detected that on ice you cannot walk
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "building possible")) {
			return true;
		}
		return true;
	}
	
	/**
	 * If true, you can build walls. beaches are places where walls can be built, but generic buildings cannot
	 * @param row
	 * @return
	 */
	private boolean isWallBuildable(String[] row) {
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "can place walls")) {
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "beach")) {
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "no building possible")) {
			return false;
		}
		return true;
	}
	
	/**
	 * if true, you can build a dock on the terrain.
	 * if the terrain does not say anything, we will assume the terrain is not dockable
	 * @param row
	 * @return
	 */
	private boolean isDockable(String[] row) {
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "no buildings")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "not dockable")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "dockable")) {
			return true;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.RMS_CONST_DE_SCENARIO_EDITOR], "forest")) {
			return false;
		}
		return false;
		
	}
	
	/**
	 * if true, resources can be put on this terrain, false otherwise
	 * @param row
	 * @return
	 */
	private boolean canResourcesBePutOnIt(String[] row) {
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "cannot place natural resources")) {
			return false;
		}
		if (Utils.containsCaseInsensitive(row[DefinitiveEdition.COMMENT], "no natural resources")) {
			return false;
		}
		return true;
	}
	
	private Path getTexturePath(String[] row) {
		var textureFileDeFilename = row[8];
		return this.importantFiles.getTerrainTextures().resolve("2x").resolve(textureFileDeFilename + ".dss").toAbsolutePath().normalize();
		
	}
	
	@Override
	public RichIterable<RMSConstSymbol> getAvailableMapTypes() {
		return MapConstants.all();
	}
	
	@Override
	public RichIterable<ITerrain> getUsableTerrains() {
		if (this.usableTerrains.isEmpty()) {
			//read the file https://docs.google.com/spreadsheets/d/1Fg4BM8dY0FoJ5yoQtL5S4f9LEcMIrYbH0skrFBnrS14/edit#gid=101522202, containing all the avaialble const name for terrains
			try (CSVReader reader = new CSVReader(Utils.LoadResourceAsReader("data/AoE2 Terrains - Sheet2.csv", StandardCharsets.UTF_8))) {
				var csv = reader.readAll();

				for (int i=0; i<csv.size(); ++i) {
					if (i == 0) {
						//skip header
						continue;
					}
					var row = csv.get(i);
					
					var rmsConstName = row[DefinitiveEdition.RMS_CONST_NAME];
					if (rmsConstName.equals("-")) {
						//skip the terrain
						continue;
					}
					
					
					var rmsConstValue = Integer.parseInt(row[DefinitiveEdition.CONST_ID]);
					var texturePath = this.getTexturePath(row);
					var isForest = this.isForest(row);
					var isLandWalkable = this.isLandWalkable(row);
					var isShipWalkable = this.isShipWalkable(row);
					var isBuildable = this.isBuildable(row);
					var isDockable = this.isDockable(row);
					var isWallable = this.isWallBuildable(row);
					var canResourcesBePutOnIt = this.canResourcesBePutOnIt(row);
					
					var terrain = new StandardTerrain(
						new Color(255,255,255), //TODO csv file does not save the minimap color sadly!
						texturePath,
						rmsConstName,
						rmsConstValue,
						isForest,
						isLandWalkable,
						isShipWalkable,
						isBuildable,
						isDockable,
						isWallable,
						canResourcesBePutOnIt
					);
					
					this.usableTerrains.add(terrain);
				}
				
			} catch (IOException | CsvException e) {
				throw new RuntimeException(e);
			}
		}
		
		return this.usableTerrains;
	}

	@Override
	public RichIterable<RMSConstSymbol> getSpecificConstSymbols() {
		return Sets.immutable.empty();
	}
	
	@Override
	public RichIterable<RMSDefineSymbol> getSpecificDefineSymbols() {
		return Lists.immutable.of(
				RMSDefineSymbol.get("DE_AVAILABLE")
		);
	}

}
