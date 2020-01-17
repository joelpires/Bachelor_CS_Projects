%{
	#include <stdio.h>
	#include <string.h>
	#include "parsingTree.h"
	#include "symbol_table.h"
	#define TAMANHO 600

	int yylex(void);
    void yyerror (const char *s);
	extern int flag_tokens, analise_sintaxe;
	int linha = 1;
	int coluna = 1;
	int erros_sintaxe = 0;
	extern char* yytext;
	extern int yyleng;


	ParseNode* root = NULL, *temp = NULL, *temp1 = NULL,  *temp2 = NULL, *temp3 = NULL;
	char varType[10];

%}

%token precedencia
%token BOOLEAN
%token <str> BOOLLIT
%token <str> CLASS
%token DO
%token DOTLENGTH
%token DOUBLE
%token ELSE
%token IF
%token INT
%token PARSEINT
%token PRINT
%token PUBLIC
%token RETURN
%token STATIC
%token STR
%token VOID
%token WHILE
%token OCURV
%token CCURV
%token OBRACE
%token CBRACE
%token OSQUARE
%token CSQUARE
%token AND
%token OR
%token LT
%token GT
%token EQ
%token NEQ
%token LEQ
%token GEQ
%token PLUS
%token MINUS
%token STAR
%token DIV
%token MOD
%token NOT
%token ASSIGN
%token SEMI
%token COMMA
%token LINEBREAK
%token <str> RESERVED
%token <str> ID
%token <str> DECLIT
%token <str> REALLIT
%token <str> STRLIT

%union{
	int inteiro;
	double real;
	char* str;
	struct parseNode *ast;
}

%nonassoc NO_ELSE
%nonassoc ELSE

%start Program
%left COMMA
%right ASSIGN
%left OR
%left AND
%left EQ NEQ
%left LT GT LEQ GEQ
%left PLUS MINUS
%left STAR DIV MOD
%right precedencia

%type <ast> Program
%type <ast> typeDeclarations
%type <ast> FieldDecl
%type <ast> FieldDeclAux
%type <ast> MethodDecl
%type <ast> MethodHeader
%type <ast> MethodBody
%type <ast> MethodBodyAux
%type <ast> FormalParams
%type <ast> FormalParamsAux
%type <ast> VarDecl
%type <ast> VarDeclAux
%type <ast> Type
%type <ast> Statement
%type <ast> StatementAux
%type <ast> Assignment
%type <ast> MethodInvocation
%type <ast> MethodInvocationParams
%type <ast> ParseArgs
%type <ast> Expression
%type <ast> ExpressionAux
%type <ast> PrintStatement
%type <ast> ReturnStatement
%type <ast> IfStatement
%type <ast> DoWhileStatement
%type <ast> WhileStatement



%%

Program
	:  CLASS ID OBRACE typeDeclarations CBRACE					{if(erros_sintaxe == 0) {$$ = createNode("Program", "NULL"); root = $$; temp = createNode("Id", $2); free($2); appendChild(temp,root); appendBrother($4,temp);}}
	;

typeDeclarations
	: typeDeclarations MethodDecl									{if(erros_sintaxe == 0) {appendBrother($2, $1); $$ = $1;}}
	| typeDeclarations FieldDecl									{if(erros_sintaxe == 0) {appendBrother($2, $1); $$ = $1;}}
	| typeDeclarations SEMI											{if(erros_sintaxe == 0) {$$ = $1;}}
	| %empty 														{if(erros_sintaxe == 0) {$$ = createNode("NULL", "NULL"); }}
	;

FieldDecl
	: PUBLIC STATIC Type ID FieldDeclAux SEMI		{if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); appendChild($3, temp); temp1 = createNode("Id",$4); free($4); appendBrother(temp1,$3); appendBrother($5, temp); $$ = temp;}}
	| PUBLIC STATIC Type ID SEMI 					{if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); appendChild($3, temp); temp1 = createNode("Id",$4); free($4); appendBrother(temp1, $3); $$ = temp;}}
	| error SEMI									{{$$ = NULL;}}
	;

