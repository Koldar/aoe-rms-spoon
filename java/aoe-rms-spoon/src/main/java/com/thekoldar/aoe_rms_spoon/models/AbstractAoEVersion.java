package com.thekoldar.aoe_rms_spoon.models;

import java.util.Collection;

import org.eclipse.collections.api.RichIterable;

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
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.directives.StandardConst;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.directives.StandardDefine;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.directives.StandardInclude;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.directives.StandardIncludeDrs;

/**
 * Class identifiying a version of Age fo Empires.
 * 
 * It actys as a factory class for building nodes. Each AST node depends on the age of empires you are considering
 * 
 * @author massi
 *
 */

//TROOT,TEXPR,TPLAYER_SETUP,TLAND_GENERATION,TELEVATION_GENERATION,TCLIFF_GENERATION,TTERRAIN_GENERATION,TCONNECTION_GENERATION,TOBJECTS_GENERATION,
//TCREATE_PLAYER_LANDS,TRANDOM_PLACEMENT,TGROUPED_BY_TEAM,TDIRECT_PLACEMENT,TNOMAD_RESOURCES,TAI_INFO_MAP_TYPE,TWEATHER_TYPE,TEFFECT_AMOUNT,TEFFECT_PERCENT,TGUARD_STATE,TTERRAIN_STATE,TSET_GAIA_CIVILIZATION,TBEHAVIOR_VERSION,TBASE_TERRAIN,TBASE_LAYER,TENABLE_WAVES,TCREATE_LAND,TTERRAIN_TYPE,TLAND_PERCENT,TNUMBER_OF_TILES,TBASE_SIZE,TLAND_POSITION,TCIRCLE_RADIUS,TLEFT_BORDER,TRIGHT_BORDER,TTOP_BORDER,TBOTTOM_BORDER,TBORDER_FUZZINESS,TCLUMPING_FACTOR,TBASE_ELEVATION,TASSIGN_TO_PLAYER,TASSIGN_TO,TZONE,TSET_ZONE_BY_TEAM,TSET_ZONE_RANDOMLY,TOTHER_ZONE_AVOIDANCE_DISTANCE,TMIN_PLACEMENT_DISTANCE,TLAND_ID,TCREATE_ELEVATION,TNUMBER_OF_CLUMPS,TSET_SCALE_BY_SIZE,TSET_SCALE_BY_GROUPS,TSPACING,TENABLE_BALANCED_ELEVATION,TMIN_NUMBER_OF_CLIFFS,TMAX_NUMBER_OF_CLIFFS,TMIN_LENGTH_OF_CLIFF,TMAX_LENGTH_OF_CLIFF,TCLIFF_CURLINESS,TMIN_DISTANCE_CLIFFS,TMIN_TERRAIN_DISTANCE,TCOLOR_CORRECTION,TCREATE_TERRAIN,TTERRAIN_MASK,TSPACING_TO_OTHER_TERRAIN_TYPES,TSET_FLAT_TERRAIN_ONLY,TSET_AVOID_PLAYER_START_AREAS,THEIGHT_LIMITS,TCREATE_CONNECT_ALL_PLAYERS_LAND,TCREATE_CONNECT_TEAMS_LANDS,TCREATE_CONNECT_ALL_LANDS,TCREATE_CONNECT_SAME_LAND_ZONES,TCREATE_CONNECT_TO_NONPLAYER_LAND,TDEFAULT_TERRAIN_REPLACEMENT,TREPLACE_TERRAIN,TTERRAIN_COST,TTERRAIN_SIZE,TCREATE_OBJECT,TNUMBER_OF_OBJECTS,TNUMBER_OF_GROUPS,TGROUP_VARIANCE,TRESOURCE_DELTA,TSECOND_OBJECT,TSET_SCALING_TO_MAP_SIZE,TSET_SCALING_TO_PLAYER_NUMBER,TSET_PLACE_FOR_EVERY_PLAYER,TPLACE_ON_SPECIFIC_LAND_ID,TSET_GAIA_OBJECT_ONLY,TSET_GAIA_UNCONVERTIBLE,TGROUP_PLACEMENT_RADIUS,TSET_TIGHT_GROUPING,TSET_LOOSE_GROUPING,TTERRAIN_TO_PLACE_ON,TLAYER_TO_PLACE_ON,TPLACE_ON_FOREST_ZONE,TAVOID_FOREST_ZONE,TAVOID_CLIFF_ZONE,TMIN_DISTANCE_TO_PLAYERS,TMAX_DISTANCE_TO_PLAYERS,TMAX_DISTANCE_TO_OTHER_ZONES,TMIN_DISTANCE_GROUP_PLACEMENT,TTEMP_MIN_DISTANCE_GROUP_PLACEMENT,TFIND_CLOSEST,TFORCE_PLACEMENT,TACTOR_AREA,TACTOR_AREA_RADIUS,TACTOR_AREA_TO_PLACE_IN,TAVOID_ACTOR_AREA,TAVOID_ALL_ACTOR_AREAS,
//TCOMMENT,TINCLUDE,TINCLUDE_DRS,TCONST,TDEFINE

