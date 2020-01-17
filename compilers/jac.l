%X COMMENT_1 COMMENT_2 STRING

%{
	#include "y.tab.h"
	#include "parsingTree.h"

	extern int coluna;
	extern int linha;
	int flag_tokens = 0;
	int analise_sintaxe = 0;
	int comment_line = 1, comment_column = 1;
	int string_line = 1, string_column = 1;
%}

EMPTYSPACE										(" ")|(\t)|(\f)
NUMERO											[0-9]+(([_]*[0-9]+))*
EXPOENTE										(["e""E"][+-]?{NUMERO})
ID												[a-zA-Z_$][0-9a-zA-Z_$]*
DECLIT											0|[1-9]+(([_]*[0-9]+))*
REALLIT											{NUMERO}\.{NUMERO}?{EXPOENTE}?|\.{NUMERO}{EXPOENTE}?|{NUMERO}{EXPOENTE}
STRLIT											\"(([^\r\n\\\"]*)|((\\\\)*)|((\\\\)*(\\)(f|r|n|t|\"|\\)))*\"
RESERVED										"++"|"--"|"null"|"Integer"|"System"|"abstract"|"assert"|"break"|"byte"|"case"|"catch"|"char"|"continue"|"const"|"default"|"enum"|"extends"|"final"|"finally"|"float"|"for"|"goto"|"implements"|"import"|"instanceof"|"interface"|"long"|"native"|"new"|"package"|"private"|"protected"|"short"|"strictfp"|"super"|"switch"|"synchronized"|"this"|"throw"|"throws"|"transient"|"try"|"volatile"
LINEBREAK										(\n)|(\r)|(\r\n)
SPACE											(" ")|(\t)|(\f)

%%

"/*"											{BEGIN COMMENT_1;comment_line = linha; comment_column = coluna;coluna+=yyleng;}
<COMMENT_1>("\n"|"\r"|"\r\n")					{linha++;coluna=1;}
<COMMENT_1>"*/"									{BEGIN 0;coluna+=yyleng;}
<COMMENT_1>.									{coluna+=yyleng;}
<COMMENT_1><<EOF>>								{printf("Line %d, col %d: unterminated comment\n", comment_line, comment_column);BEGIN 0;}


"//"											{BEGIN COMMENT_2; coluna+=yyleng;}
<COMMENT_2>("\n"|"\r"|"\r\n")					{BEGIN 0;linha++;coluna=1;}
<COMMENT_2><<EOF>>								{BEGIN 0;}
<COMMENT_2>.									{coluna+=yyleng;}

\"												{BEGIN STRING; string_line = linha; string_column = coluna;coluna+=yyleng;}
<STRING>\"										{BEGIN 0;coluna+=yyleng;}
<STRING>\\f|\\n|\\r|\\t|\\\\|\\\"				{coluna+=yyleng;}
<STRING>(\\.|\\)								{printf("Line %d, col %d: invalid escape sequence (%s)\n",linha, coluna, yytext);coluna+=yyleng;BEGIN STRING;}
<STRING>{LINEBREAK}								{BEGIN 0; printf("Line %d, col %d: unterminated string literal\n", string_line, string_column);coluna=1;linha++;}
<STRING><<EOF>>									{BEGIN 0;printf("Line %d, col %d: unterminated string literal\n", string_line, string_column);}
<STRING>.										{coluna+=yyleng;}


