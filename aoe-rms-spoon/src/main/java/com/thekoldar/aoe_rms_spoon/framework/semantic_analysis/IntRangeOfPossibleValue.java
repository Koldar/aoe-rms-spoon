package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import java.util.stream.IntStream;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

/**
 * a range fo int. rns() function for example generate such a class
 * @author massi
 *
 */
public class IntRangeOfPossibleValue extends AbstractRangeOfPossibleValue<Integer>{

	public IntRangeOfPossibleValue(Integer lowerBound, Integer upperBound,
			boolean lowerboundIncluded, boolean upperboundIncluded) {
		super(lowerBound, upperBound, lowerboundIncluded, upperboundIncluded);
	}
	
	@Override
	public RichIterable<Integer> getPossibleValues() {
		var lb = this.lowerBound.intValue() + (this.lowerboundIncluded ? 0 : 1);
		var ub = this.upperBound.intValue() - (this.upperboundIncluded ? 0 : 1);
		return Lists.fixedSize.fromStream(IntStream.range(lb, ub + 1).mapToObj(i -> Integer.valueOf(i)));
	}
	
	public IntRangeOfPossibleValue clone() {
		return new IntRangeOfPossibleValue(this.lowerBound, this.upperBound, this.lowerboundIncluded, this.upperboundIncluded);
	}
	
	public int getActualLowerbound() {
		return this.lowerBound + (this.lowerboundIncluded ? 0 : 1);
	}
	
	public int getActualUpperbound() {
		return this.upperBound - (this.upperboundIncluded ? 0 : 1);
	}

	@Override
	public Integer getAny() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("Interval is empty!");
		}
		return this.getActualLowerbound();
	}

	@Override
	public Integer getOnly() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("Interval is empty!");
		}
		if (this.getActualLowerbound() == this.getActualUpperbound()) {
			return this.getActualLowerbound();
		}
		else {
			throw new IllegalArgumentException("Interval has more than one value!");
		}
	}

	@Override
	public boolean contains(Integer value) {
		if (this.isEmpty()) {
			return false;
		}
		return value.intValue() >= this.getActualLowerbound() && value.intValue() <= this.getActualUpperbound();
	}

	@Override
	public boolean isEmpty() {
		return this.getActualLowerbound() > this.getActualUpperbound();
	}

}