//<
//ROOT,
//TEXPR,
//
//TPLAYER_SETUP,
//TLAND_GENERATION,
//TELEVATION_GENERATION,
//TCLIFF_GENERATION,
//TTERRAIN_GENERATION,
//TCONNECTION_GENERATION,
//TOBJECTS_GENERATION,
//
//TCREATE_PLAYER_LANDS,
//TRANDOM_PLACEMENT,
//TGROUPED_BY_TEAM,
//TDIRECT_PLACEMENT,
//TNOMAD_RESOURCES,
//TAI_INFO_MAP_TYPE,
//TWEATHER_TYPE,
//TEFFECT_AMOUNT,
//TEFFECT_PERCENT,
//TGUARD_STATE,
//TTERRAIN_STATE,
//TSET_GAIA_CIVILIZATION,
//TBEHAVIOR_VERSION,
//TBASE_TERRAIN,
//TBASE_LAYER,
//TENABLE_WAVES,
//TCREATE_LAND,
//TTERRAIN_TYPE,
//TLAND_PERCENT,
//TNUMBER_OF_TILES,
//TBASE_SIZE,
//TLAND_POSITION,
//TCIRCLE_RADIUS,
//TLEFT_BORDER,
//TRIGHT_BORDER,
//TTOP_BORDER,
//TBOTTOM_BORDER,
//TBORDER_FUZZINESS,
//TCLUMPING_FACTOR,
//TBASE_ELEVATION,
//TASSIGN_TO_PLAYER,
//TASSIGN_TO,
//TZONE,
//TSET_ZONE_BY_TEAM,
//TSET_ZONE_RANDOMLY,
//TOTHER_ZONE_AVOIDANCE_DISTANCE,
//TMIN_PLACEMENT_DISTANCE,
//TLAND_ID,
//TCREATE_ELEVATION,
//TNUMBER_OF_CLUMPS,
//TSET_SCALE_BY_SIZE,
//TSET_SCALE_BY_GROUPS,
//TSPACING,
//TENABLE_BALANCED_ELEVATION,
//TMIN_NUMBER_OF_CLIFFS,
//TMAX_NUMBER_OF_CLIFFS,
//TMIN_LENGTH_OF_CLIFF,
//TMAX_LENGTH_OF_CLIFF,
//TCLIFF_CURLINESS,
//TMIN_DISTANCE_CLIFFS,
//TMIN_TERRAIN_DISTANCE,
//TCOLOR_CORRECTION,
//TCREATE_TERRAIN,
//TTERRAIN_MASK,
//TSPACING_TO_OTHER_TERRAIN_TYPES,
//TSET_FLAT_TERRAIN_ONLY,
//TSET_AVOID_PLAYER_START_AREAS,
//THEIGHT_LIMITS,
//TCREATE_CONNECT_ALL_PLAYERS_LAND,
//TCREATE_CONNECT_TEAMS_LANDS,
//TCREATE_CONNECT_ALL_LANDS,
//TCREATE_CONNECT_SAME_LAND_ZONES,
//TCREATE_CONNECT_TO_NONPLAYER_LAND,
//TDEFAULT_TERRAIN_REPLACEMENT,
//TREPLACE_TERRAIN,
//TTERRAIN_COST,
//TTERRAIN_SIZE,
//TCREATE_OBJECT,
//TNUMBER_OF_OBJECTS,
//TNUMBER_OF_GROUPS,
//TGROUP_VARIANCE,
//TRESOURCE_DELTA,
//TSECOND_OBJECT,
//TSET_SCALING_TO_MAP_SIZE,
//TSET_SCALING_TO_PLAYER_NUMBER,
//TSET_PLACE_FOR_EVERY_PLAYER,
//TPLACE_ON_SPECIFIC_LAND_ID,
//TSET_GAIA_OBJECT_ONLY,
//TSET_GAIA_UNCONVERTIBLE,
//TGROUP_PLACEMENT_RADIUS,
//TSET_TIGHT_GROUPING,
//TSET_LOOSE_GROUPING,
//TTERRAIN_TO_PLACE_ON,
//TLAYER_TO_PLACE_ON,
//TPLACE_ON_FOREST_ZONE,
//TAVOID_FOREST_ZONE,
//TAVOID_CLIFF_ZONE,
//TMIN_DISTANCE_TO_PLAYERS,
//TMAX_DISTANCE_TO_PLAYERS,
//TMAX_DISTANCE_TO_OTHER_ZONES,
//TMIN_DISTANCE_GROUP_PLACEMENT,
//TTEMP_MIN_DISTANCE_GROUP_PLACEMENT,
//TFIND_CLOSEST,
//TFORCE_PLACEMENT,
//TACTOR_AREA,
//TACTOR_AREA_RADIUS,
//TACTOR_AREA_TO_PLACE_IN,
//TAVOID_ACTOR_AREA,
//TAVOID_ALL_ACTOR_AREAS,
//
//TCOMMENT,
//TINCLUDE,
//TINCLUDE_DRS,
//TCONST,
//TDEFINE
//>

