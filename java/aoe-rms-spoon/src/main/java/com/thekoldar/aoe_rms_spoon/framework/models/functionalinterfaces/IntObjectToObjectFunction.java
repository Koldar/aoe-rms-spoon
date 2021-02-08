package com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces;

@FunctionalInterface
public interface IntObjectToObjectFunction<T1, T2> {

	public T2 apply(int a, T1 b);
}
