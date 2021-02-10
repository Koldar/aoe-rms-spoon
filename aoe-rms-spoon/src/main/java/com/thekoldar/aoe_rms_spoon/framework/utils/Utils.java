package com.thekoldar.aoe_rms_spoon.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.tuple.primitive.BooleanObjectPair;
import org.eclipse.collections.api.tuple.primitive.IntObjectPair;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.MapConstants;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;

/**
 * Set of generic utils used in the project
 * @author massi
 *
 */
public class Utils {
	
	public static <T> RichIterable<MutableList<T>> cartesianProduct(MutableSet<T>... s) {
		return cartesianProductFromSet(Lists.fixedSize.of(s));
	}
	
	public static <T> RichIterable<MutableList<T>> cartesianProductFromSet(RichIterable<MutableSet<T>> s) {
		return cartesianProduct(s.collect(i -> i.toList()));
	}
	
	/**
	 * generate all the cartesian products bwetwen the sets
	 * @param <T>
	 * @param s sets to consider
	 * @return all the combinations in this product cartesian 
	 * @see https://stackoverflow.com/a/9591777/1887602
	 */
	public static <T> RichIterable<MutableList<T>> cartesianProduct(RichIterable<MutableList<T>> s) {
		return cartesianProductAndStopAsFirstReject((e) -> true, s).getTwo();
	}
	
	/**
	 * generate all the cartesian products but stops as soon as we detect a combination that does not satisfy the predicate
	 * 
	 * @param <T>
	 * @param predicate predicate each combination in the cartesian product needs to satisfy
	 * @param s sets to consider
	 * @return if true, all the combinations. If false a single list containing the first problematic combination that didn't suprssed the predicate
	 */
	public static <T> BooleanObjectPair<RichIterable<MutableList<T>>> cartesianProductAndStopAsFirstReject(Predicate<MutableList<T>> predicate, RichIterable<MutableList<T>> s) {
		int solutions = 1;
		var sets = s.toList();
		MutableSet<MutableList<T>> result = Sets.mutable.empty();
		
		for (int i=0; i<sets.size(); solutions *= sets.get(i).size(), ++i) {};
		
		for (int i=0; i<solutions; ++i) {
			int j = 1;
			MutableList<T> tmp = Lists.mutable.empty();
			for (var set : sets) {
				tmp.add(set.get((i/j) % set.size()));
				j *= set.size();
			}
			if (!predicate.test(tmp)) {
				return PrimitiveTuples.pair(false, Lists.fixedSize.of(tmp));
			}
			result.add(tmp);
		}
		return PrimitiveTuples.pair(true, result);
	}
	
	
	/**
	 * get all the values in constants
	 * @return
	 */
	public static <TCLASS, TOUT> RichIterable<TOUT> getPublicStaticFinalFieldsOfClass(Class<TCLASS> clazz, Class<TOUT> toutput) {
		return Lists.immutable.of(clazz.getFields())
		.select(f -> Modifier.isPublic(f.getModifiers()))
		.select(f -> Modifier.isStatic(f.getModifiers()))
		.select(f -> Modifier.isFinal(f.getModifiers()))
		.collect(f -> {
			try {
				return (TOUT)f.get(null);
			} catch (Exception e) {
				return null;
			}
		})
		.select(v -> v != null)
		;
	}
	
	/**
	 * Check if a substring is present in a given string, without considering the case
	 * @param str string where to search
	 * @param substring sub string to find
	 * @return
	 */
	public static boolean containsCaseInsensitive(String str, String substring) {
		return str.toLowerCase().contains(substring.toLowerCase());
	}
	
	/**
	 * given a <b>re-iterable</b> rich iterable, we associate to each element of the iterable the same percentage value that it may occur.
	 * We try to given balanced percentage in case the 100% cannot be equally divided between the different choices
	 * @param <T> type of the rich iterable
	 * @param it iterable
	 * @return a iterable of pairs, where the first is the eprcentage (between 1 and 100) while the other is the element itself
	 */
	public static <T> RichIterable<IntObjectPair<T>> percentage(RichIterable<T> it) {
		//this implementation allows  2 2 2 1 instead of 1 1 1 4 with the percentage to distribute is 7 over 4 items (which is much more equal)
		//if there 54 choices, 100/54 return 1
		var conservativePerc = 100 / it.size();
		//if there 54 choices, 100/54 return 1.8 => which yield 2.
		var unsafePerc = (int)Math.ceil(100.0 / it.size());
		
		//used as lambda context
		var remainderPercentage = new AtomicInteger(100);
		
		return it.zipWithIndex()
				.collect(pair -> {
					var i = pair.getTwo();
					var t = pair.getOne();
					var remainderItemsCount = it.size() - i;
					
					//this is the last item. use the remainder percentage
					if (i == (it.size() - 1)) {
						//last element
						return PrimitiveTuples.pair(remainderPercentage.get(), t);
					} else {
						//let's check if the remainder percentage is enough for the remainder items
						if (remainderPercentage.get() > (conservativePerc * remainderItemsCount)) {
							//we still have enough percentage to cover the remainder items via the ciobnservativePerc
							remainderPercentage.set(remainderPercentage.get() - unsafePerc);
							return PrimitiveTuples.pair(unsafePerc, t);
						} else {
							// we do not have enough remainder percantage. switch to the conservative approcj
							remainderPercentage.set(remainderPercentage.get() - conservativePerc);
							return PrimitiveTuples.pair(conservativePerc, t);
						}
					}
				}
		);
	}
	
	/**
	 * Clamp a number betwee an minimum and a maximum
	 * @param x
	 * @param minX
	 * @param maxX
	 * @return
	 */
	public static int clamp(int x, int minX, int maxX) {
		if (x < minX) {
			return minX;
		}
		if (x > maxX) {
			return maxX;
		}
		return x;
	}
	
	public static int clamp0100(int x) {
		return clamp(x, 0, 100);
	}
	
	/**
	 * load a resource within this very jar file
	 * @param classPathPath
	 * @return
	 */
	public static InputStream LoadResourceAsInputStream(String classPathPath) {
		var inputStream = Utils.class.getClassLoader().getResourceAsStream(classPathPath);
		return inputStream;
	}
	
	public static Reader LoadResourceAsReader(String classPathPath, Charset encoding) {
		var inputStream = Utils.class.getClassLoader().getResourceAsStream(classPathPath);
		return new InputStreamReader(inputStream, encoding);
	}
	
	/**
	 * load a resoruce from the file system. If the path is irelative, it is relative to the CWD
	 * 
	 * @param path path of the file to load
	 * @return
	 * @throws FileNotFoundExceptionif the file does not exists on the file system or it is not readable 
	 */
	public static InputStream loadResourcesOutsideJar(Path path) throws FileNotFoundException {
		path = path.toAbsolutePath().normalize();
		if (!path.toFile().exists()) {
			throw new FileNotFoundException(path.toString());
		}
		return new FileInputStream(path.toAbsolutePath().toString());
	}
	
	/**
	 * load a resoruce from the file system. If the path is irelative, it is relative to the CWD
	 * 
	 * @param path path of the file to load
	 * @return
	 * @throws FileNotFoundExceptionif the file does not exists on the file system or it is not readable 
	 */
	public static Reader loadResourceOutsideJarAsReader(Path path) throws FileNotFoundException {
		return new InputStreamReader(loadResourcesOutsideJar(path));
	}
	
}