public abstract class AbstractAoEVersion {
	
	/**
	 * name of the age of empires version
	 * @return
	 */
	public abstract String name();
	
	//const defined by the environment
	/**
	 * list of const value that the AGe of empries version gives you
	 * @return
	 */
	public abstract RichIterable<RMSConstSymbol> availableConst();
	
	//defines available by the environment
	/**
	 * list of defines that the AGe of empries version gives you
	 * @return
	 */
	public abstract RichIterable<RMSDefineSymbol> availableDefines();
	
	// FACTORY METHODS
	
	// non terminal nodes
	
	public abstract AbstractRootNode root();
//	public abstract TEXPR expr();
//	
	public abstract AbstractPlayerSetup playerSetup();
	public abstract AbstractLandGeneration landGeneration();
	public abstract AbstractElevationGeneration elevationGeneration();
	public abstract AbstractCliffGeneration cliffGeneration();
	public abstract AbstractTerrainGeneration terrainGeneration();
	public abstract AbstractConnectionGeneration connectionGeneration();
	public abstract AbstractObjectsGeneration objectsGeneration();
//
	public abstract AbstractCreatePlayerLands createPlayerLands();
	public abstract AbstractRandomPlacement randomPlacement();
	public abstract AbstractGroupedByTeam groupedByTeam();
	public abstract AbstractDirectPlacement directPlacement();
	public abstract AbstractNomadResources nomadResources();
	public abstract AbstractAiInfoMapType aiInfoMapType();
	public abstract AbstractWeatherType weatherType();
	public abstract AbstractEffectAmount effectAmount();
	public abstract AbstractEffectPercent effectPercent();
	public abstract AbstractGuardState guardState();
	public abstract AbstractTerrainState terrainState();
	public abstract AbstractSetGaiaCivilization setGaiaCivilization();
	public abstract AbstractBehaviorVersion behaviorVersion();
	public abstract AbstractBaseTerrain baseTerrain();
	public abstract AbstractBaseLayer baseLayer();
	public abstract AbstractEnableWaves enableWaves();
	public abstract AbstractCreateLand createLand();
	public abstract AbstractTerrainType terrainType();
	public abstract AbstractLandPercent landPercent();
	public abstract AbstractNumberOfTiles numberOfTiles();
	public abstract AbstractBaseSize baseSize();
	public abstract AbstractLandPosition landPosition();
	public abstract AbstractCircleRadius circleRadius();
	public abstract AbstractCirclePlacement circlePlacement();
	public abstract AbstractLeftBorder leftBorder();
	public abstract AbstractRightBorder rightBorder();
	public abstract AbstractTopBorder topBorder();
	public abstract AbstractBottomBorder bottomBorder();
	public abstract AbstractBorderFuzziness borderFuzziness();
	public abstract AbstractClumpingFactor clumpingFactor();
	public abstract AbstractBaseElevation baseElevation();
	public abstract AbstractAssignToPlayer assignToPlayer();
	public abstract AbstractAssignTo assignTo();
	public abstract AbstractZone zone();
	public abstract AbstractSetZoneByTeam setZoneByTeam();
	public abstract AbstractSetZoneRandomly setZoneRandomly();
	public abstract AbstractOtherZoneAvoidanceDistance otherZoneAvoidanceDistance();
	public abstract AbstractMinPlacementDistance minPlacementDistance();
	public abstract AbstractLandId landId();
	public abstract AbstractCreateElevation createElevation();
	public abstract AbstractNumberOfClumps numberOfClumps();
	public abstract AbstractSetScaleBySize setScaleBySize();
	public abstract AbstractSetScaleByGroups setScaleByGroups();
	public abstract AbstractSpacing spacing();
	public abstract AbstractEnableBalancedElevation enableBalancedElevation();
	public abstract AbstractMinNumberOfCliffs minNumberOfCliffs();
	public abstract AbstractMaxNumberOfCliffs maxNumberOfCliffs();
	public abstract AbstractMinLengthOfCliff minLengthOfCliff();
	public abstract AbstractMaxLengthOfCliff maxLengthOfCliff();
	public abstract AbstractCliffCurliness cliffCurliness();
	public abstract AbstractMinDistanceCliffs minDistanceCliffs();
	public abstract AbstractMinTerrainDistance minTerrainDistance();
	public abstract AbstractColorCorrection colorCorrection();
	public abstract AbstractCreateTerrain createTerrain();
	public abstract AbstractTerrainMask terrainMask();
	public abstract AbstractSpacingToOtherTerrainTypes spacingToOtherTerrainTypes();
	public abstract AbstractSetFlatTerrainOnly setFlatTerrainOnly();
	public abstract AbstractSetAvoidPlayerStartAreas setAvoidPlayerStartAreas();
	public abstract AbstractHeightLimits heightLimits();
	public abstract AbstractCreateConnectAllPlayerLand createConnectAllPlayersLand();
	public abstract AbstractCreateConnectTeamsLands createConnectTeamLands();
	public abstract AbstractCreateConnectAllLands createConnectAllLands();
	public abstract AbstractCreateConnectSameLandZones createConnectSameLandZones();
	public abstract AbstractCreateConnectToNonPlayerLand createConnectToNonPlayerLand();
	public abstract AbstractDefaultTerrainReplacement defaultTerrainReplacement();
	public abstract AbstractReplaceTerrain replaceTerrain();
	public abstract AbstractTerrainCost terrainCost();
	public abstract AbstractTerrainSize terrainSize();
	public abstract AbstractCreateObject createObject();
	public abstract AbstractNumberOfObjects numberOfObjects();
	public abstract AbstractNumberOfGroups numberOfGroups();
//	public abstract AbstractGROUP_VARIANCE GROUP_VARIANCE();
//	public abstract AbstractRESOURCE_DELTA RESOURCE_DELTA();
//	public abstract AbstractSECOND_OBJECT SECOND_OBJECT();
//	public abstract AbstractSET_SCALING_TO_MAP_SIZE SET_SCALING_TO_MAP_SIZE();
//	public abstract AbstractSET_SCALING_TO_PLAYER_NUMBER SET_SCALING_TO_PLAYER_NUMBER();
	public abstract AbstractSetPlaceForEveryPlayer setPlaceForEveryPlayer();
//	public abstract AbstractPLACE_ON_SPECIFIC_LAND_ID PLACE_ON_SPECIFIC_LAND_ID();
	public abstract AbstractSetGaiaObjectOnly setGaiaObjectOnly();
//	public abstract AbstractSET_GAIA_UNCONVERTIBLE SET_GAIA_UNCONVERTIBLE();
	public abstract AbstractGroupPlacementRadius groupPlacementRadius();
//	public abstract AbstractSET_TIGHT_GROUPING SET_TIGHT_GROUPING();
//	public abstract AbstractSET_LOOSE_GROUPING SET_LOOSE_GROUPING();
//	public abstract AbstractTERRAIN_TO_PLACE_ON TERRAIN_TO_PLACE_ON();
//	public abstract AbstractLAYER_TO_PLACE_ON LAYER_TO_PLACE_ON();
//	public abstract AbstractPLACE_ON_FOREST_ZONE PLACE_ON_FOREST_ZONE();
//	public abstract AbstractAVOID_FOREST_ZONE AVOID_FOREST_ZONE();
//	public abstract AbstractAVOID_CLIFF_ZONE AVOID_CLIFF_ZONE();
	public abstract AbstractMinDistanceToPlayers minDistanceToPlayers();
	public abstract AbstractMaxDistanceToPlayers maxDistanceToPlayers();
//	public abstract AbstractMAX_DISTANCE_TO_OTHER_ZONES MAX_DISTANCE_TO_OTHER_ZONES();
	public abstract AbstractMinDistanceGroupPlacement minDistanceGroupPlacement();
//	public abstract AbstractTEMP_MIN_DISTANCE_GROUP_PLACEMENT TEMP_MIN_DISTANCE_GROUP_PLACEMENT();
//	public abstract AbstractFIND_CLOSEST findClosest();
//	public abstract AbstractFORCE_PLACEMENT forcePlacement();
//	public abstract AbstractACTOR_AREA actorArea();
//	public abstract AbstractACTOR_AREA_RADIUS actorAreaRadius();
//	public abstract AbstractACTOR_AREA_TO_PLACE_IN actorAreaToPlaceIn();
//	public abstract AbstractAVOID_ACTOR_AREA avoidActorArea();
//	public abstract AbstractAVOID_ALL_ACTOR_AREAS avoidAllActorAreas();
	
	// terminal nodes

	public StandardComment comment(RichIterable<String> lines) {
		return new StandardComment(lines);
	}
	
	public AbstractInclude include(String file) {
		return new StandardInclude(file);
	}
	
	public AbstractIncludeDrs includeDrs(String file) {
		return new StandardIncludeDrs(file);
	}
	
	public AbstractConst constant(String name, int value) {
		return new StandardConst(name, value);
	}
	
	public AbstractDefine define(String value) {
		return new StandardDefine(value);
	}
	
}
