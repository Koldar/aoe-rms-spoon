package com.thekoldar.aoe_rms_spoon.framework;

import java.util.Collection;
import java.util.function.Consumer;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Iterables;
import org.eclipse.collections.impl.lazy.LazyIterableAdapter;

import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.StandardComment;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.StandardMultiplexerNode;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.directives.StandardConst;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.directives.StandardDefine;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.directives.StandardInclude;
import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.directives.StandardIncludeDrs;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.*;
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
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.ast.functions.IfNode;
import com.thekoldar.aoe_rms_spoon.ast.functions.RandomNode;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSDefineSymbol;

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
	 * Name of the directory where mods can be saved
	 * @return
	 */
	public abstract String getUserGameDirectoryName();
	
	/**
	 * List of files which are very important for this particular version
	 * @return
	 */
	public abstract IImportantFiles getImportantFiles();
	
	/**
	 * List of all the terrain you can safely use
	 * @return
	 */
	public abstract RichIterable<ITerrain> getUsableTerrains();
	
	/**
	 * get the const types that represents all the avaialble map types
	 * @return
	 */
	public abstract RichIterable<RMSConstSymbol> getAvailableMapTypes();
	
	/**
	 * name of the age of empires version
	 * @return
	 */
	public abstract String getName();
	
	//const defined by the environment
	/**
	 * list of <b>all</b> const value that this AGE of Empires version gives you.
	 * @return
	 */
	public RichIterable<RMSConstSymbol> availableConst() {
		MutableList<RMSConstSymbol> result = Lists.mutable.empty();
		
		result.addAllIterable(this.getUsableTerrains().collect(t -> t.getConstSymbol()));
		result.addAllIterable(this.getAvailableMapTypes());
		result.addAllIterable(this.getSpecificConstSymbols());
		
		return result;
	}
	
	/**
	 * get all the const ref that are specifci to this Age of empires version.
	 * @return
	 */
	public abstract RichIterable<RMSConstSymbol> getSpecificConstSymbols();
	
	//defines available by the environment
	/**
	 * list of defines that the AGe of empries version gives you
	 * @return
	 */
	public RichIterable<RMSDefineSymbol> availableDefines() {
		MutableList<RMSDefineSymbol> result = Lists.mutable.empty();
		
		result.addAllIterable(this.getSpecificDefineSymbols());
		
		return result;
	}
	
	/**
	 * list of defines that this specific AGE of empries version gives you
	 * 
	 * @return
	 */
	public abstract RichIterable<RMSDefineSymbol> getSpecificDefineSymbols();
	
	// FACTORY METHODS
	
	// non terminal nodes
	
	/**
	 * 
	 * @return an implementation of the root of the AST node
	 */
	public abstract AbstractRootNode root();
	
	/**
	 * a generic node with no specific meaning. You can use it as a transparent container of other RMS commands
	 * @return
	 */
	public AbstractRMSNode multiplexer() {
		return new StandardMultiplexerNode();
	}
	
	/**
	 * generates a new {@link IfNode}
	 * @return if node
	 */
	public IfNode ifNode() {
		return new IfNode();
	}
	/**
	 * Generates a new {@link RandomNode}
	 * @return random node
	 */
	public RandomNode randomNode() {
		return new RandomNode();
	}
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
	
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreatePlayerLands createPlayerLands();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractRandomPlacement randomPlacement();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractGroupedByTeam groupedByTeam();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractDirectPlacement directPlacement();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractNomadResources nomadResources();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractAiInfoMapType aiInfoMapType();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractWeatherType weatherType();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractEffectAmount effectAmount();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractEffectPercent effectPercent();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractGuardState guardState();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractTerrainState terrainState();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetGaiaCivilization setGaiaCivilization();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractBehaviorVersion behaviorVersion();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractBaseTerrain baseTerrain();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractBaseLayer baseLayer();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractEnableWaves enableWaves();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateLand createLand();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractTerrainType terrainType();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractLandPercent landPercent();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractNumberOfTiles numberOfTiles();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractBaseSize baseSize();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractLandPosition landPosition();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCircleRadius circleRadius();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCirclePlacement circlePlacement();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractLeftBorder leftBorder();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractRightBorder rightBorder();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractTopBorder topBorder();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractBottomBorder bottomBorder();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractBorderFuzziness borderFuzziness();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractClumpingFactor clumpingFactor();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractBaseElevation baseElevation();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractAssignToPlayer assignToPlayer();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractAssignTo assignTo();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractZone zone();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetZoneByTeam setZoneByTeam();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetZoneRandomly setZoneRandomly();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractOtherZoneAvoidanceDistance otherZoneAvoidanceDistance();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractMinPlacementDistance minPlacementDistance();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractLandId landId();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateElevation createElevation();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractNumberOfClumps numberOfClumps();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetScaleBySize setScaleBySize();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetScaleByGroups setScaleByGroups();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSpacing spacing();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractEnableBalancedElevation enableBalancedElevation();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractMinNumberOfCliffs minNumberOfCliffs();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractMaxNumberOfCliffs maxNumberOfCliffs();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractMinLengthOfCliff minLengthOfCliff();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractMaxLengthOfCliff maxLengthOfCliff();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCliffCurliness cliffCurliness();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractMinDistanceCliffs minDistanceCliffs();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractMinTerrainDistance minTerrainDistance();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractColorCorrection colorCorrection();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateTerrain createTerrain();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractTerrainMask terrainMask();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSpacingToOtherTerrainTypes spacingToOtherTerrainTypes();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetFlatTerrainOnly setFlatTerrainOnly();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetAvoidPlayerStartAreas setAvoidPlayerStartAreas();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractHeightLimits heightLimits();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateConnectAllPlayerLand createConnectAllPlayersLand();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateConnectTeamsLands createConnectTeamLands();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateConnectAllLands createConnectAllLands();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateConnectSameLandZones createConnectSameLandZones();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateConnectToNonPlayerLand createConnectToNonPlayerLand();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractDefaultTerrainReplacement defaultTerrainReplacement();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractReplaceTerrain replaceTerrain();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractTerrainCost terrainCost();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractTerrainSize terrainSize();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractCreateObject createObject();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractNumberOfObjects numberOfObjects();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractNumberOfGroups numberOfGroups();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractGroupVariance groupVariance();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractResourceDelta resourceDelta();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSecondObject secondObject();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetScalingToMapSize setScalingToMapSize();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetScalingToPlayerNumber setScalingToPlayerNumber();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetPlaceForEveryPlayer setPlaceForEveryPlayer();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractPlaceOnSpecificLandId placeOnSpecificLandId();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetGaiaObjectOnly setGaiaObjectOnly();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractSetGaiaUnconvertible setGaiaUnconvertible();
	/**
	 * Create an empty node whose arguments need to be populated
	 * 
	 * @return a new instance of the node.
	 */
	public abstract AbstractGroupPlacementRadius groupPlacementRadius();
	/**
	 * Objects belonging to the same group must be in adjacent tiles.  Commonly used for berries, gold and stone.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractSetTightGrouping setTightGrouping();
	/**
	 * Objects belonging to the same group can be placed anywhere within the confines of group_placement_radius
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractSetLooseGrouping setLooseGrouping();
	/**
	 * The object(s) will only be placed on the specified terrain.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractTerrainToPlaceOn terrainToPlaceOn();
	/**
	 * The object(s) will only be placed on the specified layering terrain.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractLayerToPlaceOn layerToPlaceOn();
	/**
	 * Place objects only on, and directly next to, tiles with trees on them (including straggler trees and scenario editor trees).
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractPlaceOnForestZone placeOnForestZone();
	/**
	 * Objects will stay the specified number of tiles away from any trees (including straggler trees and scenario editor trees)
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractAvoidForestZone avoidForestZone();
	/**
	 * Objects will stay the specified number of tiles away from cliffs.  Note that because of the size of the cliff objects, you need to specify at least a distance of 2 to actually create a gap between the cliffs and the object(s).
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractAvoidCliffZone avoidCliffZone();
	/**
	 * Minimum distance (in tiles) from the origin of player lands, that the object can be placed.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractMinDistanceToPlayers minDistanceToPlayers();
	/**
	 * maximum distance (in tiles) from the origin of player lands, that the object can be placed.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractMaxDistanceToPlayers maxDistanceToPlayers();
	/**
	 * Minimum (NOT maximum), distance in tiles that objects will stay away from terrains that they are restricted from being placed on.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractMaxDistanceToOtherZones maxDistanceToOtherZones();
	/**
	 * Minimum distance in tiles that individual objects of the same create_object command, and ALL future objects, must stay away from each object.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractMinDistanceGroupPlacement minDistanceGroupPlacement();
	/**
	 * Like min_distance_group_placement, but only applies to the current create_object command - future objects are unaffected.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractTempMinDistanceGroupPlacement tempMinDistanceGroupPlacement();
	/**
	 * Place the object on the closest free tile to the center of the land, taking into consideration all other constraints.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractFindClosest findClosest();
	/**
	 * Allows multiple objects to be placed on the same tile at once, if necessary.  
	 * Normally, objects are placed one per tile, and if the tiles run out, no more objects are placed.  
	 * With force_placement active, when tiles run out, the remaining objects are placed on the corners of tiles, and then on top of each other.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractForcePlacement forcePlacement();
	/**
	 * Specifies a numerical identifier which can be referred to in future objects with avoid_actor_area or actor_area_to_place_in
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractActorArea actorArea();
	/**
	 * Used with actor_area to specify how large it should be.
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractActorAreaRadius actorAreaRadius();
	/**
	 * Place the object only within the radius of the specified actor_area
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractActorAreaToPlaceIn actorAreaToPlaceIn();
	/**
	 * The object will avoid the specified actor_area
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractAvoidActorArea avoidActorArea();
	/**
	 * The object will avoid being placed within ANY existing actor_area
	 * 
	 * @return a new instance of the node
	 */
	public abstract AbstractAvoidAllActorAreas avoidAllActorAreas();
	
	//shortcuts
	
	/**
	 * Number of objects to create
	 * @param value number of objects to place
	 * @return a new instance of the node
	 */
	public AbstractNumberOfObjects numberOfObjects(int value) {
		return (AbstractNumberOfObjects) this.numberOfObjects().addArgument(value); 
	}
	
	/**
	 * Number of tiles between each elevation level.  Numbers larger than 1 will produce rings of flat terrain on each level of a hill.  

	 * @param value number of tiles between each level of the hill
	 * @return
	 */
	public AbstractSpacing spacing(int value) {
		return (AbstractSpacing) this.spacing().addArgument(value);
	}
	
	/**
	 * Total base tile count allocated to this create_elevation command.  If number_of_clumps is specified, this value is divided equally among the clumps.

	 * @param maxHeight number
	 * @return
	 */
	public AbstractNumberOfTiles numberOfTiles(int maxHeight) {
		return (AbstractNumberOfTiles) this.numberOfTiles().addArgument(maxHeight);
	}
	
	/**
	 * Specify the exact origin point for a land, as a percentage of total map dimensions.
	 * <ul>
	 * <li>land_position 50 50 is the center of the map</li>
	 * <li>Ignores border restrictions</li>
	 * <li>If placed outside of specified borders, the land will not grow beyond its base_size</li>
	 * <li>Disabled for create_player_lands</li>
	 * <li>Disabled when using assign_to_player or assign_to for create_land, unless direct_placement is specified in <PLAYER_SETUP></li>
	 * </ul>
	 * @param percentageX X is the axis running from the top-left edge to bottom-right
	 * @param percentageY Y is the axis running from the bottom-left edge to top-right. 100 may crash the game
	 * @return
	 */
	public AbstractLandPosition landPosition(int percentageX, int percentageY) {
		return (AbstractLandPosition) this.landPosition().addArgument(percentageX).addArgument(percentageY);
	}
	
	/**
	 * Specify a percentage of map width for land placement and growth to stay away from a given border.
	 * 
	 * <ul>
	 * <li>Left is south-west; right is north-east, top is north-west; bottom is south-east</li>
	 * <li>There is a hard-coded feature that makes lands look like octagons instead of squares when constrained by borders.</li>
	 * <li>Negative values can be used, as long as the land origin stays inside the map.  To ensure this, do one of the following:
	 * 		<ul>
	 * 			<li>Specify a land_position within the map</li>
	 * 			<li>Specify a sufficiently large base size (this may require manually scaling of base_size with map size)</li>
	 * 		</ul>
	 * </li>
	 * <li>Borders shift the entire circle for player lands</li>
	 * <li>You cannot have multiple rings of player lands with different borders; they will all be in the same circle</li>
	 * <li>BUG: asymmetric borders for player lands can cause issues when the top_border is larger than other borders (External reference: RMS Border Bugs Exposed).  Avoid this by always using another border along with top_border when creating player lands.</li>
	 * </ul>
	 * 
	 * @param percentage left is south-west.
	 * @return
	 */
	public AbstractLeftBorder leftBorder(int percentage) {
		return (AbstractLeftBorder) this.leftBorder().addArgument(percentage);
	}
	
	/**
	 * Specify a percentage of map width for land placement and growth to stay away from a given border.
	 * 
	 * <ul>
	 * <li>Left is south-west; right is north-east, top is north-west; bottom is south-east</li>
	 * <li>There is a hard-coded feature that makes lands look like octagons instead of squares when constrained by borders.</li>
	 * <li>Negative values can be used, as long as the land origin stays inside the map.  To ensure this, do one of the following:
	 * 		<ul>
	 * 			<li>Specify a land_position within the map</li>
	 * 			<li>Specify a sufficiently large base size (this may require manually scaling of base_size with map size)</li>
	 * 		</ul>
	 * </li>
	 * <li>Borders shift the entire circle for player lands</li>
	 * <li>You cannot have multiple rings of player lands with different borders; they will all be in the same circle</li>
	 * <li>BUG: asymmetric borders for player lands can cause issues when the top_border is larger than other borders (External reference: RMS Border Bugs Exposed).  Avoid this by always using another border along with top_border when creating player lands.</li>
	 * </ul>
	 * 
	 * @param percentage left is south-west.
	 * @return
	 */
	public AbstractRightBorder rightBorder(int percentage) {
		return (AbstractRightBorder) this.rightBorder().addArgument(percentage);
	}
	
	/**
	 * Specify a percentage of map width for land placement and growth to stay away from a given border.
	 * 
	 * <ul>
	 * <li>Left is south-west; right is north-east, top is north-west; bottom is south-east</li>
	 * <li>There is a hard-coded feature that makes lands look like octagons instead of squares when constrained by borders.</li>
	 * <li>Negative values can be used, as long as the land origin stays inside the map.  To ensure this, do one of the following:
	 * 		<ul>
	 * 			<li>Specify a land_position within the map</li>
	 * 			<li>Specify a sufficiently large base size (this may require manually scaling of base_size with map size)</li>
	 * 		</ul>
	 * </li>
	 * <li>Borders shift the entire circle for player lands</li>
	 * <li>You cannot have multiple rings of player lands with different borders; they will all be in the same circle</li>
	 * <li>BUG: asymmetric borders for player lands can cause issues when the top_border is larger than other borders (External reference: RMS Border Bugs Exposed).  Avoid this by always using another border along with top_border when creating player lands.</li>
	 * </ul>
	 * 
	 * @param percentage left is south-west.
	 * @return
	 */
	public AbstractTopBorder topBorder(int percentage) {
		return (AbstractTopBorder) this.topBorder().addArgument(percentage);
	}
	
	/**
	 * Specify a percentage of map width for land placement and growth to stay away from a given border.
	 * 
	 * <ul>
	 * <li>Left is south-west; right is north-east, top is north-west; bottom is south-east</li>
	 * <li>There is a hard-coded feature that makes lands look like octagons instead of squares when constrained by borders.</li>
	 * <li>Negative values can be used, as long as the land origin stays inside the map.  To ensure this, do one of the following:
	 * 		<ul>
	 * 			<li>Specify a land_position within the map</li>
	 * 			<li>Specify a sufficiently large base size (this may require manually scaling of base_size with map size)</li>
	 * 		</ul>
	 * </li>
	 * <li>Borders shift the entire circle for player lands</li>
	 * <li>You cannot have multiple rings of player lands with different borders; they will all be in the same circle</li>
	 * <li>BUG: asymmetric borders for player lands can cause issues when the top_border is larger than other borders (External reference: RMS Border Bugs Exposed).  Avoid this by always using another border along with top_border when creating player lands.</li>
	 * </ul>
	 * 
	 * @param percentage left is south-west.
	 * @return
	 */
	public AbstractBottomBorder bottomBorder(int percentage) {
		return (AbstractBottomBorder) this.bottomBorder().addArgument(percentage);
	}
	
	/**
	 * Assign a numeric label to a land, which can later be used to place objects specifically on that land with place_on_specific_land_id.  Unrelated to any zone numbers.
	 * 
	 * <ul>
	 * 	<li>Multiple lands can have the same ID.  In this case, the objects will be placed on all of them.</li>
	 * <li>Must be used after assign_to_player / assign_to since they will reset the ID.</li>
	 * <li>Can theoretically be used for create_player_lands, but will disable the ability to use set_place_for_every_player for object placement, so is not recommended.</li>
	 * <li>Note that objects may be placed on surrounding terrain rather than the land itself, if the surrounding terrain is something the object can be placed on.</li>
	 * </ul>
	 * 
	 * @param id number. land id -9 should not be used, because it belongst o gaia. 
	 * @return
	 */
	public AbstractLandId landId(int id) {
		return (AbstractLandId) this.landId().addArgument(id);
	}
	
	public AbstractZone zone(int zoneId) {
		return (AbstractZone) this.zone().addArgument(zoneId);
	}
	
	public AbstractOtherZoneAvoidanceDistance otherZoneAvoidanceDistance(int distance) {
		return (AbstractOtherZoneAvoidanceDistance) this.otherZoneAvoidanceDistance().addArgument(distance);
	}
	
	public AbstractCircleRadius circleRadius(int radius) {
		return (AbstractCircleRadius) this.circleRadius().addArgument(radius);
	}
	
	public AbstractCircleRadius circleRadius(int radius, int variance) {
		return (AbstractCircleRadius) this.circleRadius().addArgument(radius).addArgument(variance);
	}
	
	/**
	 * Square radius of the initially placed land origin, before any growth.
	 * 
	 * <ul>
	 *  <li>The default of 3 results in a 7x7 land origin (49 tiles total).</li>
	 *  <li>base_size will produce a perfect square if used with land_percent 0 or number_of_tiles 0.</li>
	 *  <li>base_size is the minimum distance that a land will be placed from the edge of the map.</li>
	 *  <li>Land bases are placed sequentially, so if they are large and overlap, the land placed last will be the one visible in the overlapping region.</li>
	 *  <li>Non-player land bases will not overlap with each other, unless...</li>
	 *  <li>If base_size for non-player lands is too large, the land will fail to find a valid position and will be placed at the center of the map, overlapping any other lands at the center.</li>
	 * </ul>
	 * 
	 * @param radius, number, in cells
	 * @return
	 */
	public AbstractBaseSize baseSize(int size) {
		return (AbstractBaseSize) this.baseSize().addArgument(size); 
	}
	
	/**
	 * Specify which terrain the land should have.
	 * 
	 * @param terrainType terrain constant
	 * @return
	 * @see https://docs.google.com/document/d/1jnhZXoeL9mkRUJxcGlKnO98fIwFKStP_OBozpr0CHXo/edit#
	 */
	public AbstractTerrainType terrainType(String terrainType) {
		return (AbstractTerrainType) this.terrainType().addArgument(terrainType);
	}
	
	/**
	 * Specify a terrain to initially fill the map.  
	 * Maps with rivers going through them or oceans on the outside usually use water.  Maps with forest on the outside usually use a forest terrain.
	 * 
	 * Specify the base terrain on which you want to place your new terrain.
	 * 
	 * @param terrainName terrain constant
	 * @return a new fully initialize base terrain statement
	 */
	public AbstractBaseTerrain baseTerrain(String terrainName) {
		return (AbstractBaseTerrain) this.baseTerrain().addArgument(terrainName);
	}
	
	/**
	 * Minimum distance that this terrain will stay away from other terrain types
	 * 
	 * <ul>
	 * 	<li>Only considers existing terrains at the time of generation - terrains generated later will need their own spacing.</li>
	 *  <li>Terrains will not stay away from the same terrain type created previously.  This requires the use of an intermediate placeholder terrain.</li>
	 *  <li>Also affects the distance that the terrain will stay away from cliffs (because cliffs generate their own terrain underneath them - terrain 16)</li>
	 *  <li>When used with set_flat_terrain_only it also affects the distance that the terrain will stay away from slopes.</li>
	 * </ul>
	 * 
	 * @param amount distance from other terrain types
	 * @return
	 */
	public AbstractSpacingToOtherTerrainTypes spacingToOtherTerrainTypes(int amount) {
		return (AbstractSpacingToOtherTerrainTypes) this.spacingToOtherTerrainTypes().addArgument(amount);
	}
	
	/**
	 * Percentage of the total map allocated to this create_terrain command.  If number_of_clumps is specified, this value is divided equally among the clumps.
	 * 
	 * <ul>
	 * 	<li>Terrain will only be replaced where the appropriate base_terrain or base_layer is present, and will only replace the specified number of individual clumps, so it will not necessarily fill 100% of the map if set to 100.</li>
	 * </ul>
	 * 
	 * @param percentage percentage number
	 * @return
	 */
	public AbstractLandPercent landPercent(int percentage) {
		return (AbstractLandPercent) this.landPercent().addArgument(percentage);
	}
	
	/**
	 * Number of individual terrain patches to create.
	 * 
	 * <ul>
	 * 	<li>If clumps are larger than expected (or total count is lower than expected), several adjacent clumps have merged.</li>
	 * </ul>
	 * 
	 * @param amount number
	 * @return
	 */
	public AbstractNumberOfClumps numberOfClumps(int amount) {
		return (AbstractNumberOfClumps) this.numberOfClumps().addArgument(amount);
	}
	
	/**
	 * Specifies the extent to which land growth respects borders and actually stops at a border.
	 * 
	 * <ul>
	 * 	<li>Low values allow lands to exceed specified borders, giving ragged looking edges when land is constrained by borders.</li>
	 *  <li>0 causes land growth to ignore borders entirely.</li>
	 *  <li>100 (or any negative value) means that borders are fully respected, resulting in perfectly straight lands along borders.</li>
	 * </ul>
	 * 
	 * @param borderAdherence
	 * @return
	 */
	public AbstractBorderFuzziness borderFuzziness(int borderAdherence) {
		return (AbstractBorderFuzziness) this.borderFuzziness().addArgument(borderAdherence);
	}
	
	/**
	 * The extent to which land growth prefers to clump together near existing tiles.  High values create rounder lands, while low values create more elongated irregular lands.  Negative values create extremely snakey lands, and are generally not recommended.
	 * 
	 * @param factor number useful range of about 0 100
	 * @return
	 */
	public AbstractClumpingFactor clumpingFactor(int factor) {
		return (AbstractClumpingFactor) this.clumpingFactor().addArgument(factor);
	}
	
	/**
	 * The terrain will only be placed on tiles of height between min and max (inclusive).
	 * 
	 * <ul>
	 * 	<li>For most purposes, values between 0-7 are useful.  0 being the standard non-elevated height and 7 being the max height that can be produced by create_elevation</li>
	 * </ul>
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public AbstractHeightLimits heightLimits(int min, int max) {
		return (AbstractHeightLimits) this.heightLimits().addArgument(min).addArgument(max);
	}
	
	/**
	 * If the specified terrain is part of the connection, replace it with the new terrain specified.
	 * 
	 * <ul>
	 * 	<li>This attribute can, and should, be used multiple times for different terrains.</li>
	 *  <li>A terrain can be replaced with itself.</li>
	 *  <li>Connections can pass through terrains even if they are not specified.</li>
	 *  <li>BUG (DE):The old terrain refers to the terrain that was present at the beginning of <CONNECTION_GENERATION> - even if that terrain has already been replaced by a previous command or attribute.</li>
	 * </ul>
	 * @param oldTerrain terrain constant
	 * @param newTerrain terrain constant
	 * @return
	 */
	public AbstractReplaceTerrain replaceTerrain(String oldTerrain, String newTerrain) {
		return (AbstractReplaceTerrain) this.replaceTerrain().addArgument(oldTerrain).addArgument(newTerrain);
	}
	
	/**
	 * Enables terrain masking/layering for the terrain being created.  Terrain inherits all properties, placement restrictions, automatic objects (such as trees for forest terrains), minimap color, etc. from the terrain underneath (ie. base_terrain when masking over, or create_terrain when masking under)
	 * 
	 * <ul>
	 * 	<li>Terrain masking is a great way to blend terrains in a realistic and visually appealing manner.</li>
	 *  <li>Masking layers 1 and 2 have different masking patterns.</li>
	 *  <li>Terrain will have animated water if ANY of the component terrains are water.</li>
	 *  <li>Legacy terrains that are already a blend of two texture files cannot be visually masked.  They will contribute fully to the appearance of the final terrain.  These terrains are:
	 *		GRASS_SNOW, DIRT_SNOW, (dirt snow foundation), DLC_MOORLAND, DLC_JUNGLELEAVES, (road snow), (road fungus), DLC_DRYROAD, DLC_JUNGLEROAD, DLC_ROADGRAVEL</li>
	 *	<li>There are also some special cases with beach terrains, which may not always mask as expected.  (potentially a bug)</li>
	 * </ul>
	 * @param layer
	 * @return
	 */
	public AbstractTerrainMask terrainMask(int layer) {
		return (AbstractTerrainMask) this.terrainMask().addArgument(layer);
	}
	
	/**
	 * The cost of having the connection run through the specified terrain.
	 * 
	 * <ul>
	 * 	<li>This attribute can be used multiple times for different terrains.</li>
	 * <li>If all costs are equal, the connections will be straight lines.</li>
	 * <li>If some costs are higher, the algorithm will prefer going though lower cost routes, even if they are longer than the more direct route.</li>
	 * <li>A cost of 0 will prevent the connection generation from running through that terrain.  If a land origin is on such a terrain, or such a terrain MUST be crossed, then the connections to that land will not be generated at all.  This can be used to manipulate connections to only connect certain lands and not others</li>
	 * </ul>
	 * 
	 * @param terrainType terrain constant
	 * @param cost number (0-4294967296) (default 1)
	 * 	<ul>
			<li>0 (or any negative value) means that the connection CANNOT pass through the specified terrain at all, so 1 is the "lowest" cost</li>
			<li>For most usual applications a cost range of 1-15 is sufficient</li>
		</ul>
	 * @return
	 */
	public AbstractTerrainCost terrainCost(String terrainType, int cost) {
		return (AbstractTerrainCost) this.terrainCost().addArgument(terrainType).addArgument(cost);
	}
	
	/**
	 * When a connection passes through a tile of the specified terrain, the area within radius +/- variance will be subject to replace_terrain / default_terrain_replacement and terrains will be replaced accordingly.
	 * 
	 * <ul>
	 * 	<li>This attribute can be used multiple times for different terrains.</li>
	 *  <li>A radius of 0 will still replace a single-tile width path.</li>
	 *  <li>Variance is randomly selected for each tile crossed.</li>
	 *  <li>If variance is larger than radius, it can reduce the radius to a negative value, in which case no terrain will be replaced around these specific locations.</li>
	 * </ul>
	 * 
	 * @param terrainType terrain constant
	 * @param radius number. Given that a Ludicrously-sized map is 480 tiles wide, 961 will be enough to cover the entire map in all situations.
	 * @param variance number
	 * @return
	 */
	public AbstractTerrainSize terrainSize(String terrainType, int radius, int variance) {
		return (AbstractTerrainSize) this.terrainSize().addArgument(terrainType).addArgument(radius).addArgument(variance);
	}
	
	// terminal nodes
	
	/**
	 * adsd multiple lines of comments
	 * @param lines
	 * @return
	 */
	public StandardComment comment(String... lines) {
		return new StandardComment(Sets.immutable.of(lines));
	}
	
	/**
	 * add a single comment
	 * @param line
	 * @return
	 */
	public StandardComment comment(String line) {
		return new StandardComment(Sets.immutable.of(line));
	}

	public StandardComment comment(RichIterable<String> lines) {
		return new StandardComment(lines);
	}
	
	public AbstractInclude include(String file) {
		return new StandardInclude(file);
	}
	
	public AbstractIncludeDrs includeDrs(String file) {
		return new StandardIncludeDrs(file);
	}
	
	/**
	 * Create an association between a name and an int value
	 * @param name
	 * @param value
	 * @return
	 */
	public AbstractConst constant(String name, int value) {
		return new StandardConst(name, value);
	}
	
	/**
	 * Create an association between a name and an int value
	 * @param symbol symbol used to create the const
	 * @return
	 */
	public AbstractConst constant(RMSConstSymbol symbol) {
		return new StandardConst(symbol.getName(), symbol.getValue());
	}
	
	public AbstractDefine define(String value) {
		return new StandardDefine(value);
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
}
