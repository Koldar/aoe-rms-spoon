package com.thekoldar.aoe_rms_spoon.framework.code_generation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.primitive.IntObjectPair;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

/**
 * A class that represents the output of a code generation routine. Code generation routines allows the program to generates lines of RMS code, thus the output is just a list of lines
 * 
 * @author massi
 *
 */
public class CodeGenerationOutput {

	
	/**
	 * pairs. the first is the number of tab required to add w.r.t. the base. The second is the acrtual string to add
	 */
	private MutableList<IntObjectPair<String>> lines;
	/**
	 * tab number that a new line in this structure will need to have prepended. 0 means that no tabs need to be prepended 
	 */
	private int currentTabNumber;

	/**
	 * Create a new instance of a code generation output. The instance is completely empty
	 * @return a new fresh instance
	 */
	public static CodeGenerationOutput instance() {
		return new CodeGenerationOutput();
	}

	/**
	 * Create a new instance of a code generation output. The instance is completely empty
	 */
	public CodeGenerationOutput() {
		super();
		this.lines = Lists.mutable.empty();
		this.currentTabNumber = 0;
	}
	
	/**
	 * 
	 * @return the lines that thsi code generation will print on a file, if needed. The function will return the number of tab each line requires to be prepended as well 
	 */
	public List<IntObjectPair<String>> getTabLines() {
		return lines;
	}
	
	/**
	 * 
	 * @return the lines that thsi code generation will print on a file, if needed
	 */
	public List<String> getLines() {
		return lines.collect(l -> l.getTwo());
	}
	
	/**
	 * Increase the tab number to print by 1. After this instruction,the tab number is effectively increased
	 * 
	 * @return this
	 */
	public CodeGenerationOutput increaseTabNumber() {
		this.currentTabNumber += 1;
		return this;
	}

	/**
	 * Decrease the tab number to print by 1. After this instruction,the tab number is effectively decreased
	 * 
	 * @return this
	 */
	public CodeGenerationOutput decreaseTabNumber() {
		this.currentTabNumber -= 1;
		return this;
	}
	
	/**
	 * Alter the last line of the code generation by appending  the given strings.
	 * If there is no last line, the method is equal to {@link #addLine(String)}.
	 * 
	 * @param start the string to be added after the current last line
	 * @param end string added at the very end of the updated last line
	 * @param delimiter strings added between each element in{@code strings}
	 * @param strings string to be added  after {@code start} and delimited with {@code delimiter}
	 */
	public void addStringsToLastLine(String start, String end, String delimiter, Collection<String> strings) {
		var oldLine = this.lines.isEmpty() ? "" : this.lines.get(this.lines.size() - 1).getTwo();
		oldLine = oldLine + start + String.join(delimiter, strings) + end;
		
		if (this.lines.isEmpty()) {
			this.lines.add(PrimitiveTuples.pair(this.currentTabNumber, oldLine));
		}
		else {
			var indexToAlter = this.lines.size() -1;
			this.lines.set(indexToAlter, PrimitiveTuples.pair(this.lines.get(indexToAlter).getOne(), oldLine));
		}
	}
	
	/**
	 * Alter the last line of the code generation by appending  the given strings.
	 * If there is no last line, the method is equal to {@link #addLine(String)}.
	 * 
	 * @param start the string to be added after the current last line
	 * @param end string added at the very end of the updated last line
	 * @param delimiter strings added between each element in{@code strings}
	 * @param strings string to be added  after {@code start} and delimited with {@code delimiter}
	 */
	public void addStringsToLastLine(String start, String end, String delimiter, String... strings) {
		this.addStringsToLastLine(start, end, delimiter, Lists.fixedSize.of(strings));
	}
	
	/**
	 * Alter the last line of the code generation by appending  the given strings.
	 * If there is no last line, the method is equal to {@link #addLine(String)}.
	 * We put a space between each element in {@code string}, a space after the current last line and nothing at the end of the updated last line 
	 * 
	 * @param strings string to be added in the last line
	 */
	public void addStringsToLastLine(Collection<String> strings) {
		this.addStringsToLastLine(" ", "", " ", strings);
	}
	
	/**
	 * Alter the last line of the code generation by appending  the given strings.
	 * If there is no last line, the method is equal to {@link #addLine(String)}.
	 * We put a space between each element in {@code string}, a space after the current last line and nothing at the end of the updated last line 
	 * 
	 * @param string string to be added in the last line
	 */
	public void addStringToLastLine(String string) {
		this.addStringsToLastLine(" ", "", " ", new String[] {string});
	}
	
