package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections;

import java.util.function.Consumer;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddStandard;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractTerrainGeneration extends AbstractRMSSection implements IAddStandard<AbstractTerrainGeneration>{


	public AbstractTerrainGeneration() {
		super(RMSNodeType.TERRAIN_GENERATION);
	}

	public AbstractTerrainGeneration colorCorrection(AbstractExpressionNode expr) {
		this.addStatement(this.getAgeVersion().colorCorrection().addArgument(expr));
		return this;
	}
	
	public AbstractTerrainGeneration createTerrain(AbstractExpressionNode terrainType, DictExpr attributes) {
		this.addStatement(this.getAgeVersion().createTerrain()
			.addArgument(terrainType)
			.addArgument(attributes)
		);
		return this;
	}
	
	public AbstractTerrainGeneration createTerrain(String terrainType, Consumer<DictExpr> attributes) {
		var dict = RMSExprs.dict();
		
		this.addStatement(this.getAgeVersion().createTerrain()
			.addArgument(terrainType)
			.addArgument(dict)
		);
		
		attributes.accept(dict);
		
		return this;
	}
	
	public AbstractTerrainGeneration createTerrain(String terrainType, DictExpr attributes) {
		return this.createTerrain(RMSExprs.constVal(terrainType), attributes);
	}
	
	public AbstractTerrainGeneration createTerrain(String terrainType, IRMSNode... nodes) {
		return this.createTerrain(RMSExprs.constVal(terrainType),  RMSExprs.dict(nodes));
	}
	
	
	
	
}
