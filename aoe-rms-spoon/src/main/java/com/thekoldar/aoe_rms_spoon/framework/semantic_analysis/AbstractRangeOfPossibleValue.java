package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

/**
 * the possible values are in a continuous range of values. Useful for int variables
 * @author massi
 *
 * @param <T> type of the possible values encoded in this class
 */
public abstract class AbstractRangeOfPossibleValue<T extends Comparable<T>> implements IPossibleValue<T> {

	protected T lowerBound;
	protected T upperBound;
	protected boolean lowerboundIncluded;
	protected boolean upperboundIncluded;
	
	public AbstractRangeOfPossibleValue(T lowerBound, T upperBound, boolean lowerboundIncluded, boolean upperboundIncluded) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.lowerboundIncluded = lowerboundIncluded;
		this.upperboundIncluded = upperboundIncluded;
	}
	
	public abstract IPossibleValue<T> clone() throws CloneNotSupportedException;

	@Override
	public String toString() {
		String l = this.lowerboundIncluded ? "[" : "(";
		String u = this.upperboundIncluded ? "]" : ")";
		
		return String.format("%s %s; %s %s", l, this.lowerBound, this.upperBound, u);
	}
	
}
