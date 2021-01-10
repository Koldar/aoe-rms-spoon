package com.thekoldar.aoe_rms_spoon.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public class CodeGenerationOutput {

	
	private List<String> lines;

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
	
	public void addLine() {
		this.lines.add("");
	}
	
	public void addLine(String line) {
		this.lines.add(line);
	}
	
	public void addLines(Collection<String> lines) {
		this.lines.addAll(lines);
	}
	
	public void addTheseLiens(String... lines) {
		this.lines.addAll(Lists.fixedSize.of(lines));
	}
	
	public CodeGenerationOutput merge(@Nullable CodeGenerationOutput other) {
		if (other == null) {
			return this;
		}
		this.lines.addAll(other.lines);
		return this;
	}
	
	
}
