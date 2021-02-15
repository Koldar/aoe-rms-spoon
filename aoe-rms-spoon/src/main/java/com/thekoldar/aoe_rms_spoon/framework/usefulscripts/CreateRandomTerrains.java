package com.thekoldar.aoe_rms_spoon.framework.usefulscripts;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.primitive.IntObjectToIntFunction;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.api.tuple.primitive.IntObjectPair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

import com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals.MapSizes;
import com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals.SeasonDefines;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractLandGeneration;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.ast.functions.RandomNumberNode;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.MapSize;
import com.thekoldar.aoe_rms_spoon.framework.models.Point2D;
import com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces.IntObjectToObjectFunction;
import com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces.TriConsumer;
import com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces.TriFunction;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

/**
 * Contains useful function that can be used to create complex subscripts.
 * It is an interesting featuer because with these functions we can embed scripts inside the same rms file. In this way, the output file is a single file which can be safely transmitted into 
 * your mod.
 * 
 * @author massi
 *
 */
public class CreateRandomTerrains {

	/**
	 * generate code s.t. RMS will generate a random terrain const which contains a forest.
	 * At high level, we create a const containing the id of the walkable terrain chosen
	 * 
	 * @param aoe aoe version
	 * @param constToCreate name of the const to create
	 * @param considerLandWalkable true if you want to consider in the output the walkable terrains
	 * @param expectedLandWalkable the value we want a random terrain to have land walkable-wise
	 * @param considerShipWalkable true if you want to consider in the output navigable terrains
	 * @param expectedShipWalkable the value we want a random terrain to have ship walkable-wise
	 * @param considerForest true if you want to filter using forests as criterion
	 * @param expectedForest the value we want a random terrain to have forest-wise
	 * @param considerBuildable true if you want to filter using buildable as criterion
	 * @param expectedBuildable the value we want a random terrain to have buildable-wise
	 * @param considerDockable true if you want to filter using dockable as criterion
	 * @param expectedDockable the value we want a random terrain to have dockable-wise
	 * @param considerPuttingNaturalResources true if you want to filter using puttable natural resources as criterion
	 * @param expectedPuttingNaturalResources the value we wantt a random terrain to have putting natural resources-wise
	 * @return a node containing a the code to generate a random terrain with the specifics you have required
	 */
	public static IRMSNode getRandomTerrainWithSpecific(AbstractAoEVersion aoe, String constToCreate, String commentString, boolean considerLandWalkable, boolean expectedLandWalkable, boolean considerShipWalkable, boolean expectedShipWalkable, boolean considerForest, boolean expectedForest, boolean considerBuildable, boolean expectedBuildable, boolean considerDockable, boolean expectedDockable, boolean considerPuttingNaturalResources, boolean expectedPuttingNaturalResources) {
		var terrainChosen = aoe.getUsableTerrains()
				.select(t -> {
					if (considerForest) {
						return t.isForest() == expectedForest;
					}
					return true;
				})
				.select(t -> {
					if (considerBuildable) {
						return t.isBuildable() == expectedBuildable;
					}
					return true;
				})
				.select(t -> {
					if (considerDockable) {
						return t.isDockable() == expectedDockable;
					}
					return true;
				})
				.select(t -> {
					if (considerLandWalkable) {
						return t.isLandWalkable() == expectedLandWalkable;
					}
					return true;
				})
				.select(t -> {
					if (considerShipWalkable) {
						return t.isShipWalkable() == expectedShipWalkable;
					}
					return true;
				})
				.select(t -> {
					if (considerPuttingNaturalResources) {
						return t.canNaturalResourcesBePutOnIt() == expectedPuttingNaturalResources;
					}
					return true;
				})
				.toList();
		
		var result = aoe.multiplexer();
		
		result.addStatement(aoe.comment(
				String.format("**** %s TERRAIN ****", commentString), 
				String.format("After this, \"%s\" const is set to the id of the chosen terrain", constToCreate),
				String.format("There are %d terrains to consider", terrainChosen.size())
		));
		
		var builder = RandomBlockBuilder.instance(result);
		
		Utils.percentage(terrainChosen).forEach(pair -> {
			var percentage = pair.getOne();
			var terrain = pair.getTwo();
			builder.percentChance(percentage, aoe.constant(constToCreate, terrain.getConstValue()), aoe.comment(String.format("terrain %s: %s", terrain.getConstName(), terrain.getDescription())));
		});
		
		builder.endRandom();
		result.addStatement(aoe.comment(String.format("**** END %s TERRAIN ****", commentString)));
		
		return result;
	}
	
	/**
	 * generate code s.t. RMS will generate a random terrain const which is immediately walkable and buildable.
	 * At high level, we create a const containing the id of the walkable terrain chosen
	 * 
	 * @param aoe aoe version
	 * @param constToCreate name fo the const to create
	 * @return node
	 */
	public static IRMSNode getRandomWalkableBuildableTerrain(AbstractAoEVersion aoe, String constToCreate) {
		return getRandomTerrainWithSpecific(aoe, constToCreate, "RANDOM LAND AND BUILDABLE", 
				true, true, //land
				false, false, //ship
				false, false, //forest
				true, true, //buildable
				false, false, //dockable
				false, false //natural resources
		);
	}
	
	/**
	 * generate code s.t. RMS will generate a random terrain const which is immediately walkable, dockable and natural resources.
	 * At high level, we create a const containing the id of the walkable terrain chosen
	 * 
	 * @param aoe aoe version
	 * @param constToCreate name fo the const to create
	 * @return node
	 */
	public static IRMSNode getRandomWalkableBuildableNaturalResourcesTerrain(AbstractAoEVersion aoe, String constToCreate) {
		return getRandomTerrainWithSpecific(aoe, constToCreate, "RANDOM LAND, BUILDABLE AND NATURAL RESOURCES", 
				true, true, //land
				false, false, //ship
				false, false, //forest
				true, true, //buildable
				false, false, //dockable
				true, true //natural resources
		);
	}
	
