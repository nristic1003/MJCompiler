
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
import java.util.*;

import rs.ac.bg.etf.pp1.CompilerError.CompilerErrorType;


%%

%{

	private List<CompilerError> lexError = new ArrayList<>();

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
	
	public List<CompilerError> getLexList()
	{
		return lexError;
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"  		 { return new_symbol(sym.PROG, yytext());}
"break"  		 { return new_symbol(sym.BREAK, yytext());}
"print" 		 { return new_symbol(sym.PRINT, yytext()); }
"return" 		 { return new_symbol(sym.RETURN, yytext()); }
"void" 			 { return new_symbol(sym.VOID, yytext()); }
"class" 		 { return new_symbol(sym.CLASS, yytext()); }
"enum" 		     { return new_symbol(sym.ENUM, yytext()); }
"const" 	     { return new_symbol(sym.CONST, yytext()); }
"if" 		     { return new_symbol(sym.IF, yytext()); }
"else" 		     { return new_symbol(sym.ELSE, yytext()); }
"switch" 		 { return new_symbol(sym.SWITCH, yytext()); }
"do" 		     { return new_symbol(sym.DO, yytext()); }
"while" 		 { return new_symbol(sym.WHILE, yytext()); }
"new" 		     { return new_symbol(sym.NEW, yytext()); }
"extends" 		 { return new_symbol(sym.EXTENDS, yytext()); }
"continue" 		 { return new_symbol(sym.CONTINUE, yytext()); }
"case" 		 	 { return new_symbol(sym.CASE, yytext()); }
"read" 			 { return new_symbol(sym.READ, yytext()); }




"--"		{ return new_symbol(sym.MM, yytext()); }
"++"		{ return new_symbol(sym.PP, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"*"			{ return new_symbol(sym.MUL, yytext()); }
"/"			{ return new_symbol(sym.DIV, yytext()); }
"%"			{ return new_symbol(sym.MOD, yytext()); }
"=="		{ return new_symbol(sym.SAME, yytext()); }
"!="		{ return new_symbol(sym.NOTSAME, yytext()); }
">"			{ return new_symbol(sym.G, yytext()); }
">="		{ return new_symbol(sym.GEQ, yytext()); }
"<"			{ return new_symbol(sym.L, yytext()); }
"<="		{ return new_symbol(sym.LEQ, yytext()); }
"&&"		{ return new_symbol(sym.AND, yytext()); }
"||"		{ return new_symbol(sym.OR, yytext()); }
"."			{ return new_symbol(sym.DOT, yytext()); }
"["			{ return new_symbol(sym.LSQUARE, yytext()); }
"]"			{ return new_symbol(sym.RSQUARE, yytext()); }
"?"			{ return new_symbol(sym.QUESTIONMARK, yytext()); }
":"			{ return new_symbol(sym.COLON, yytext()); }



"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }


"true"                     		{return new_symbol(sym.BOOLCONST, new Boolean(yytext()));}
"false"  						{return new_symbol(sym.BOOLCONST, new Boolean(yytext()));}


'.'    							{return new_symbol(sym.CHARCONST , new Character(yytext().charAt(1)));}
[0-9]+  						{ return new_symbol(sym.NUMCONST, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }


. 								{lexError.add(new CompilerError(yyline+1, "Leksicka greska u liniji: ", CompilerErrorType.LEXICAL_ERROR)); System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }


