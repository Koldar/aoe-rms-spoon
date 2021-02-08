package com.thekoldar.aoe_rms_spoon.ast.builders;

import javax.annotation.Nullable;

import com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.StandardMultiplexerNode;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.ast.functions.PercentChance;
import com.thekoldar.aoe_rms_spoon.ast.functions.RandomNode;

/**
 * builder class used to generate start_random and end_random
 * @author massi
 *
 */
public class RandomBlockBuilder {

	@Nullable
	private IRMSNode parent;
	private RandomNode building;
	
	public static RandomBlockBuilder instance(IRMSNode parent) {
		return new RandomBlockBuilder(parent); 
	}
	
	/**
	 * create a random bloc with percent_chance
	 * @param parent parent of the code we need to generate. If null, the created code is attached to no root
	 */
	private RandomBlockBuilder(@Nullable IRMSNode parent) {
		this.parent = parent;
		this.building = new RandomNode();
	}
	
	/**
	 * adds a new percent block
	 * 
	 * @param percentValue value of the percent
	 * @param stmts list fo operations to perform inside the percent change
	 * @return
	 */
	public RandomBlockBuilder percentChance(int percentValue, IRMSNode ...stmts) {
		var multiplexer = new StandardMultiplexerNode();
		multiplexer.addStatements(stmts);
		return this.percentChance(RMSExprs.intVal(percentValue), multiplexer);
	}
	
	/**
	 * adds a new percent block
	 * 
	 * @param percentValue value of the percent
	 * @param body body of the percent value to add
	 * @return
	 */
	public RandomBlockBuilder percentChance(int percentValue, IRMSNode body) {
		return this.percentChance(RMSExprs.intVal(percentValue), body);
	}
	
	/**
	 * adds a new percent block
	 * @param percentValue value of the percent
	 * @param body body of the percent value to add
	 * @return
	 */
	public RandomBlockBuilder percentChance(AbstractExpressionNode percentValue, IRMSNode body) {
		var percentChance = new PercentChance();
		
		percentChance.addStatement(percentValue).addStatement(body);
		this.building.addStatement(percentChance);
		
		return this;
	}
	
	/**
	 * terminates this whole random section.
	 * 
	 * @return the code just generated
	 */
	public IRMSNode endRandom() {
		if (this.parent != null) {
			this.parent.addStatement(this.building);	
		}
		return this.building;
	}

//	public <TPARENT extends IRMSNode> TPARENT endRandom(Class<TPARENT> castTo) {
//		return (TPARENT)this.endRandom();
//	}
	
	
}