	/**
	 * generate code s.t. RMS will generate a random terrain const which is immediately walkable.
	 * At high level, we create a const containing the id of the walkable terrain chosen
	 * 
	 * @param aoe aoe version
	 * @param constToCreate name fo the const to create
	 * @return node
	 */
	public static IRMSNode getRandomWalkableTerrain(AbstractAoEVersion aoe, String constToCreate) {
		return getRandomTerrainWithSpecific(aoe, constToCreate, "RANDOM LAND WALKABLE", 
				true, true, //land
				false, false, //ship
				false, false, //forest
				false, false, //buildable
				false, false, //dockable
				false, false //natural resources
		);
	}
	
	/**
	 * generate code s.t. RMS will generate a random terrain const which contains a forest.
	 * At high level, we create a const containing the id of the walkable terrain chosen
	 * 
	 * @param aoe aoe version
	 * @param constToCreate name fo the const to create
	 * @return node
	 */
	public static IRMSNode getRandomForestTerrain(AbstractAoEVersion aoe, String constToCreate) {
		return getRandomTerrainWithSpecific(aoe, constToCreate, "RANDOM FOREST", 
				false, false, //land
				false, false, //ship
				true, true, //forest
				false, false, //buildable
				false, false, //dockable
				false, false //natural resources
		);
	}
	
	/**
	 * generate code s.t. RMS will generate a random terrain const which contains a forest.
	 * At high level, we create a const containing the id of the walkable terrain chosen
	 * 
	 * @param aoe age of empires version
	 * @param constToCreate name of the const to create
	 * @return node representing the code that randomly select a navigable terrain
	 */
	public static IRMSNode getRandomNavigableTerrain(AbstractAoEVersion aoe, String constToCreate) {
		return getRandomTerrainWithSpecific(aoe, constToCreate, "RANDOM NAVIGABLE WALKABLE", 
				false, false, //land
				true, true, //ship
				false, false, //forest
				false, false, //buildable
				false, false, //dockable
				false, false //natural resources
		);
	}
	
	/**
	 * get a terrain randomly chosen from the pool you have requested
	 * @param aoe
	 * @param node
	 * @param constToCreate
	 * @param terrainToChoose
	 * @return
	 */
	public static IRMSNode getRandomTerrainBetween(AbstractAoEVersion aoe, IRMSNode node, String constToCreate, String... terrainToChoose) {
		var s = Sets.immutable.of(terrainToChoose);
		var terrainsInvolved = aoe.getUsableTerrains().select(t -> s.contains(terrainToChoose)).toList();
		
		node.addStatement(aoe.comment(
				"**** GET RANDOM TERRAIN ****", 
				String.format("After this, \"%s\" const is set to the id of the chosen terrain", constToCreate),
				String.format("There are %d terrains to consider", terrainsInvolved.size())
		));
		
		var builder = RandomBlockBuilder.instance(node);
		
		Utils.percentage(terrainsInvolved).forEach(pair -> {
			var percentage = pair.getOne();
			var terrain = pair.getTwo();
			builder.percentChance(percentage, aoe.constant(constToCreate, terrain.getConstValue()), aoe.comment(String.format("terrain %s", terrain.getConstName())));
		});
		
		builder.endRandom();
		node.addStatement(aoe.comment("**** END GET RANDOM TERRAIN ****"));
		
		return node;
	}
	
	/**
	 * generate a equally probable random choice between several options
	 * @param <T> type of <code>availableOptions</code>
	 * @param aoe version of age of empires
	 * @param availableOptions iterable which is the baseline to create the random
	 * @param function function used to create RMS node from a single item in {@code availableOptions}
	 * @return a RMS containign the random choice
	 */
	public static <T> IRMSNode randomlyDo(AbstractAoEVersion aoe, RichIterable<T> availableOptions, IntObjectToObjectFunction<T, IRMSNode> function) {
		var result = aoe.multiplexer();
		
		result.addStatement(aoe.comment(
				String.format("**** RANDOMLY DO ****"), 
				String.format("Choose between %d choiced, which are %s", availableOptions.size(), availableOptions.makeString())
		));
		
		var builder = RandomBlockBuilder.instance(result);
		
		Utils.percentage(availableOptions).zipWithIndex().forEach(pair -> {
			var index = pair.getTwo();
			var percentage = pair.getOne().getOne();
			var element = pair.getOne().getTwo();
			var body = aoe.multiplexer();
			body.addStatement(function.apply(index, element));
			body.addStatement(aoe.comment(String.format("choice %s", element)))
			;
			
			builder.percentChance(percentage, body);
		});
		
		builder.endRandom();
		result.addStatement(aoe.comment(String.format("**** END RANDOMLY DO ****")));
		
		return result;
	}
	
	/**
	 * generate a random choice,. Each choice define a specific define
	 * @param aoe
	 * @param possibleDefines
	 */
	public static IRMSNode defineRandom(AbstractAoEVersion aoe, String... possibleDefines) {
		ImmutableList<String> s = Lists.immutable.of(possibleDefines);
		return CreateRandomTerrains.randomlyDo(aoe, s, (i, define) -> aoe.define(define));
	}
	
	/**
	 * Randomly select a season. You still need to call {@code include F_seasons.inc} after this
	 * @param aoe
	 * @return
	 */
	public static IRMSNode defineRandomSeason(AbstractAoEVersion aoe) {
		return CreateRandomTerrains.defineRandom(aoe, SeasonDefines.all().collect(s -> s.getName()).toArray(new String[] {}));
	}
	
	
}
