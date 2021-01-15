package com.thekoldar.aoe_rms_spoon.models;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;

import com.thekoldar.aoe_rms_spoon.models.symbols.IRMSSymbol;
import com.thekoldar.aoe_rms_spoon.models.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.models.symbols.RMSSymbolType;

public class SemanticCheckInput {

	private MutableMap<String, IRMSSymbol> symbolTable;
	
	public SemanticCheckInput() {
		this.symbolTable = Maps.mutable.empty();
	}
	
	public MutableMap<String, RMSConstSymbol> constAvailable() {
		return this.symbolTable
				.select((k,symbol) -> symbol.getType().equals(RMSSymbolType.CONST))
				.collect((k,symbol) -> Tuples.pair(k, (RMSConstSymbol)symbol))
				;
	}
	
	public MutableMap<String, RMSConstSymbol> defineAvailable() {
		return this.symbolTable
				.select((k,symbol) -> symbol.getType().equals(RMSSymbolType.DEFINE))
				.collect((k,symbol) -> Tuples.pair(k, (RMSConstSymbol)symbol))
				;
	}
	
	public boolean isConstDefined(String name) {
		return this.constAvailable().containsKey(name);
	}
	
	public boolean isConstUndefined(String name) {
		return !this.constAvailable().containsKey(name);
	}
	
	public boolean isDefined(String name) {
		return !this.defineAvailable().containsKey(name);
	}
	
}