Type
	: BOOLEAN								{if(erros_sintaxe == 0) {$$ = createNode("Bool", "NULL"); strcpy(varType, "Bool");}}
	| INT									{if(erros_sintaxe == 0) {$$ = createNode("Int", "NULL"); strcpy(varType, "Int");}}
	| DOUBLE								{if(erros_sintaxe == 0) {$$ = createNode("Double", "NULL"); strcpy(varType, "Double");}}
	;

FieldDeclAux
	: COMMA ID FieldDeclAux						{if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); temp1 = createNode(varType, "NULL"); appendChild(temp1,temp); temp2=createNode("Id", $2); free($2); appendBrother(temp2,temp1); appendBrother($3,temp);$$ = temp;}}
	| COMMA ID 									{if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); temp1 = createNode(varType, "NULL"); appendChild(temp1, temp); temp2=createNode("Id", $2); free($2); appendBrother(temp2, temp1);$$ = temp;}}


MethodDecl
	: PUBLIC STATIC MethodHeader MethodBody			{if(erros_sintaxe == 0) {temp = createNode("MethodDecl", "NULL"); appendChild($3,temp); appendBrother($4,$3); $$ = temp;}}
	;

MethodHeader
	: Type ID OCURV CCURV								{if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); appendChild($1,temp); temp1=createNode("Id", $2); free($2); appendBrother(temp1, $1); temp2 = createNode("MethodParams", "NULL"); appendBrother(temp2, temp1); $$ = temp;}}
	| Type ID OCURV FormalParams CCURV					{if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); appendChild($1,temp); temp1=createNode("Id", $2); free($2); appendBrother(temp1, $1); temp2 = createNode("MethodParams", "NULL"); appendBrother(temp2, temp1); appendChild($4, temp2); $$ = temp;}}
	| VOID ID OCURV CCURV								{if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); temp1 = createNode("Void", "NULL"); appendChild(temp1,temp); temp2=createNode("Id", $2); free($2); appendBrother(temp2, temp1); temp3 = createNode("MethodParams", "NULL"); appendBrother(temp3, temp2); $$ = temp;}}
	| VOID ID OCURV FormalParams CCURV 					{if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); temp1 = createNode("Void", "NULL"); appendChild(temp1,temp); temp2=createNode("Id", $2); free($2); appendBrother(temp2, temp1); temp3 = createNode("MethodParams", "NULL"); appendBrother(temp3, temp2); appendChild($4,temp3); $$ = temp;}}
	;

FormalParams
	: Type ID FormalParamsAux							{if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild($1, temp); temp1 = createNode("Id", $2); appendBrother(temp1, $1); appendBrother($3,temp); $$=temp;}}
	| Type ID 											{if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild($1, temp); temp1 = createNode("Id", $2); appendBrother(temp1, $1); $$=temp;}}
	| STR OSQUARE CSQUARE ID							{if(erros_sintaxe == 0) {temp = createNode("ParamDecl", "NULL"); temp1 = createNode("StringArray", "NULL"); appendChild(temp1,temp); temp2 = createNode("Id", $4); free($4);appendBrother(temp2,temp1); $$=temp;}}
	;

FormalParamsAux
	: COMMA Type ID FormalParamsAux						{if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild($2,temp); temp1 = createNode("Id", $3); free($3); appendBrother(temp1, $2); appendBrother($4,temp); $$ = temp;}}
	| COMMA Type ID 									{if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild($2, temp); temp1 = createNode("Id", $3); free($3); appendBrother(temp1, $2); $$ = temp;}}
	;

MethodBody
	: OBRACE MethodBodyAux CBRACE					{if(erros_sintaxe == 0) {temp = createNode("MethodBody", "NULL"); appendChild($2, temp); $$ = temp;}}
	;

MethodBodyAux
	: MethodBodyAux VarDecl					{if(erros_sintaxe == 0) {appendBrother($2, $1); $$ = $1;}}
	| MethodBodyAux Statement				{if(erros_sintaxe == 0) {appendBrother($2, $1); $$ = $1;}}
	| %empty 					 			{if(erros_sintaxe == 0) {$$ = createNode("NULL", "NULL");}}
	;

