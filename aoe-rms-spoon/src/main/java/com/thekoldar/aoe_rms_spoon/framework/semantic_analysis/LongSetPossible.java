package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import java.util.Arrays;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.LongLists;

public class LongSetPossible extends SetPossibleValue<Long> {
	
	

	public LongSetPossible() {
		super();
	}
	
	public LongSetPossible(long... more) {
		this(LongLists.immutable.of(more).collect(t -> Long.valueOf(t)));
	}

	public LongSetPossible(Long... more) {
		super(more);
	}

	public LongSetPossible(Long single) {
		super(single);
	}

	public LongSetPossible(RichIterable<Long> values) {
		super(values);
	}
	
	public LongSetPossible(IPossibleValue<Long> values) {
		super(values.getPossibleValues());
	}
	
	public static LongSetPossible of(int value) {
		return new LongSetPossible((long)value);
	}
	
	public static LongSetPossible of(long value) {
		return new LongSetPossible(value);
	}
	
	public static LongSetPossible of(long... values) {
		return new LongSetPossible(values);
	}
	
	public static LongSetPossible of(int... values) {
		return new LongSetPossible(IntLists.mutable.of(values).collect(i -> Long.valueOf(i)));
	}
	
	public static LongSetPossible of(IPossibleValue<Long> argumentAsInt) {
		return new LongSetPossible(argumentAsInt);
	}
	
	public boolean areAtLeastOneGreaterThanAnyOf(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i > j));
	}
	
	public boolean areAtLeastOneGreaterOrEqualThanAnyOf(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i >= j));
	}
	
	public boolean areAtLeastOneLessThanAnyOf(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i < j));
	}
	
	public boolean areAtLeastOneLessOrEqualThanAnyOf(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i <= j));
	}

	public boolean areAllGreaterThan(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i > j));
	}
	
	public boolean areAllGreaterOrEqualThan(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i >= j));
	}
	
	public boolean areAllLessThan(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i < j));
	}
	
	public boolean areAllLessOrEqualThan(IPossibleValue<Long> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i <= j));
	}
	
	/**
	 * check if at least one value in the set is greater than the given one
	 * @param other the value to check
	 * @return
	 */
	public boolean isAtLeastOneGreaterThan(long other) {
		return this.getPossibleValues().anySatisfy(t -> t > other);
	}
	
	/**
	 * check if at least one value in the set is greater than the given one
	 * @param other the value to check
	 * @return
	 */
	public boolean isAtLeastOneGreaterThan(int other) {
		return this.isAtLeastOneGreaterThan((long)other);
	}
	
	/**
	 * check if at least one value in the set is greater than the given one
	 * @param other the value to check
	 * @return
	 */
	public boolean isAtLeastOneLessThan(long other) {
		return this.getPossibleValues().anySatisfy(t -> t < other);
	}
	
	/**
	 * check if at least one value in the set is greater than the given one
	 * @param other the value to check
	 * @return
	 */
	public boolean isAtLeastOneLessThan(int other) {
		return this.isAtLeastOneGreaterThan((long)other);
	}
}
