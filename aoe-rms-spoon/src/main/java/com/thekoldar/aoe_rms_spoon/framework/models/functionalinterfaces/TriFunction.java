package com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces;

/**
 * a function that accepts 3 parameters and generates a single outptu value
 * @author massi
 *
 * @param <T1> type of the first parameter
 * @param <T2> type of the second parameter
 * @param <T3> type of the third parameter
 * @param <TOUT> type of the geneated output
 */
@FunctionalInterface
public interface TriFunction<T1, T2, T3, TOUT> {

	/**
	 * function to apply
	 * @param param1 first parameter
	 * @param param2 second parameter
	 * @param param3 thrid parameter
	 * @return output of the 3D function
	 */
	TOUT apply(T1 param1, T2 param2, T3 param3);
}
