package com.thekoldar.aoe_rms_spoon.ast.builders;

import javax.annotation.Nullable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
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
	}
	
	public IfBlockBuilder condition(IRMSNode condition) {
		this.ifNode.addStatement(condition);
		return this;
	}
	
	public IfBlockBuilder then(IRMSNode block) {
		this.ifNode.addStatement(block);
		return this;
	} 
	
	public IfBlockBuilder elseIf(IRMSNode condition, IRMSNode block) {
		this.ifNode.addStatement(condition);
		this.ifNode.addStatement(block);
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
