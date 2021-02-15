package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives;


import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractDirective;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
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
public class AbstractInclude extends AbstractDirective {

	private String filepath;
	
	protected AbstractInclude(String filepath) {
		super(RMSNodeType.INCLUDE);
		this.filepath = filepath;
	}
	
	public String getLabel() {
		return String.format("%s", this.filepath);
	}


	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		result.addLine(String.format("#include %s", this.filepath));
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
		return String.format("include %s", this.filepath);
	}

	
}