VarDecl
	: Type ID VarDeclAux SEMI					{if(erros_sintaxe == 0) {temp = createNode("VarDecl","NULL"); appendChild($1, temp); temp1 = createNode("Id", $2); free($2); appendBrother(temp1, $1); appendBrother($3, temp); $$=temp;}}
	| Type ID SEMI 								{if(erros_sintaxe == 0) {temp = createNode("VarDecl", "NULL"); appendChild($1, temp); temp1 = createNode("Id", $2); free($2); appendBrother(temp1, $1); $$ = temp;}}
	;

VarDeclAux
	: COMMA ID VarDeclAux						{if(erros_sintaxe == 0) {temp = createNode("VarDecl", "NULL"); $$=temp; temp1 = createNode(varType,"NULL"); appendChild(temp1, temp); temp2 = createNode("Id", $2); free($2); appendBrother(temp2, temp1); appendBrother($3,temp);}}
	| COMMA ID 									{if(erros_sintaxe == 0) {temp = createNode("VarDecl", "NULL"); $$=temp; temp1 = createNode(varType, "NULL"); temp2 = createNode("Id", $2); free($2); appendChild(temp1,temp); appendBrother(temp2, temp1); }}
	;

Statement
	: OBRACE CBRACE																{if(erros_sintaxe == 0) {$$ = NULL;}}
	| SEMI																		{if(erros_sintaxe == 0) {$$ = NULL;}}
	| OBRACE StatementAux CBRACE												{if(erros_sintaxe == 0) {if($2!=NULL){
																											if($2->brother != NULL){
																												temp = createNode("Block","NULL");
																												appendChild($2, temp); $$ = temp;
																											}else{
																												$$ = $2;
																											}
																											} else {$$ = $2;}
																										}
																				}
	| IfStatement																{if(erros_sintaxe == 0) {$$ = $1;}}
	| WhileStatement															{if(erros_sintaxe == 0) {$$ = $1;}}
	| DoWhileStatement															{if(erros_sintaxe == 0) {$$ = $1;}}
	| PrintStatement															{if(erros_sintaxe == 0) {$$ = $1;}}
	| Assignment SEMI															{if(erros_sintaxe == 0) {$$ = $1;}}
	| MethodInvocation SEMI														{if(erros_sintaxe == 0) {$$ = $1;}}
	| ParseArgs SEMI															{if(erros_sintaxe == 0) {$$ = $1;}}
	| ReturnStatement															{if(erros_sintaxe == 0) {$$ = $1;}}
	| error SEMI																{$$ = NULL;}
	;

IfStatement
	: IF OCURV Expression CCURV Statement	%prec NO_ELSE						{if(erros_sintaxe == 0) {temp = createNode("If","NULL"); appendChild( $3, temp); $$ = temp; temp1 = createNode("Block","NULL"); if($5 != NULL){
																																															if($5->brother != NULL){
																																																temp = createNode("Block","NULL");
																																																appendBrother(temp, $3);
																																																appendChild($5, temp);
																																																appendChild(temp1, $$);
																																															} else{
																																																appendBrother($5, $3);
																																																appendBrother(temp1, $5);
																																															}
																																														} else{
																																															appendBrother(temp1, $3);
																																															temp2 = createNode("Block","NULL");
																																															appendBrother(temp2, $3);
																																														}
																				}}

	| IF OCURV Expression CCURV Statement ELSE Statement						{if(erros_sintaxe == 0) {temp = createNode("If","NULL"); appendChild( $3, temp); $$ = temp;
																										if($5 != NULL){
																											if($5->brother!=NULL){
																												temp2 = createNode("Block","NULL");
																												appendBrother(temp2, $3);
																												appendChild($5, temp2);
																											}else{
																												appendBrother($5, $3);
																											}
																										}else{
																											temp1 = createNode("Block","NULL");
																											appendBrother(temp1, $3);
																										}
																										if($7 != NULL){
																											if($7->brother != NULL){
																												temp3 = createNode("Block","NULL");
																												appendBrother(temp3, $3);
																												appendChild($7,temp3);

																											}else appendBrother($7, $3);
																										}else{
																											temp3 = createNode("Block","NULL");
																											appendBrother(temp3, $3);
																										}
																				}}
	;

