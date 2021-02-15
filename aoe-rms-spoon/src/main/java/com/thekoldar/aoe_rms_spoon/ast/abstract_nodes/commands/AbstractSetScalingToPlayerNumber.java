package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import java.util.stream.IntStream;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.tuple.Tuples;

import com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals.MapSizes;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractSetScalingToPlayerNumber extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScalingToPlayerNumber() {
		super(RMSNodeType.SET_SCALING_TO_PLAYER_NUMBER);
	}
	
	
	@Override
	public String getComment() {
		return "Scales number_of_groups to player count. This means x2 for a 2-player game and x8 for an 8-player game.";
	}
	
	/**
	 * compute the involve objhect in this create_object
	 * @param input
	 * @return
	 */
	public IPossibleValue<Long> getInvolvedObject(SemanticCheckInput input) {
		var createObject = (AbstractCreateObject)this.getFirstNodeFromPathSatisfying(n -> n.getNodeType().equals(RMSNodeType.CREATE_OBJECT)).getAny();
		return createObject.getInvolvedObject(input);
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItIsOnlyInstructionOfTypeInDict(this);
		if (this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_GROUPS).isEmpty()) {
			if (this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_OBJECTS).isEmpty()) {
				result.addError(this, RMSErrorCode.NODE_NOT_FOUND, "In order to use %s you need either %s or %s", this.getNodeType(), RMSNodeType.NUMBER_OF_GROUPS, RMSNodeType.NUMBER_OF_OBJECTS);
			}
		}
		if (!this.getSiblingOfTypes(RMSNodeType.SET_SCALING_TO_MAP_SIZE).isEmpty()) {
			result.addError(this, RMSErrorCode.CONFLICTING_COMMANDS, "%s and %s are mututally exclusive!", RMSNodeType.SET_SCALING_TO_PLAYER_NUMBER, RMSNodeType.SET_SCALING_TO_MAP_SIZE);
		}
		this.infoCmd("1646 indestructible markets you can use to trade with");
		
		return result.merge(this.semanticCheckChildren(input));
	}


	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		
		input.getSemanticInput().ifPresent(ai -> {
			var line = "";
			if (this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_GROUPS).isEmpty()) {
				LongSetPossible groups = ((AbstractRMSCommand)this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_GROUPS).getAny().getTwo()).getArgumentAsInt(0, ai);
				line = String.format("/* scale by groups: %s */", IntLists.immutable.ofAll(IntStream.range(0, 9))
						.collect(player -> Tuples.pair(player, groups.getPossibleValues().collect(agroup -> { return (int)(agroup * player);} ).makeString(" or ")))
						.collect(pair -> String.format("player %d = %s", pair.getOne(), pair.getTwo()))
						.makeString("; ")
				);
			} else {
				LongSetPossible objects = ((AbstractRMSCommand)this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_OBJECTS).getAny().getTwo()).getArgumentAsInt(0, ai);
				line = String.format("/* scale by objects: %s */", IntLists.immutable.ofAll(IntStream.range(0, 9))
						.collect(player -> Tuples.pair(player, objects.getPossibleValues().collect(aobject -> { return (int)(aobject * player);} ).makeString(" or ")))
						.collect(pair -> String.format("player %d = %s", pair.getOne(), pair.getTwo()))
						.makeString("; ")
				);
			}
			result.addLine(line);
		});
		result.merge(super.codeGeneration(input));
		
		return result;
	}

}