"boolean"										{coluna+=yyleng; if (flag_tokens==1)printf("BOOL\n"); else if (analise_sintaxe == 1)  return BOOLEAN;else {;}}
"true"|"false"									{coluna+=yyleng; if (flag_tokens==1)printf("BOOLLIT(%s)\n",yytext); else if (analise_sintaxe == 1) {yylval.str=(char*)strdup(yytext); return BOOLLIT;}else {;}}
"class"											{coluna+=yyleng; if (flag_tokens==1)printf("CLASS\n"); else if (analise_sintaxe == 1)  return CLASS;else {;}}
"do"											{coluna+=yyleng; if (flag_tokens==1)printf("DO\n"); else if (analise_sintaxe == 1)  return DO;else {;}}
".length"										{coluna+=yyleng; if (flag_tokens==1)printf("DOTLENGTH\n"); else if (analise_sintaxe == 1)  return DOTLENGTH;else {;}}
"double"										{coluna+=yyleng; if (flag_tokens==1)printf("DOUBLE\n"); else if (analise_sintaxe == 1)  return DOUBLE;else {;}}
"else"											{coluna+=yyleng; if (flag_tokens==1)printf("ELSE\n"); else if (analise_sintaxe == 1)  return ELSE;else {;}}
"if"											{coluna+=yyleng; if (flag_tokens==1)printf("IF\n"); else if (analise_sintaxe == 1)  return IF;else {;}}
"int"											{coluna+=yyleng; if (flag_tokens==1)printf("INT\n"); else if (analise_sintaxe == 1)  return INT;else {;}}
"Integer.parseInt"								{coluna+=yyleng; if (flag_tokens==1)printf("PARSEINT\n"); else if (analise_sintaxe == 1)  return PARSEINT;else {;}}
"System.out.println"							{coluna+=yyleng; if (flag_tokens==1)printf("PRINT\n"); else if (analise_sintaxe == 1)  return PRINT;else {;}}
"public"										{coluna+=yyleng; if (flag_tokens==1)printf("PUBLIC\n"); else if (analise_sintaxe == 1)  return PUBLIC;else {;}}
"return"										{coluna+=yyleng; if (flag_tokens==1)printf("RETURN\n"); else if (analise_sintaxe == 1)  return RETURN;else {;}}
"static"										{coluna+=yyleng; if (flag_tokens==1)printf("STATIC\n"); else if (analise_sintaxe == 1)  return STATIC;else {;}}
"String"										{coluna+=yyleng; if (flag_tokens==1)printf("STRING\n"); else if (analise_sintaxe == 1)  return STR;else {;}}
"void"											{coluna+=yyleng; if (flag_tokens==1)printf("VOID\n"); else if (analise_sintaxe == 1)  return VOID;else {;}}
"while"											{coluna+=yyleng; if (flag_tokens==1)printf("WHILE\n"); else return WHILE;}
"("												{coluna+=yyleng; if (flag_tokens==1)printf("OCURV\n"); else if (analise_sintaxe == 1)  return OCURV;else {;}}
")"												{coluna+=yyleng; if (flag_tokens==1)printf("CCURV\n"); else if (analise_sintaxe == 1)  return CCURV;else {;}}
"{"												{coluna+=yyleng; if (flag_tokens==1)printf("OBRACE\n"); else if (analise_sintaxe == 1)  return OBRACE;else {;}}
"}"												{coluna+=yyleng; if (flag_tokens==1)printf("CBRACE\n"); else if (analise_sintaxe == 1)  return CBRACE; else {;}}
"["												{coluna+=yyleng; if (flag_tokens==1)printf("OSQUARE\n"); else if (analise_sintaxe == 1)  return OSQUARE; else {;}}
"]"												{coluna+=yyleng; if (flag_tokens==1)printf("CSQUARE\n"); else if (analise_sintaxe == 1)  return CSQUARE; else {;}}
"&&"											{coluna+=yyleng; if (flag_tokens==1)printf("AND\n"); else if (analise_sintaxe == 1)  return AND; else {;}}
"||"											{coluna+=yyleng; if (flag_tokens==1)printf("OR\n"); else if (analise_sintaxe == 1)  return OR; else {;}}
"<"												{coluna+=yyleng; if (flag_tokens==1)printf("LT\n"); else if (analise_sintaxe == 1)  return LT; else {;}}
">"												{coluna+=yyleng; if (flag_tokens==1)printf("GT\n"); else if (analise_sintaxe == 1)  return GT; else {;}}
"=="											{coluna+=yyleng; if (flag_tokens==1)printf("EQ\n"); else if (analise_sintaxe == 1)  return EQ; else {;}}
"!="											{coluna+=yyleng; if (flag_tokens==1)printf("NEQ\n"); else if (analise_sintaxe == 1)  return NEQ; else {;}}
"<="											{coluna+=yyleng; if (flag_tokens==1)printf("LEQ\n"); else if (analise_sintaxe == 1)  return LEQ; else {;}}
">="											{coluna+=yyleng; if (flag_tokens==1)printf("GEQ\n"); else if (analise_sintaxe == 1)  return GEQ; else {;}}
"+"												{coluna+=yyleng; if (flag_tokens==1)printf("PLUS\n"); else if (analise_sintaxe == 1)  return PLUS; else {;}}
"-"												{coluna+=yyleng; if (flag_tokens==1)printf("MINUS\n"); else if (analise_sintaxe == 1)  return MINUS; else {;}}
"*"												{coluna+=yyleng; if (flag_tokens==1)printf("STAR\n"); else if (analise_sintaxe == 1)  return STAR; else {;}}
"/"												{coluna+=yyleng; if (flag_tokens==1)printf("DIV\n"); else if (analise_sintaxe == 1)  return DIV; else {;}}
"%"												{coluna+=yyleng; if (flag_tokens==1)printf("MOD\n"); else if (analise_sintaxe == 1)  return MOD; else {;}}
"!"												{coluna+=yyleng; if (flag_tokens==1)printf("NOT\n"); else if (analise_sintaxe == 1)  return NOT; else {;}}
"="												{coluna+=yyleng; if (flag_tokens==1)printf("ASSIGN\n"); else if (analise_sintaxe == 1)  return ASSIGN; else {;}}
";"												{coluna+=yyleng; if (flag_tokens==1)printf("SEMI\n"); else if (analise_sintaxe == 1)  return SEMI; else {;}}
","												{coluna+=yyleng; if (flag_tokens==1)printf("COMMA\n"); else if (analise_sintaxe == 1)  return COMMA; else {;}}
<<EOF>>		 		 							{yylval.inteiro = EOF; return 0;}

