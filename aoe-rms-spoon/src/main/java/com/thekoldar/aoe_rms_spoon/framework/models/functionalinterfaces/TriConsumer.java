package com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces;

/**
 * A procedure that accepts 3 values and do something with them
 * @author massi
 *
 * @param <T1> type fo the first argument
 * @param <T2> type of the second argument
 * @param <T3> type of the third argument
 */
@FunctionalInterface
public interface TriConsumer<T1,T2,T3> {

	/**
	 * procedure that does something
	 * 
	 * @param a first paraemter
	 * @param b second parameter
	 * @param c third parameter
	 */
	public void apply(T1 a, T2 b, T3 c);
}
