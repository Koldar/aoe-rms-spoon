package com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces;

/**
 * A function that accepts 2 parameter and convert them into one. The first value is a primitive int
 * @author massi
 *
 * @param <T1> type of the second parameter
 * @param <T2> type fo the generated output
 */
@FunctionalInterface
public interface IntObjectToObjectFunction<T1, T2> {

	/**
	 * 2D function
	 * @param a first value
	 * @param b second value
	 * @return output of the function
	 */
	public T2 apply(int a, T1 b);
}
