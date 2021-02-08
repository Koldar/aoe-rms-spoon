package com.thekoldar.aoe_rms_spoon.framework.semantic_analysis;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.predicate.primitive.IntObjectPredicate;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractInclude;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractIncludeDrs;
import com.thekoldar.aoe_rms_spoon.ast.expr.IntExpr;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticWarningException;

/**
 * output of a semantic analysis on a node.
 * 
 * This class contains all the assertions used in the semantic analysis of each node (i.e., the one in {@link IRMSNode#semanticCheck(SemanticCheckInput)})
 * 
 * @author massi
 *
 */
public class SemanticCheckOutput {

	private static final Logger LOG = LoggerFactory.getLogger(SemanticCheckOutput.class);
	
	private MutableList<AbstractRMSException> errors;
	private MutableList<AbstractRMSException> warnings;
	private SemanticCheckInput input;
	
	public SemanticCheckOutput(SemanticCheckInput input) {
		this.input = input;
		this.errors = Lists.mutable.empty();
		this.warnings = Lists.mutable.empty();
	}
	
	/**
	 * log a debug entry
	 * 
	 * @param message message to log. Support slf4j syntax
	 * @param args slf4j emssage parameter
	 */
	public void debug(String message, Object... args) {
		LOG.debug(message, args);
	}
	
	/**
	 * log a info entry
	 * 
	 * @param message message to log. Support slf4j syntax
	 * @param args slf4j emssage parameter
	 */
	public void info(String message, Object... args) {
		LOG.info(message, args);
	}
	
	/**
	 * log a warnign entry
	 * 
	 * @param message message to log. Support slf4j syntax
	 * @param args slf4j emssage parameter
	 */
	public void warn(String message, Object... args) {
		LOG.warn(message, args);
	}
	
	/**
	 * log a error entry
	 * 
	 * @param message message to log. Support slf4j syntax
	 * @param args slf4j emssage parameter
	 */
	public void error(String message, Object... args) {
		LOG.error(message, args);
	}
	
	/**
	 * declare that the player with the given lobby order needs to be set
	 * @param playerLobbyOrder
	 */
	public SemanticCheckOutput declareThatPlayerWithLobbyOrderNeedsToBePlaying(int player) {
		this.input.declareThatPlayerWithLobbyOrderNeedsToBePlaying(player);
		return this;
	}
	
	/**
	 * declare that the player with the given color order needs to be set
	 * @param playerLobbyOrder
	 */
	public SemanticCheckOutput declareThatPlayerWithColorNeedsToBePlaying(int player) {
		this.input.declareThatPlayerWithColorNeedsToBePlaying(player);
		return this;
	}
	
	/**
	 * Merge the two semantic output in a safe manner
	 * @param other
	 * @return
	 */
	public SemanticCheckOutput merge(@Nullable SemanticCheckOutput other) {
		
		return this;
	}
	
	/**
	 * Ensure that all the nodes in the subtree rooted in <code>node</code> have a specific type
	 * 
	 * @param node root of the subtree
	 * @param expectedTypes expected types
	 * @throws AbstractRMSException
	 */
	public void ensureItContainsOnlyNodes(IRMSNode node, MutableSet<RMSNodeType> expectedTypes) throws AbstractRMSException {
		var actual = node.getAllSubTreeNodeTypes(false).toSet();
		var difference = actual.difference(expectedTypes);
		if (!difference.isEmpty()) {
			this.addError(RMSErrorCode.INVALID_NODE_LOCATION, "The commands %s cannot be under the command %s (either directly or indirectly)! Allowed nodes are %s", difference.makeString(), node, expectedTypes.makeString());
		}
	}
	
