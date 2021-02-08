package com.thekoldar.aoe_rms_spoon.framework.usefulscripts;

import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

import com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals.MapSizes;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.MapSize;

public class Switches {

	/**
	 * add in the script a switch of defines. The defines are placed in the code as they are extracted from the iterable 
	 * 
	 * @param aoe version of aoe to use
	 * @param node node where the switch is added. If null, the generated if node are not appended to the AST at all. You need to do it yourself 
	 * @param it iterable iterable of pairs. Each pair represents the condition and the effect to place if the condition is satisfied
	 * @param elseBlock if not null, represents the code to put in the else block
	 * @return node the node representing the switch
	 */
	public static IRMSNode addSwitch(AbstractAoEVersion aoe, @Nullable IRMSNode node, RichIterable<Pair<DefineRefExpr, IRMSNode>> it, @Nullable IRMSNode elseBlock) {
		var builder = IfBlockBuilder.instance(node, aoe);
		
		boolean first = false;
		for (var pair : it) {
			var condition = pair.getOne();
			var block = pair.getTwo();
			if (first) {
				builder = builder.condition(condition).then(block);
			} else {
				builder = builder.elseIf(condition, block);
			}
		}
		
		if (elseBlock != null) {
			builder = builder.elseBlock(elseBlock);
		}
		
		return builder.endIf();
	}
	
	/**
	 * add in the script a switch of defines. The defines are placed in the code as they are extracted from the iterable.
	 * the generated if node is not appended to the AST at all. You need to do it yourself 
	 * 
	 * @param aoe version of aoe to use  
	 * @param it iterable iterable of pairs. Each pair represents the condition and the effect to place if the condition is satisfied
	 * @param elseBlock if not null, represents the code to put in the else block
	 * @return node the node representing the switch
	 */
	public static IRMSNode addSwitch(AbstractAoEVersion aoe, RichIterable<Pair<DefineRefExpr, IRMSNode>> it, @Nullable IRMSNode elseBlock) {
		return addSwitch(aoe, null, it, elseBlock);
	}
	
	/**
	 * add in the script a switch of defines. The defines are placed in the code as they are extracted from the iterable. No else block is present
	 * 
	 * @param aoe version of aoe to use
	 * @param node node where the switch is added. If null, the generated if node are not appended to the AST at all. You need to do it yourself 
	 * @param it iterable iterable of pairs. Each pair represents the condition and the effect to place if the condition is satisfied
	 * @return node the node representing the switch
	 */
	public static IRMSNode addSwitch(AbstractAoEVersion aoe, @Nullable IRMSNode node, RichIterable<Pair<DefineRefExpr, IRMSNode>> it) {
		return addSwitch(aoe, node, it, null);
	}
	
	/**
	 * add in the script a switch of defines. The defines are placed in the code as they are extracted from the iterable. No else block is present
	 * You need to add the if block to the root by yourself
	 * 
	 * @param aoe version of aoe to use 
	 * @param it iterable iterable of pairs. Each pair represents the condition and the effect to place if the condition is satisfied
	 * @return node the node representing the switch
	 */
	public static IRMSNode addSwitch(AbstractAoEVersion aoe, RichIterable<Pair<DefineRefExpr, IRMSNode>> it) {
		return addSwitch(aoe, null, it, null);
	}
	
	
	
	/**
	 * perform some AST node depending on the map size detected from the lobby
	 * 
	 * Code generations is like the following one:
	 * 
	 * <pre>{@code
	 * 	if TINY_MAP 
	 *		#const CUSTOM_BASE_SIZE 10 
	 * 		#const CUSTOM_CIRCLE_RADIUS 15 
	 *	elseif SMALL_MAP 
	 *		#const CUSTOM_BASE_SIZE 12 
	 *		#const CUSTOM_CIRCLE_RADIUS 18 
	 *	elseif MEDIUM_MAP 
	 *		#const CUSTOM_BASE_SIZE 15 
	 *		#const CUSTOM_CIRCLE_RADIUS 21 
	 *	elseif LARGE_MAP 
	 *		#const CUSTOM_BASE_SIZE 18 
	 *		#const CUSTOM_CIRCLE_RADIUS 26 
	 *	elseif HUGE_MAP 
	 *		#const CUSTOM_BASE_SIZE 19 
	 *		#const CUSTOM_CIRCLE_RADIUS 28 
	 *	elseif GIGANTIC_MAP 
	 *		#const CUSTOM_BASE_SIZE 21 
	 *		#const CUSTOM_CIRCLE_RADIUS 31 
	 *	elseif LUDIKRIS_MAP 
	 *		#const CUSTOM_BASE_SIZE 43 
	 *		#const CUSTOM_CIRCLE_RADIUS 62 
	 *	else  
	 *		#const CUSTOM_BASE_SIZE 19 
	 *		#const CUSTOM_CIRCLE_RADIUS 38 
	 *	endif  
	 * }</pre>
	 * 
	 * 
	 * @param aoe version of age of empries
	 * @param node node where the switch will be added
	 * @param function function that generate the content of each map size
	 * @param unrecognizedMapSize code executed fi for some weird reason we could not find the map size
	 * @return node the node representing the switch
	 */
	public static IRMSNode doDependingOnMapSize(AbstractAoEVersion aoe, IRMSNode node, Function<MapSize, IRMSNode> function, @Nullable Supplier<IRMSNode> unrecognizedMapSize) {
		return addSwitch(aoe, node, MapSizes.all().collect(mapSize -> {
			var body = function.apply(mapSize);
			return Tuples.pair(mapSize.getDefineRef(), body);
		}), unrecognizedMapSize != null ? unrecognizedMapSize.get() : null);
	}
	
	/**
	 * perform some AST node depending on the map size detected from the lobby
	 * @param aoe version of age of empries
	 * @param node node where the switch will be added
	 * @param function function that generate the content of each map size
	 * @return node the node representing the switch
	 */
	public static IRMSNode doDependingOnMapSize(AbstractAoEVersion aoe, IRMSNode node, Function<MapSize, IRMSNode> function) {
		return doDependingOnMapSize(aoe, node, function, null);
	}
	
	/**
	 * perform some AST node depending on the map size detected from the lobby. You need to add the generated RMS node by yourself into the AST
	 * 
	 * @param aoe version of age of empries
	 * @param function function that generate the content of each map size
	 * @return node the node representing the switch
	 */
	public static IRMSNode doDependingOnMapSize(AbstractAoEVersion aoe, Function<MapSize, IRMSNode> function) {
		return doDependingOnMapSize(aoe, null, function, null);
	}
}
