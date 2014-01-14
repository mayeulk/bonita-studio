/*
* generated by Xtext
*/

package org.bonitasoft.studio.condition.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import java.util.List;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class ConditionModelGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class Operation_CompareElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Compare");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_CompareAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cOpAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final Alternatives cOpAlternatives_1_0 = (Alternatives)cOpAssignment_1.eContents().get(0);
		private final RuleCall cOpOperationParserRuleCall_1_0_0 = (RuleCall)cOpAlternatives_1_0.eContents().get(0);
		private final RuleCall cOpUnary_OperationParserRuleCall_1_0_1 = (RuleCall)cOpAlternatives_1_0.eContents().get(1);
		
		//Operation_Compare:
		//	{Operation_Compare} op=(Operation | Unary_Operation);
		public ParserRule getRule() { return rule; }

		//{Operation_Compare} op=(Operation | Unary_Operation)
		public Group getGroup() { return cGroup; }

		//{Operation_Compare}
		public Action getOperation_CompareAction_0() { return cOperation_CompareAction_0; }

		//op=(Operation | Unary_Operation)
		public Assignment getOpAssignment_1() { return cOpAssignment_1; }

		//Operation | Unary_Operation
		public Alternatives getOpAlternatives_1_0() { return cOpAlternatives_1_0; }

		//Operation
		public RuleCall getOpOperationParserRuleCall_1_0_0() { return cOpOperationParserRuleCall_1_0_0; }

		//Unary_Operation
		public RuleCall getOpUnary_OperationParserRuleCall_1_0_1() { return cOpUnary_OperationParserRuleCall_1_0_1; }
	}

	public class Unary_OperationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Unary_Operation");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cOperation_UnaryParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cOperation_NotUnaryParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//Unary_Operation:
		//	Operation_Unary | Operation_NotUnary;
		public ParserRule getRule() { return rule; }

		//Operation_Unary | Operation_NotUnary
		public Alternatives getAlternatives() { return cAlternatives; }

		//Operation_Unary
		public RuleCall getOperation_UnaryParserRuleCall_0() { return cOperation_UnaryParserRuleCall_0; }

		//Operation_NotUnary
		public RuleCall getOperation_NotUnaryParserRuleCall_1() { return cOperation_NotUnaryParserRuleCall_1; }
	}

	public class OperationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cOperation_Less_EqualsParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cOperation_LessParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cOperation_Greater_EqualsParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cOperation_GreaterParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cOperation_Not_EqualsParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cOperation_EqualsParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		
		//Operation:
		//	Operation_Less_Equals | Operation_Less | Operation_Greater_Equals | Operation_Greater | Operation_Not_Equals |
		//	Operation_Equals;
		public ParserRule getRule() { return rule; }

		//Operation_Less_Equals | Operation_Less | Operation_Greater_Equals | Operation_Greater | Operation_Not_Equals |
		//Operation_Equals
		public Alternatives getAlternatives() { return cAlternatives; }

		//Operation_Less_Equals
		public RuleCall getOperation_Less_EqualsParserRuleCall_0() { return cOperation_Less_EqualsParserRuleCall_0; }

		//Operation_Less
		public RuleCall getOperation_LessParserRuleCall_1() { return cOperation_LessParserRuleCall_1; }

		//Operation_Greater_Equals
		public RuleCall getOperation_Greater_EqualsParserRuleCall_2() { return cOperation_Greater_EqualsParserRuleCall_2; }

		//Operation_Greater
		public RuleCall getOperation_GreaterParserRuleCall_3() { return cOperation_GreaterParserRuleCall_3; }

		//Operation_Not_Equals
		public RuleCall getOperation_Not_EqualsParserRuleCall_4() { return cOperation_Not_EqualsParserRuleCall_4; }

		//Operation_Equals
		public RuleCall getOperation_EqualsParserRuleCall_5() { return cOperation_EqualsParserRuleCall_5; }
	}

	public class Operation_Less_EqualsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Less_Equals");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_Less_EqualsAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cLeftAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLeftExpression_TerminalParserRuleCall_1_0 = (RuleCall)cLeftAssignment_1.eContents().get(0);
		private final Keyword cLessThanSignEqualsSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightExpression_TerminalParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		
		//Operation_Less_Equals returns Operation:
		//	{Operation_Less_Equals} left=Expression_Terminal "<=" right=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_Less_Equals} left=Expression_Terminal "<=" right=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_Less_Equals}
		public Action getOperation_Less_EqualsAction_0() { return cOperation_Less_EqualsAction_0; }

		//left=Expression_Terminal
		public Assignment getLeftAssignment_1() { return cLeftAssignment_1; }

		//Expression_Terminal
		public RuleCall getLeftExpression_TerminalParserRuleCall_1_0() { return cLeftExpression_TerminalParserRuleCall_1_0; }

		//"<="
		public Keyword getLessThanSignEqualsSignKeyword_2() { return cLessThanSignEqualsSignKeyword_2; }

		//right=Expression_Terminal
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }

		//Expression_Terminal
		public RuleCall getRightExpression_TerminalParserRuleCall_3_0() { return cRightExpression_TerminalParserRuleCall_3_0; }
	}

	public class Operation_LessElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Less");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_LessAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cLeftAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLeftExpression_TerminalParserRuleCall_1_0 = (RuleCall)cLeftAssignment_1.eContents().get(0);
		private final Keyword cLessThanSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightExpression_TerminalParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		
		//Operation_Less returns Operation:
		//	{Operation_Less} left=Expression_Terminal "<" right=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_Less} left=Expression_Terminal "<" right=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_Less}
		public Action getOperation_LessAction_0() { return cOperation_LessAction_0; }

		//left=Expression_Terminal
		public Assignment getLeftAssignment_1() { return cLeftAssignment_1; }

		//Expression_Terminal
		public RuleCall getLeftExpression_TerminalParserRuleCall_1_0() { return cLeftExpression_TerminalParserRuleCall_1_0; }

		//"<"
		public Keyword getLessThanSignKeyword_2() { return cLessThanSignKeyword_2; }

		//right=Expression_Terminal
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }

		//Expression_Terminal
		public RuleCall getRightExpression_TerminalParserRuleCall_3_0() { return cRightExpression_TerminalParserRuleCall_3_0; }
	}

	public class Operation_Greater_EqualsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Greater_Equals");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_Greater_EqualsAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cLeftAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLeftExpression_TerminalParserRuleCall_1_0 = (RuleCall)cLeftAssignment_1.eContents().get(0);
		private final Keyword cGreaterThanSignEqualsSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightExpression_TerminalParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		
		//Operation_Greater_Equals returns Operation:
		//	{Operation_Greater_Equals} left=Expression_Terminal ">=" right=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_Greater_Equals} left=Expression_Terminal ">=" right=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_Greater_Equals}
		public Action getOperation_Greater_EqualsAction_0() { return cOperation_Greater_EqualsAction_0; }

		//left=Expression_Terminal
		public Assignment getLeftAssignment_1() { return cLeftAssignment_1; }

		//Expression_Terminal
		public RuleCall getLeftExpression_TerminalParserRuleCall_1_0() { return cLeftExpression_TerminalParserRuleCall_1_0; }

		//">="
		public Keyword getGreaterThanSignEqualsSignKeyword_2() { return cGreaterThanSignEqualsSignKeyword_2; }

		//right=Expression_Terminal
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }

		//Expression_Terminal
		public RuleCall getRightExpression_TerminalParserRuleCall_3_0() { return cRightExpression_TerminalParserRuleCall_3_0; }
	}

	public class Operation_GreaterElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Greater");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_GreaterAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cLeftAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLeftExpression_TerminalParserRuleCall_1_0 = (RuleCall)cLeftAssignment_1.eContents().get(0);
		private final Keyword cGreaterThanSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightExpression_TerminalParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		
		//Operation_Greater returns Operation:
		//	{Operation_Greater} left=Expression_Terminal ">" right=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_Greater} left=Expression_Terminal ">" right=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_Greater}
		public Action getOperation_GreaterAction_0() { return cOperation_GreaterAction_0; }

		//left=Expression_Terminal
		public Assignment getLeftAssignment_1() { return cLeftAssignment_1; }

		//Expression_Terminal
		public RuleCall getLeftExpression_TerminalParserRuleCall_1_0() { return cLeftExpression_TerminalParserRuleCall_1_0; }

		//">"
		public Keyword getGreaterThanSignKeyword_2() { return cGreaterThanSignKeyword_2; }

		//right=Expression_Terminal
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }

		//Expression_Terminal
		public RuleCall getRightExpression_TerminalParserRuleCall_3_0() { return cRightExpression_TerminalParserRuleCall_3_0; }
	}

	public class Operation_Not_EqualsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Not_Equals");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_Not_EqualsAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cLeftAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLeftExpression_TerminalParserRuleCall_1_0 = (RuleCall)cLeftAssignment_1.eContents().get(0);
		private final Keyword cExclamationMarkEqualsSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightExpression_TerminalParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		
		//Operation_Not_Equals returns Operation:
		//	{Operation_Not_Equals} left=Expression_Terminal "!=" right=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_Not_Equals} left=Expression_Terminal "!=" right=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_Not_Equals}
		public Action getOperation_Not_EqualsAction_0() { return cOperation_Not_EqualsAction_0; }

		//left=Expression_Terminal
		public Assignment getLeftAssignment_1() { return cLeftAssignment_1; }

		//Expression_Terminal
		public RuleCall getLeftExpression_TerminalParserRuleCall_1_0() { return cLeftExpression_TerminalParserRuleCall_1_0; }

		//"!="
		public Keyword getExclamationMarkEqualsSignKeyword_2() { return cExclamationMarkEqualsSignKeyword_2; }

		//right=Expression_Terminal
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }

		//Expression_Terminal
		public RuleCall getRightExpression_TerminalParserRuleCall_3_0() { return cRightExpression_TerminalParserRuleCall_3_0; }
	}

	public class Operation_EqualsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Equals");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_EqualsAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cLeftAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLeftExpression_TerminalParserRuleCall_1_0 = (RuleCall)cLeftAssignment_1.eContents().get(0);
		private final Keyword cEqualsSignEqualsSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cRightAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRightExpression_TerminalParserRuleCall_3_0 = (RuleCall)cRightAssignment_3.eContents().get(0);
		
		//Operation_Equals returns Operation:
		//	{Operation_Equals} left=Expression_Terminal "==" right=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_Equals} left=Expression_Terminal "==" right=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_Equals}
		public Action getOperation_EqualsAction_0() { return cOperation_EqualsAction_0; }

		//left=Expression_Terminal
		public Assignment getLeftAssignment_1() { return cLeftAssignment_1; }

		//Expression_Terminal
		public RuleCall getLeftExpression_TerminalParserRuleCall_1_0() { return cLeftExpression_TerminalParserRuleCall_1_0; }

		//"=="
		public Keyword getEqualsSignEqualsSignKeyword_2() { return cEqualsSignEqualsSignKeyword_2; }

		//right=Expression_Terminal
		public Assignment getRightAssignment_3() { return cRightAssignment_3; }

		//Expression_Terminal
		public RuleCall getRightExpression_TerminalParserRuleCall_3_0() { return cRightExpression_TerminalParserRuleCall_3_0; }
	}

	public class Operation_UnaryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_Unary");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_UnaryAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueExpression_TerminalParserRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//Operation_Unary returns Unary_Operation:
		//	{Operation_Unary} value=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_Unary} value=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_Unary}
		public Action getOperation_UnaryAction_0() { return cOperation_UnaryAction_0; }

		//value=Expression_Terminal
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }

		//Expression_Terminal
		public RuleCall getValueExpression_TerminalParserRuleCall_1_0() { return cValueExpression_TerminalParserRuleCall_1_0; }
	}

	public class Operation_NotUnaryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Operation_NotUnary");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cOperation_NotUnaryAction_0 = (Action)cGroup.eContents().get(0);
		private final Keyword cExclamationMarkKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueExpression_TerminalParserRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		
		//Operation_NotUnary returns Unary_Operation:
		//	{Operation_NotUnary} "!" value=Expression_Terminal;
		public ParserRule getRule() { return rule; }

		//{Operation_NotUnary} "!" value=Expression_Terminal
		public Group getGroup() { return cGroup; }

		//{Operation_NotUnary}
		public Action getOperation_NotUnaryAction_0() { return cOperation_NotUnaryAction_0; }

		//"!"
		public Keyword getExclamationMarkKeyword_1() { return cExclamationMarkKeyword_1; }

		//value=Expression_Terminal
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }

		//Expression_Terminal
		public RuleCall getValueExpression_TerminalParserRuleCall_2_0() { return cValueExpression_TerminalParserRuleCall_2_0; }
	}

	public class Expression_TerminalElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Expression_Terminal");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cExpression_DoubleParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cExpression_IntegerParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cExpression_BooleanParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cExpression_StringParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cExpression_ProcessRefParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		
		//Expression_Terminal returns Expression:
		//	Expression_Double | Expression_Integer | Expression_Boolean | Expression_String | Expression_ProcessRef;
		public ParserRule getRule() { return rule; }

		//Expression_Double | Expression_Integer | Expression_Boolean | Expression_String | Expression_ProcessRef
		public Alternatives getAlternatives() { return cAlternatives; }

		//Expression_Double
		public RuleCall getExpression_DoubleParserRuleCall_0() { return cExpression_DoubleParserRuleCall_0; }

		//Expression_Integer
		public RuleCall getExpression_IntegerParserRuleCall_1() { return cExpression_IntegerParserRuleCall_1; }

		//Expression_Boolean
		public RuleCall getExpression_BooleanParserRuleCall_2() { return cExpression_BooleanParserRuleCall_2; }

		//Expression_String
		public RuleCall getExpression_StringParserRuleCall_3() { return cExpression_StringParserRuleCall_3; }

		//Expression_ProcessRef
		public RuleCall getExpression_ProcessRefParserRuleCall_4() { return cExpression_ProcessRefParserRuleCall_4; }
	}

	public class Expression_DoubleElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Expression_Double");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExpression_DoubleAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueDOUBLETerminalRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//Expression_Double:
		//	{Expression_Double} value=DOUBLE;
		public ParserRule getRule() { return rule; }

		//{Expression_Double} value=DOUBLE
		public Group getGroup() { return cGroup; }

		//{Expression_Double}
		public Action getExpression_DoubleAction_0() { return cExpression_DoubleAction_0; }

		//value=DOUBLE
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }

		//DOUBLE
		public RuleCall getValueDOUBLETerminalRuleCall_1_0() { return cValueDOUBLETerminalRuleCall_1_0; }
	}

	public class Expression_IntegerElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Expression_Integer");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExpression_IntegerAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueLONGTerminalRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//Expression_Integer:
		//	{Expression_Integer} value=LONG;
		public ParserRule getRule() { return rule; }

		//{Expression_Integer} value=LONG
		public Group getGroup() { return cGroup; }

		//{Expression_Integer}
		public Action getExpression_IntegerAction_0() { return cExpression_IntegerAction_0; }

		//value=LONG
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }

		//LONG
		public RuleCall getValueLONGTerminalRuleCall_1_0() { return cValueLONGTerminalRuleCall_1_0; }
	}

	public class Expression_StringElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Expression_String");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExpression_StringAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueSTRINGTerminalRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//Expression_String:
		//	{Expression_String} value=STRING;
		public ParserRule getRule() { return rule; }

		//{Expression_String} value=STRING
		public Group getGroup() { return cGroup; }

		//{Expression_String}
		public Action getExpression_StringAction_0() { return cExpression_StringAction_0; }

		//value=STRING
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }

		//STRING
		public RuleCall getValueSTRINGTerminalRuleCall_1_0() { return cValueSTRINGTerminalRuleCall_1_0; }
	}

	public class Expression_ProcessRefElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Expression_ProcessRef");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExpression_ProcessRefAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cValueEObjectCrossReference_1_0 = (CrossReference)cValueAssignment_1.eContents().get(0);
		private final RuleCall cValueEObjectIDTerminalRuleCall_1_0_1 = (RuleCall)cValueEObjectCrossReference_1_0.eContents().get(1);
		
		//Expression_ProcessRef hidden(WS):
		//	{Expression_ProcessRef} value=[ecore::EObject];
		public ParserRule getRule() { return rule; }

		//{Expression_ProcessRef} value=[ecore::EObject]
		public Group getGroup() { return cGroup; }

		//{Expression_ProcessRef}
		public Action getExpression_ProcessRefAction_0() { return cExpression_ProcessRefAction_0; }

		//value=[ecore::EObject]
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }

		//[ecore::EObject]
		public CrossReference getValueEObjectCrossReference_1_0() { return cValueEObjectCrossReference_1_0; }

		//ID
		public RuleCall getValueEObjectIDTerminalRuleCall_1_0_1() { return cValueEObjectIDTerminalRuleCall_1_0_1; }
	}

	public class Expression_BooleanElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Expression_Boolean");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Action cExpression_BooleanAction_0 = (Action)cGroup.eContents().get(0);
		private final Assignment cValueAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cValueBOOLEANTerminalRuleCall_1_0 = (RuleCall)cValueAssignment_1.eContents().get(0);
		
		//Expression_Boolean:
		//	{Expression_Boolean} value=BOOLEAN;
		public ParserRule getRule() { return rule; }

		//{Expression_Boolean} value=BOOLEAN
		public Group getGroup() { return cGroup; }

		//{Expression_Boolean}
		public Action getExpression_BooleanAction_0() { return cExpression_BooleanAction_0; }

		//value=BOOLEAN
		public Assignment getValueAssignment_1() { return cValueAssignment_1; }

		//BOOLEAN
		public RuleCall getValueBOOLEANTerminalRuleCall_1_0() { return cValueBOOLEANTerminalRuleCall_1_0; }
	}
	
	
	private Operation_CompareElements pOperation_Compare;
	private Unary_OperationElements pUnary_Operation;
	private OperationElements pOperation;
	private Operation_Less_EqualsElements pOperation_Less_Equals;
	private Operation_LessElements pOperation_Less;
	private Operation_Greater_EqualsElements pOperation_Greater_Equals;
	private Operation_GreaterElements pOperation_Greater;
	private Operation_Not_EqualsElements pOperation_Not_Equals;
	private Operation_EqualsElements pOperation_Equals;
	private Operation_UnaryElements pOperation_Unary;
	private Operation_NotUnaryElements pOperation_NotUnary;
	private Expression_TerminalElements pExpression_Terminal;
	private Expression_DoubleElements pExpression_Double;
	private Expression_IntegerElements pExpression_Integer;
	private Expression_StringElements pExpression_String;
	private Expression_ProcessRefElements pExpression_ProcessRef;
	private Expression_BooleanElements pExpression_Boolean;
	private TerminalRule tWS;
	private TerminalRule tBOOLEAN;
	private TerminalRule tDATE;
	private TerminalRule tLONG;
	private TerminalRule tDOUBLE;
	
	private final Grammar grammar;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public ConditionModelGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.bonitasoft.studio.condition.ConditionModel".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	
	public Grammar getGrammar() {
		return grammar;
	}
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Operation_Compare:
	//	{Operation_Compare} op=(Operation | Unary_Operation);
	public Operation_CompareElements getOperation_CompareAccess() {
		return (pOperation_Compare != null) ? pOperation_Compare : (pOperation_Compare = new Operation_CompareElements());
	}
	
	public ParserRule getOperation_CompareRule() {
		return getOperation_CompareAccess().getRule();
	}

	//Unary_Operation:
	//	Operation_Unary | Operation_NotUnary;
	public Unary_OperationElements getUnary_OperationAccess() {
		return (pUnary_Operation != null) ? pUnary_Operation : (pUnary_Operation = new Unary_OperationElements());
	}
	
	public ParserRule getUnary_OperationRule() {
		return getUnary_OperationAccess().getRule();
	}

	//Operation:
	//	Operation_Less_Equals | Operation_Less | Operation_Greater_Equals | Operation_Greater | Operation_Not_Equals |
	//	Operation_Equals;
	public OperationElements getOperationAccess() {
		return (pOperation != null) ? pOperation : (pOperation = new OperationElements());
	}
	
	public ParserRule getOperationRule() {
		return getOperationAccess().getRule();
	}

	//Operation_Less_Equals returns Operation:
	//	{Operation_Less_Equals} left=Expression_Terminal "<=" right=Expression_Terminal;
	public Operation_Less_EqualsElements getOperation_Less_EqualsAccess() {
		return (pOperation_Less_Equals != null) ? pOperation_Less_Equals : (pOperation_Less_Equals = new Operation_Less_EqualsElements());
	}
	
	public ParserRule getOperation_Less_EqualsRule() {
		return getOperation_Less_EqualsAccess().getRule();
	}

	//Operation_Less returns Operation:
	//	{Operation_Less} left=Expression_Terminal "<" right=Expression_Terminal;
	public Operation_LessElements getOperation_LessAccess() {
		return (pOperation_Less != null) ? pOperation_Less : (pOperation_Less = new Operation_LessElements());
	}
	
	public ParserRule getOperation_LessRule() {
		return getOperation_LessAccess().getRule();
	}

	//Operation_Greater_Equals returns Operation:
	//	{Operation_Greater_Equals} left=Expression_Terminal ">=" right=Expression_Terminal;
	public Operation_Greater_EqualsElements getOperation_Greater_EqualsAccess() {
		return (pOperation_Greater_Equals != null) ? pOperation_Greater_Equals : (pOperation_Greater_Equals = new Operation_Greater_EqualsElements());
	}
	
	public ParserRule getOperation_Greater_EqualsRule() {
		return getOperation_Greater_EqualsAccess().getRule();
	}

	//Operation_Greater returns Operation:
	//	{Operation_Greater} left=Expression_Terminal ">" right=Expression_Terminal;
	public Operation_GreaterElements getOperation_GreaterAccess() {
		return (pOperation_Greater != null) ? pOperation_Greater : (pOperation_Greater = new Operation_GreaterElements());
	}
	
	public ParserRule getOperation_GreaterRule() {
		return getOperation_GreaterAccess().getRule();
	}

	//Operation_Not_Equals returns Operation:
	//	{Operation_Not_Equals} left=Expression_Terminal "!=" right=Expression_Terminal;
	public Operation_Not_EqualsElements getOperation_Not_EqualsAccess() {
		return (pOperation_Not_Equals != null) ? pOperation_Not_Equals : (pOperation_Not_Equals = new Operation_Not_EqualsElements());
	}
	
	public ParserRule getOperation_Not_EqualsRule() {
		return getOperation_Not_EqualsAccess().getRule();
	}

	//Operation_Equals returns Operation:
	//	{Operation_Equals} left=Expression_Terminal "==" right=Expression_Terminal;
	public Operation_EqualsElements getOperation_EqualsAccess() {
		return (pOperation_Equals != null) ? pOperation_Equals : (pOperation_Equals = new Operation_EqualsElements());
	}
	
	public ParserRule getOperation_EqualsRule() {
		return getOperation_EqualsAccess().getRule();
	}

	//Operation_Unary returns Unary_Operation:
	//	{Operation_Unary} value=Expression_Terminal;
	public Operation_UnaryElements getOperation_UnaryAccess() {
		return (pOperation_Unary != null) ? pOperation_Unary : (pOperation_Unary = new Operation_UnaryElements());
	}
	
	public ParserRule getOperation_UnaryRule() {
		return getOperation_UnaryAccess().getRule();
	}

	//Operation_NotUnary returns Unary_Operation:
	//	{Operation_NotUnary} "!" value=Expression_Terminal;
	public Operation_NotUnaryElements getOperation_NotUnaryAccess() {
		return (pOperation_NotUnary != null) ? pOperation_NotUnary : (pOperation_NotUnary = new Operation_NotUnaryElements());
	}
	
	public ParserRule getOperation_NotUnaryRule() {
		return getOperation_NotUnaryAccess().getRule();
	}

	//Expression_Terminal returns Expression:
	//	Expression_Double | Expression_Integer | Expression_Boolean | Expression_String | Expression_ProcessRef;
	public Expression_TerminalElements getExpression_TerminalAccess() {
		return (pExpression_Terminal != null) ? pExpression_Terminal : (pExpression_Terminal = new Expression_TerminalElements());
	}
	
	public ParserRule getExpression_TerminalRule() {
		return getExpression_TerminalAccess().getRule();
	}

	//Expression_Double:
	//	{Expression_Double} value=DOUBLE;
	public Expression_DoubleElements getExpression_DoubleAccess() {
		return (pExpression_Double != null) ? pExpression_Double : (pExpression_Double = new Expression_DoubleElements());
	}
	
	public ParserRule getExpression_DoubleRule() {
		return getExpression_DoubleAccess().getRule();
	}

	//Expression_Integer:
	//	{Expression_Integer} value=LONG;
	public Expression_IntegerElements getExpression_IntegerAccess() {
		return (pExpression_Integer != null) ? pExpression_Integer : (pExpression_Integer = new Expression_IntegerElements());
	}
	
	public ParserRule getExpression_IntegerRule() {
		return getExpression_IntegerAccess().getRule();
	}

	//Expression_String:
	//	{Expression_String} value=STRING;
	public Expression_StringElements getExpression_StringAccess() {
		return (pExpression_String != null) ? pExpression_String : (pExpression_String = new Expression_StringElements());
	}
	
	public ParserRule getExpression_StringRule() {
		return getExpression_StringAccess().getRule();
	}

	//Expression_ProcessRef hidden(WS):
	//	{Expression_ProcessRef} value=[ecore::EObject];
	public Expression_ProcessRefElements getExpression_ProcessRefAccess() {
		return (pExpression_ProcessRef != null) ? pExpression_ProcessRef : (pExpression_ProcessRef = new Expression_ProcessRefElements());
	}
	
	public ParserRule getExpression_ProcessRefRule() {
		return getExpression_ProcessRefAccess().getRule();
	}

	//Expression_Boolean:
	//	{Expression_Boolean} value=BOOLEAN;
	public Expression_BooleanElements getExpression_BooleanAccess() {
		return (pExpression_Boolean != null) ? pExpression_Boolean : (pExpression_Boolean = new Expression_BooleanElements());
	}
	
	public ParserRule getExpression_BooleanRule() {
		return getExpression_BooleanAccess().getRule();
	}

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return (tWS != null) ? tWS : (tWS = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "WS"));
	} 

	//terminal BOOLEAN returns ecore::EBoolean:
	//	"true" | "false";
	public TerminalRule getBOOLEANRule() {
		return (tBOOLEAN != null) ? tBOOLEAN : (tBOOLEAN = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "BOOLEAN"));
	} 

	//terminal DATE returns ecore::EDate:
	//	"\'" !"\'"* "\'";
	public TerminalRule getDATERule() {
		return (tDATE != null) ? tDATE : (tDATE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "DATE"));
	} 

	//terminal LONG returns ecore::ELong:
	//	"-"? "0".."9"+;
	public TerminalRule getLONGRule() {
		return (tLONG != null) ? tLONG : (tLONG = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "LONG"));
	} 

	//terminal DOUBLE returns ecore::EDouble:
	//	"-"? INT "." INT;
	public TerminalRule getDOUBLERule() {
		return (tDOUBLE != null) ? tDOUBLE : (tDOUBLE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "DOUBLE"));
	} 

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal INT returns ecore::EInt:
	//	"0".."9"+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"" | "\'" ("\\" ("b" | "t" |
	//	"n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\'"))* "\'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
