package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.tuple.Tuples;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.ast.symbols.IRMSSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSDefineSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSSymbolType;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;

public class SemanticCheckInput {
	
	private static final Logger LOG = LoggerFactory.getLogger(SemanticCheckInput.class);

	private MutableMap<String, IRMSSymbol> symbolTable;
	private MutableIntSet playerLobbyOrderThatNeedsToBePlaying;
	private MutableIntSet playerColorOrderThatNeedsToBePlaying;
	
	private ImmutableSet<RMSErrorCode> treatedAsWarning;
	private ImmutableSet<RMSErrorCode> treatedAsErrors;
	private ConstNotFoundInSymbolTableActionEnum constNotFoundInSymbolTableAction;
	
	
	public SemanticCheckInput(ImmutableSet<RMSErrorCode> treatedAsWarning, ImmutableSet<RMSErrorCode> treatedAsErrors, ConstNotFoundInSymbolTableActionEnum action) {
		this.symbolTable = Maps.mutable.empty();
		this.treatedAsErrors = treatedAsErrors;
		this.treatedAsWarning = treatedAsWarning;
		this.constNotFoundInSymbolTableAction = action;
		this.playerLobbyOrderThatNeedsToBePlaying = IntSets.mutable.empty();
		this.playerColorOrderThatNeedsToBePlaying = IntSets.mutable.empty();
	}
	
	private SemanticCheckInput(SemanticCheckInput other) {
		this(other.treatedAsWarning, other.treatedAsErrors, other.constNotFoundInSymbolTableAction);
		this.symbolTable = Maps.mutable.empty();
		for (var entry : this.symbolTable.entrySet()) {
			this.symbolTable.put(entry.getKey(), entry.getValue());
		}
		this.playerColorOrderThatNeedsToBePlaying = IntSets.mutable.empty();
		for (var i : other.playerColorOrderThatNeedsToBePlaying.toArray()) {
			this.playerColorOrderThatNeedsToBePlaying.add(i);
		}
		
		this.playerLobbyOrderThatNeedsToBePlaying = IntSets.mutable.empty();
		for (var i : other.playerLobbyOrderThatNeedsToBePlaying.toArray()) {
			this.playerLobbyOrderThatNeedsToBePlaying.add(i);
		}
	}
	
	/**
	 * create a deep copy of this input.
	 * @return
	 */
	public SemanticCheckInput copy() {
		return new SemanticCheckInput(this);		
	}
	
	/**
	 * declare that the player with the given lobby order needs to be set
	 * @param playerLobbyOrder
	 */
	public void declareThatPlayerWithLobbyOrderNeedsToBePlaying(int playerLobbyOrder) {
		this.playerLobbyOrderThatNeedsToBePlaying.add(playerLobbyOrder);
	}
	
	/**
	 * declare that the player with the given color order needs to be set
	 * @param playerColor
	 */
	public void declareThatPlayerWithColorNeedsToBePlaying(int playerColor) {
		this.playerColorOrderThatNeedsToBePlaying.add(playerColor);
	}
	
	/**
	 * get the value of a defined const. We assume the const exists
	 * @param name
	 * @return
	 */
	public int getConstValue(String name) {
		if (!this.symbolTable.contains(name)) {
			switch (this.constNotFoundInSymbolTableAction) {
			case ASSUME_0:
				LOG.warn("We tried to access the value of the const \"%s\". Sadly such a const was not present in the symbol table! We assume the const value is 0!", name);
				return 0;
			case RAISE_EXCEPTION:
				throw new IllegalArgumentException(String.format("We tried to access the value of the const \"%s\". sadly such a const was not present in the symbol table!", name));
			default:
				throw new IllegalArgumentException(String.format("Invalid constNotFoundInSymbolTableAction %s", this.constNotFoundInSymbolTableAction));
			}
		}
		assert this.symbolTable.get(name) instanceof RMSConstSymbol;
		return ((RMSConstSymbol)this.symbolTable.get(name)).getValue();
	}
	
	/**
	 * Create a new semantic check output filled with the information stored in thisd ata structure
	 * @return
	 */
	public SemanticCheckOutput createOutput() {
		return new SemanticCheckOutput(this);
	}
	
	public boolean shouldBeTreatedAsError(RMSErrorCode code) {
		return this.treatedAsErrors.contains(code);
	}
	
	public boolean shouldBeratedAsWarning(RMSErrorCode code) {
		return this.treatedAsWarning.contains(code);
	}
	
	/**
	 * generate a map representing all the const that we know the script has defined
	 * @return
	 */
	public MutableMap<String, RMSConstSymbol> constAvailable() {
		return this.symbolTable
				.select((k,symbol) -> symbol.getType().equals(RMSSymbolType.CONST))
				.collect((k,symbol) -> Tuples.pair(k, (RMSConstSymbol)symbol))
				;
	}
	
	/**
	 * generate a map representing all the defines that we know the script has defined
	 * @return
	 */
	public MutableMap<String, RMSDefineSymbol> defineAvailable() {
		return this.symbolTable
				.select((k,symbol) -> symbol.getType().equals(RMSSymbolType.DEFINE))
				.collect((k,symbol) -> Tuples.pair(k, (RMSDefineSymbol)symbol))
				;
	}
	
	/**
	 * Use this method to declare the fact that the compiler knows that the given constant has the associated value
	 * @param name
	 * @param val
	 */
	public void knowThatConstIs(String name, int val) {
		this.symbolTable.put(name, new RMSConstSymbol(name, val));
		LOG.info("We now know that \"{}\" name is associated with the int value {}", name, val);
	}
	
	/**
	 * Use this method to declare that the compiler knows that the given define is in fact defined
	 * @param name define to check
	 */
	public void knowThatDefineIsDefined(String name) {
		this.symbolTable.put(name, new RMSDefineSymbol(name));
		LOG.info("We now know that \"{}\" is defined", name);
	}
	
	public boolean isConstDefined(String name) {
		return this.constAvailable().containsKey(name);
	}
	
	public boolean isConstUndefined(String name) {
		return !this.constAvailable().containsKey(name);
	}
	
	public boolean isDefined(String name) {
		return this.defineAvailable().containsKey(name);
	}
	
}
