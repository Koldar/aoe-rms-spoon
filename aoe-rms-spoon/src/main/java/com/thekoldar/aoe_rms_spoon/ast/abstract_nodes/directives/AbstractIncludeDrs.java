package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives;


import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractDirective;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class AbstractIncludeDrs extends AbstractDirective {

	private String filepath;
	
	protected AbstractIncludeDrs(String filepath) {
		super(RMSNodeType.INCLUDE_DRS);
		this.filepath = filepath;
	}
	
	public String getLabel() {
		return String.format("%s", this.filepath);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) {
		var result = input.createOutput();
		
		//ensure the file exists
		
		return result;
	}


	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		
		this.addCommentOfInclude(result, this.filepath);
		
		result.addLine(String.format("#include_drs %s", this.filepath));
		return result;
	}

	/**
	 * the file included by this statement
	 * @return
	 */
	public String getIncludedFile() {
		return this.filepath;
	}

	@Override
	public String toString() {
		return String.format("include_drs %s", this.filepath);
	}
	
	private void addCommentOfInclude(CodeGenerationOutput result, String filepath) {
		if (Utils.containsCaseInsensitive(this.filepath, "f_seasons.inc")) {
			result.addTheseLines(
					"/* Include if you want to generate terrains and animals with the same feel (e.g., generate frozen lands and animal, jungle lands and animals and so on)",
					" * To use it: first define a season define (see SeasonsDefines), then include the file.",
					" * Use PH_EXTENDEDSEASONS if you want to add more variety",
					" * After including the file, you can use the following const which are automatically themed (see SeasonProvidedDefines): ",
					" * - terrains: LAYER_A, LAYER_B, LAYER_C, LAYER_E, LAYER_F",
					" * - forest: WOODIES, WOODIES_B, WOORDIE_C",
					" * - water: VODA",
					" * - shallows: MELCINA",
					" * - path/road: CESTA",
					" * - huntable (e.g. deers): HUNTABLE",
					" * - herds (e.g., sheeps): HERDABLE_A, HERDABLE_B",
					" * - lures (e.g., boars): LURABLE_A, LURABLE_B",
					" * - predator (e.g., wolf): PREDATOR_A, PREDATOR_B",
					" * - birds (e.g., hawk): BIRDS_A, BIRDS_B",
					" * - standard fish with 225 food: FISH_A, FISH_B",
					" * - small fish (e.g., shore fish): MELKARYBA",
					" * - bushes (e.g., berries): KERICEK",
					" * - straggler tree(e.g., tree): STRAGGLER */"
			);
		} else {
			result.addLine("/* We don't know what this include does! */");
		}
	}
}
