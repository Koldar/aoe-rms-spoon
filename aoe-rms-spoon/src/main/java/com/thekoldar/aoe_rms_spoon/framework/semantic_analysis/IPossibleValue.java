package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import java.util.function.Function;

import org.eclipse.collections.api.RichIterable;

/**
 * represents the set of possibl values a variable may have
 * @author massi
 *
 */
public interface IPossibleValue<T> {

	/**
	 * close the possible values
	 * @return
	 */
	public IPossibleValue<T> clone() throws CloneNotSupportedException;
	
	
	/**
	 * check if the possible values of this are a subset (proper or im
	 * @param value
	 * @return
	 */
	public default boolean isSubsetOf(IPossibleValue<T> value) {
		return value.getPossibleValues().containsAllIterable(this.getPossibleValues());
	}
	
	/**
	 * check if the possible values of this are a subset of at least one of the values in <code>values</code>
	 * @param values
	 * @return
	 */
	public default boolean isSubsetOfAtLeastOne(IPossibleValue<T>... values) {
		for (var v : values) {
			if (this.isSubsetOf(v)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * convert the possible values into something else
	 * @param <U>
	 * @param function
	 * @return
	 */
	public default <U> IPossibleValue<U> collect(Function<T, U> function) {
		return new SetPossibleValue<U>(this.getPossibleValues().collect(i -> function.apply(i)));
	}
	
	/**
	 * get a value from the possible value list
	 * @return
	 */
	public default T getAny() {
		return this.getPossibleValues().getAny();
	}
	
	/**
	 * get the only value from the possible list
	 * @return
	 */
	public default T getOnly() {
		return this.getPossibleValues().getOnly();
	}
	
	/**
	 * get all the possible values in this structure
	 * @return
	 */
	public RichIterable<T> getPossibleValues();
	
	/**
	 * if true, the value given in input is indeed in the possible values
	 * @param value
	 * @return
	 */
	public default boolean contains(T value) {
		return this.getPossibleValues().contains(value);
	}
	
	/**
	 * if true, the only value inside the set is the one specified by the user
	 * if fasle, there may be multiple values, none of them, or a single value that does not match with the one provided by the user
	 * 
	 * @param value
	 * @return
	 */
	public default boolean containsOnly(T value) {
		var s = this.getPossibleValues();
		if (s.size() > 1) {
			return false;
		}
		return s.contains(value);
	}
	
	/**
	 * generate a new possible values s.t. the possible values stored in it are present both in <code>this</code> and in <code>other</code>
	 * @param other
	 * @return
	 */
	public default IPossibleValue<T> intersect(IPossibleValue<T> other) {
		var otherSet = other.getPossibleValues().toSet(); 
		return new SetPossibleValue<T>(this.getPossibleValues().select(i -> otherSet.contains(i)));
	}
	
	/**
	 * generate a new possible values s.t. the possible values by allowing a new one to be present
	 * @param value
	 * @return the new structure with both the items
	 */
	public default IPossibleValue<T> union(IPossibleValue<T> other) {
		var otherSet = other.getPossibleValues().toSet();
		otherSet.addAll(this.getPossibleValues().toSet());
		return new SetPossibleValue<T>(otherSet);
	}
	
	/**
	 * generate a new possible values s.t. the possible values by allowing a new one to be present
	 * @param value
	 */
	public default IPossibleValue<T> union(T value) {
		return this.union(new SetPossibleValue<T>(value));
	}
	
	/**
	 * if true, there are no possible values for this variable
	 * @return
	 */
	public default boolean isEmpty() {
		return this.getPossibleValues().isEmpty();
	}
	
	/**
	 * get the number of all the possible values the variable may have
	 * @return
	 */
	public default int getNumberOfPossibleValues() {
		return this.getPossibleValues().size();
	}
	
	public default int size() {
		return this.getNumberOfPossibleValues();
	}
	
	/**
	 * string representation of all the possible values
	 * @return
	 */
	public String toString();
}
