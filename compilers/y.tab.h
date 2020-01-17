/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison interface for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

#ifndef YY_YY_Y_TAB_H_INCLUDED
# define YY_YY_Y_TAB_H_INCLUDED
/* Debug traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif
#if YYDEBUG
extern int yydebug;
#endif

/* Token type.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
  enum yytokentype
  {
    precedencia = 258,
    BOOLEAN = 259,
    BOOLLIT = 260,
    CLASS = 261,
    DO = 262,
    DOTLENGTH = 263,
    DOUBLE = 264,
    ELSE = 265,
    IF = 266,
    INT = 267,
    PARSEINT = 268,
    PRINT = 269,
    PUBLIC = 270,
    RETURN = 271,
    STATIC = 272,
    STR = 273,
    VOID = 274,
    WHILE = 275,
    OCURV = 276,
    CCURV = 277,
    OBRACE = 278,
    CBRACE = 279,
    OSQUARE = 280,
    CSQUARE = 281,
    AND = 282,
    OR = 283,
    LT = 284,
    GT = 285,
    EQ = 286,
    NEQ = 287,
    LEQ = 288,
    GEQ = 289,
    PLUS = 290,
    MINUS = 291,
    STAR = 292,
    DIV = 293,
    MOD = 294,
    NOT = 295,
    ASSIGN = 296,
    SEMI = 297,
    COMMA = 298,
    LINEBREAK = 299,
    RESERVED = 300,
    ID = 301,
    DECLIT = 302,
    REALLIT = 303,
    STRLIT = 304,
    NO_ELSE = 305
  };
#endif
/* Tokens.  */
#define precedencia 258
#define BOOLEAN 259
#define BOOLLIT 260
#define CLASS 261
#define DO 262
#define DOTLENGTH 263
#define DOUBLE 264
#define ELSE 265
#define IF 266
#define INT 267
#define PARSEINT 268
#define PRINT 269
#define PUBLIC 270
#define RETURN 271
#define STATIC 272
#define STR 273
#define VOID 274
#define WHILE 275
#define OCURV 276
#define CCURV 277
#define OBRACE 278
#define CBRACE 279
#define OSQUARE 280
#define CSQUARE 281
#define AND 282
#define OR 283
#define LT 284
#define GT 285
#define EQ 286
#define NEQ 287
#define LEQ 288
#define GEQ 289
#define PLUS 290
#define MINUS 291
#define STAR 292
#define DIV 293
#define MOD 294
#define NOT 295
#define ASSIGN 296
#define SEMI 297
#define COMMA 298
#define LINEBREAK 299
#define RESERVED 300
#define ID 301
#define DECLIT 302
#define REALLIT 303
#define STRLIT 304
#define NO_ELSE 305

/* Value type.  */
#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED

union YYSTYPE
{
#line 71 "jac.y" /* yacc.c:1909  */

	int inteiro;
	double real;
	char* str;
	struct parseNode *ast;

#line 161 "y.tab.h" /* yacc.c:1909  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_Y_TAB_H_INCLUDED  */
