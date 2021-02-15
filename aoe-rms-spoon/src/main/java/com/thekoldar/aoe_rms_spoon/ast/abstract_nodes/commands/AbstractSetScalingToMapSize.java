package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

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
public abstract class AbstractSetScalingToMapSize extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScalingToMapSize() {
		super(RMSNodeType.SET_SCALING_TO_MAP_SIZE);
	}
	
	@Override
	public String getComment() {
		return "Scales number_of_groups to the map size.  Unscaled value refers to a 100x100 map. If no grouping is present, scaling applies to number_of_objects instead";
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
		if (!this.getSiblingOfTypes(RMSNodeType.SET_SCALING_TO_PLAYER_NUMBER).isEmpty()) {
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
			if (!this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_GROUPS).isEmpty()) {
				LongSetPossible groups = ((AbstractRMSCommand)this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_GROUPS).getAny().getTwo()).getArgumentAsInt(0, ai);
				line = String.format("/* scale by groups: %s */", MapSizes
						.all()
						.collect(ms -> Tuples.pair(ms, groups.getPossibleValues().collect(agroup -> { return (int)(agroup * ms.getTilesPer100x100());} ).makeString(" or ")))
						.collect(pair -> String.format("%s = %s", pair.getOne().getName(), pair.getTwo()))
						.makeString("; ")
				);
			} else {
				LongSetPossible objects = ((AbstractRMSCommand)this.getPreviousSiblingofTypes(RMSNodeType.NUMBER_OF_OBJECTS).getAny().getTwo()).getArgumentAsInt(0, ai);
				line = String.format("/* scale by groups: %s */", MapSizes
						.all()
						.collect(ms -> Tuples.pair(ms, objects.getPossibleValues().collect(aobject -> { return (int)(aobject * ms.getTilesPer100x100());} ).makeString(" or ")))
						.collect(pair -> String.format("%s = %s", pair.getOne().getName(), pair.getTwo()))
						.makeString("; ")
				);
			}
			result.addLine(line);
		});
		result.merge(super.codeGeneration(input));
		
		return result;
	}
	
	

}