	/**
	 * Alter the last line of the code generation by appending  the given strings.
	 * If there is no last line, the method is equal to {@link #addLine(String)}.
	 * We put a space between each element in {@code string}, a space after the current last line and nothing at the end of the updated last line
	 * 
	 * <pre>{@code
	 *  //last line is "pippo"
	 * 	addStringToLastLine("hello", "world");
	 *  //after is pippohelloworld
	 * }</pre>
	 * 
	 * @param start the string to be added after the current last line
	 * @param string string to be added in the last line
	 */
	public void addStringToLastLine(String start, String string) {
		this.addStringsToLastLine(start, "", " ", new String[] {string});
	}
	
	/**
	 * Alte rthe last line in such a way that a trailing space is added at the end. Useful when you want to ensure there is a space between a line and the enxt one
	 */
	public void addTralingSpaceToLastLine() {
		this.addStringsToLastLine("", " ", " ", new String[] {});
	}
	
	/**
	 * Alter the last line of the code generation by appending  the given strings.
	 * If there is no last line, the method is equal to {@link #addLine(String)}.
	 * We put a space between each element in {@code string}, a space after the current last line and nothing at the end of the updated last line
	 * 
	 * <pre>{@code
	 *  //last line is "pippo"
	 * 	addStringToLastLine("hello", "!", "world");
	 *  //after is pippohelloworld!
	 * }</pre>
	 * 
	 * @param start the string to be added after the current last line
	 * @param end the string to be appended at the end of the line
	 * @param string string to be added in the last line
	 */
	public void addStringToLastLine(String start, String end, String string) {
		this.addStringsToLastLine(start, "", end, new String[] {string});
	}
	
	/**
	 * Add an empty line specified at the end of the lines in {@code this}
	 * 
	 */
	public void addLine() {
		this.lines.add(PrimitiveTuples.pair(0, ""));
	}
	
	/**
	 * Add the line specified at the end of the lines in {@code this}
	 * 
	 * @param line the lines to add
	 */
	public void addLine(String line) {
		this.lines.add(PrimitiveTuples.pair(this.currentTabNumber, line));
	}
	
	/**
	 * Add the lines specified at the end of the lines in {@code this}
	 * 
	 * @param lines the lines to add
	 */
	public void addLines(RichIterable<String> lines) {
		this.lines.addAllIterable(lines.collect(s -> PrimitiveTuples.pair(this.currentTabNumber, s)));
	}
	
	/**
	 * Add the lines specified at the end of the lines in {@code this}
	 * 
	 * @param lines the lines to add
	 */
	public void addTheseLines(String... lines) {
		this.addLines(Lists.fixedSize.of(lines));
	}
	
	/**
	 * Merge a code generation outptu with {@code this}. Specifically, the lines of {@code} will be put at the end of the lines of {@code this}
	 * 
	 * @param other the code generation to merge. 
	 * @return {code this}
	 */
	public CodeGenerationOutput merge(@Nullable CodeGenerationOutput other) {
		if (other == null) {
			return this;
		}
		
		this.lines.addAll(other.lines.collect(pair -> PrimitiveTuples.pair(this.currentTabNumber + pair.getOne(), pair.getTwo())));
		return this;
	}

	/**
	 * Merge the lines in {@code other} inside {@code} this code generation output. Specifically it merges them into the last line of {@code this} 
	 * output. Lines in {@code other} are merged into a single line joined with a space between them
	 * 
	 * @param other the code generation to merge with {@code this}
	 * @return the merged code generation
	 */
	public CodeGenerationOutput mergeIntoStringAfterLastLine(@Nullable CodeGenerationOutput other) {
		if (other == null) {
			return this;
		}
		this.addLine(String.join(" ", other.getLines()));
		return this;
	}
	
	/**
	 * adds the code generation of all the lines in {@code other} into the last line of {@link CodeGenerationOutput#lines}. 
	 * @param other the other output of code generation to merge
	 * @param appendToLastLine the separator between the last line in this generator and the lines in {@code other} 
	 * @return output of code generation
	 */
	public CodeGenerationOutput mergeIntoLastLine(@Nullable CodeGenerationOutput other, String appendToLastLine) {
		if (other == null) {
			return this;
		}
		if (this.lines.isEmpty()) {
			this.addLine(String.join(" ", other.getLines()));
		}
		else {
			var index = this.lines.size() - 1;
			var lastLine = this.lines.get(this.lines.size() - 1);
			this.lines.set(
					index, 
					PrimitiveTuples.pair(
							this.currentTabNumber, 
							lastLine.getTwo() + appendToLastLine + String.join(" ", other.getLines())
					)
			);
		}
		
		return this;
	}
	
	
}
