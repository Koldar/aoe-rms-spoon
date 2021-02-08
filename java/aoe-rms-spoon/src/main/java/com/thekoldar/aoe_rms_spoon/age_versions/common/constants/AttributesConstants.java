package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class AttributesConstants {

	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(AttributesConstants.class, RMSConstSymbol.class);
	}
	
	public static final RMSConstSymbol ATTR_DISABLE = new RMSConstSymbol("ATTR_DISABLE", 0);
	public static final RMSConstSymbol ATTR_ENABLE = new RMSConstSymbol("ATTR_ENABLE", 1);
	public static final RMSConstSymbol ATTR_FORCE = new RMSConstSymbol("ATTR_FORCE", 2);
	public static final RMSConstSymbol ATTR_SET = new RMSConstSymbol("ATTR_SET", 0);
	public static final RMSConstSymbol ATTR_ADD = new RMSConstSymbol("ATTR_ADD", 1);

	public static final RMSConstSymbol ATTR_SET_TIME = new RMSConstSymbol("ATTR_SET_TIME", -1);
	public static final RMSConstSymbol ATTR_ADD_TIME = new RMSConstSymbol("ATTR_ADD_TIME", -2);
	public static final RMSConstSymbol ATTR_SET_FOOD_COST = new RMSConstSymbol("ATTR_SET_FOOD_COST", 0);
	public static final RMSConstSymbol ATTR_SET_WOOD_COST = new RMSConstSymbol("ATTR_SET_WOOD_COST", 1);
	public static final RMSConstSymbol ATTR_SET_STONE_COST = new RMSConstSymbol("ATTR_SET_STONE_COST", 2);
	public static final RMSConstSymbol ATTR_SET_GOLD_COST = new RMSConstSymbol("ATTR_SET_GOLD_COST", 3);
	public static final RMSConstSymbol ATTR_SET_LOCATION = new RMSConstSymbol("ATTR_SET_LOCATION", 4);
	public static final RMSConstSymbol ATTR_SET_BUTTON = new RMSConstSymbol("ATTR_SET_BUTTON", 5);
	public static final RMSConstSymbol ATTR_SET_ICON = new RMSConstSymbol("ATTR_SET_ICON", 6);
	public static final RMSConstSymbol ATTR_ADD_FOOD_COST = new RMSConstSymbol("ATTR_ADD_FOOD_COST", 16384);
	public static final RMSConstSymbol ATTR_ADD_WOOD_COST = new RMSConstSymbol("ATTR_ADD_WOOD_COST", 16385);
	public static final RMSConstSymbol ATTR_ADD_STONE_COST = new RMSConstSymbol("ATTR_ADD_STONE_COST", 16386);
	public static final RMSConstSymbol ATTR_ADD_GOLD_COST = new RMSConstSymbol("ATTR_ADD_GOLD_COST", 16387);

	
	public static final RMSConstSymbol ATTR_HITPOINTS = new RMSConstSymbol("ATTR_HITPOINTS", 0);
	public static final RMSConstSymbol ATTR_LINE_OF_SIGHT = new RMSConstSymbol("ATTR_LINE_OF_SIGHT", 1);
	public static final RMSConstSymbol ATTR_GARRISON_CAPACITY = new RMSConstSymbol("ATTR_GARRISON_CAPACITY", 2);
	public static final RMSConstSymbol ATTR_RADIUS_1 = new RMSConstSymbol("ATTR_RADIUS_1", 3);
	public static final RMSConstSymbol ATTR_RADIUS_2 = new RMSConstSymbol("ATTR_RADIUS_2", 4);
	public static final RMSConstSymbol ATTR_MOVE_SPEED = new RMSConstSymbol("ATTR_MOVE_SPEED", 5);
	public static final RMSConstSymbol ATTR_ROTATE_SPEED = new RMSConstSymbol("ATTR_ROTATE_SPEED", 6);
	public static final RMSConstSymbol ATTR_ARMOR = new RMSConstSymbol("ATTR_ARMOR", 8);
	public static final RMSConstSymbol ATTR_ATTACK = new RMSConstSymbol("ATTR_ATTACK", 9);
	public static final RMSConstSymbol ATTR_RELOAD_TIME = new RMSConstSymbol("ATTR_RELOAD_TIME", 10);
	public static final RMSConstSymbol ATTR_ACCURACY_PERCENT = new RMSConstSymbol("ATTR_ACCURACY_PERCENT", 11);
	public static final RMSConstSymbol ATTR_MAX_RANGE = new RMSConstSymbol("ATTR_MAX_RANGE", 12);
	public static final RMSConstSymbol ATTR_WORK_RATE = new RMSConstSymbol("ATTR_WORK_RATE", 13);
	public static final RMSConstSymbol ATTR_RESOURCE_CARRY = new RMSConstSymbol("ATTR_RESOURCE_CARRY", 14);
	public static final RMSConstSymbol ATTR_BASE_ARMOR = new RMSConstSymbol("ATTR_BASE_ARMOR", 15);
	public static final RMSConstSymbol ATTR_PROJECTILE_ID = new RMSConstSymbol("ATTR_PROJECTILE_ID", 16);
	public static final RMSConstSymbol ATTR_UPGRADE_GRAPHIC = new RMSConstSymbol("ATTR_UPGRADE_GRAPHIC", 17);
	public static final RMSConstSymbol ATTR_PROJECTILE_INTELLIGENCE = new RMSConstSymbol("ATTR_PROJECTILE_INTELLIGENCE", 19);
	public static final RMSConstSymbol ATTR_MIN_RANGE = new RMSConstSymbol("ATTR_MIN_RANGE", 20);
	public static final RMSConstSymbol ATTR_STORAGE_VALUE = new RMSConstSymbol("ATTR_STORAGE_VALUE", 21);
	public static final RMSConstSymbol ATTR_BLAST_RADIUS = new RMSConstSymbol("ATTR_BLAST_RADIUS", 22);
	public static final RMSConstSymbol ATTR_SEARCH_RADIUS = new RMSConstSymbol("ATTR_SEARCH_RADIUS", 23);
	public static final RMSConstSymbol ATTR_HIDDEN_DAMAGE_RESIST = new RMSConstSymbol("ATTR_HIDDEN_DAMAGE_RESIST", 24);
	public static final RMSConstSymbol ATTR_ICON_ID = new RMSConstSymbol("ATTR_ICON_ID", 25);
	public static final RMSConstSymbol ATTR_HERO_STATUS = new RMSConstSymbol("ATTR_HERO_STATUS", 40);
	public static final RMSConstSymbol ATTR_ATTACK_DELAY = new RMSConstSymbol("ATTR_ATTACK_DELAY", 41);
	public static final RMSConstSymbol ATTR_TRAIN_LOCATION = new RMSConstSymbol("ATTR_TRAIN_LOCATION", 42);
	public static final RMSConstSymbol ATTR_TRAIN_BUTTON = new RMSConstSymbol("ATTR_TRAIN_BUTTON", 43);
	public static final RMSConstSymbol ATTR_BLAST_LEVEL = new RMSConstSymbol("ATTR_BLAST_LEVEL", 44);
	public static final RMSConstSymbol ATTR_SHOWN_ATTACK = new RMSConstSymbol("ATTR_SHOWN_ATTACK", 46);
	public static final RMSConstSymbol ATTR_SHOWN_RANGE = new RMSConstSymbol("ATTR_SHOWN_RANGE", 47);
	public static final RMSConstSymbol ATTR_SHOWN_MELEE_ARMOR = new RMSConstSymbol("ATTR_SHOWN_MELEE_ARMOR", 48);
	public static final RMSConstSymbol ATTR_SHOWN_PIERCE_ARMOR = new RMSConstSymbol("ATTR_SHOWN_PIERCE_ARMOR", 49);
	public static final RMSConstSymbol ATTR_NAME_ID = new RMSConstSymbol("ATTR_NAME_ID", 50);
	public static final RMSConstSymbol ATTR_CREATE_SDESC_ID = new RMSConstSymbol("ATTR_CREATE_SDESC_ID", 51);
	public static final RMSConstSymbol ATTR_TERRAIN_ID = new RMSConstSymbol("ATTR_TERRAIN_ID", 53);
	public static final RMSConstSymbol ATTR_DEAD_ID = new RMSConstSymbol("ATTR_DEAD_ID", 57);
	public static final RMSConstSymbol ATTR_BOARDING_RELOAD = new RMSConstSymbol("ATTR_BOARDING_RELOAD", 80);
	public static final RMSConstSymbol ATTR_RESOURCE_COST = new RMSConstSymbol("ATTR_RESOURCE_COST", 100);
	public static final RMSConstSymbol ATTR_CREATION_TIME = new RMSConstSymbol("ATTR_CREATION_TIME", 101);
	public static final RMSConstSymbol ATTR_GARRISON_ARROWS = new RMSConstSymbol("ATTR_GARRISON_ARROWS", 102);
	public static final RMSConstSymbol ATTR_FOOD_COST = new RMSConstSymbol("ATTR_FOOD_COST", 103);
	public static final RMSConstSymbol ATTR_WOOD_COST = new RMSConstSymbol("ATTR_WOOD_COST", 104);
	public static final RMSConstSymbol ATTR_GOLD_COST = new RMSConstSymbol("ATTR_GOLD_COST", 105);
	public static final RMSConstSymbol ATTR_STONE_COST = new RMSConstSymbol("ATTR_STONE_COST", 106);
	public static final RMSConstSymbol ATTR_MAX_DUP_MISSILES = new RMSConstSymbol("ATTR_MAX_DUP_MISSILES", 107);
	public static final RMSConstSymbol ATTR_HEALING_RATE = new RMSConstSymbol("ATTR_HEALING_RATE", 108);

	
	public static final RMSConstSymbol ATTR_HERO_HEAL_TIME = new RMSConstSymbol("ATTR_HERO_HEAL_TIME", 45);
	public static final RMSConstSymbol ATTR_CREATE_LDESC_ID = new RMSConstSymbol("ATTR_CREATE_LDESC_ID", 52);
	public static final RMSConstSymbol ATTR_TRAITS = new RMSConstSymbol("ATTR_TRAITS", 54);
	public static final RMSConstSymbol ATTR_CIV_ID = new RMSConstSymbol("ATTR_CIV_ID", 55);
	public static final RMSConstSymbol ATTR_PIECE = new RMSConstSymbol("ATTR_PIECE", 56);

}
