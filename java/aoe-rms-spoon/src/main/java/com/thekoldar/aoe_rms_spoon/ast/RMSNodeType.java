package com.thekoldar.aoe_rms_spoon.ast;

public enum RMSNodeType {

	//general
	ROOT("root", false, false),
	MULTIPLEXER("multipelxer", false, false),
	EXPR("expr", false, true),
	IF("if", false, false),
	ELSE("else", false, false),
	RANDOM("", false, false),
	PERCENT_CHANCE("percent_change", false, false),
	RANDOM_NUMBER("rnd", false, false),
	
	//commands
	
    PLAYER_SETUP("PLAYER_SETUP", false, false),
    LAND_GENERATION("LAND_GENERATION", false, false),
    ELEVATION_GENERATION("ELEVATION_GENERATION", false, false),
    CLIFF_GENERATION("CLIFF_GENERATION", false, false),
    TERRAIN_GENERATION("TERRAIN_GENERATION", false, false),
    CONNECTION_GENERATION("CONNECTION_GENERATION", false, false),
    OBJECTS_GENERATION("OBJECTS_GENERATION", false, false),
 
    CREATE_PLAYER_LANDS("create_player_lands", true, false),
    RANDOM_PLACEMENT("random_placement", true, false),
    GROUPED_BY_TEAM("grouped_by_team", true, false),
    DIRECT_PLACEMENT("direct_placement", true, false),
    NOMAD_RESOURCES("nomad_resources", true, false),
    AI_INFO_MAP_TYPE("ai_info_map_type", true, false),
    WEATHER_TYPE("weather_type", true, false),
    EFFECT_AMOUNT("effect_amount", true, false),
    EFFECT_PERCENT("effect_percent", true, false),
    GUARD_STATE("guard_state", true, false),
    TERRAIN_STATE("terrain_state", true, false),
    SET_GAIA_CIVILIZATION("set_gaia_civilization", true, false),
    BEHAVIOR_VERSION("behavior_version", true, false),
    BASE_TERRAIN("base_terrain", true, false),
    BASE_LAYER("base_layer", true, false),
    ENABLE_WAVES("enable_waves", true, false),
    CREATE_LAND("create_land", true, false),
    TERRAIN_TYPE("terrain_type", true, false),
    LAND_PERCENT("land_percent", true, false),
    NUMBER_OF_TILES("number_of_tiles", true, false),
    BASE_SIZE("base_size", true, false),
    LAND_POSITION("land_position", true, false),
    CIRCLE_RADIUS("circle_radius", true, false),
    CIRCLE_PLACEMENT("circle_placement", true, false),
    LEFT_BORDER("left_border", true, false),
    RIGHT_BORDER("right_border", true, false),
    TOP_BORDER("top_border", true, false),
    BOTTOM_BORDER("bottom_border", true, false),
    BORDER_FUZZINESS("border_fuzziness", true, false),
    CLUMPING_FACTOR("clumping_factor", true, false),
    BASE_ELEVATION("base_elevation", true, false),
    ASSIGN_TO_PLAYER("assign_to_player", true, false),
    ASSIGN_TO("assign_to", true, false),
    ZONE("zone", true, false),
    SET_ZONE_BY_TEAM("set_zone_by_team", true, false),
    SET_ZONE_RANDOMLY("set_zone_randomly", true, false),
    OTHER_ZONE_AVOIDANCE_DISTANCE("other_zone_avoidance_distance", true, false),
    MIN_PLACEMENT_DISTANCE("min_placement_distance", true, false),
    LAND_ID("land_id", true, false),
    CREATE_ELEVATION("create_elevation", true, false),
    NUMBER_OF_CLUMPS("number_of_clumps", true, false),
    SET_SCALE_BY_SIZE("set_scale_by_size", true, false),
    SET_SCALE_BY_GROUPS("set_scale_by_groups", true, false),
    SPACING("spacing", true, false),
    ENABLE_BALANCED_ELEVATION("enable_balanced_elevation", true, false),
    MIN_NUMBER_OF_CLIFFS("min_number_of_cliffs", true, false),
    MAX_NUMBER_OF_CLIFFS("max_number_of_cliffs", true, false),
    MIN_LENGTH_OF_CLIFF("min_length_of_cliff", true, false),
    MAX_LENGTH_OF_CLIFF("max_length_of_cliff", true, false),
    CLIFF_CURLINESS("cliff_curliness", true, false),
    MIN_DISTANCE_CLIFFS("min_distance_cliffs", true, false),
    MIN_TERRAIN_DISTANCE("min_terrain_distance", true, false),
    COLOR_CORRECTION("color_correction", true, false),
    CREATE_TERRAIN("create_terrain", true, false),
    TERRAIN_MASK("terrain_mask", true, false),
    SPACING_TO_OTHER_TERRAIN_TYPES("spacing_to_other_terrain_types", true, false),
    SET_FLAT_TERRAIN_ONLY("set_flat_terrain_only", true, false),
    SET_AVOID_PLAYER_START_AREAS("set_avoid_player_start_areas", true, false),
    HEIGHT_LIMITS("height_limits", true, false),
    CREATE_CONNECT_ALL_PLAYERS_LAND("create_connect_all_players_land", true, false),
    CREATE_CONNECT_TEAMS_LANDS("create_connect_teams_lands", true, false),
    CREATE_CONNECT_ALL_LANDS("create_connect_all_lands", true, false),
    CREATE_CONNECT_SAME_LAND_ZONES("create_connect_same_land_zones", true, false),
    CREATE_CONNECT_TO_NONPLAYER_LAND("create_connect_to_nonplayer_land", true, false),
    DEFAULT_TERRAIN_REPLACEMENT("default_terrain_replacement", true, false),
    REPLACE_TERRAIN("replace_terrain", true, false),
    TERRAIN_COST("terrain_cost", true, false),
    TERRAIN_SIZE("terrain_size", true, false),
    CREATE_OBJECT("create_object", true, false),
    NUMBER_OF_OBJECTS("number_of_objects", true, false),
    NUMBER_OF_GROUPS("number_of_groups", true, false),
    GROUP_VARIANCE("group_variance", true, false),
    RESOURCE_DELTA("resource_delta", true, false),
    SECOND_OBJECT("second_object", true, false),
    SET_SCALING_TO_MAP_SIZE("set_scaling_to_map_size", true, false),
    SET_SCALING_TO_PLAYER_NUMBER("set_scaling_to_player_number", true, false),
    SET_PLACE_FOR_EVERY_PLAYER("set_place_for_every_player", true, false),
    PLACE_ON_SPECIFIC_LAND_ID("place_on_specific_land_id", true, false),
    SET_GAIA_OBJECT_ONLY("set_gaia_object_only", true, false),
    SET_GAIA_UNCONVERTIBLE("set_gaia_unconvertible", true, false),
    GROUP_PLACEMENT_RADIUS("group_placement_radius", true, false),
    SET_TIGHT_GROUPING("set_tight_grouping", true, false),
    SET_LOOSE_GROUPING("set_loose_grouping", true, false),
    TERRAIN_TO_PLACE_ON("terrain_to_place_on", true, false),
    LAYER_TO_PLACE_ON("layer_to_place_on", true, false),
    PLACE_ON_FOREST_ZONE("place_on_forest_zone", true, false),
    AVOID_FOREST_ZONE("avoid_forest_zone", true, false),
    AVOID_CLIFF_ZONE("avoid_cliff_zone", true, false),
    MIN_DISTANCE_TO_PLAYERS("min_distance_to_players",  true, false),
    MAX_DISTANCE_TO_PLAYERS("max_distance_to_players", true, false),
    MAX_DISTANCE_TO_OTHER_ZONES("max_distance_to_other_zones", true, false),
    MIN_DISTANCE_GROUP_PLACEMENT("min_distance_group_placement", true, false),
    TEMP_MIN_DISTANCE_GROUP_PLACEMENT("temp_min_distance_group_placement", true, false),
    FIND_CLOSEST("find_closest", true, false),
    FORCE_PLACEMENT("force_placement", true, false),
    ACTOR_AREA("actor_area", true, false),
    ACTOR_AREA_RADIUS("actor_area_radius", true, false),
    ACTOR_AREA_TO_PLACE_IN("actor_area_to_place_in", true, false),
    AVOID_ACTOR_AREA("avoid_actor_area", true, false),
    AVOID_ALL_ACTOR_AREAS("avoid_all_actor_areas", true, false),
   
    COMMENT("", false, false),
    INCLUDE("include", false, false),
    INCLUDE_DRS("include_drs", false, false),
    CONST("const", false, false),
    DEFINE("define", false, false)
    ;
	      
    
    private String rmsName;
    private boolean isCommand;
    private boolean isExpression;
    
    private RMSNodeType(String id, boolean isCommand, boolean isExpression) {
		this.rmsName = id;
		this.isCommand = isCommand;
		this.isExpression = isExpression;
	}

	public String getRmsName() {
		return rmsName;
	}
	
	public boolean isCommand() {
		return this.isCommand;
	}
	
	public boolean isExpression() {
		return this.isExpression;
	}
    
    
}
