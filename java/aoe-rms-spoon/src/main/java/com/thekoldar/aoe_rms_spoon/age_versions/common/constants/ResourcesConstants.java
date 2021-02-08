package com.thekoldar.aoe_rms_spoon.age_versions.common.constants;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractEffectAmount;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractEffectPercent;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * used {@link AbstractEffectAmount} and {@link AbstractEffectPercent} to modify various things in the gamedata
 * @author massi
 *
 */
public class ResourcesConstants {
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static RichIterable<RMSConstSymbol> all() {
		return Utils.getPublicStaticFinalFieldsOfClass(ResourcesConstants.class, RMSConstSymbol.class);
	}
	
	public static final RMSConstSymbol AMOUNT_FOOD = new RMSConstSymbol("AMOUNT_FOOD", 0);
	public static final RMSConstSymbol AMOUNT_WOOD = new RMSConstSymbol("AMOUNT_WOOD", 1);
	public static final RMSConstSymbol AMOUNT_STONE = new RMSConstSymbol("AMOUNT_STONE", 2);
	public static final RMSConstSymbol AMOUNT_GOLD = new RMSConstSymbol("AMOUNT_GOLD", 3);
	public static final RMSConstSymbol AMOUNT_POPULATION_CAP = new RMSConstSymbol("AMOUNT_POPULATION_CAP", 4);
	public static final RMSConstSymbol AMOUNT_POPULATION = new RMSConstSymbol("AMOUNT_POPULATION", 11);
	public static final RMSConstSymbol AMOUNT_CONVERT_PRIEST = new RMSConstSymbol("AMOUNT_CONVERT_PRIEST", 27);
	public static final RMSConstSymbol AMOUNT_CONVERT_BUILDING = new RMSConstSymbol("AMOUNT_CONVERT_BUILDING", 28);
	public static final RMSConstSymbol AMOUNT_BONUS_POPULATION_CAP = new RMSConstSymbol("AMOUNT_BONUS_POPULATION_CAP", 32);
	public static final RMSConstSymbol AMOUNT_TOWN_CENTER_UNAVAILABLE = new RMSConstSymbol("AMOUNT_TOWN_CENTER_UNAVAILABLE", 48);
	public static final RMSConstSymbol AMOUNT_STARTING_FOOD = new RMSConstSymbol("AMOUNT_STARTING_FOOD", 91);
	public static final RMSConstSymbol AMOUNT_STARTING_WOOD = new RMSConstSymbol("AMOUNT_STARTING_WOOD", 92);
	public static final RMSConstSymbol AMOUNT_STARTING_STONE = new RMSConstSymbol("AMOUNT_STARTING_STONE", 93);
	public static final RMSConstSymbol AMOUNT_STARTING_GOLD = new RMSConstSymbol("AMOUNT_STARTING_GOLD", 94);
	public static final RMSConstSymbol AMOUNT_BUILDING_TRICKLE_FOOD = new RMSConstSymbol("AMOUNT_BUILDING_TRICKLE_FOOD", 205);
	public static final RMSConstSymbol AMOUNT_BUILDING_TRICKLE_WOOD = new RMSConstSymbol("AMOUNT_BUILDING_TRICKLE_WOOD", 206);
	public static final RMSConstSymbol AMOUNT_BUILDING_TRICKLE_STONE = new RMSConstSymbol("AMOUNT_BUILDING_TRICKLE_STONE", 207);
	public static final RMSConstSymbol AMOUNT_BUILDING_TRICKLE_GOLD = new RMSConstSymbol("AMOUNT_BUILDING_TRICKLE_GOLD", 208);
	public static final RMSConstSymbol AMOUNT_REVEAL_ENEMY = new RMSConstSymbol("AMOUNT_REVEAL_ENEMY", 209);
	public static final RMSConstSymbol AMOUNT_REVEAL_RELICS = new RMSConstSymbol("AMOUNT_REVEAL_RELICS", 210);
	public static final RMSConstSymbol AMOUNT_ELEVATION_HIGHER_BONUS = new RMSConstSymbol("AMOUNT_ELEVATION_HIGHER_BONUS", 211);
	public static final RMSConstSymbol AMOUNT_ELEVATION_LOWER_BONUS = new RMSConstSymbol("AMOUNT_ELEVATION_LOWER_BONUS", 212);
	public static final RMSConstSymbol AMOUNT_MONUMENT_TRICKLE_FOOD = new RMSConstSymbol("AMOUNT_MONUMENT_TRICKLE_FOOD", 221);
	public static final RMSConstSymbol AMOUNT_MONUMENT_TRICKLE_WOOD = new RMSConstSymbol("AMOUNT_MONUMENT_TRICKLE_WOOD", 222);
	public static final RMSConstSymbol AMOUNT_MONUMENT_TRICKLE_STONE = new RMSConstSymbol("AMOUNT_MONUMENT_TRICKLE_STONE", 223);
	public static final RMSConstSymbol AMOUNT_MONUMENT_TRICKLE_GOLD = new RMSConstSymbol("AMOUNT_MONUMENT_TRICKLE_GOLD", 224);
	public static final RMSConstSymbol AMOUNT_FOOD_GENERATION = new RMSConstSymbol("AMOUNT_FOOD_GENERATION", 230);
	public static final RMSConstSymbol AMOUNT_WOOD_GENERATION = new RMSConstSymbol("AMOUNT_WOOD_GENERATION", 231);
	public static final RMSConstSymbol AMOUNT_STONE_GENERATION = new RMSConstSymbol("AMOUNT_STONE_GENERATION", 232);
	public static final RMSConstSymbol AMOUNT_GOLD_GENERATION = new RMSConstSymbol("AMOUNT_GOLD_GENERATION", 233);

	
}
