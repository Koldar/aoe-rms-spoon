package com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces;

@FunctionalInterface
public interface TriFunction<T1, T2, T3, TOUT> {

	TOUT apply(T1 param1, T2 param2, T3 param3);
}
