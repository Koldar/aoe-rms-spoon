package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.MutableSet;

/**
 * the variable may have a set of possible values
 * @author massi
 *
 * @param <T>
 */
public class SetPossibleValue<T> implements IPossibleValue<T> {

	private MutableSet<T> set;
	
	public SetPossibleValue() {
		this(Sets.fixedSize.empty());
	}
	
	public SetPossibleValue(T single) {
		this(Sets.fixedSize.of(single));
	}
	
	public SetPossibleValue(T... more) {
		this.set = Sets.mutable.of(more);
	}
	
	public SetPossibleValue(RichIterable<T> values) {
		this.set = Sets.mutable.ofAll(values);
	}
	

	@Override
	public RichIterable<T> getPossibleValues() {
		return this.set;
	}
	
	@Override
	public SetPossibleValue<T> clone() {
		return new SetPossibleValue<T>(this.set);
	}

	@Override
	public String toString() {
		return this.set.collect(t -> t.toString()).toSortedList(String::compareTo).makeString();
	}

}