	/**
	 * Ensure that all the nodes in the subtree rooted in <code>node</code> have a specific type.
	 * Common nodes like {@link RMSNodeType#COMMENT} or {@link RMSNodeType#MULTIPLEXER} are automatically added 
	 * 
	 * @param node root of the subtree
	 * @param types expected types
	 * @throws AbstractRMSException
	 */
	public void ensureItContainsCommonNodesAnd(IRMSNode node, RMSNodeType ...types) throws AbstractRMSException {
		var s = Sets.mutable.of(RMSNodeType.COMMENT, RMSNodeType.INCLUDE, RMSNodeType.INCLUDE_DRS, RMSNodeType.DEFINE, RMSNodeType.IF, RMSNodeType.RANDOM, RMSNodeType.RANDOM_NUMBER, RMSNodeType.MULTIPLEXER, RMSNodeType.EXPR);
		s.addAll(Arrays.asList(types));
		this.ensureItContainsOnlyNodes(node, s);
	}
	
	/**
	 * ensure the given node is under (directly or indirectly) at least one of the given node types
	 * 
	 * @param node node to consider
	 * @param allowedParents allwoed parent types
	 * @throws AbstractRMSException
	 */
	public void ensureNodeToBeUnder(IRMSNode node, RMSNodeType... allowedParents) throws AbstractRMSException {
		var expected = Sets.immutable.of(allowedParents);
		var actual = node.getPathToRoot(false, false).collect(n -> n.getNodeType()).toSet();
				
		if (expected.intersect(actual).isEmpty()) {
			this.addError(RMSErrorCode.INVALID_NODE_LOCATION, "We expect node %s to be under either the following parents: %s. However the path found is %s", 
				node.getNodeType(),
				expected.makeString(", "),
				node.getPathToRoot().makeString(" -> ")
			);
		}
	
	}
	
	/**
	 * Ensure that the given node represents an integer literal.
	 * 
	 * This because some commands requires not an integer expression, but an interger literal (e.g., base_size)
	 *  
	 * @param command to check
	 * @param argumentNumber index of the argument to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentIsLiteralInteger(IRMSNode command, int argumentNumber) throws AbstractRMSException {
		this.ensureIsCommand(command);
		this.ensureArgumentIsLiteralInteger(((AbstractRMSCommand)command).getArgument(argumentNumber));
	}
	
	/**
	 * Ensure that the given node represents an integer literal.
	 * 
	 * This because some commands requires not an integer expression, but an interger literal (e.g., base_size)
	 * 
	 * @param argument argument to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentIsLiteralInteger(IRMSNode argument) throws AbstractRMSException {
		this.ensureIsExpression(argument);
		if (!(argument instanceof IntExpr )) {
			this.addError(argument, RMSErrorCode.INVALID_EXPR_TYPE, "Expected an integer literal for argument, got %s instead!", argument.getNodeType());
		}
	}

	/**
	 * Ensure that the direct children in this node have the specific types.
	 * if we find a children which does not have the given type, we raise an exception
	 * 
	 * @param node
	 * @param types
	 * @throws AbstractRMSException
	 */
	public void ensureDirectChildrenAreOnlyOf(IRMSNode node, RMSNodeType... types) throws AbstractRMSException {
		var s = Sets.immutable.of(types);
		if (!node.getDirectChildrenNotOfTypes(types).isEmpty()) {
			this.addError(RMSErrorCode.INVALID_EXPR_TYPE, "Inside node %s there can only be nodes of the following types: %s", node, s.makeString());
		}
	}
	