WhileStatement
	: WHILE OCURV Expression CCURV Statement									{if(erros_sintaxe == 0) {temp = createNode("While","NULL");  appendChild($3, temp); $$ = temp; if($5 != NULL){
																																								if($5->brother != NULL){
																																									temp2 = createNode("Block","NULL");
																																									appendChild($5, temp2);
																																									appendBrother(temp2, $3);
																																								} else appendBrother($5, $3);
																																							} else {
																																								temp1 = createNode("Block","NULL");
																																								appendBrother(temp1, $3);
																																							}
																				}}
	;

DoWhileStatement
	:DO Statement WHILE OCURV Expression CCURV SEMI								{if(erros_sintaxe == 0) {temp = createNode("DoWhile","NULL"); $$=temp; if($2 != NULL){
																																	if($2->brother != NULL){
																																		temp2 = createNode("Block","NULL");
																																		appendChild($2, temp2);
																																		appendBrother($5, temp2);
																																		appendChild(temp2, temp);
																																	}else{
																																		appendBrother($5, $2);
																																		appendChild($2, temp);
																																	}
																																}else{
																																	temp1 = createNode("Block","NULL");
																																	appendBrother($5, temp1);
																																	appendChild(temp1, temp);
																																}
																				}}
	;

PrintStatement
	: PRINT OCURV Expression CCURV SEMI 										{if(erros_sintaxe == 0) {temp = createNode("Print","NULL"); $$ = temp; appendChild($3, temp); }}
	| PRINT OCURV STRLIT CCURV SEMI 											{if(erros_sintaxe == 0) {temp = createNode("Print","NULL"); $$ = temp; temp1 = createNode("StrLit",$3);  appendChild(temp1, temp);}}
	;

Assignment
	: ID ASSIGN Expression														{if(erros_sintaxe == 0) {temp = createNode("Id",$1); free($1); temp1 = createNode("Assign","NULL"); $$ = temp1; appendChild(temp, temp1); appendBrother($3, temp);}}
	;

MethodInvocation
	: ID OCURV CCURV															{if(erros_sintaxe == 0) {temp = createNode("Call","NULL"); $$=temp; temp1 = createNode("Id",$1); free($1); appendChild(temp1, temp); }}
	|ID OCURV Expression CCURV													{if(erros_sintaxe == 0) {temp = createNode("Call","NULL"); $$=temp; temp1 = createNode("Id",$1); free($1); appendChild(temp1, temp); appendBrother($3, temp1);}}
	|ID OCURV Expression MethodInvocationParams CCURV							{if(erros_sintaxe == 0) {temp = createNode("Call","NULL"); $$=temp; temp1 = createNode("Id",$1); free($1); appendChild(temp1, temp); appendBrother($3, temp1); if($3!= NULL) appendBrother($4, $3); else appendBrother($4, temp1);}}
	|ID OCURV error CCURV														{{$$ = NULL;}}
	;

MethodInvocationParams
	: MethodInvocationParams COMMA	Expression									{if(erros_sintaxe == 0) {appendBrother($3, $1); $$=$1;}}
	| COMMA Expression															{if(erros_sintaxe == 0) {$$ = $2;}}
	;

ParseArgs
	: PARSEINT OCURV ID OSQUARE Expression CSQUARE CCURV						{if(erros_sintaxe == 0) {temp = createNode("ParseArgs","NULL"); $$ = temp; temp1 = createNode("Id",$3); free($3); appendChild(temp1, temp); appendBrother($5, temp1);}}
	| PARSEINT OCURV error CCURV												{{$$ = NULL;}}
	;

ReturnStatement
	:RETURN SEMI																{if(erros_sintaxe == 0) {temp = createNode("Return","NULL"); $$ = temp;}}
	|RETURN Expression SEMI														{if(erros_sintaxe == 0) {temp = createNode("Return","NULL"); $$ = temp; appendChild($2, temp);}}
	;

StatementAux
	: Statement																	{if(erros_sintaxe == 0) {$$ = $1;}}
	| StatementAux Statement 													{if(erros_sintaxe == 0) {if($1 != NULL){
																											$$ = $1;
																											appendBrother($2, $$);
																										}else{$$ = $2;}}}
;

