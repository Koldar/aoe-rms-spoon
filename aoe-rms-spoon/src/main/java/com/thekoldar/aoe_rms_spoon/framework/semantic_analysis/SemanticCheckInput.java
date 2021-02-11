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
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.ast.symbols.IRMSSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSConstSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSDefineSymbol;
import com.thekoldar.aoe_rms_spoon.ast.symbols.RMSSymbolType;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;

public class SemanticCheckInput {
	
	private static final Logger LOG = LoggerFactory.getLogger(SemanticCheckInput.class);

	/**
	 * All the available const symbol we know the user has declare in the file as well as its possible values.
	 * MNotice that a symbol may have multiple values (e.g., rnd function may generate differen values).
	 * A const which has not been defined is not cited in this map
	 */
	private MutableMap<String, IPossibleValue<Long>> constSymbolTable;
	/**
	 * All the available define symbols we know the user has declare in the file as well as its possible values.
	 * MNotice that a symbol may have multiple values (e.g., rnd function may generate differen values).
	 * 
	 * A const which has not been defined is not cited in this map
	 */
	private MutableMap<String, IPossibleValue<Boolean>> defineSymbolTable;
	/**
	 * the player (in lobby order) that needs to be set in order for this RMS to work
	 */
	private MutableSet<IPossibleValue<Long>> playerLobbyOrderThatNeedsToBePlaying;
	/**
	 * the player (in color order) that needs to be set in order for this RMS to work
	 */
	private MutableSet<IPossibleValue<Long>> playerColorOrderThatNeedsToBePlaying;
	
	private ImmutableSet<RMSErrorCode> treatedAsWarning;
	private ImmutableSet<RMSErrorCode> treatedAsErrors;
	private ConstNotFoundInSymbolTableActionEnum constNotFoundInSymbolTableAction;
	
	
	public SemanticCheckInput(ImmutableSet<RMSErrorCode> treatedAsWarning, ImmutableSet<RMSErrorCode> treatedAsErrors, ConstNotFoundInSymbolTableActionEnum action) {
		this.constSymbolTable = Maps.mutable.empty();
		this.defineSymbolTable = Maps.mutable.empty();
		this.treatedAsErrors = treatedAsErrors;
		this.treatedAsWarning = treatedAsWarning;
		this.constNotFoundInSymbolTableAction = action;
		this.playerLobbyOrderThatNeedsToBePlaying = Sets.mutable.empty();
		this.playerColorOrderThatNeedsToBePlaying = Sets.mutable.empty();
	}
	