	/**
	 * ensure that the given node is root
	 * @param node
	 * @throws AbstractRMSException 
	 */
	public void ensureIsRoot(IRMSNode node) throws AbstractRMSException {
		if (!node.isRoot()) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.NODE_IS_NOT_ROOT, "Expected node %s to be root, but it was not!", node));
		}
	}
	
	/**
	 * ensure that the given node is <b>not</b> root
	 * @param node
	 * @throws AbstractRMSException 
	 */
	public void ensureIsNotRoot(IRMSNode node) throws AbstractRMSException {
		if (node.isRoot()) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.NODE_IS_ROOT, "Expected node %s not to be root, but it was!", node));
		}
	}
	
	/**
	 * ensure that the parent of the given node is root
	 * @param node
	 * @throws AbstractRMSException 
	 */
	public void ensureParentIsRoot(IRMSNode node) throws AbstractRMSException {
		this.ensureIsNotRoot(node);
		if (!node.getParent().isRoot()) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.NODE_SHOULD_BE_UNDER_ROOT, "Expected node %sto be under root, but it was not!", node));
		}
	}
	
	/**
	 * ensure that the parent of the given node is of a specific type
	 * @param node
	 * @throws AbstractRMSException 
	 */
	public void ensureParentIsOfType(IRMSNode node, RMSNodeType... expectedParentTypes) throws AbstractRMSException {
		var l = Lists.immutable.of(expectedParentTypes); 
		this.ensureIsNotRoot(node);
		if (!l.contains(node.getParent().getNodeType())) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.INVALID_PARENT_TYPE, "Expected parent of node %s (parent=%s) to be of type %s, but instead it was of type %s", node, node.getParent(), l.makeString(", "), node.getParent().getNodeType()));
		}
	}
	
	/**
	 * Detect if a type is specficied exactly once.
	 * If the type is specified twice along a tree path from a generic node to the root, it will be counted only once!
	 * 
	 * @param node node to investigate
	 * @param expectedType type we need to look for
	 * @throws AbstractRMSException 
	 */
	public void ensureNodeIsSpecifiedOnce(IRMSNode node, RMSNodeType expectedType) throws AbstractRMSException {
		var count = this.countNodeInTreeThat(node, (i, n) -> n.getNodeType().equals(expectedType));
		if (count != 1) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.DUPLICATE_TYPE, "Expected node %s to be specified exactly once, but it was specfied %d times instead!", expectedType, count));
		}
	}
	
	/**
	 * Detect if a type is specficied at most once.
	 * If the type is specified twice along a tree path from a generic node to the root, it will be counted only once!
	 * 
	 * @param node node to investigate
	 * @param expectedType type we need to look for
	 * @throws AbstractRMSException 
	 */
	public void ensureNodeIsSpecifiedAtMostOnce(IRMSNode node, RMSNodeType expectedType) throws AbstractRMSException {
		var count = this.countNodeInTreeThat(node, (i, n) -> n.getNodeType().equals(expectedType));
		if (count > 1) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.DUPLICATE_TYPE, "Expected node %s to be specified at most once, but it was specfied %d times instead!", expectedType, count));
		}
	}
	
	/**
	 * Detect if a type is specficied at most once. We will consider only direct children
	 * 
	 * @param node node to investigate
	 * @param expectedType type we need to look for
	 * @throws AbstractRMSException 
	 */
	public void ensureNodeIsDirectlySpecifiedAtMostOnce(IRMSNode node, RMSNodeType expectedType) throws AbstractRMSException {
		var count = node.getChildren().count(n -> n.getNodeType().equals(expectedType));
		if (count > 1) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.DUPLICATE_TYPE, "Expected node %s to be specified at most once, but it was specfied %d times instead!", expectedType, count));
		}
	}
	
	/**
	 * Detect if a type is specficied exactly once. We will consider only direct children
	 * 
	 * @param node node to investigate
	 * @param expectedType type we need to look for
	 * @throws AbstractRMSException 
	 */
	public void ensureNodeIsDirectlySpecifiedExactlyOnce(IRMSNode node, RMSNodeType expectedType) throws AbstractRMSException {
		var count = node.getChildren().count(n -> n.getNodeType().equals(expectedType));
		if (count != 1) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.DUPLICATE_TYPE, "Expected node %s to be specified exactly once, but it was specfied %d times instead!", expectedType, count));
		}
	}
	
	/**
	 * Detect if a type is specficied at least once. We will consider only direct children
	 * 
	 * @param node node to investigate
	 * @param expectedType type we need to look for
	 * @throws AbstractRMSException 
	 */
	public void ensureNodeIsDirectlySpecifiedAtLeastOnce(IRMSNode node, RMSNodeType expectedType) throws AbstractRMSException {
		var count = node.getChildren().count(n -> n.getNodeType().equals(expectedType));
		if (count == 0) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.NODE_NOT_FOUND, "Expected node %s to be specified at least once, but it was specfied 0 times instead!", expectedType, count));
		}
	}
	
	/**
	 * Detect if a type is specficied at least once.
	 * If the type is specified twice along a tree path from a generic node to the root, it will be counted only once!
	 * 
	 * @param node node to investigate
	 * @param expectedType type we need to look for
	 * @throws AbstractRMSException
	 */
	public void ensureNodeIsSpecifiedAtLeastOnce(IRMSNode node, RMSNodeType expectedType) throws AbstractRMSException {
		var count = this.countNodeInTreeThat(node, (i, n) -> n.getNodeType().equals(expectedType));
		if (count > 1) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.NODE_NOT_FOUND, "Expected node %s to be specified at least once, but it was never specfied!", expectedType));
		}
	}
	
	public void ensureIncludedFileIsInSection(IRMSNode node, String file, String note) throws AbstractRMSException {
		if (!this.assertWeHaveIncludedFile(node, file)) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.REQUIRED_INCLUDE_NOT_INCLUDED, "Expected to have included the file %s, but you did not! %s", file, note));
		}
	}
	
	
	/**
	 * Ensure the current node has exactly n children
	 * 
	 * @param expectedChildren expected number of children
	 * @return
	 * @throws RMSSemanticErrorException 
	 */
	public void ensureNChildren(IRMSNode node, int expectedChildren) throws AbstractRMSException {
		if (node.getChildren().size() != expectedChildren) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.WRONG_CHILDREN_NUMBER, "Expected %d children for node %s, but got %d!", expectedChildren, node.getChildren().size(), this));
		}
	}
	
	/**
	 * Ensure you have defined the given directive
	 * @param define
	 * @throws AbstractRMSException 
	 */
	public void ensureIsDefined(String define, String note) throws AbstractRMSException {
		if (!this.input.isDefined(define)) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.DEFINE_IS_NOT_DEFINED, "Expected to have defined the required define %s, but you did not! Note: %s", define, note));
		}
	}
	
	public void ensureIsDefined(String define) throws AbstractRMSException {
		this.ensureIsDefined(define, "");
	}
	
	/**
	 * Ensure you have <b>not</b> defined the given directive
	 * @param define define name to check
	 * @throws AbstractRMSException
	 */
	public void ensureIsNotDefined(String define) throws AbstractRMSException {
		if (this.input.isDefined(define)) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.DEFINE_IS_DEFINED, "Expected to have undefined %s, but you defined it!"));
		}
	}
	
	/**
	 * Ensure you have included in the tree rooted in node the given file.
	 * @param node
	 * @param file
	 * @throws AbstractRMSException
	 */
	public void ensureWeHaveIncludedFile(IRMSNode node, String file, String note) throws AbstractRMSException {
		if (!this.assertWeHaveIncludedFile(node, file)) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.REQUIRED_INCLUDE_NOT_INCLUDED, "Expected to have included the file %s, but you did not! %s", file, note));
		}
	}
	
	public void ensureWeHaveIncludedFile(IRMSNode node, String file) throws AbstractRMSException {
		ensureWeHaveIncludedFile(node, file, "");
	}
	
	/**
	 * Ensure the given node is actuall a command
	 * 
	 * @param node node to check
	 */
	public void ensureIsCommand(IRMSNode node) throws AbstractRMSException {
		if (!(node.getNodeType().isCommand())) {
			this.add(new RMSSemanticErrorException(RMSErrorCode.NOT_A_COMMAND, "Expected node %s to be a command, but it was not", node.getNodeType()));
		}
	}
	
	/**
	 * Ensure the given node is actually an expression
	 * 
	 * @param node node to check
	 */
	public void ensureIsExpression(IRMSNode node) throws AbstractRMSException {
		if (!(node.getNodeType().isExpression())) {
			this.add(new RMSSemanticErrorException(node, RMSErrorCode.NOT_A_EXPR, "Expected node %s to be an expression, but it was not", node.getNodeType()));
		}
	}
	
	/**
	 * Ensure that an int argument is among the one the user has given
	 * 
	 * @param arg node representing the actual argument
	 * @param argIndex indexo f the argument
	 * @param possibleValues allowed values
	 * @throws AbstractRMSException
	 */
	public void ensureIntArgumentIsOneOf(IRMSNode arg, int argIndex, int... possibleValues) throws AbstractRMSException {
		this.ensureIsExpression(arg);
		var actualvalue = ((AbstractExpressionNode)arg).getAsInt(this.input);
		var s = Sets.immutable.of(possibleValues);
		if (!s.contains(actualvalue)) {
			this.addError(RMSErrorCode.INVALID_ARGUMENT, "%d-th arg %s cannot be set to value %d. Only %s are allowed", argIndex, arg, actualvalue, s.makeString());
		}
	}
	
	/**
	 * Ensure that the given node has at least one sibling of tjhe given type. If note, raises an exception with the additional note
	 * 
	 * @param node node whose siblings we need to check
	 * @param types allowed sibling types
	 * @param note additional note
	 * @throws AbstractRMSException
	 */
	public void ensureThereIsAtLeastOneSiblingOfType(IRMSNode node, RichIterable<RMSNodeType> types, String note) throws AbstractRMSException {
		if (!node.hasAtLeastOneSiblingOfTypes(types)) {
			this.addError(RMSErrorCode.EXPECTED_REQUIRED_COMMAND, "Detect %s not to be present with at least one of the following commands: %s. Note: %s", node, types.makeString(), note);
		}
	}
	
	/**
	 * ensure that in every branch of the rms there is a node of the following type
	 * @param n
	 * @param type
	 */
	public void ensureContainsNodeWithType(IRMSNode n, RMSNodeType type) {
		
	}
	
	/**
	 * Ensure the parent of the given node has no other node of the same type as the one given as input
	 * 
	 *  @param node the node whose sigling we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureThereAreNoSiblingOfTheSameType(IRMSNode node) throws AbstractRMSException {
		if (node.hasAtLeastOneSiblingOfTypes(node.getNodeType())) {
			this.addError(node, RMSErrorCode.DUPLICATE_TYPE, "Detect that %s has two entries of type %s", node.getParent().getNodeType(), node.getNodeType());
		}
	}
	
	/**
	 * Ensure the parent of the given node has no other node of the same type as the one given as input
	 * 
	 *  @param node the node whose sigling we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureThereAreNoSiblingOfTheSameTypes(IRMSNode node, RMSNodeType... types) throws AbstractRMSException {
		if (node.hasAtLeastOneSiblingOfTypes(types)) {
			var s = Sets.immutable.of(types);
			this.addError(RMSErrorCode.CONFLICTING_COMMANDS, "%s should not go without siblings %s", node, s.makeString());
		}
	}
	
	/**
	 * Ensure the parent of the given node has no other node of the same type as the one given as input
	 * 
	 *  @param node the node whose sigling we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureThereAreNoSiblingOfTheSameTypes(IRMSNode node, RichIterable<RMSNodeType> types, String note) throws AbstractRMSException {
		if (node.hasAtLeastOneSiblingOfTypes(types)) {
			this.addError(RMSErrorCode.CONFLICTING_COMMANDS, "%s should not go without siblings %s. Note: %s", node, types.makeString(), note);
		}
	}
	
	/**
	 * convenience method telling that the argument should be between 0 and 100, both extermis included
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentIsPercentage(IRMSNode node, int argumentIndex) throws AbstractRMSException {
		this.ensureArgumentIsBetween(node, argumentIndex, 0, 100, true, true);
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentIsBetween(IRMSNode node, int argumentIndex, int lowbound, int upperbound, boolean lowIncluded, boolean upperIncluded) throws AbstractRMSException {
		var arg = (AbstractExpressionNode)node;
		var n = arg.getAsInt(this.input);
		if ((n == lowbound) && (lowIncluded)) {
			return;
		}
		if ((n == upperbound) && (upperIncluded)) {
			return;
		}
		if (n > lowbound && n < upperbound) {
			return;
		}
		this.add(new RMSSemanticErrorException(RMSErrorCode.INVALID_RANGE, "node %s argument %d needs to be between %d and %d (%d %s and %d %s)", arg.getNodeType(), argumentIndex, lowbound, upperbound, lowbound, lowIncluded ? "included" : "excluded", upperbound, upperIncluded ? "included" : "excluded"));
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentGreaterThan(IRMSNode node, int argumentIndex, int value) throws AbstractRMSException {
		var arg = (AbstractExpressionNode)node;
		var n = arg.getAsInt(this.input);
		if (n > value) {
			return;
		}
		this.add(new RMSSemanticErrorException(RMSErrorCode.GREATER_THAN, "node %s argument %d needs to be greater than %d", arg.getNodeType(), argumentIndex, value));
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentGreaterThan0(IRMSNode node, int argumentIndex) throws AbstractRMSException {
		this.ensureArgumentGreaterThan(node, argumentIndex, 0);
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentGreaterThanOrEqual(IRMSNode node, int argumentIndex, int value) throws AbstractRMSException {
		var arg = (AbstractExpressionNode)node;
		var n = arg.getAsInt(this.input);
		if (n >= value) {
			return;
		}
		this.add(new RMSSemanticErrorException(RMSErrorCode.GREATER_OR_EQUAL_THAN, "node %s argument %d needs to be greater than or equal to %d", arg.getNodeType(), argumentIndex, value));
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentGreaterThanOrEqual0(IRMSNode node, int argumentIndex) throws AbstractRMSException {
		this.ensureArgumentGreaterThanOrEqual(node, argumentIndex, 0);
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentLessThan(IRMSNode node, int argumentIndex, int value) throws AbstractRMSException {
		var arg = (AbstractExpressionNode)node;
		var n = arg.getAsInt(this.input);
		if (n < value) {
			return;
		}
		this.add(new RMSSemanticErrorException(RMSErrorCode.LESS_THAN, "node %s argument %d needs to be less than %d", arg.getNodeType(), argumentIndex, value));
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentLessThan0(IRMSNode node, int argumentIndex) throws AbstractRMSException {
		this.ensureArgumentLessThan(node, argumentIndex, 0);
	}
	
	/**
	 * Ensure the given argument at index is a number that satisfies the range.
	 * 
	 * @param argumentIndex index of the argument we need to check
	 * @throws AbstractRMSException 
	 */
	public void ensureArgumentLessThanOrEqual(IRMSNode node, int argumentIndex, int value) throws AbstractRMSException {
		var arg = (AbstractExpressionNode)node;
		var n = arg.getAsInt(this.input);
		if (n <= value) {
			return;
		}
		this.add(new RMSSemanticErrorException(RMSErrorCode.LESS_OR_EQUAL_THAN, "node %s argument %d needs to be less or equal to %d", arg.getNodeType(), argumentIndex, value));
	}
	
	public void ensureArgumentLessThanOrEqual0(IRMSNode node, int argumentIndex, int value) throws AbstractRMSException {
		this.ensureArgumentLessThanOrEqual(node, argumentIndex, 0);
	}
	
	protected boolean assertWeHaveIncludedFile(IRMSNode node, String file) {
		
		var nodes = node.getNodesOfTypes(RMSNodeType.INCLUDE, RMSNodeType.INCLUDE_DRS);
		for (var n : nodes) {
			switch (n.getNodeType()) {
				case INCLUDE: {
					if ((((AbstractInclude)n).getIncludedFile()).equals(file)) {
						return true;
					}
					break;
				}
				case INCLUDE_DRS: {
					if ((((AbstractIncludeDrs)n).getIncludedFile()).equals(file)) {
						return true;
					}
					break;
				}
				default:
					throw new IllegalArgumentException();
			}
		}
		return false;
	}
	
	/**
	 * Ensure that there is at least one node in the underlying tree with the given node type
	 * @param expectedType the expected type
	 * @return
	 */
	protected boolean ensureThereIsAtLeastOneNodeInTreeOfTypes(IRMSNode node, RMSNodeType... expectedTypes) {
		return this.assertThereIsAtLeastOneNodeInTreeThat(node, (n) -> {
			return Sets.immutable.of(expectedTypes).contains(n);
		});
	}
	
	/**
	 * Ensure that there is at least one node in the underlying tree with the given node type
	 * @param expectedType the expected type
	 * @return
	 */
	protected boolean ensureThereIsExactlyOneNodeInTreeOfTypes(IRMSNode node, RMSNodeType... expectedTypes) {
		return this.assertThereIsExactlyOneNodeInTreeThat(node, (n) -> {
			return Sets.immutable.of(expectedTypes).contains(n);
		});
	}
	
	/**
	 * Ensure that exactly one node in the underlying tree of the given node satisfies the condition
	 * 
	 * @param node root of the AST RMS tree to verify
	 * @param condition condition to be satisfies
	 * @return
	 */
	protected boolean assertThereIsExactlyOneNodeInTreeThat(IRMSNode node, Predicate<IRMSNode> condition) {
		var l = node.getNodesSatisfyingCriterion(condition).toList();
		if (l.size() == 0) {
			return false;
		}
		if (l.size() > 1) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Ensure that at least one node in the underlying tree of the given node satisfies the condition
	 * 
	 * @param node root of the AST RMS tree to verify
	 * @param condition condition to be satisfies
	 * @return
	 */
	protected boolean assertThereIsAtLeastOneNodeInTreeThat(IRMSNode node, Predicate<IRMSNode> condition) {
		return this._assertThereIsAtLeastOneNodeInTreeThat(node, condition);
	}
	
	private boolean _assertThereIsAtLeastOneNodeInTreeThat(IRMSNode node, Predicate<IRMSNode> condition) {
		if (condition.test(node)) {
			return true;
		}
		for (var c : node.getChildren()) {
			if (this._assertThereIsAtLeastOneNodeInTreeThat(c, condition)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Count how many nodes satisfiesthe given predicate. Whenever a node satisfies the condition, its subtree is <b>not visisted</b>
	 * 
	 * @param node root of the AST RMS tree to verify
	 * @param condition condition to be satisfies. The condition specified the number of nodes has satisfied the condition up until this point.
	 * @return
	 */
	protected int countNodeInTreeThat(IRMSNode node, IntObjectPredicate<IRMSNode> condition) {
		return this._countNodeInTreeThat(node, 0, condition);
	}
	
	private int _countNodeInTreeThat(IRMSNode node, int found, IntObjectPredicate<IRMSNode> condition) {
		if (condition.accept(found, node)) {
			return 1;
		}
		for (var c : node.getChildren()) {
			found += this._countNodeInTreeThat(c, found, condition);
		}
		return found;
	}
	
	
	/**
	 * check if the code is correct
	 * @return
	 */
	public boolean isCorrect() {
		return this.errors.isEmpty();
	}
	
	public Optional<AbstractRMSException> getFirstError() {
		return this.errors.getFirstOptional();
	}
	
	public ImmutableList<AbstractRMSException> getWarnings() {
		return this.warnings.toImmutable();
	}
	
	public void add(AbstractRMSException error) throws AbstractRMSException {
		
		if (this.input.shouldBeTreatedAsError(error.getErrorCode())) {
			this.errors.add(error);	
			throw error;
		}
		else if (this.input.shouldBeratedAsWarning(error.getErrorCode())) {
			this.warnings.add(error);
		} else {
			// treated using the defalt
			if (error.getErrorCode().isNormallyError()) {
				this.errors.add(error);	
				throw error;	
			} else {
				this.warnings.add(error);
			}
		}
		
	}
	
	public void addWarning(RMSErrorCode code, String message, Object... args) throws AbstractRMSException {
		this.add(new RMSSemanticWarningException(code, message, args));
	}
	
	public void addWarning(IRMSNode n, RMSErrorCode code, String message, Object... args) throws AbstractRMSException {
		this.add(new RMSSemanticWarningException(n, code, message, args));
	}
	
	public void addError(RMSErrorCode code, String message, Object... args) throws AbstractRMSException {
		this.add(new RMSSemanticErrorException(code, message, args));
	}
	
	public void addError(IRMSNode node, RMSErrorCode code, String message, Object... args) throws AbstractRMSException {
		this.add(new RMSSemanticErrorException(node, code, message, args));
	}
}
