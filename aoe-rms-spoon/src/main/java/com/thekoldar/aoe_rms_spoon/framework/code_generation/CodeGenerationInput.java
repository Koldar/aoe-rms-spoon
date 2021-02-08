package com.thekoldar.aoe_rms_spoon.framework.code_generation;

import java.util.Optional;

import javax.annotation.Nullable;

import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;

public class CodeGenerationInput {

	private boolean enableComments;
	private SemanticCheckInput semanticInput;
	
	
	
	public CodeGenerationInput(@Nullable SemanticCheckInput input, boolean enableComments) {
		super();
		this.semanticInput = input;
		this.enableComments = enableComments;
	}

	public Optional<SemanticCheckInput> getSemanticInput() {
		return Optional.ofNullable(this.semanticInput);
	}

	public boolean enableComments() {
		return this.enableComments;
	}
	
}
