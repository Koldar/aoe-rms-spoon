package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections;

import java.util.function.Consumer;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddStandard;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractObjectsGeneration extends AbstractRMSSection implements IAddStandard<AbstractObjectsGeneration>{


	public AbstractObjectsGeneration() {
		super(RMSNodeType.OBJECTS_GENERATION);
	}

	@Override
	public String getSectionName() {
		return "OBJECTS_GENERATION";
	}
	
	public AbstractObjectsGeneration createObject(String name, Consumer<DictExpr> specifics) {
		return this.createObject(RMSExprs.constVal(name), specifics);
	}
	
	
	public AbstractObjectsGeneration createObject(IRMSNode object, Consumer<DictExpr> specifics) {
		var m = RMSExprs.dict();
		this.createObject(object, m);
		specifics.accept(m);
		return this;
	}
	
	public AbstractObjectsGeneration createObject(IRMSNode object, DictExpr specifics) {
		var result = this.getAgeVersion().createObject()
				.addArgument(object)
				.addArgument(specifics)
		;
		this.addStatement(result);
		return this;
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.CREATE_OBJECT);
		
		return result;
	}
	
	
	
}
