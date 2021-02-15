package com.thekoldar.aoe_rms_spoon.ast;

/**
 * Set of types a node inside the RMS AST tree may have
 * @author massi
 *
 */
public enum RMSNodeType {

	//general
	ROOT("root", false, false, false),
	MULTIPLEXER("multipelxer", false, false, false),
	EXPR("expr", false, true, false),
	IF("if", false, false, false),
	ELSE("else", false, false, false),
	RANDOM("", false, false, false),
	PERCENT_CHANCE("percent_change", false, false, false),
	RANDOM_NUMBER("rnd", false, false, false),
	
	// sections
	
    PLAYER_SETUP("PLAYER_SETUP", false, false, false),
    LAND_GENERATION("LAND_GENERATION", false, false, false),
    ELEVATION_GENERATION("ELEVATION_GENERATION", false, false, false),
    CLIFF_GENERATION("CLIFF_GENERATION", false, false, false),
    TERRAIN_GENERATION("TERRAIN_GENERATION", false, false, false),
    CONNECTION_GENERATION("CONNECTION_GENERATION", false, false, false),
    OBJECTS_GENERATION("OBJECTS_GENERATION", false, false, false),
    
    // commands
 
    CREATE_PLAYER_LANDS("create_player_lands", true, false, true),
    RANDOM_PLACEMENT("random_placement", true, false, false),
    GROUPED_BY_TEAM("grouped_by_team", true, false, false),
    DIRECT_PLACEMENT("direct_placement", true, false, false),
    NOMAD_RESOURCES("nomad_resources", true, false, false),
    AI_INFO_MAP_TYPE("ai_info_map_type", true, false, false),
    WEATHER_TYPE("weather_type", true, false, false),
    EFFECT_AMOUNT("effect_amount", true, false, false),
    EFFECT_PERCENT("effect_percent", true, false, false),
    GUARD_STATE("guard_state", true, false, false),
    TERRAIN_STATE("terrain_state", true, false, false),
    SET_GAIA_CIVILIZATION("set_gaia_civilization", true, false, false),
    BEHAVIOR_VERSION("behavior_version", true, false, false),
    BASE_TERRAIN("base_terrain", true, false, false),
    BASE_LAYER("base_layer", true, false, false),
    ENABLE_WAVES("enable_waves", true, false, false),
    CREATE_LAND("create_land", true, false, true),
    TERRAIN_TYPE("terrain_type", true, false, false),
    LAND_PERCENT("land_percent", true, false, false),
    NUMBER_OF_TILES("number_of_tiles", true, false, false),
    BASE_SIZE("base_size", true, false, false),
    LAND_POSITION("land_position", true, false, false),
    CIRCLE_RADIUS("circle_radius", true, false, false),
    CIRCLE_PLACEMENT("circle_placement", true, false, false),
    LEFT_BORDER("left_border", true, false, false),
    RIGHT_BORDER("right_border", true, false, false),
    TOP_BORDER("top_border", true, false, false),
    BOTTOM_BORDER("bottom_border", true, false, false),
    BORDER_FUZZINESS("border_fuzziness", true, false, false),
    CLUMPING_FACTOR("clumping_factor", true, false, false),
    BASE_ELEVATION("base_elevation", true, false, false),
    ASSIGN_TO_PLAYER("assign_to_player", true, false, false),
    ASSIGN_TO("assign_to", true, false, false),
    ZONE("zone", true, false, false),
    SET_ZONE_BY_TEAM("set_zone_by_team", true, false, false),
    SET_ZONE_RANDOMLY("set_zone_randomly", true, false, false),
    OTHER_ZONE_AVOIDANCE_DISTANCE("other_zone_avoidance_distance", true, false, false),
    MIN_PLACEMENT_DISTANCE("min_placement_distance", true, false, false),
    LAND_ID("land_id", true, false, false),
    CREATE_ELEVATION("create_elevation", true, false, true),
    NUMBER_OF_CLUMPS("number_of_clumps", true, false, false),
    SET_SCALE_BY_SIZE("set_scale_by_size", true, false, false),
    SET_SCALE_BY_GROUPS("set_scale_by_groups", true, false, false),
    SPACING("spacing", true, false, false),
    ENABLE_BALANCED_ELEVATION("enable_balanced_elevation", true, false, false),
    MIN_NUMBER_OF_CLIFFS("min_number_of_cliffs", true, false, false),
    MAX_NUMBER_OF_CLIFFS("max_number_of_cliffs", true, false, false),
    MIN_LENGTH_OF_CLIFF("min_length_of_cliff", true, false, false),
    MAX_LENGTH_OF_CLIFF("max_length_of_cliff", true, false, false),
    CLIFF_CURLINESS("cliff_curliness", true, false, false),
    MIN_DISTANCE_CLIFFS("min_distance_cliffs", true, false, false),
    MIN_TERRAIN_DISTANCE("min_terrain_distance", true, false, false),
    COLOR_CORRECTION("color_correction", true, false, false),
    CREATE_TERRAIN("create_terrain", true, false, true),
    TERRAIN_MASK("terrain_mask", true, false, false),
    SPACING_TO_OTHER_TERRAIN_TYPES("spacing_to_other_terrain_types", true, false, false),
    SET_FLAT_TERRAIN_ONLY("set_flat_terrain_only", true, false, false),
    SET_AVOID_PLAYER_START_AREAS("set_avoid_player_start_areas", true, false, false),
    HEIGHT_LIMITS("height_limits", true, false, false),
    CREATE_CONNECT_ALL_PLAYERS_LAND("create_connect_all_players_land", true, false, true),
    CREATE_CONNECT_TEAMS_LANDS("create_connect_teams_lands", true, false, true),
    CREATE_CONNECT_ALL_LANDS("create_connect_all_lands", true, false, true),
    CREATE_CONNECT_SAME_LAND_ZONES("create_connect_same_land_zones", true, false, true),
    CREATE_CONNECT_TO_NONPLAYER_LAND("create_connect_to_nonplayer_land", true, false, true),
    DEFAULT_TERRAIN_REPLACEMENT("default_terrain_replacement", true, false, false),
    REPLACE_TERRAIN("replace_terrain", true, false, false),
    TERRAIN_COST("terrain_cost", true, false, false),
    TERRAIN_SIZE("terrain_size", true, false, false),
    CREATE_OBJECT("create_object", true, false, true),
    NUMBER_OF_OBJECTS("number_of_objects", true, false, false),
    NUMBER_OF_GROUPS("number_of_groups", true, false, false),
    GROUP_VARIANCE("group_variance", true, false, false),
    RESOURCE_DELTA("resource_delta", true, false, false),
    SECOND_OBJECT("second_object", true, false, false),
    SET_SCALING_TO_MAP_SIZE("set_scaling_to_map_size", true, false, false),
    SET_SCALING_TO_PLAYER_NUMBER("set_scaling_to_player_number", true, false, false),
    SET_PLACE_FOR_EVERY_PLAYER("set_place_for_every_player", true, false, false),
    PLACE_ON_SPECIFIC_LAND_ID("place_on_specific_land_id", true, false, false),
    SET_GAIA_OBJECT_ONLY("set_gaia_object_only", true, false, false),
    SET_GAIA_UNCONVERTIBLE("set_gaia_unconvertible", true, false, false),
    GROUP_PLACEMENT_RADIUS("group_placement_radius", true, false, false),
    SET_TIGHT_GROUPING("set_tight_grouping", true, false, false),
    SET_LOOSE_GROUPING("set_loose_grouping", true, false, false),
    TERRAIN_TO_PLACE_ON("terrain_to_place_on", true, false, false),
    LAYER_TO_PLACE_ON("layer_to_place_on", true, false, false),
    PLACE_ON_FOREST_ZONE("place_on_forest_zone", true, false, false),
    AVOID_FOREST_ZONE("avoid_forest_zone", true, false, false),
    AVOID_CLIFF_ZONE("avoid_cliff_zone", true, false, false),
    MIN_DISTANCE_TO_PLAYERS("min_distance_to_players",  true, false, false),
    MAX_DISTANCE_TO_PLAYERS("max_distance_to_players", true, false, false),
    MAX_DISTANCE_TO_OTHER_ZONES("max_distance_to_other_zones", true, false, false),
    MIN_DISTANCE_GROUP_PLACEMENT("min_distance_group_placement", true, false, false),
    TEMP_MIN_DISTANCE_GROUP_PLACEMENT("temp_min_distance_group_placement", true, false, false),
    FIND_CLOSEST("find_closest", true, false, false),
    FORCE_PLACEMENT("force_placement", true, false, false),
    ACTOR_AREA("actor_area", true, false, false),
    ACTOR_AREA_RADIUS("actor_area_radius", true, false, false),
    ACTOR_AREA_TO_PLACE_IN("actor_area_to_place_in", true, false, false),
    AVOID_ACTOR_AREA("avoid_actor_area", true, false, false),
    AVOID_ALL_ACTOR_AREAS("avoid_all_actor_areas", true, false, false),
   
    COMMENT("", false, false, false),
    INCLUDE("include", false, false, false),
    INCLUDE_DRS("include_drs", false, false, false),
    CONST("const", false, false, false),
    DEFINE("define", false, false, false)
    ;
	      
    
    private String rmsName;
    private boolean isCommand;
    private boolean isExpression;
    
    private RMSNodeType(String id, boolean isCommand, boolean isExpression, boolean isCommandWithExprArgument) {
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
