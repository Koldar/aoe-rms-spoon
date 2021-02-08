package com.thekoldar.aoe_rms_spoon.ast;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.Nullable;
import javax.naming.OperationNotSupportedException;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.predicate.primitive.IntObjectPredicate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.tuple.primitive.IntObjectPair;
import org.eclipse.collections.impl.lazy.LazyIterableAdapter;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractLandGeneration;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * a RMS node in the AST of the RMS we are bnuilding
 * @author massi
 *
 */
public interface IRMSNode
{
	
	public AbstractAoEVersion getAgeVersion();
	
	public ImmutableList<IRMSNode> getChildren();
	
	@Nullable
	public IRMSNode getParent();
	
	public void setParent(IRMSNode newParent);
	
	/**
	 * type of the node
	 * @return
	 */
	public RMSNodeType getNodeType();
	
	/**
	 * string representing some information typical of this node. Shoud not include information about children or parent, just of this node.
	 * Used while generating AST image
	 * @return
	 */
	public String getLabel();
	
	public default boolean isLeaf() {
		return !this.getChildren().iterator().hasNext();
	}
	
	public default boolean isRoot() {
		return this.getParent() == null;
	}
	
	/**
	 * Add a new children at the end of the children list of this node.
	 * 
	 * This has the side effect that the children parent is set to this
	 * @param stmt statement to add
	 * @return parent of the node <code>stmt</code> which sould be this
	 */
	public IRMSNode addStatement(IRMSNode stmt);

	/**
	 * Adds a sequence of statements as children of this node.
	 * This has the side effect that the children parent is set to this
	 * 
	 * 
	 * @param stmts statement to add
	 * @return this
	 */
	public default IRMSNode addStatements(IRMSNode... stmts) {
		for (var s : stmts) {
			this.addStatement(s);
		}
		return this;
	}
	
	/**
	 * Adds a sequence of statements as children of this node.
	 * This has the side effect that the children parent is set to this
	 * 
	 * 
	 * @param stmts statement to add
	 * @return this
	 */
	public default IRMSNode addStatements(Iterable<IRMSNode> stmts) {
		for (var s : stmts) {
			this.addStatement(s);
		}
		return this;
	}
	
	/**
	 * poerform semantic analysis on the node
	 * @param input
	 * @return if null, the check is assumed to be ok anbd nothing will be updated
	 */
	@Nullable
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException;
	
