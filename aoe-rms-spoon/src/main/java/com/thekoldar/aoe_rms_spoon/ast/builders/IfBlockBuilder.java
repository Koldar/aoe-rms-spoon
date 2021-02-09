package com.thekoldar.aoe_rms_spoon.ast.builders;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.functions.IfNode;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;

/**
 * Allows you to fluently build an if
 * @author massi
 *
 * @param <TPARENT>
 */
public class IfBlockBuilder {

	/**
	 * if null, the if block we are building is not attached to any root
	 */
	@Nullable
	private IRMSNode parent;
	private IfNode ifNode;
	private AbstractAoEVersion aoe;
	
	public static IfBlockBuilder instance(IRMSNode parent, AbstractAoEVersion aoe) {
		return new IfBlockBuilder(parent, aoe);
	}
	
	public static IfBlockBuilder instance(AbstractAoEVersion aoe) {
		return new IfBlockBuilder(null, aoe);
	}
	
	public static IfBlockBuilder instance(IRMSNode parent) {
		return new IfBlockBuilder(parent, parent.getAgeVersion());
	}
	
	private IfBlockBuilder(IRMSNode parent, AbstractAoEVersion aoe) {
		this.parent = parent;
		this.ifNode = aoe.ifNode();
		this.aoe = aoe;
	}
	
	public IfBlockBuilder condition(IRMSNode condition) {
		this.ifNode.addStatement(condition);
		return this;
	}
	
	public IfBlockBuilder condition(String condition) {
		this.ifNode.addStatement(RMSExprs.defineVal(condition));
		return this;
	}
	
	/**
	 * add as then condition a const reference 
	 * @param val name of the const reference
	 * @return if block
	 */
	public IfBlockBuilder then(String val) {
		return this.then(RMSExprs.constVal(val));
	}
	
	public IfBlockBuilder then(IRMSNode block) {
		this.ifNode.addStatement(block);
		return this;
	}
	
	public IfBlockBuilder then(Consumer<IRMSNode> block) {
		var m = this.aoe.multiplexer();
		this.ifNode.addStatement(m);
		block.accept(m);
		return this;
	}
	
	public IfBlockBuilder elseIf(String condition, Consumer<IRMSNode> block) {
		return this.elseIf(RMSExprs.constVal(condition), block);
	}
	
	public IfBlockBuilder elseIf(IRMSNode condition, Consumer<IRMSNode> block) {
		var m = this.aoe.multiplexer();
		this.elseIf(condition, m);
		block.accept(m);
		return this;
	}
	
	public IfBlockBuilder elseIf(IRMSNode condition, IRMSNode block) {
		this.ifNode.addStatement(condition);
		this.ifNode.addStatement(block);
		return this;
	}
	
	public IfBlockBuilder elseBlock(String constRef) {
		return this.elseBlock(RMSExprs.constVal(constRef));
	}
	
	public IfBlockBuilder elseBlock(Consumer<IRMSNode> block) {
		var m = this.aoe.multiplexer();
		this.elseBlock(m);
		block.accept(m);
		return this;
	}
	
	public IfBlockBuilder elseBlock(IRMSNode block) {
		this.ifNode.addStatement(block);
		return this;
	}
	
	/**
	 * 
	 * @return block representing the if node
	 */
	public IRMSNode endIf() {
		if (this.parent != null) {
			this.parent.addStatement(this.ifNode);	
		}
		return this.ifNode;
	}
	
//	public <TPARENT extends IRMSNode> TPARENT endIf(Class<TPARENT> clazz) {
//		return (TPARENT)this.endIf();
//	}
}