{LINEBREAK}										{linha++;coluna=1; yylval.str=(char*)strdup(yytext);}
{SPACE}											{coluna+=yyleng; yylval.str=(char*)strdup(yytext);}
{RESERVED}										{coluna+=yyleng; if (flag_tokens==1)printf("RESERVED(%s)\n", yytext); else if (analise_sintaxe == 1) { yylval.str=(char*)strdup(yytext); return RESERVED;} else {;}}
{ID}											{coluna+=yyleng; if (flag_tokens==1)printf("ID(%s)\n", yytext); else if (analise_sintaxe == 1){ yylval.str=(char*)strdup(yytext); return ID;} else {;}}
{DECLIT}										{coluna+=yyleng; if (flag_tokens==1)printf("DECLIT(%s)\n",yytext); else if (analise_sintaxe == 1){ yylval.str=(char*)strdup(yytext); return DECLIT;} else {;}}
{REALLIT}										{coluna+=yyleng; if (flag_tokens==1)printf("REALLIT(%s)\n",yytext); else if (analise_sintaxe == 1){ yylval.str=(char*)strdup(yytext); return REALLIT;} else {;}}
{STRLIT}										{coluna+=yyleng; if (flag_tokens==1)printf("STRLIT(%s)\n",yytext); else if (analise_sintaxe == 1){ yylval.str=(char*)strdup(yytext); return STRLIT;} else {;}}

.												{printf("Line %d, col %d: illegal character (%s)\n",linha,coluna,yytext);coluna+=yyleng;}


%%


int yywrap(){
	return 1;
}
