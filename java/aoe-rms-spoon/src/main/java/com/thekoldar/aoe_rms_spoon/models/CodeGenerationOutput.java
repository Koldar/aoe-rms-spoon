package com.thekoldar.aoe_rms_spoon.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class CodeGenerationOutput {

	
	private List<String> lines;
	
	public static CodeGenerationOutput instance() {
		return new CodeGenerationOutput();
	}

	public CodeGenerationOutput() {
		this(new ArrayList<String>());
	}
	
	public CodeGenerationOutput(List<String> lines) {
		super();
		this.lines = lines;
	}

	public CodeGenerationOutput(MutableList<String> lines) {
		super();
		this.lines = lines;
	}
	
	public List<String> getLines() {
		return lines;
	}
	
	public void addStringsToLastLine(String start, String end, String delimiter, Collection<String> strings) {
		var oldLine = this.lines.isEmpty() ? "" : this.lines.get(this.lines.size() - 1);
		oldLine = oldLine + start + String.join(delimiter, strings) + end;
		
		if (this.lines.isEmpty()) {
			this.lines.add(oldLine);
		}
		else {
			this.lines.set(this.lines.size() - 1, oldLine);
		}
	}
	
	public void addStringsToLastLine(String start, String end, String delimiter, String... strings) {
		this.addStringsToLastLine(Lists.fixedSize.of(strings));
	}
	
	public void addStringsToLastLine(Collection<String> strings) {
		this.addStringsToLastLine(" ", "\n", " ", strings);
	}
	
	public void addStringToLastLine(String string) {
		this.addStringsToLastLine(" ", "\n", " ", new String[] {string});
	}
	
	public void addLine() {
		this.lines.add("");
	}
	
	public void addLine(String line) {
		this.lines.add(line);
	}
	
	public void addLines(Collection<String> lines) {
		this.lines.addAll(lines);
	}
	
	public void addTheseLines(String... lines) {
		this.lines.addAll(Lists.fixedSize.of(lines));
	}
	
	public CodeGenerationOutput merge(@Nullable CodeGenerationOutput other) {
		if (other == null) {
			return this;
		}
		this.lines.addAll(other.lines);
		return this;
	}
	
	public CodeGenerationOutput mergeIntoStringAfterLastLine(@Nullable CodeGenerationOutput other) {
		if (other == null) {
			return this;
		}
		this.lines.add(String.join(" ", other.lines));
		return this;
	}
	
	/**
	 * adds the code generation of all the lines in <tt>other</tt> into the last line of {@link CodeGenerationOutput#lines}. 
	 * @param other
	 * @param appendToLastLine
	 * @return
	 */
	public CodeGenerationOutput mergeIntoLastLine(@Nullable CodeGenerationOutput other, String appendToLastLine) {
		if (other == null) {
			return this;
		}
		if (this.lines.isEmpty()) {
			this.lines.add(String.join(" ", other.lines));
		}
		else {
			var lastLine = this.lines.get(this.lines.size() - 1);
			this.lines.set(this.lines.size() - 1, lastLine + appendToLastLine + String.join(" ", other.lines));
		}
		
		return this;
	}
	
	
}
