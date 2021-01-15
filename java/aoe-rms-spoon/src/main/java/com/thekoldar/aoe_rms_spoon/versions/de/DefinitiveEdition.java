package com.thekoldar.aoe_rms_spoon.versions.de;

import java.util.Collection;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

import com.thekoldar.aoe_rms_spoon.models.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractAiInfoMapType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractAssignTo;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractAssignToPlayer;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBaseElevation;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBaseLayer;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBaseSize;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBaseTerrain;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBehaviorVersion;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBorderFuzziness;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractBottomBorder;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCirclePlacement;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCircleRadius;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCliffCurliness;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractClumpingFactor;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractColorCorrection;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateConnectAllLands;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateConnectAllPlayerLand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateConnectSameLandZones;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateConnectTeamsLands;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateConnectToNonPlayerLand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateElevation;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateLand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateObject;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreatePlayerLands;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractCreateTerrain;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractDefaultTerrainReplacement;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractDirectPlacement;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractEffectAmount;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractEffectPercent;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractEnableBalancedElevation;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractEnableWaves;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractGroupPlacementRadius;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractGroupedByTeam;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractGuardState;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractHeightLimits;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractLandId;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractLandPercent;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractLandPosition;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractLeftBorder;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMaxDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMaxLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMaxNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMinDistanceCliffs;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMinDistanceGroupPlacement;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMinDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMinLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMinNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMinPlacementDistance;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractMinTerrainDistance;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractNomadResources;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractNumberOfClumps;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractNumberOfGroups;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractNumberOfObjects;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractNumberOfTiles;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractOtherZoneAvoidanceDistance;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractRandomPlacement;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractReplaceTerrain;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractRightBorder;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetAvoidPlayerStartAreas;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetFlatTerrainOnly;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetGaiaCivilization;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetGaiaObjectOnly;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetPlaceForEveryPlayer;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetScaleByGroups;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetScaleBySize;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetZoneByTeam;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSetZoneRandomly;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSpacing;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractSpacingToOtherTerrainTypes;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractTerrainCost;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractTerrainMask;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractTerrainSize;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractTerrainState;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractTerrainType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractTopBorder;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractWeatherType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation.AbstractZone;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.directives.AbstractConst;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.directives.AbstractDefine;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.directives.AbstractInclude;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.directives.AbstractIncludeDrs;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractCliffGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractConnectionGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractElevationGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractLandGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractObjectsGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractPlayerSetup;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractTerrainGeneration;
import com.thekoldar.aoe_rms_spoon.models.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.models.symbols.RMSDefineSymbol;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.StandardComment;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.StandardRootRMSNode;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.cliff_generation.StandardCliffCurliness;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.cliff_generation.StandardMaxLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.cliff_generation.StandardMaxNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.cliff_generation.StandardMinDistanceCliffs;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.cliff_generation.StandardMinLengthOfCliff;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.cliff_generation.StandardMinNumberOfCliffs;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.cliff_generation.StandardMinTerrainDistance;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardCreateConnectAllLands;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardCreateConnectAllPlayerLand;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardCreateConnectSameLandZones;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardCreateConnectTeamsLands;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardCreateConnectToNonPlayerLand;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardDefaultTerrainReplacement;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardReplaceTerrain;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardTerrainCost;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.connection_generation.StandardTerrainSize;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.elevation_generation.StandardCreateElevation;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.elevation_generation.StandardEnableBalancedElevation;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.elevation_generation.StandardNumberOfClumps;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.elevation_generation.StandardSetScaleByGroups;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.elevation_generation.StandardSetScaleBySize;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.elevation_generation.StandardSpacing;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardAssignTo;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardAssignToPlayer;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardBaseElevation;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardBaseLayer;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardBaseTerrain;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardBorderFuzziness;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardBottomBorder;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardCirclePlacement;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardClumpingFactor;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardCreateLand;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardCreatePlayerLands;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardEnableWaves;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardLandId;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardLeftBorder;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardMinPlacementDistance;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardOtherZoneAvoidanceDistance;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardRightBorder;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardSetZoneByTeam;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardSetZoneRandomly;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardTopBorder;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.land_generation.StandardZone;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardCreateObject;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardGroupPlacementRadius;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardMaxDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardMinDistanceGroupPlacement;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardMinDistanceToPlayers;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardNumberOfGroups;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardNumberOfObjects;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardSetGaiaObjectOnly;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.objects_generation.StandardSetPlaceForEveryPlayer;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardAiInfoMapType;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardBaseSize;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardBehaviorVersion;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardCircleRadius;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardDirectPlacement;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardEffectAmount;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardEffectPercent;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardGroupedByTeam;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardGuardState;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardLandPercent;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardLandPosition;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardNomadResources;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardNumberOfTiles;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardRandomPlacement;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardSetGaiaCivilization;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardTerrainState;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardTerrainType;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.player_setup.StandardWeatherType;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.terrain_generation.StandardColorCorrection;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.terrain_generation.StandardCreateTerrain;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.terrain_generation.StandardHeightLimits;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.terrain_generation.StandardSetAvoidPlayerStartAreas;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.terrain_generation.StandardSetFlatTerrainOnly;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.terrain_generation.StandardSpacingToOtherTerrainTypes;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.commands.terrain_generation.StandardTerrainMask;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.sections.StandardCliffGeneration;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.sections.StandardConnectionGeneration;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.sections.StandardElevationGeneration;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.sections.StandardLandGeneration;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.sections.StandardObjectsGeneration;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.sections.StandardPlayerSetup;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.sections.StandardTerrainGeneration;

public class DefinitiveEdition extends AbstractAoEVersion {

	@Override
	public AbstractRootNode root() {
		return new StandardRootRMSNode(this);
	}

	@Override
	public String name() {
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
	public RichIterable<RMSConstSymbol> availableConst() {
		return Lists.immutable.empty();
	}

	@Override
	public RichIterable<RMSDefineSymbol> availableDefines() {
		return Lists.immutable.empty();
	}

	
	
}
