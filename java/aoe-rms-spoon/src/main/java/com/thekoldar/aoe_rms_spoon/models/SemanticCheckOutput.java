package com.thekoldar.aoe_rms_spoon.models;

import javax.annotation.Nullable;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.thekoldar.aoe_rms_spoon.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.models.exceptions.RMSSemanticWarningException;

public class SemanticCheckOutput {

	@Nullable
	private RMSSemanticErrorException firstError;
	private MutableList<RMSSemanticWarningException> warnings;
	
	/**
	 * check if the code is correct
	 * @return
	 */
	public boolean isCorrect() {
		return this.firstError == null;
	}
	
	public RMSSemanticErrorException getFirstError() {
		return this.firstError;
	}
	
	public ImmutableList<RMSSemanticWarningException> getWarnings() {
		return this.warnings.toImmutable();
	}
}