	/**
	 * generate RMS code from this node
	 * @param input
	 * @return
	 */
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input);
	
	/**
	 * Keep computing the parent fo the node until we reach the root
	 * @return the root of the node
	 */
	public default IRMSNode getRoot() {
		var tmp = this;
		while (!tmp.isRoot()) {
			tmp = tmp.getParent();
		}
		return tmp;
	}
	
	/**
	 * get all the direct children that satisfies the criterion
	 * @param criterion criterion to satisfies
	 * @return
	 */
	public default RichIterable<IRMSNode> getDirectChildrenSatisfying(Predicate<IRMSNode> criterion) {
		return this.getChildren().select(t -> criterion.test(t));
	}
	
	/**
	 * get all the direct children that are of the given types
	 * @param types if a node has a type included here, it will be present in the output
	 * @return
	 */
	public default RichIterable<IRMSNode> getDirectChildrenOfTypes(RMSNodeType... types) {
		var s = Sets.immutable.of(types);
		return this.getChildren().select(t -> s.contains(t.getNodeType()));
	}
	
	/**
	 * get all the direct children that are not of the given types
	 * @param types if a node has a type included here, it will <b>not</b> be present in the output
	 * @return
	 */
	public default RichIterable<IRMSNode> getDirectChildrenNotOfTypes(RMSNodeType... types) {
		var s = Sets.immutable.of(types);
		return this.getChildren().reject(t -> s.contains(t.getNodeType()));
	}
	
	/**
	 * An iterable containing all the node types of all the nodes inside the subtree rooted on this node
	 * 
	 * @param includeThis if true, in the outptu we wll return this type as well
	 * @return each type will be returned at most once
	 */
	public default RichIterable<RMSNodeType> getAllSubTreeNodeTypes(boolean includeThis) {
		return this.getNodesSatisfyingCriterion(c -> includeThis ? true : c != this)
				.collect(n -> n.getNodeType())
				.toSet()
				;
	}
	
	/**
	 * generate an ordered list of node starting from {@code this} till the root
	 * @param addThis if true, we will add in the generated list this node
	 * @param addRoot if true, we will add in the gnerated list the root node
	 * @return
	 */
	public default RichIterable<IRMSNode> getPathToRoot(boolean addThis, boolean addRoot) {
		MutableList<IRMSNode> result = Lists.mutable.empty();
		var tmp = this;
		
		if (addThis) {
			result.add(tmp);
		}
		while (!tmp.isRoot()) {
			tmp = tmp.getParent();
			
			if (tmp.isRoot()) {
				if (addRoot) {
					result.add(tmp);
				}
			} else {
				result.add(tmp);
			}
		}
		
		return result;
	}
	
	/**
	 * Alias of {@link #getPathToRoot(boolean, boolean)} where both this and roto are added
	 * @return
	 */
	public default RichIterable<IRMSNode> getPathToRoot() {
		return this.getPathToRoot(true, true);
	}
	
	/**
	 * Get all the nodes in the whole AST that is satisfying the given criterion
	 * @param criterion criterion that a node needs to satisfy in order to be in the output
	 * @return
	 */
	public default RichIterable<IRMSNode> getAllNodesInAST(Predicate<IRMSNode> criterion) {
		return this.getRoot().getNodesSatisfyingCriterion(criterion);
	}
	
	/**
	 * Get all the nodes in the whole AST that have one of the type
	 * @param types allowed types
	 * @return
	 */
	public default RichIterable<IRMSNode> getAllNodesInASTOfTypes(RMSNodeType... types) {
		ImmutableList<RMSNodeType> l = Lists.immutable.of(types);
		return this.getAllNodesInAST(n -> l.contains(n.getNodeType()));
	}
	
	/**
	 * check if at least one parents (excluded root) of this node has the given type 
	 * @param types
	 * @return
	 */
	public default boolean isUnderNodeWithTypes(RMSNodeType... types) {
		var l = Lists.immutable.of(types);
		return !this.getPathToRoot(false, false).select(n -> l.contains(n.getNodeType())).isEmpty();
	}
	
	/**
	 * get all the nodes underlying this node tree, as a java stream
	 * @return
	 */
	public default Stream<IRMSNode> nodeStream() {
		var result = this.getNodesSatisfyingCriterion((n) -> true);
		return StreamSupport.stream(result.spliterator(), false);
	}
	
	public default RichIterable<IRMSNode> nodeRichIterable() {
		return this.getNodesSatisfyingCriterion((n) -> true);
	}
	
	/**
	 * get all the nodes in the underlying tree with the given node type
	 * @param types node types that you want to include
	 * @return
	 */
	public default RichIterable<IRMSNode> getNodesOfTypes(RMSNodeType... types) {
		var s = Sets.immutable.of(types);
		return this.getNodesSatisfyingCriterion((n) -> s.contains(n.getNodeType()));
	}

	
	public default RichIterable<IRMSNode> getNodesSatisfyingCriterion(Predicate<IRMSNode> criterion) {
		MutableList<IRMSNode> result = Lists.mutable.empty();
		return this._getNodesSatisfyingCriterion(result, criterion);
	}
	
	private RichIterable<IRMSNode> _getNodesSatisfyingCriterion(MutableList<IRMSNode> result, Predicate<IRMSNode> criterion) {
		if (criterion.test(this)) {
			result.add(this);
		}
		for (var n: this.getChildren()) {
			n._getNodesSatisfyingCriterion(result, criterion);
		}
		return result;
	}
	
	/**
	 * fetches all the sibling of this node satisfying the criterion
	 * @param criterion criteiron to check
	 * @return iterable of all the sibling of this node satisfying the criterio or empty if the node has not such sibligns
	 */
	public default RichIterable<IRMSNode> getSiblingsSatisfying(Predicate<IRMSNode> criterion) {
		return this.getSiblings().select(n -> criterion.test(n));
	}
	
	/**
	 * fetches all the ordered sibling of this node
	 * @param criterion criteiron to check
	 * @return iterable of all the sibling of this node satisfying the criterio or empty if the node has not such sibligns. The roder is the same as the one present in the AST 
	 */
	public default RichIterable<IRMSNode> getSiblings() {
		if (this.isRoot()) {
			return Lists.immutable.empty();
		}
		var currentNode = this;
		return this.getParent().getChildren()
			.reject(n -> n.equals(currentNode))
			;
	}
	
	
	/**
	 * fetches all the siblings of the given type
	 * @param types allowed types
	 * @return
	 */
	public default RichIterable<IRMSNode> getSiblingOfTypes(RMSNodeType... types) {
		var s = Sets.immutable.of(types);
		return this.getSiblings().select(n -> s.contains(n.getNodeType()));
	}
	
	/**
	 * true if the node has at least one sibling satisfying the criterion
	 * @param criterion
	 * @return
	 */
	public default boolean hasAtLeastOneSiblingSatisfying(Predicate<IRMSNode> criterion) {
		return !this.getSiblingsSatisfying(criterion).isEmpty();
	}
	
	/**
	 * true if the node has at least one sibling with one of the given types
	 * @param types allowed type we need to check over
	 * @return
	 */
	public default boolean hasAtLeastOneSiblingOfTypes(RMSNodeType... types) {
		return this.hasAtLeastOneSiblingSatisfying(sibling -> Sets.immutable.of(types).contains(sibling.getNodeType()));
	}
	
	/**
	 * true if the node has at least one sibling with one of the given types
	 * @param types allowed type we need to check over
	 * @return
	 */
	public default boolean hasAtLeastOneSiblingOfTypes(RichIterable<RMSNodeType> types) {
		return this.hasAtLeastOneSiblingSatisfying(sibling -> types.contains(sibling.getNodeType()));
	}
	
	/**
	 * true if the node has no sibling that has even one of the expected types
	 * @param types
	 * @return true if the node has no siblings having even one of the given types
	 */
	public default boolean hasNoSiblingsOfTypes(RMSNodeType... types) {
		var s = Sets.immutable.of(types);
		return this.getSiblingsSatisfying(n -> s.contains(n.getNodeType())).isEmpty();
	}
	
	/**
	 * fetches all the ordered sibling of this node, alongside with the index each sibling occurs wihint the parent node 
	 * 
	 * @param criterion criterion to check
	 * @return iterable of all the sibling of this node satisfying the criterion or empty if the node has not such siblings. The order is the same as the one present in the AST 
	 */
	public default RichIterable<IntObjectPair<IRMSNode>> getOrderedSiblings() {
		if (this.isRoot()) {
			return Lists.immutable.empty();
		}
		var currentNode = this;
		return this.getParent().getChildren()
			.collectWithIndex((c, i) -> PrimitiveTuples.pair(i, c))
			.reject(pair -> pair.getTwo().equals(currentNode))
		;
	}
	
	/**
	 * fetches all the children of the parent of this node. This will yield this node as well 
	 * 
	 * @return
	 */
	public default RichIterable<IntObjectPair<IRMSNode>> getAllOrderedSiblings() {
		return this.getParent().getChildren()
		.collectWithIndex((c, i) -> PrimitiveTuples.pair(i, c))
		;
	}
	
	/**
	 * fetches all the ordered sibling of this node, alongside with the index each sibling occurs within the parent node.
	 * It automatically filters out siblings not satisfying the given criterion 
	 * 
	 * @param criterion criterion to check
	 * @return
	 */
	public default RichIterable<IntObjectPair<IRMSNode>> getOrderedSiblingsSatisfying(IntObjectPredicate<IRMSNode> criterion) {
		return this.getOrderedSiblings()
				.select((pair) -> criterion.accept(pair.getOne(), pair.getTwo()))
				;
	}
	
	/**
	 * fetches all the sibling occuring strictly after this node. This node is not included in the output
	 * @return
	 */
	public default RichIterable<IntObjectPair<IRMSNode>> getNextSiblings() {
		final AtomicBoolean found = new AtomicBoolean(false);
		return this.getAllOrderedSiblings()
				.select((pair) -> {
					var i = pair.getOne();
					var n = pair.getTwo();
					if (found.get()) {
						return true;
					} else if (this.equals(n)) {
						//found the current node. We will return the enxt ones
						found.set(true);
						return false;
					} else {
						//skipping the node, since we have not found "this" node yet
						return false;
					}
				})
				;
	}
	
	/**
	 * fetches all the siblings occuring after this node satisfying a certain criterion
	 *  
	 * @param criterion criterion to satisfy
	 * @return
	 */
	public default RichIterable<IntObjectPair<IRMSNode>> getNextSiblingSatisfying(IntObjectPredicate<IRMSNode> criterion) {
		return this.getNextSiblings().select(pair -> criterion.accept(pair.getOne(), pair.getTwo()));
	}
	
	
	
	/**
	 * Check if at least one sibling occuring after {@code this} is actually satisfying the given criterion
	 * 
	 * @param criterion criterion to satisfy
	 * @return true if at least one sibling after this one satisfies the criterion, false otherwise
	 */
	public default boolean hasAtLeastOneNextSiblingSatisfying(IntObjectPredicate<IRMSNode> criterion) {
		return !this.getNextSiblingSatisfying(criterion).isEmpty();
	}
	
	/**
	 * check if there is a sibling next to the given child has a given type.
	 * Previous siblings are not considered at all
	 * 
	 * @param types
	 * @return
	 */
	public default boolean hasAtLeastOneNextSiblingOfTypes(RMSNodeType... types) {
		var s = Sets.immutable.of(types);
		return this.hasAtLeastOneNextSiblingSatisfying((i, c) -> s.contains(c.getNodeType()));
	}
	
	/**
	 * fetches all the sibling occuring strictly before this node. This node is not included in the output
	 * @return
	 */
	public default RichIterable<IntObjectPair<IRMSNode>> getPreviousSiblings() {
		final AtomicBoolean found = new AtomicBoolean(false);
		return this.getAllOrderedSiblings()
				.select((pair) -> {
					var i = pair.getOne();
					var n = pair.getTwo();
					if (found.get()) {
						return false;
					} else if (this.equals(n)) {
						//found the current node. We will return the enxt ones
						found.set(true);
						return false;
					} else {
						//skipping the node, since we have not found "this" node yet
						return true;
					}
				})
				;
	}
	
	/**
	 * fetches all the siblings occuring before this node satisfying a certain criterion
	 *  
	 * @param criterion criterion to satisfy
	 * @return
	 */
	public default RichIterable<IntObjectPair<IRMSNode>> getPreviousSiblingSatisfying(IntObjectPredicate<IRMSNode> criterion) {
		return this.getPreviousSiblings().select(pair -> criterion.accept(pair.getOne(), pair.getTwo()));
	}
	
	
	
	/**
	 * Check if at least one sibling occuring previous {@code this} is actually satisfying the given criterion
	 * 
	 * @param criterion criterion to satisfy
	 * @return true if at least one sibling before this one satisfies the criterion, false otherwise
	 */
	public default boolean hasAtLeastOnePreviousSiblingSatisfying(IntObjectPredicate<IRMSNode> criterion) {
		return !this.getNextSiblingSatisfying(criterion).isEmpty();
	}
	
	/**
	 * check if there is a sibling previous to the given child has a given type.
	 * Previous siblings are not considered at all
	 * 
	 * @param types
	 * @return
	 */
	public default boolean hasAtLeastOnePreviousSiblingOfTypes(RMSNodeType... types) {
		var s = Sets.immutable.of(types);
		return this.hasAtLeastOnePreviousSiblingSatisfying((i, c) -> s.contains(c.getNodeType()));
	}
	
	
}