Expression
	: Assignment																{if(erros_sintaxe == 0) {$$ = $1;}}
	| ExpressionAux																{if(erros_sintaxe == 0) {$$ = $1;}}
	;

ExpressionAux
	:MethodInvocation															{if(erros_sintaxe == 0) {$$ = $1;}}
	|ParseArgs 																	{if(erros_sintaxe == 0) {$$ = $1;}}
	|ID 																		{if(erros_sintaxe == 0) {temp = createNode("Id",$1); free($1); $$ = temp;}}
	|ID DOTLENGTH																{if(erros_sintaxe == 0) {temp = createNode("Length","NULL"); $$ = temp; temp1 = createNode("Id",$1); free($1); appendChild(temp1, temp);}}
	|OCURV Expression CCURV														{if(erros_sintaxe == 0) {$$ = $2;}}
	|PLUS ExpressionAux		%prec precedencia									{if(erros_sintaxe == 0) {temp = createNode("Plus","NULL"); $$ = temp; appendChild( $2, temp);}}
	|MINUS ExpressionAux	%prec precedencia									{if(erros_sintaxe == 0) {temp = createNode("Minus","NULL"); $$ = temp; appendChild( $2, temp);}}
	|NOT ExpressionAux		%prec precedencia									{if(erros_sintaxe == 0) {temp = createNode("Not","NULL"); $$ = temp; appendChild( $2, temp);}}
	|ExpressionAux EQ ExpressionAux												{if(erros_sintaxe == 0) {temp = createNode("Eq","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux GEQ ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Geq","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux AND ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("And","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux OR ExpressionAux												{if(erros_sintaxe == 0) {temp = createNode("Or","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux GT ExpressionAux												{if(erros_sintaxe == 0) {temp = createNode("Gt","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux LEQ ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Leq","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux LT ExpressionAux												{if(erros_sintaxe == 0) {temp = createNode("Lt","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux NEQ ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Neq","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux STAR ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Mul","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux DIV ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Div","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux MOD ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Mod","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux PLUS ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Add","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|ExpressionAux MINUS ExpressionAux											{if(erros_sintaxe == 0) {temp = createNode("Sub","NULL"); $$ = temp; appendChild( $1, temp); appendBrother($3, $1);}}
	|DECLIT																		{if(erros_sintaxe == 0) {temp = createNode("DecLit",$1); free($1); $$ = temp;}}
	|BOOLLIT																	{if(erros_sintaxe == 0) {temp = createNode("BoolLit",$1); free($1); $$ = temp;}}
	|REALLIT																	{if(erros_sintaxe == 0) {temp = createNode("RealLit",$1); free($1); $$ = temp;}}
	|OCURV error CCURV															{if(erros_sintaxe == 0) {$$ = NULL;}}
	;

%%


void yyerror(const char *s){
	if (analise_sintaxe) {
		erros_sintaxe = 1;
		printf("Line %d, col %d: %s: %s\n", linha, (int)(coluna - strlen(yytext)), s, yytext);
		return ;
	}
}


int main(int argc, char *argv[]){
	if(argc > 1){
		if(strcmp(argv[1],"-1") == 0){
			yylex();
		} else if(strcmp(argv[1],"-l") == 0 ) {
			flag_tokens = 1;
			yylex();
		} else if(strcmp(argv[1],"-2") == 0){
			analise_sintaxe = 1;
			yyparse();
		} else if(strcmp(argv[1],"-t") == 0){
			analise_sintaxe = 1;

			yyparse();
			if(erros_sintaxe == 0)
				printParseTree(root,0);
		}else {
			analise_sintaxe = 1;
			yyparse();
			if (erros_sintaxe == 0) {
				/*initialize table list*/
				tableList = createTableList();
				/*build table from parsingTree*/
				buildTables(root);
				print_TableList();
				printf("\n");
				ParseNode* no = root;
				buildNotedTree(no);
				printNotedParseTree(root,0);
			}
		}
	}
	else{
		analise_sintaxe = 1;
		yyparse();
		if (erros_sintaxe == 0) {
			/*initialize table list*/
			tableList = createTableList();
			/*build table from parsingTree*/
			buildTables(root);
		}
	}

	return 0;

}
