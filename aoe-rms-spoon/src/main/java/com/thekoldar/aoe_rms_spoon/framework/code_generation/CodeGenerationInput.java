package com.thekoldar.aoe_rms_spoon.framework.code_generation;

import java.util.Optional;

import javax.annotation.Nullable;

import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;

/**
 * class representing the input of the code generation routine. 
 * @author massi
 *
 */
public class CodeGenerationInput {

	/**
	 * if true, we will generate comments as well in the output. If false, we will <b>NOT</b> generate comments
	 */
	private boolean enableComments;
	
	/**
	 * if not null, it is the semantci input updated from the semantic analysis. Most notably contains the symbol table that we may use to add further information in the generated output
	 */
	@Nullable
	private SemanticCheckInput semanticInput;
	
	
	/**
	 * new instance of the class
	 * @param input semantic anlysis updated input. Null if the semantic analysis was skipped
	 * @param enableComments if te, we will generate comments inside the RMS output code
	 */
	public CodeGenerationInput(@Nullable SemanticCheckInput input, boolean enableComments) {
		super();
		this.semanticInput = input;
		this.enableComments = enableComments;
	}

	/**
	 * get the semantic input fetched during initialization
	 * @return semantic analysis
	 */
	public Optional<SemanticCheckInput> getSemanticInput() {
		return Optional.ofNullable(this.semanticInput);
	}

	/**
	 * 
	 * @return true if comments are generated, false otherwise
	 */
	public boolean enableComments() {
		return this.enableComments;
	}
	
}
