package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import java.util.Arrays;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntLists;

public class IntSetPossible extends SetPossibleValue<Integer> {
	
	

	public IntSetPossible() {
		super();
	}

	public IntSetPossible(Integer... more) {
		super(more);
	}

	public IntSetPossible(Integer single) {
		super(single);
	}

	public IntSetPossible(RichIterable<Integer> values) {
		super(values);
	}
	
	public IntSetPossible(IPossibleValue<Integer> values) {
		super(values.getPossibleValues());
	}
	
	public static IntSetPossible of(int value) {
		return new IntSetPossible(value);
	}
	
	public static IntSetPossible of(int... values) {
		return new IntSetPossible(IntLists.mutable.of(values).collect(i -> Integer.valueOf(i)));
	}
	
	public static IntSetPossible of(IPossibleValue<Integer> argumentAsInt) {
		return new IntSetPossible(argumentAsInt);
	}
	
	public boolean areAtLeastOneGreaterThanAnyOf(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i > j));
	}
	
	public boolean areAtLeastOneGreaterOrEqualThanAnyOf(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i >= j));
	}
	
	public boolean areAtLeastOneLessThanAnyOf(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i < j));
	}
	
	public boolean areAtLeastOneLessOrEqualThanAnyOf(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.anySatisfy(i -> other.getPossibleValues().anySatisfy(j -> i <= j));
	}

	public boolean areAllGreaterThan(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i > j));
	}
	
	public boolean areAllGreaterOrEqualThan(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i >= j));
	}
	
	public boolean areAllLessThan(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i < j));
	}
	
	public boolean areAllLessOrEqualThan(IPossibleValue<Integer> other) {
		return this.getPossibleValues()
			.allSatisfy(i -> other.getPossibleValues().allSatisfy(j -> i <= j));
	}
}
