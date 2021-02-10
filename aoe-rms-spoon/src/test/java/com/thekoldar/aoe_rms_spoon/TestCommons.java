package com.thekoldar.aoe_rms_spoon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.junit.Test;

import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class TestCommons {
	
	@Test
	public void TestCartesianProduct_00() {
		
		var out = Utils.cartesianProduct(
				Sets.mutable.of(1,2,3), 
				Sets.mutable.of(4,5),
				Sets.mutable.of(6,7,8)
		).toList();
		
		assertEquals(18, out.size());
		assertTrue(!out.select(i -> i.contains(1) && i.contains(4)).isEmpty());
		assertTrue(!out.select(i -> i.contains(1) && i.contains(5)).isEmpty());
		assertTrue(!out.select(i -> i.contains(1) && i.contains(6)).isEmpty());
		
		assertTrue(!out.select(i -> i.contains(2) && i.contains(4)).isEmpty());
		assertTrue(!out.select(i -> i.contains(2) && i.contains(5)).isEmpty());

		
		assertTrue(!out.select(i -> i.contains(3) && i.contains(4)).isEmpty());
		assertTrue(!out.select(i -> i.contains(3) && i.contains(5)).isEmpty());
		
		assertTrue(out.select(i -> i.contains(1) && i.contains(2)).isEmpty());
		assertTrue(out.select(i -> i.contains(1) && i.contains(3)).isEmpty());
		
	}

	@Test
	public void TestCartesianProduct_01() {
		
		var out = Utils.cartesianProduct(
				Sets.mutable.of(1,2,3), 
				Sets.mutable.of(4,5)
		).toList();
		
		assertEquals(6, out.size());
		assertTrue(!out.select(i -> i.contains(1) && i.contains(4)).isEmpty());
		assertTrue(!out.select(i -> i.contains(1) && i.contains(5)).isEmpty());
		
		assertTrue(!out.select(i -> i.contains(2) && i.contains(4)).isEmpty());
		assertTrue(!out.select(i -> i.contains(2) && i.contains(5)).isEmpty());
		
		assertTrue(!out.select(i -> i.contains(3) && i.contains(4)).isEmpty());
		assertTrue(!out.select(i -> i.contains(3) && i.contains(5)).isEmpty());
		
		assertTrue(out.select(i -> i.contains(1) && i.contains(2)).isEmpty());
		assertTrue(out.select(i -> i.contains(1) && i.contains(3)).isEmpty());
		
	}
	
	@Test
	public void TestCartesianProduct_02() {
		
		var out = Utils.cartesianProduct(
				Sets.mutable.of(1,2,3)
		).toList();
		
		assertEquals(3, out.size());
		assertTrue(!out.select(i -> i.contains(1)).isEmpty());
		
		assertTrue(!out.select(i -> i.contains(2)).isEmpty());
		
		assertTrue(!out.select(i -> i.contains(3)).isEmpty());
		
	}
}
