package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections;

import com.thekoldar.aoe_rms_spoon.models.IRMSNode;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddComment;
import com.thekoldar.aoe_rms_spoon.models.add_methods.AddDirectives;

public abstract class AbstractPlayerSetup extends AbstractRMSSection implements AddDirectives<AbstractPlayerSetup> {

	public AbstractPlayerSetup() {
		super(RMSNodeType.PLAYER_SETUP);
	}
	
	public AbstractPlayerSetup groupedByTeam() {
		this.addStatement(this.getAgeVersion().groupedByTeam());
		return this;
	}
	
	public AbstractPlayerSetup randomPlacement() {
		this.addStatement(this.getAgeVersion().randomPlacement());
		return this;
	}
	
	public AbstractPlayerSetup directPlacement() {
		this.addStatement(this.getAgeVersion().directPlacement());
		return this;
	}
	
	public AbstractPlayerSetup nomadResources() {
		this.addStatement(this.getAgeVersion().nomadResources());
		return this;
	}
	
	public AbstractPlayerSetup aiInfoMapType(AbstractExpressionNode map, AbstractExpressionNode isNomad, AbstractExpressionNode isMichi, AbstractExpressionNode showType) {
		this.addStatement(this.getAgeVersion().aiInfoMapType()
				.setOrAddArgument(0, map)
				.setOrAddArgument(1, isNomad)
				.setOrAddArgument(2, isMichi)
				.setOrAddArgument(3, showType)
		);
		return this;
	}
	
	public AbstractPlayerSetup weatherType(AbstractExpressionNode precipitationStyle, AbstractExpressionNode liveColor, AbstractExpressionNode fogColor, AbstractExpressionNode waterDirection) {
		this.addStatement(this.getAgeVersion().weatherType()
				.setOrAddArgument(0, precipitationStyle)
				.setOrAddArgument(1, liveColor)
				.setOrAddArgument(2, fogColor)
				.setOrAddArgument(3, waterDirection)
		);
		return this;
	}
	
	public AbstractPlayerSetup effectAmount(AbstractExpressionNode upEffectType, AbstractExpressionNode type, AbstractExpressionNode upAttributeType, AbstractExpressionNode amount) {
		this.addStatement(this.getAgeVersion().effectAmount()
				.setOrAddArgument(0, upEffectType)
				.setOrAddArgument(1, type)
				.setOrAddArgument(2, upAttributeType)
				.setOrAddArgument(3, amount)
		);
		return this;
	}
	
	public AbstractPlayerSetup effectPercent(AbstractExpressionNode upEffectType, AbstractExpressionNode type, AbstractExpressionNode upAttributeType, AbstractExpressionNode amount) {
		this.addStatement(this.getAgeVersion().effectPercent()
				.setOrAddArgument(0, upEffectType)
				.setOrAddArgument(1, type)
				.setOrAddArgument(2, upAttributeType)
				.setOrAddArgument(3, amount)
		);
		return this;
	}
	
	public AbstractPlayerSetup guardState(AbstractExpressionNode objectType, AbstractExpressionNode upResourceType, AbstractExpressionNode resourceDelta, AbstractExpressionNode flags) {
		this.addStatement(this.getAgeVersion().guardState()
				.setOrAddArgument(0, objectType)
				.setOrAddArgument(1, upResourceType)
				.setOrAddArgument(2, resourceDelta)
				.setOrAddArgument(3, flags)
		);
		return this;
	}
	
	public AbstractPlayerSetup terrainState(AbstractExpressionNode mode, AbstractExpressionNode parameter1, AbstractExpressionNode parameter2, AbstractExpressionNode flags) {
		this.addStatement(this.getAgeVersion().terrainState()
				.setOrAddArgument(0, mode)
				.setOrAddArgument(1, parameter1)
				.setOrAddArgument(2, parameter2)
				.setOrAddArgument(3, flags)
		);
		return this;
	}
	
	public AbstractPlayerSetup setGaiaCivilization(AbstractExpressionNode civNumber) {
		this.addStatement(this.getAgeVersion().setGaiaCivilization()
				.setOrAddArgument(0, civNumber)
		);
		return this;
	}
	
	public AbstractPlayerSetup behaviorVersion(AbstractExpressionNode versionNumber) {
		this.addStatement(this.getAgeVersion().behaviorVersion()
				.setOrAddArgument(0, versionNumber)
		);
		return this;
	}

}
