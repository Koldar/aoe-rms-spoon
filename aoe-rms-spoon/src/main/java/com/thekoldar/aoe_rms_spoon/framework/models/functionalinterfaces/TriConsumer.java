package com.thekoldar.aoe_rms_spoon.framework.models.functionalinterfaces;

@FunctionalInterface
public interface TriConsumer<T1,T2,T3> {

	public void apply(T1 a, T2 b, T3 c);
}
