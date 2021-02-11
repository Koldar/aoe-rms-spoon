package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

/**
 * a range fo int. rns() function for example generate such a class
 * @author massi
 *
 */
public class LongRangeOfPossibleValue extends AbstractRangeOfPossibleValue<Long>{

	public LongRangeOfPossibleValue(Long lowerBound, Long upperBound,
			boolean lowerboundIncluded, boolean upperboundIncluded) {
		super(lowerBound, upperBound, lowerboundIncluded, upperboundIncluded);
	}
	
	@Override
	public RichIterable<Long> getPossibleValues() {
		var lb = this.lowerBound.intValue() + (this.lowerboundIncluded ? 0 : 1);
		var ub = this.upperBound.intValue() - (this.upperboundIncluded ? 0 : 1);
		return Lists.fixedSize.fromStream(LongStream.range(lb, ub + 1).mapToObj(i -> Long.valueOf(i)));
	}
	
	public LongRangeOfPossibleValue clone() {
		return new LongRangeOfPossibleValue(this.lowerBound, this.upperBound, this.lowerboundIncluded, this.upperboundIncluded);
	}
	
	public int getActualLowerboundAsInt() {
		return (int)this.getActualLowerbound();
	}
	
	public long getActualLowerbound() {
		return this.lowerBound + (this.lowerboundIncluded ? 0 : 1);
	}
	
	public int getActualUpperboundAsInt() {
		return (int)this.getActualUpperbound();
	}
	
	public long getActualUpperbound() {
		return this.upperBound - (this.upperboundIncluded ? 0 : 1);
	}

	@Override
	public Long getAny() {
		if (this.isEmpty()) {
			throw new IllegalArgumentException("Interval is empty!");
		}
		return this.getActualLowerbound();
	}

	@Override
	public Long getOnly() {
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
	public boolean contains(Long value) {
		if (this.isEmpty()) {
			return false;
		}
		return value.longValue() >= this.getActualLowerbound() && value.longValue() <= this.getActualUpperbound();
	}

	@Override
	public boolean isEmpty() {
		return this.getActualLowerbound() > this.getActualUpperbound();
	}

}
