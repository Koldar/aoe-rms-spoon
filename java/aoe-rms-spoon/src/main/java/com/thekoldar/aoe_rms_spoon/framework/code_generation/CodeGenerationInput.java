package com.thekoldar.aoe_rms_spoon.framework.code_generation;

import com.thekoldar.aoe_rms_spoon.framework.SpoonFramework;

public class CodeGenerationInput {

	private boolean enableComments;
	
	
	
	public CodeGenerationInput(boolean enableComments) {
		super();
		this.enableComments = enableComments;
	}



	public boolean enableComments() {
		return this.enableComments;
	}
	
}
