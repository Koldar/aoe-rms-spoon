package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections;



import java.util.function.Consumer;
import java.util.function.Function;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSExprs;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.IAddStandard;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;

/**
 * The abstract version of the associated RMS section. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractLandGeneration extends AbstractRMSSection implements IAddStandard<AbstractLandGeneration>{


	public AbstractLandGeneration() {
		super(RMSNodeType.LAND_GENERATION);
	}

	public AbstractLandGeneration baseTerrain(AbstractExpressionNode expr) {
		this.addStatement(
				this.getAgeVersion().baseTerrain()
					.setOrAddArgument(0, expr)
		);
		return this;
	}
	
	/**
	 * create player lands, but we customize a {@link DictExpr}
	 * @param function function used to customize the {@link DictExpr}
	 * @return
	 */
	public AbstractLandGeneration createPlayerLands(Consumer<DictExpr> function) {
		var d = RMSExprs.dict();
		this.createPlayerLands(d);
		function.accept(d);
		return this;
	}
	
	public AbstractLandGeneration createPlayerLands(DictExpr d) {
		this.addStatement(
				this.getAgeVersion().createPlayerLands()
					.setOrAddArgument(0, d)
		);
		return this;
	}
	
	public AbstractLandGeneration createPlayerLands(IRMSNode... exprs) {
		return this.createPlayerLands(RMSExprs.dict(exprs));
	}
	
	/**
	 * create lands, but we customize a {@link DictExpr}
	 * @param function function used to customize the {@link DictExpr}
	 * @return
	 */
	public AbstractLandGeneration createLand(Consumer<DictExpr> function) {
		var d = RMSExprs.dict();
		this.createLand(d);
		function.accept(d);
		return this;
	}
	
	/**
	 * Start a create_land_command
	 * @param d dictionary used to constraint the land to build
	 * @return
	 */
	public AbstractLandGeneration createLand(DictExpr d) {
		this.addStatement(
				this.getAgeVersion().createLand()
					.setOrAddArgument(0, d)
		);
		return this;
	}	
	
	public AbstractLandGeneration createPlayerLands(AbstractExpressionNode... exprs) {
		return this.createPlayerLands(RMSExprs.dict(exprs));
	}
	
	public AbstractLandGeneration enableWaves(AbstractExpressionNode showWaves) {
		this.addStatement(
				this.getAgeVersion().enableWaves()
					.setOrAddArgument(0, showWaves)
		);
		return this;
	}
	
	public AbstractLandGeneration enableWaves(boolean showWaves) {
		this.addStatement(
				this.getAgeVersion().enableWaves()
					.setOrAddArgument(0, showWaves)
		);
		return this;
	}
	
	public AbstractLandGeneration baseLayer(AbstractExpressionNode terrainType) {
		this.addStatement(
				this.getAgeVersion().baseLayer()
					.setOrAddArgument(0, terrainType)
		);
		return this;
	}
	
	public AbstractLandGeneration baseLayer(int terrainType) {
		this.addStatement(
				this.getAgeVersion().baseLayer()
					.setOrAddArgument(0, terrainType)
		);
		return this;
	}
	
}