	private SemanticCheckInput(SemanticCheckInput other) {
		this(other.treatedAsWarning, other.treatedAsErrors, other.constNotFoundInSymbolTableAction);
		//copy const symbol table
		for (var entry : other.constSymbolTable.entrySet()) {
			this.constSymbolTable.put(entry.getKey(), entry.getValue());
		}
		// copy define symbol table
		for (var entry : other.defineSymbolTable.entrySet()) {
			this.defineSymbolTable.put(entry.getKey(), entry.getValue());
		}
		//copy the player colors that needs to be set
		this.playerColorOrderThatNeedsToBePlaying = Sets.mutable.empty();
		for (var i : other.playerColorOrderThatNeedsToBePlaying) {
			this.playerColorOrderThatNeedsToBePlaying.add(i);
		}
		
		//copy the player lobby that needs to be set
		this.playerLobbyOrderThatNeedsToBePlaying = Sets.mutable.empty();
		for (var i : other.playerLobbyOrderThatNeedsToBePlaying) {
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
	public void declareThatPlayerWithLobbyOrderNeedsToBePlaying(IPossibleValue<Long> playerLobbyOrder) {
		this.playerLobbyOrderThatNeedsToBePlaying.add(playerLobbyOrder);
	}
	
	/**
	 * declare that the player with the given color order needs to be set
	 * @param playerColor
	 */
	public void declareThatPlayerWithColorNeedsToBePlaying(IPossibleValue<Long> playerColor) {
		this.playerColorOrderThatNeedsToBePlaying.add(playerColor);
	}
	
	/**
	 * get the value of a defined const. We assume the const exists
	 * @param name
	 * @return
	 */
	public IPossibleValue<Long> getConstValue(String name) {
		if (!this.constSymbolTable.contains(name)) {
			switch (this.constNotFoundInSymbolTableAction) {
			case ASSUME_0:
				LOG.warn("We tried to access the value of the const \"{}\". Sadly such a const was not present in the symbol table! We assume the const value is 0!", name);
				return new LongSetPossible(0);
			case RAISE_EXCEPTION:
				throw new IllegalArgumentException(String.format("We tried to access the value of the const \"%s\". sadly such a const was not present in the symbol table!", name));
			default:
				throw new IllegalArgumentException(String.format("Invalid constNotFoundInSymbolTableAction %s", this.constNotFoundInSymbolTableAction));
			}
		}
		return this.constSymbolTable.get(name);
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
	public MutableMap<String, IPossibleValue<Long>> constAvailable() {
		return this.constSymbolTable.asUnmodifiable();
	}
	
	/**
	 * generate a map representing all the defines that we know the script has defined
	 * @return
	 */
	public MutableMap<String, IPossibleValue<Boolean>> defineAvailable() {
		return this.defineSymbolTable.asUnmodifiable();
	}
	
	/**
	 * Use this method to declare the fact that the compiler knows that the given constant can also have the associated value
	 * @param name
	 * @param val
	 */
	public void knowThatConstCanAlsoBe(String name, int val) {
		if (this.constSymbolTable.containsKey(name)) {
			var values = this.constSymbolTable.get(name);
			this.constSymbolTable.put(name, values.union(Long.valueOf(val)));
		} else {
			this.constSymbolTable.put(name, new LongSetPossible(val));
		}
		LOG.info("We now know that \"{}\" name is associated with the int value {}", name, val);
	}
	
	/**
	 * Use this method to declare the fact that the compiler knows that the given constant can only have this value
	 * @param name
	 * @param val
	 */
	public void knowThatConstCanOnlyBe(String name, int val) {
		this.constSymbolTable.put(name, new LongSetPossible(val));
		LOG.info("We now know that \"{}\" name is associated with the int value {}", name, val);
	}
	
	/**
	 * Use this method to declare that the compiler knows that the given define is in fact defined
	 * @param name define to check
	 */
	public void knowThatDefineCanAlsoBe(String name, boolean defined) {
		if (this.defineSymbolTable.containsKey(name)) {
			var values = this.defineSymbolTable.get(name);
			this.defineSymbolTable.put(name, values.union(defined));
		} else {
			this.defineSymbolTable.put(name, new SetPossibleValue<Boolean>(defined));
		}
		LOG.info("We now know that \"{}\" is defined", name);
	}
	
	/**
	 * Use this method to declare that the compiler knows that the given define is in fact defined
	 * @param name define to check
	 */
	public void knowThatDefineCanOnlyBe(String name, boolean defined) {
		this.defineSymbolTable.put(name, new SetPossibleValue<Boolean>(defined));
		LOG.info("We now know that \"{}\" is defined {}", name, defined);
	}
	
	public boolean isConstDefined(String name) {
		return this.constAvailable().containsKey(name);
	}
	
	public boolean isConstNotdefined(String name) {
		return !this.constAvailable().containsKey(name);
	}
	
	/**
	 * check if the we know for sure that the define is defined
	 * 
	 * @param name name of the defined
	 * @return true if the only possible scenario right now is that the define is defined
	 */
	public boolean isForSureDefined(String name) {
		if (!this.defineAvailable().containsKey(name)) {
			return false;
		}
		if (this.defineAvailable().get(name).contains(false)) {
			return false;
		}
		return this.defineAvailable().get(name).contains(true);
	}
	
	/**
	 * check if the we know for sure that the define is undefined
	 * 
	 * @param name name of the defined
	 * @return true if the only possible scenario right now is that the define is defined
	 */
	public boolean isForSureUndefined(String name) {
		if (!this.defineAvailable().containsKey(name)) {
			return true;
		}
		if (this.defineAvailable().get(name).contains(true)) {
			return false;
		}
		return this.defineAvailable().get(name).contains(false);
	}
	
	/**
	 * check if there is at least one scenario where the define is defined
	 * @param name
	 * @return
	 */
	public boolean isMaybeDefined(String name) {
		if (!this.defineAvailable().containsKey(name)) {
			return false;
		}
		return this.defineAvailable().get(name).contains(true);
	}
	
	/**
	 * get the possible values of the define. If the define is not present, we assume it can only be undefined
	 * 
	 * @param name
	 * @return
	 */
	public IPossibleValue<Boolean> getDefinedValue(String name) {
		if (!this.defineSymbolTable.contains(name)) {
			//assume undefined
			return new SetPossibleValue<Boolean>(false);
		}
		return this.defineSymbolTable.get(name);
	}
	
	public String toString() {
		var c = this
				.constAvailable()
				.collect((name, values) -> Tuples.pair(name, String.format("%s=%s", name, values)))
				.toSortedList(String::compareTo)
				.makeString();
				;
		var d = this
				.defineAvailable()
				.collect((name, values) -> Tuples.pair(name, String.format("%s=%s", name, values)))
				.toSortedList(String::compareTo)
				.makeString()
				;
		return "Const: " + c + " Defines: " + d;
	}
	
}
