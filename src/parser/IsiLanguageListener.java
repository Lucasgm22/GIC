// Generated from IsiLanguage.g4 by ANTLR 4.13.0
package parser;

	import java.util.ArrayList;
	import java.util.Stack;
	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import expressions.*;
	import ast.*;
	

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiLanguageParser}.
 */
public interface IsiLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(IsiLanguageParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(IsiLanguageParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(IsiLanguageParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(IsiLanguageParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(IsiLanguageParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(IsiLanguageParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#lista_var}.
	 * @param ctx the parse tree
	 */
	void enterLista_var(IsiLanguageParser.Lista_varContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#lista_var}.
	 * @param ctx the parse tree
	 */
	void exitLista_var(IsiLanguageParser.Lista_varContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(IsiLanguageParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(IsiLanguageParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdDoWhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdDoWhile(IsiLanguageParser.CmdDoWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdDoWhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdDoWhile(IsiLanguageParser.CmdDoWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdWhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdWhile(IsiLanguageParser.CmdWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdWhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdWhile(IsiLanguageParser.CmdWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void enterCmdIf(IsiLanguageParser.CmdIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdIf}.
	 * @param ctx the parse tree
	 */
	void exitCmdIf(IsiLanguageParser.CmdIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdRead}.
	 * @param ctx the parse tree
	 */
	void enterCmdRead(IsiLanguageParser.CmdReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdRead}.
	 * @param ctx the parse tree
	 */
	void exitCmdRead(IsiLanguageParser.CmdReadContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdWrite}.
	 * @param ctx the parse tree
	 */
	void enterCmdWrite(IsiLanguageParser.CmdWriteContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdWrite}.
	 * @param ctx the parse tree
	 */
	void exitCmdWrite(IsiLanguageParser.CmdWriteContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdAttr}.
	 * @param ctx the parse tree
	 */
	void enterCmdAttr(IsiLanguageParser.CmdAttrContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdAttr}.
	 * @param ctx the parse tree
	 */
	void exitCmdAttr(IsiLanguageParser.CmdAttrContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiLanguageParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiLanguageParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#bRelationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterBRelationalExpr(IsiLanguageParser.BRelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#bRelationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitBRelationalExpr(IsiLanguageParser.BRelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(IsiLanguageParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(IsiLanguageParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#exprl}.
	 * @param ctx the parse tree
	 */
	void enterExprl(IsiLanguageParser.ExprlContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#exprl}.
	 * @param ctx the parse tree
	 */
	void exitExprl(IsiLanguageParser.ExprlContext ctx);
}