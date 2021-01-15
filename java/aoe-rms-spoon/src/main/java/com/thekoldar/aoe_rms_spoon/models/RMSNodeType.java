package com.thekoldar.aoe_rms_spoon.models;

public enum RMSNodeType {

	//general
	ROOT(""),
	EXPR(""),
	IF("if"),
	ELSE("else"),
	RANDOM(""),
	PERCENT_CHANCE("percent_change"),
	
	//commands
	
    PLAYER_SETUP("PLAYER_SETUP"),
    LAND_GENERATION("LAND_GENERATION"),
    ELEVATION_GENERATION("ELEVATION_GENERATION"),
    CLIFF_GENERATION("CLIFF_GENERATION"),
    TERRAIN_GENERATION("TERRAIN_GENERATOR"),
    CONNECTION_GENERATION("CONNECTION_GENERATION"),
    OBJECTS_GENERATION("OBJECTS_GENERATION"),
 
    CREATE_PLAYER_LANDS("create_player_lands"),
    RANDOM_PLACEMENT("random_placement"),
    GROUPED_BY_TEAM("grouped_by_team"),
    DIRECT_PLACEMENT("direct_placement"),
    NOMAD_RESOURCES("nomad_resources"),
    AI_INFO_MAP_TYPE("ai_info_map_type"),
    WEATHER_TYPE("weather_type"),
    EFFECT_AMOUNT("effect_amount"),
    EFFECT_PERCENT("effect_percent"),
    GUARD_STATE("guard_state"),
    TERRAIN_STATE("terrain_state"),
    SET_GAIA_CIVILIZATION("set_gaia_civilization"),
    BEHAVIOR_VERSION("behavior_version"),
    BASE_TERRAIN("base_terrain"),
    BASE_LAYER("base_layer"),
    ENABLE_WAVES("enable_waves"),
    CREATE_LAND("create_land"),
    TERRAIN_TYPE("terrain_type"),
    LAND_PERCENT("land_percent"),
    NUMBER_OF_TILES("number_of_tiles"),
    BASE_SIZE("base_size"),
    LAND_POSITION("land_position"),
    CIRCLE_RADIUS("circle_radius"),
    CIRCLE_PLACEMENT("circle_palcement"),
    LEFT_BORDER("left_border"),
    RIGHT_BORDER("right_border"),
    TOP_BORDER("top_border"),
    BOTTOM_BORDER("bottom_border"),
    BORDER_FUZZINESS("border_fuzziness"),
    CLUMPING_FACTOR("clumping_factor"),
    BASE_ELEVATION("base_elevation"),
    ASSIGN_TO_PLAYER("assign_to_player"),
    ASSIGN_TO("assign_to"),
    ZONE("zone"),
    SET_ZONE_BY_TEAM("set_zone_by_team"),
    SET_ZONE_RANDOMLY("set_zone_randomly"),
    OTHER_ZONE_AVOIDANCE_DISTANCE("other_zone_avoidance_distance"),
    MIN_PLACEMENT_DISTANCE("min_placement_distance"),
    LAND_ID("land_id"),
    CREATE_ELEVATION("create_elevation"),
    NUMBER_OF_CLUMPS("number_of_clumps"),
    SET_SCALE_BY_SIZE("set_scale_by_size"),
    SET_SCALE_BY_GROUPS("set_scale_by_groups"),
    SPACING("spacing"),
    ENABLE_BALANCED_ELEVATION("enable_balanced_elevation"),
    MIN_NUMBER_OF_CLIFFS("min_number_of_cliffs"),
    MAX_NUMBER_OF_CLIFFS("max_number_of_cliffs"),
    MIN_LENGTH_OF_CLIFF("min_length_of_cliff"),
    MAX_LENGTH_OF_CLIFF("max_length_of_cliff"),
    CLIFF_CURLINESS("cliff_curliness"),
    MIN_DISTANCE_CLIFFS("min_distance_cliffs"),
    MIN_TERRAIN_DISTANCE("min_terrain_distance"),
    COLOR_CORRECTION("color_correction"),
    CREATE_TERRAIN("create_terrain"),
    TERRAIN_MASK("terrain_mask"),
    SPACING_TO_OTHER_TERRAIN_TYPES("spacing_to_other_terrain_types"),
    SET_FLAT_TERRAIN_ONLY("set_flat_terrain_only"),
    SET_AVOID_PLAYER_START_AREAS("set_avoid_player_start_areas"),
    HEIGHT_LIMITS("height_limits"),
    CREATE_CONNECT_ALL_PLAYERS_LAND("create_connect_all_players_land"),
    CREATE_CONNECT_TEAMS_LANDS("create_connect_teams_lands"),
    CREATE_CONNECT_ALL_LANDS("create_connect_all_lands"),
    CREATE_CONNECT_SAME_LAND_ZONES("create_connect_same_land_zones"),
    CREATE_CONNECT_TO_NONPLAYER_LAND("create_connect_to_nonplayer_land"),
    DEFAULT_TERRAIN_REPLACEMENT("default_terrain_replacement"),
    REPLACE_TERRAIN("replace_terrain"),
    TERRAIN_COST("terrain_cost"),
    TERRAIN_SIZE("terrain_size"),
    CREATE_OBJECT("create_object"),
    NUMBER_OF_OBJECTS("number_of_objects"),
    NUMBER_OF_GROUPS("number_of_groups"),
    GROUP_VARIANCE("group_variance"),
    RESOURCE_DELTA("resource_delta"),
    SECOND_OBJECT("second_object"),
    SET_SCALING_TO_MAP_SIZE("set_scaling_to_map_size"),
    SET_SCALING_TO_PLAYER_NUMBER("set_scaling_to_player_number"),
    SET_PLACE_FOR_EVERY_PLAYER("set_place_for_every_player"),
    PLACE_ON_SPECIFIC_LAND_ID("place_on_specific_land_id"),
    SET_GAIA_OBJECT_ONLY("set_gaia_object_only"),
    SET_GAIA_UNCONVERTIBLE("set_gaia_unconvertible"),
    GROUP_PLACEMENT_RADIUS("group_placement_radius"),
    SET_TIGHT_GROUPING("set_tight_grouping"),
    SET_LOOSE_GROUPING("set_loose_grouping"),
    TERRAIN_TO_PLACE_ON("terrain_to_place_on"),
    LAYER_TO_PLACE_ON("layer_to_place_on"),
    PLACE_ON_FOREST_ZONE("place_on_forest_zone"),
    AVOID_FOREST_ZONE("avoid_forest_zone"),
    AVOID_CLIFF_ZONE("avoid_cliff_zone"),
    MIN_DISTANCE_TO_PLAYERS("min_distance_to_players"),
    MAX_DISTANCE_TO_PLAYERS("max_distance_to_players"),
    MAX_DISTANCE_TO_OTHER_ZONES("max_distance_to_other_zones"),
    MIN_DISTANCE_GROUP_PLACEMENT("min_distance_group_placement"),
    TEMP_MIN_DISTANCE_GROUP_PLACEMENT("temp_min_distance_group_placement"),
    FIND_CLOSEST("find_closest"),
    FORCE_PLACEMENT("force_placement"),
    ACTOR_AREA("actor_area"),
    ACTOR_AREA_RADIUS("actor_area_radius"),
    ACTOR_AREA_TO_PLACE_IN("actor_area_to_place_in"),
    AVOID_ACTOR_AREA("avoid_actor_area"),
    AVOID_ALL_ACTOR_AREAS("avoid_all_actor_areas"),
   
    COMMENT(""),
    INCLUDE("include"),
    INCLUDE_DRS("include_drs"),
    CONST("const"),
    DEFINE("define")
    ;
	      
    
    private String rmsName;
    
    private RMSNodeType(String id) {
		this.rmsName = id;
	}

	public String getRmsName() {
		return rmsName;
	}
    
    
}
