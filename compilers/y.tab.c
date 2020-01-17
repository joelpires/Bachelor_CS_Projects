/* A Bison parser, made by GNU Bison 3.0.4.  */

/* Bison implementation for Yacc-like parsers in C

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

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "3.0.4"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* Copy the first part of user declarations.  */
#line 1 "jac.y" /* yacc.c:339  */

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


#line 88 "y.tab.c" /* yacc.c:339  */

# ifndef YY_NULLPTR
#  if defined __cplusplus && 201103L <= __cplusplus
#   define YY_NULLPTR nullptr
#  else
#   define YY_NULLPTR 0
#  endif
# endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* In a future release of Bison, this section will be replaced
   by #include "y.tab.h".  */
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
#line 71 "jac.y" /* yacc.c:355  */

	int inteiro;
	double real;
	char* str;
	struct parseNode *ast;

#line 235 "y.tab.c" /* yacc.c:355  */
};

typedef union YYSTYPE YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define YYSTYPE_IS_DECLARED 1
#endif


extern YYSTYPE yylval;

int yyparse (void);

#endif /* !YY_YY_Y_TAB_H_INCLUDED  */

/* Copy the second part of user declarations.  */

#line 252 "y.tab.c" /* yacc.c:358  */

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif

#ifndef YY_ATTRIBUTE
# if (defined __GNUC__                                               \
      && (2 < __GNUC__ || (__GNUC__ == 2 && 96 <= __GNUC_MINOR__)))  \
     || defined __SUNPRO_C && 0x5110 <= __SUNPRO_C
#  define YY_ATTRIBUTE(Spec) __attribute__(Spec)
# else
#  define YY_ATTRIBUTE(Spec) /* empty */
# endif
#endif

#ifndef YY_ATTRIBUTE_PURE
# define YY_ATTRIBUTE_PURE   YY_ATTRIBUTE ((__pure__))
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# define YY_ATTRIBUTE_UNUSED YY_ATTRIBUTE ((__unused__))
#endif

#if !defined _Noreturn \
     && (!defined __STDC_VERSION__ || __STDC_VERSION__ < 201112)
# if defined _MSC_VER && 1200 <= _MSC_VER
#  define _Noreturn __declspec (noreturn)
# else
#  define _Noreturn YY_ATTRIBUTE ((__noreturn__))
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(E) ((void) (E))
#else
# define YYUSE(E) /* empty */
#endif

#if defined __GNUC__ && 407 <= __GNUC__ * 100 + __GNUC_MINOR__
/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN \
    _Pragma ("GCC diagnostic push") \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")\
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# define YY_IGNORE_MAYBE_UNINITIALIZED_END \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif


#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYSIZE_T yynewbytes;                                            \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / sizeof (*yyptr);                          \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, (Count) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYSIZE_T yyi;                         \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  4
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   344

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  51
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  27
/* YYNRULES -- Number of rules.  */
#define YYNRULES  90
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  184

/* YYTRANSLATE[YYX] -- Symbol number corresponding to YYX as returned
   by yylex, with out-of-bounds checking.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   305

#define YYTRANSLATE(YYX)                                                \
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, without out-of-bounds checking.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50
};

#if YYDEBUG
  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,   124,   124,   128,   129,   130,   131,   135,   136,   137,
     141,   142,   143,   147,   148,   152,   156,   157,   158,   159,
     163,   164,   165,   169,   170,   174,   178,   179,   180,   184,
     185,   189,   190,   194,   195,   196,   206,   207,   208,   209,
     210,   211,   212,   213,   214,   218,   235,   263,   277,   296,
     297,   301,   305,   306,   307,   308,   312,   313,   317,   318,
     322,   323,   327,   328,   335,   336,   340,   341,   342,   343,
     344,   345,   346,   347,   348,   349,   350,   351,   352,   353,
     354,   355,   356,   357,   358,   359,   360,   361,   362,   363,
     364
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || 0
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "precedencia", "BOOLEAN", "BOOLLIT",
  "CLASS", "DO", "DOTLENGTH", "DOUBLE", "ELSE", "IF", "INT", "PARSEINT",
  "PRINT", "PUBLIC", "RETURN", "STATIC", "STR", "VOID", "WHILE", "OCURV",
  "CCURV", "OBRACE", "CBRACE", "OSQUARE", "CSQUARE", "AND", "OR", "LT",
  "GT", "EQ", "NEQ", "LEQ", "GEQ", "PLUS", "MINUS", "STAR", "DIV", "MOD",
  "NOT", "ASSIGN", "SEMI", "COMMA", "LINEBREAK", "RESERVED", "ID",
  "DECLIT", "REALLIT", "STRLIT", "NO_ELSE", "$accept", "Program",
  "typeDeclarations", "FieldDecl", "Type", "FieldDeclAux", "MethodDecl",
  "MethodHeader", "FormalParams", "FormalParamsAux", "MethodBody",
  "MethodBodyAux", "VarDecl", "VarDeclAux", "Statement", "IfStatement",
  "WhileStatement", "DoWhileStatement", "PrintStatement", "Assignment",
  "MethodInvocation", "MethodInvocationParams", "ParseArgs",
  "ReturnStatement", "StatementAux", "Expression", "ExpressionAux", YY_NULLPTR
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[NUM] -- (External) token number corresponding to the
   (internal) symbol number NUM (which must be that of a token).  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305
};
# endif

#define YYPACT_NINF -50

#define yypact_value_is_default(Yystate) \
  (!!((Yystate) == (-50)))

#define YYTABLE_NINF -1

#define yytable_value_is_error(Yytable_value) \
  0

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
     STATE-NUM.  */
static const yytype_int16 yypact[] =
{
      25,   -13,    39,    20,   -50,   -50,    37,     5,    29,   -50,
     -50,   -50,   -50,   -50,   233,   -50,   -50,   -50,     8,    17,
      54,    57,    -8,   -50,   -50,    78,    80,   -50,    34,    41,
     170,    60,   -50,    35,    75,   -50,    82,    62,   -50,    64,
     218,    86,    92,   101,   119,   105,   184,   -50,   -50,    21,
      91,   -50,   -50,   -50,   -50,   -50,   -50,   100,   109,   110,
     -50,   121,   113,   -50,   -50,   -50,   -50,   133,     9,     4,
     230,   -50,   122,   248,   248,   248,   -50,     7,   -50,   -50,
     -50,   -50,   -50,   118,   270,     9,   -50,   -50,   204,    98,
       9,   -36,   -50,   -50,   -50,   117,   127,   -50,   143,   150,
     151,   153,   154,   158,   165,   166,    16,   -50,   -50,   -50,
     -50,   -50,   248,   248,   248,   248,   248,   248,   248,   248,
     248,   248,   248,   248,   248,   167,   -50,   -50,   174,   -50,
     -14,   -50,   -50,   129,   157,   -50,   146,     9,   218,   -50,
       9,   159,   160,   -50,   -50,   294,   283,    79,    79,   305,
     305,    79,    79,    56,    56,   -50,   -50,   -50,   218,   -50,
     -50,     9,    10,   163,   -50,   113,   181,   199,   187,   -50,
     -50,   -50,   -50,   -50,     9,   -50,   -50,   168,   218,   192,
     -50,   -50,   -50,   -50
};

  /* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
     Performed when YYTABLE does not specify something else to do.  Zero
     means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       0,     0,     0,     0,     1,     6,     0,     0,     0,     2,
       5,     4,     3,     9,     0,    10,    12,    11,     0,     0,
       0,     0,     0,    28,    15,     0,     0,     8,     0,     0,
       0,     0,    18,     0,     0,    16,     0,    14,     7,     0,
       0,     0,     0,     0,     0,     0,     0,    25,    34,     0,
       0,    26,    27,    36,    37,    38,    39,     0,     0,     0,
      43,     0,    21,    19,    17,    13,    44,     0,     0,     0,
       0,    88,     0,     0,     0,     0,    60,    68,    87,    89,
      64,    66,    67,     0,    65,     0,    33,    62,     0,     0,
       0,     0,    40,    41,    42,     0,     0,    20,     0,     0,
       0,     0,     0,     0,     0,     0,    68,    71,    72,    73,
      69,    61,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,    35,    63,     0,    52,
       0,    51,    30,     0,     0,    22,     0,     0,     0,    59,
       0,     0,     0,    90,    70,    76,    77,    80,    78,    74,
      81,    79,    75,    85,    86,    82,    83,    84,     0,    55,
      53,     0,     0,    32,    29,    24,     0,    45,     0,    50,
      49,    47,    57,    54,     0,    31,    23,     0,     0,     0,
      56,    48,    46,    58
};

  /* YYPGOTO[NTERM-NUM].  */
static const yytype_int16 yypgoto[] =
{
     -50,   -50,   -50,   -50,   -10,   185,   -50,   -50,   195,    58,
     -50,   -50,   -50,    70,   -37,   -50,   -50,   -50,   -50,   -30,
     -29,   -50,   -28,   -50,   -50,   -49,   -48
};

  /* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     2,     6,    11,    33,    29,    12,    20,    34,    97,
      24,    30,    51,   134,    52,    53,    54,    55,    56,    80,
      81,   162,    82,    60,    88,    83,    84
};

  /* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
     positive, shift that token.  If negative, reduce the rule whose
     number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_uint8 yytable[] =
{
      57,    58,    59,    67,    19,   100,   132,   133,   160,    87,
      57,    58,    59,    26,    71,   110,    57,    58,    59,    99,
      50,   103,    42,   105,   110,   107,   108,   109,    89,   161,
      72,     1,   173,     3,    27,    28,   125,    89,     7,     4,
     130,   131,    89,     5,    73,    74,    14,    13,    90,    75,
     101,   127,     8,   174,    21,    77,    78,    79,    57,    58,
      59,     9,    90,    22,   145,   146,   147,   148,   149,   150,
     151,   152,   153,   154,   155,   156,   157,    23,    25,    10,
      37,    62,    15,    38,    15,    61,   136,    16,   166,    16,
      17,   168,    17,   122,   123,   124,    31,    63,    31,   128,
      32,   167,    35,    71,    64,    28,    66,    68,    57,    58,
      59,    42,   172,    69,   120,   121,   122,   123,   124,    72,
     129,   171,    70,   104,    71,   180,    85,    71,    57,    58,
      59,    15,    42,    73,    74,    42,    16,    91,    75,    17,
      72,   182,    92,    72,    77,    78,    79,    95,    57,    58,
      59,    93,    94,    98,    73,    74,    96,    73,    74,    75,
     111,    76,    75,   135,   137,    77,    78,    79,    77,    78,
      79,    39,   138,   139,    15,   163,   141,    40,   140,    16,
     142,    41,    17,    42,    43,    39,    44,   143,   144,   158,
      45,    40,   165,    46,    47,    41,   159,    42,    43,   164,
      44,   169,   170,   177,    45,    39,   133,    46,    86,   178,
     181,    40,    48,   179,   183,    41,    49,    42,    43,    39,
      44,    36,    65,   176,    45,    40,    48,    46,   126,    41,
      49,    42,    43,   175,    44,    71,     0,    15,    45,     0,
       0,    46,    16,    42,     0,    17,    48,     0,     0,     0,
      49,    72,    18,    71,     0,     0,     0,     0,     0,     0,
      48,    42,     0,     0,    49,    73,    74,     0,     0,    72,
      75,     0,     0,     0,     0,     0,    77,    78,    79,   102,
       0,     0,     0,    73,    74,     0,     0,     0,    75,     0,
       0,     0,     0,     0,   106,    78,    79,   112,   113,   114,
     115,   116,   117,   118,   119,   120,   121,   122,   123,   124,
     112,     0,   114,   115,   116,   117,   118,   119,   120,   121,
     122,   123,   124,   114,   115,   116,   117,   118,   119,   120,
     121,   122,   123,   124,   114,   115,     0,     0,   118,   119,
     120,   121,   122,   123,   124
};

static const yytype_int16 yycheck[] =
{
      30,    30,    30,    40,    14,     1,    42,    43,    22,    46,
      40,    40,    40,    21,     5,     8,    46,    46,    46,    68,
      30,    70,    13,    72,     8,    73,    74,    75,    21,    43,
      21,     6,    22,    46,    42,    43,    85,    21,     1,     0,
      89,    90,    21,    23,    35,    36,    17,    42,    41,    40,
      46,    88,    15,    43,    46,    46,    47,    48,    88,    88,
      88,    24,    41,    46,   112,   113,   114,   115,   116,   117,
     118,   119,   120,   121,   122,   123,   124,    23,    21,    42,
      46,    46,     4,    42,     4,    25,    96,     9,   137,     9,
      12,   140,    12,    37,    38,    39,    18,    22,    18,     1,
      22,   138,    22,     5,    22,    43,    42,    21,   138,   138,
     138,    13,   161,    21,    35,    36,    37,    38,    39,    21,
      22,   158,    21,     1,     5,   174,    21,     5,   158,   158,
     158,     4,    13,    35,    36,    13,     9,    46,    40,    12,
      21,   178,    42,    21,    46,    47,    48,    26,   178,   178,
     178,    42,    42,    20,    35,    36,    43,    35,    36,    40,
      42,    42,    40,    46,    21,    46,    47,    48,    46,    47,
      48,     1,    22,    22,     4,    46,    22,     7,    25,     9,
      22,    11,    12,    13,    14,     1,    16,    22,    22,    22,
      20,     7,    46,    23,    24,    11,    22,    13,    14,    42,
      16,    42,    42,    22,    20,     1,    43,    23,    24,    10,
      42,     7,    42,    26,    22,    11,    46,    13,    14,     1,
      16,    26,    37,   165,    20,     7,    42,    23,    24,    11,
      46,    13,    14,   163,    16,     5,    -1,     4,    20,    -1,
      -1,    23,     9,    13,    -1,    12,    42,    -1,    -1,    -1,
      46,    21,    19,     5,    -1,    -1,    -1,    -1,    -1,    -1,
      42,    13,    -1,    -1,    46,    35,    36,    -1,    -1,    21,
      40,    -1,    -1,    -1,    -1,    -1,    46,    47,    48,    49,
      -1,    -1,    -1,    35,    36,    -1,    -1,    -1,    40,    -1,
      -1,    -1,    -1,    -1,    46,    47,    48,    27,    28,    29,
      30,    31,    32,    33,    34,    35,    36,    37,    38,    39,
      27,    -1,    29,    30,    31,    32,    33,    34,    35,    36,
      37,    38,    39,    29,    30,    31,    32,    33,    34,    35,
      36,    37,    38,    39,    29,    30,    -1,    -1,    33,    34,
      35,    36,    37,    38,    39
};

  /* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
     symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,     6,    52,    46,     0,    23,    53,     1,    15,    24,
      42,    54,    57,    42,    17,     4,     9,    12,    19,    55,
      58,    46,    46,    23,    61,    21,    21,    42,    43,    56,
      62,    18,    22,    55,    59,    22,    59,    46,    42,     1,
       7,    11,    13,    14,    16,    20,    23,    24,    42,    46,
      55,    63,    65,    66,    67,    68,    69,    70,    71,    73,
      74,    25,    46,    22,    22,    56,    42,    65,    21,    21,
      21,     5,    21,    35,    36,    40,    42,    46,    47,    48,
      70,    71,    73,    76,    77,    21,    24,    65,    75,    21,
      41,    46,    42,    42,    42,    26,    43,    60,    20,    76,
       1,    46,    49,    76,     1,    76,    46,    77,    77,    77,
       8,    42,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    76,    24,    65,     1,    22,
      76,    76,    42,    43,    64,    46,    55,    21,    22,    22,
      25,    22,    22,    22,    22,    77,    77,    77,    77,    77,
      77,    77,    77,    77,    77,    77,    77,    77,    22,    22,
      22,    43,    72,    46,    42,    46,    76,    65,    76,    42,
      42,    65,    76,    22,    43,    64,    60,    22,    10,    26,
      76,    42,    65,    22
};

  /* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    51,    52,    53,    53,    53,    53,    54,    54,    54,
      55,    55,    55,    56,    56,    57,    58,    58,    58,    58,
      59,    59,    59,    60,    60,    61,    62,    62,    62,    63,
      63,    64,    64,    65,    65,    65,    65,    65,    65,    65,
      65,    65,    65,    65,    65,    66,    66,    67,    68,    69,
      69,    70,    71,    71,    71,    71,    72,    72,    73,    73,
      74,    74,    75,    75,    76,    76,    77,    77,    77,    77,
      77,    77,    77,    77,    77,    77,    77,    77,    77,    77,
      77,    77,    77,    77,    77,    77,    77,    77,    77,    77,
      77
};

  /* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     5,     2,     2,     2,     0,     6,     5,     2,
       1,     1,     1,     3,     2,     4,     4,     5,     4,     5,
       3,     2,     4,     4,     3,     3,     2,     2,     0,     4,
       3,     3,     2,     2,     1,     3,     1,     1,     1,     1,
       2,     2,     2,     1,     2,     5,     7,     5,     7,     5,
       5,     3,     3,     4,     5,     4,     3,     2,     7,     4,
       2,     3,     1,     2,     1,     1,     1,     1,     1,     2,
       3,     2,     2,     2,     3,     3,     3,     3,     3,     3,
       3,     3,     3,     3,     3,     3,     3,     1,     1,     1,
       3
};


#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)
#define YYEMPTY         (-2)
#define YYEOF           0

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                  \
do                                                              \
  if (yychar == YYEMPTY)                                        \
    {                                                           \
      yychar = (Token);                                         \
      yylval = (Value);                                         \
      YYPOPSTACK (yylen);                                       \
      yystate = *yyssp;                                         \
      goto yybackup;                                            \
    }                                                           \
  else                                                          \
    {                                                           \
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;                                                  \
    }                                                           \
while (0)

/* Error token number */
#define YYTERROR        1
#define YYERRCODE       256



/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)

/* This macro is provided for backward compatibility. */
#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


# define YY_SYMBOL_PRINT(Title, Type, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Type, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*----------------------------------------.
| Print this symbol's value on YYOUTPUT.  |
`----------------------------------------*/

static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  FILE *yyo = yyoutput;
  YYUSE (yyo);
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# endif
  YYUSE (yytype);
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyoutput, "%s %s (",
             yytype < YYNTOKENS ? "token" : "nterm", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yytype_int16 *yyssp, YYSTYPE *yyvsp, int yyrule)
{
  unsigned long int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       yystos[yyssp[yyi + 1 - yynrhs]],
                       &(yyvsp[(yyi + 1) - (yynrhs)])
                                              );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
static YYSIZE_T
yystrlen (const char *yystr)
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            /* Fall through.  */
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (YY_NULLPTR, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                {
                  YYSIZE_T yysize1 = yysize + yytnamerr (YY_NULLPTR, yytname[yyx]);
                  if (! (yysize <= yysize1
                         && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                    return 2;
                  yysize = yysize1;
                }
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  {
    YYSIZE_T yysize1 = yysize + yystrlen (yyformat);
    if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
      return 2;
    yysize = yysize1;
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
{
  YYUSE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YYUSE (yytype);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}




/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       'yyss': related to states.
       'yyvs': related to semantic values.

       Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken = 0;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yyssp = yyss = yyssa;
  yyvsp = yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */
  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        YYSTYPE *yyvs1 = yyvs;
        yytype_int16 *yyss1 = yyss;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * sizeof (*yyssp),
                    &yyvs1, yysize * sizeof (*yyvsp),
                    &yystacksize);

        yyss = yyss1;
        yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yytype_int16 *yyss1 = yyss;
        union yyalloc *yyptr =
          (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
        if (! yyptr)
          goto yyexhaustedlab;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
                  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 2:
#line 124 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = createNode("Program", "NULL"); root = (yyval.ast); temp = createNode("Id", (yyvsp[-3].str)); free((yyvsp[-3].str)); appendChild(temp,root); appendBrother((yyvsp[-1].ast),temp);}}
#line 1491 "y.tab.c" /* yacc.c:1646  */
    break;

  case 3:
#line 128 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {appendBrother((yyvsp[0].ast), (yyvsp[-1].ast)); (yyval.ast) = (yyvsp[-1].ast);}}
#line 1497 "y.tab.c" /* yacc.c:1646  */
    break;

  case 4:
#line 129 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {appendBrother((yyvsp[0].ast), (yyvsp[-1].ast)); (yyval.ast) = (yyvsp[-1].ast);}}
#line 1503 "y.tab.c" /* yacc.c:1646  */
    break;

  case 5:
#line 130 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[-1].ast);}}
#line 1509 "y.tab.c" /* yacc.c:1646  */
    break;

  case 6:
#line 131 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = createNode("NULL", "NULL"); }}
#line 1515 "y.tab.c" /* yacc.c:1646  */
    break;

  case 7:
#line 135 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); appendChild((yyvsp[-3].ast), temp); temp1 = createNode("Id",(yyvsp[-2].str)); free((yyvsp[-2].str)); appendBrother(temp1,(yyvsp[-3].ast)); appendBrother((yyvsp[-1].ast), temp); (yyval.ast) = temp;}}
#line 1521 "y.tab.c" /* yacc.c:1646  */
    break;

  case 8:
#line 136 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); appendChild((yyvsp[-2].ast), temp); temp1 = createNode("Id",(yyvsp[-1].str)); free((yyvsp[-1].str)); appendBrother(temp1, (yyvsp[-2].ast)); (yyval.ast) = temp;}}
#line 1527 "y.tab.c" /* yacc.c:1646  */
    break;

  case 9:
#line 137 "jac.y" /* yacc.c:1646  */
    {{(yyval.ast) = NULL;}}
#line 1533 "y.tab.c" /* yacc.c:1646  */
    break;

  case 10:
#line 141 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = createNode("Bool", "NULL"); strcpy(varType, "Bool");}}
#line 1539 "y.tab.c" /* yacc.c:1646  */
    break;

  case 11:
#line 142 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = createNode("Int", "NULL"); strcpy(varType, "Int");}}
#line 1545 "y.tab.c" /* yacc.c:1646  */
    break;

  case 12:
#line 143 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = createNode("Double", "NULL"); strcpy(varType, "Double");}}
#line 1551 "y.tab.c" /* yacc.c:1646  */
    break;

  case 13:
#line 147 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); temp1 = createNode(varType, "NULL"); appendChild(temp1,temp); temp2=createNode("Id", (yyvsp[-1].str)); free((yyvsp[-1].str)); appendBrother(temp2,temp1); appendBrother((yyvsp[0].ast),temp);(yyval.ast) = temp;}}
#line 1557 "y.tab.c" /* yacc.c:1646  */
    break;

  case 14:
#line 148 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("FieldDecl", "NULL"); temp1 = createNode(varType, "NULL"); appendChild(temp1, temp); temp2=createNode("Id", (yyvsp[0].str)); free((yyvsp[0].str)); appendBrother(temp2, temp1);(yyval.ast) = temp;}}
#line 1563 "y.tab.c" /* yacc.c:1646  */
    break;

  case 15:
#line 152 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("MethodDecl", "NULL"); appendChild((yyvsp[-1].ast),temp); appendBrother((yyvsp[0].ast),(yyvsp[-1].ast)); (yyval.ast) = temp;}}
#line 1569 "y.tab.c" /* yacc.c:1646  */
    break;

  case 16:
#line 156 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); appendChild((yyvsp[-3].ast),temp); temp1=createNode("Id", (yyvsp[-2].str)); free((yyvsp[-2].str)); appendBrother(temp1, (yyvsp[-3].ast)); temp2 = createNode("MethodParams", "NULL"); appendBrother(temp2, temp1); (yyval.ast) = temp;}}
#line 1575 "y.tab.c" /* yacc.c:1646  */
    break;

  case 17:
#line 157 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); appendChild((yyvsp[-4].ast),temp); temp1=createNode("Id", (yyvsp[-3].str)); free((yyvsp[-3].str)); appendBrother(temp1, (yyvsp[-4].ast)); temp2 = createNode("MethodParams", "NULL"); appendBrother(temp2, temp1); appendChild((yyvsp[-1].ast), temp2); (yyval.ast) = temp;}}
#line 1581 "y.tab.c" /* yacc.c:1646  */
    break;

  case 18:
#line 158 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); temp1 = createNode("Void", "NULL"); appendChild(temp1,temp); temp2=createNode("Id", (yyvsp[-2].str)); free((yyvsp[-2].str)); appendBrother(temp2, temp1); temp3 = createNode("MethodParams", "NULL"); appendBrother(temp3, temp2); (yyval.ast) = temp;}}
#line 1587 "y.tab.c" /* yacc.c:1646  */
    break;

  case 19:
#line 159 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("MethodHeader", "NULL"); temp1 = createNode("Void", "NULL"); appendChild(temp1,temp); temp2=createNode("Id", (yyvsp[-3].str)); free((yyvsp[-3].str)); appendBrother(temp2, temp1); temp3 = createNode("MethodParams", "NULL"); appendBrother(temp3, temp2); appendChild((yyvsp[-1].ast),temp3); (yyval.ast) = temp;}}
#line 1593 "y.tab.c" /* yacc.c:1646  */
    break;

  case 20:
#line 163 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild((yyvsp[-2].ast), temp); temp1 = createNode("Id", (yyvsp[-1].str)); appendBrother(temp1, (yyvsp[-2].ast)); appendBrother((yyvsp[0].ast),temp); (yyval.ast)=temp;}}
#line 1599 "y.tab.c" /* yacc.c:1646  */
    break;

  case 21:
#line 164 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild((yyvsp[-1].ast), temp); temp1 = createNode("Id", (yyvsp[0].str)); appendBrother(temp1, (yyvsp[-1].ast)); (yyval.ast)=temp;}}
#line 1605 "y.tab.c" /* yacc.c:1646  */
    break;

  case 22:
#line 165 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("ParamDecl", "NULL"); temp1 = createNode("StringArray", "NULL"); appendChild(temp1,temp); temp2 = createNode("Id", (yyvsp[0].str)); free((yyvsp[0].str));appendBrother(temp2,temp1); (yyval.ast)=temp;}}
#line 1611 "y.tab.c" /* yacc.c:1646  */
    break;

  case 23:
#line 169 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild((yyvsp[-2].ast),temp); temp1 = createNode("Id", (yyvsp[-1].str)); free((yyvsp[-1].str)); appendBrother(temp1, (yyvsp[-2].ast)); appendBrother((yyvsp[0].ast),temp); (yyval.ast) = temp;}}
#line 1617 "y.tab.c" /* yacc.c:1646  */
    break;

  case 24:
#line 170 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("ParamDecl","NULL"); appendChild((yyvsp[-1].ast), temp); temp1 = createNode("Id", (yyvsp[0].str)); free((yyvsp[0].str)); appendBrother(temp1, (yyvsp[-1].ast)); (yyval.ast) = temp;}}
#line 1623 "y.tab.c" /* yacc.c:1646  */
    break;

  case 25:
#line 174 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("MethodBody", "NULL"); appendChild((yyvsp[-1].ast), temp); (yyval.ast) = temp;}}
#line 1629 "y.tab.c" /* yacc.c:1646  */
    break;

  case 26:
#line 178 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {appendBrother((yyvsp[0].ast), (yyvsp[-1].ast)); (yyval.ast) = (yyvsp[-1].ast);}}
#line 1635 "y.tab.c" /* yacc.c:1646  */
    break;

  case 27:
#line 179 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {appendBrother((yyvsp[0].ast), (yyvsp[-1].ast)); (yyval.ast) = (yyvsp[-1].ast);}}
#line 1641 "y.tab.c" /* yacc.c:1646  */
    break;

  case 28:
#line 180 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = createNode("NULL", "NULL");}}
#line 1647 "y.tab.c" /* yacc.c:1646  */
    break;

  case 29:
#line 184 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("VarDecl","NULL"); appendChild((yyvsp[-3].ast), temp); temp1 = createNode("Id", (yyvsp[-2].str)); free((yyvsp[-2].str)); appendBrother(temp1, (yyvsp[-3].ast)); appendBrother((yyvsp[-1].ast), temp); (yyval.ast)=temp;}}
#line 1653 "y.tab.c" /* yacc.c:1646  */
    break;

  case 30:
#line 185 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("VarDecl", "NULL"); appendChild((yyvsp[-2].ast), temp); temp1 = createNode("Id", (yyvsp[-1].str)); free((yyvsp[-1].str)); appendBrother(temp1, (yyvsp[-2].ast)); (yyval.ast) = temp;}}
#line 1659 "y.tab.c" /* yacc.c:1646  */
    break;

  case 31:
#line 189 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("VarDecl", "NULL"); (yyval.ast)=temp; temp1 = createNode(varType,"NULL"); appendChild(temp1, temp); temp2 = createNode("Id", (yyvsp[-1].str)); free((yyvsp[-1].str)); appendBrother(temp2, temp1); appendBrother((yyvsp[0].ast),temp);}}
#line 1665 "y.tab.c" /* yacc.c:1646  */
    break;

  case 32:
#line 190 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("VarDecl", "NULL"); (yyval.ast)=temp; temp1 = createNode(varType, "NULL"); temp2 = createNode("Id", (yyvsp[0].str)); free((yyvsp[0].str)); appendChild(temp1,temp); appendBrother(temp2, temp1); }}
#line 1671 "y.tab.c" /* yacc.c:1646  */
    break;

  case 33:
#line 194 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = NULL;}}
#line 1677 "y.tab.c" /* yacc.c:1646  */
    break;

  case 34:
#line 195 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = NULL;}}
#line 1683 "y.tab.c" /* yacc.c:1646  */
    break;

  case 35:
#line 196 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {if((yyvsp[-1].ast)!=NULL){
																											if((yyvsp[-1].ast)->brother != NULL){
																												temp = createNode("Block","NULL");
																												appendChild((yyvsp[-1].ast), temp); (yyval.ast) = temp;
																											}else{
																												(yyval.ast) = (yyvsp[-1].ast);
																											}
																											} else {(yyval.ast) = (yyvsp[-1].ast);}
																										}
																				}
#line 1698 "y.tab.c" /* yacc.c:1646  */
    break;

  case 36:
#line 206 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1704 "y.tab.c" /* yacc.c:1646  */
    break;

  case 37:
#line 207 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1710 "y.tab.c" /* yacc.c:1646  */
    break;

  case 38:
#line 208 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1716 "y.tab.c" /* yacc.c:1646  */
    break;

  case 39:
#line 209 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1722 "y.tab.c" /* yacc.c:1646  */
    break;

  case 40:
#line 210 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[-1].ast);}}
#line 1728 "y.tab.c" /* yacc.c:1646  */
    break;

  case 41:
#line 211 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[-1].ast);}}
#line 1734 "y.tab.c" /* yacc.c:1646  */
    break;

  case 42:
#line 212 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[-1].ast);}}
#line 1740 "y.tab.c" /* yacc.c:1646  */
    break;

  case 43:
#line 213 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1746 "y.tab.c" /* yacc.c:1646  */
    break;

  case 44:
#line 214 "jac.y" /* yacc.c:1646  */
    {(yyval.ast) = NULL;}
#line 1752 "y.tab.c" /* yacc.c:1646  */
    break;

  case 45:
#line 218 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("If","NULL"); appendChild( (yyvsp[-2].ast), temp); (yyval.ast) = temp; temp1 = createNode("Block","NULL"); if((yyvsp[0].ast) != NULL){
																																															if((yyvsp[0].ast)->brother != NULL){
																																																temp = createNode("Block","NULL");
																																																appendBrother(temp, (yyvsp[-2].ast));
																																																appendChild((yyvsp[0].ast), temp);
																																																appendChild(temp1, (yyval.ast));
																																															} else{
																																																appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));
																																																appendBrother(temp1, (yyvsp[0].ast));
																																															}
																																														} else{
																																															appendBrother(temp1, (yyvsp[-2].ast));
																																															temp2 = createNode("Block","NULL");
																																															appendBrother(temp2, (yyvsp[-2].ast));
																																														}
																				}}
#line 1773 "y.tab.c" /* yacc.c:1646  */
    break;

  case 46:
#line 235 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("If","NULL"); appendChild( (yyvsp[-4].ast), temp); (yyval.ast) = temp;
																										if((yyvsp[-2].ast) != NULL){
																											if((yyvsp[-2].ast)->brother!=NULL){
																												temp2 = createNode("Block","NULL");
																												appendBrother(temp2, (yyvsp[-4].ast));
																												appendChild((yyvsp[-2].ast), temp2);
																											}else{
																												appendBrother((yyvsp[-2].ast), (yyvsp[-4].ast));
																											}
																										}else{
																											temp1 = createNode("Block","NULL");
																											appendBrother(temp1, (yyvsp[-4].ast));
																										}
																										if((yyvsp[0].ast) != NULL){
																											if((yyvsp[0].ast)->brother != NULL){
																												temp3 = createNode("Block","NULL");
																												appendBrother(temp3, (yyvsp[-4].ast));
																												appendChild((yyvsp[0].ast),temp3);

																											}else appendBrother((yyvsp[0].ast), (yyvsp[-4].ast));
																										}else{
																											temp3 = createNode("Block","NULL");
																											appendBrother(temp3, (yyvsp[-4].ast));
																										}
																				}}
#line 1803 "y.tab.c" /* yacc.c:1646  */
    break;

  case 47:
#line 263 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("While","NULL");  appendChild((yyvsp[-2].ast), temp); (yyval.ast) = temp; if((yyvsp[0].ast) != NULL){
																																								if((yyvsp[0].ast)->brother != NULL){
																																									temp2 = createNode("Block","NULL");
																																									appendChild((yyvsp[0].ast), temp2);
																																									appendBrother(temp2, (yyvsp[-2].ast));
																																								} else appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));
																																							} else {
																																								temp1 = createNode("Block","NULL");
																																								appendBrother(temp1, (yyvsp[-2].ast));
																																							}
																				}}
#line 1819 "y.tab.c" /* yacc.c:1646  */
    break;

  case 48:
#line 277 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("DoWhile","NULL"); (yyval.ast)=temp; if((yyvsp[-5].ast) != NULL){
																																	if((yyvsp[-5].ast)->brother != NULL){
																																		temp2 = createNode("Block","NULL");
																																		appendChild((yyvsp[-5].ast), temp2);
																																		appendBrother((yyvsp[-2].ast), temp2);
																																		appendChild(temp2, temp);
																																	}else{
																																		appendBrother((yyvsp[-2].ast), (yyvsp[-5].ast));
																																		appendChild((yyvsp[-5].ast), temp);
																																	}
																																}else{
																																	temp1 = createNode("Block","NULL");
																																	appendBrother((yyvsp[-2].ast), temp1);
																																	appendChild(temp1, temp);
																																}
																				}}
#line 1840 "y.tab.c" /* yacc.c:1646  */
    break;

  case 49:
#line 296 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Print","NULL"); (yyval.ast) = temp; appendChild((yyvsp[-2].ast), temp); }}
#line 1846 "y.tab.c" /* yacc.c:1646  */
    break;

  case 50:
#line 297 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Print","NULL"); (yyval.ast) = temp; temp1 = createNode("StrLit",(yyvsp[-2].str));  appendChild(temp1, temp);}}
#line 1852 "y.tab.c" /* yacc.c:1646  */
    break;

  case 51:
#line 301 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Id",(yyvsp[-2].str)); free((yyvsp[-2].str)); temp1 = createNode("Assign","NULL"); (yyval.ast) = temp1; appendChild(temp, temp1); appendBrother((yyvsp[0].ast), temp);}}
#line 1858 "y.tab.c" /* yacc.c:1646  */
    break;

  case 52:
#line 305 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Call","NULL"); (yyval.ast)=temp; temp1 = createNode("Id",(yyvsp[-2].str)); free((yyvsp[-2].str)); appendChild(temp1, temp); }}
#line 1864 "y.tab.c" /* yacc.c:1646  */
    break;

  case 53:
#line 306 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Call","NULL"); (yyval.ast)=temp; temp1 = createNode("Id",(yyvsp[-3].str)); free((yyvsp[-3].str)); appendChild(temp1, temp); appendBrother((yyvsp[-1].ast), temp1);}}
#line 1870 "y.tab.c" /* yacc.c:1646  */
    break;

  case 54:
#line 307 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Call","NULL"); (yyval.ast)=temp; temp1 = createNode("Id",(yyvsp[-4].str)); free((yyvsp[-4].str)); appendChild(temp1, temp); appendBrother((yyvsp[-2].ast), temp1); if((yyvsp[-2].ast)!= NULL) appendBrother((yyvsp[-1].ast), (yyvsp[-2].ast)); else appendBrother((yyvsp[-1].ast), temp1);}}
#line 1876 "y.tab.c" /* yacc.c:1646  */
    break;

  case 55:
#line 308 "jac.y" /* yacc.c:1646  */
    {{(yyval.ast) = NULL;}}
#line 1882 "y.tab.c" /* yacc.c:1646  */
    break;

  case 56:
#line 312 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {appendBrother((yyvsp[0].ast), (yyvsp[-2].ast)); (yyval.ast)=(yyvsp[-2].ast);}}
#line 1888 "y.tab.c" /* yacc.c:1646  */
    break;

  case 57:
#line 313 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1894 "y.tab.c" /* yacc.c:1646  */
    break;

  case 58:
#line 317 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("ParseArgs","NULL"); (yyval.ast) = temp; temp1 = createNode("Id",(yyvsp[-4].str)); free((yyvsp[-4].str)); appendChild(temp1, temp); appendBrother((yyvsp[-2].ast), temp1);}}
#line 1900 "y.tab.c" /* yacc.c:1646  */
    break;

  case 59:
#line 318 "jac.y" /* yacc.c:1646  */
    {{(yyval.ast) = NULL;}}
#line 1906 "y.tab.c" /* yacc.c:1646  */
    break;

  case 60:
#line 322 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Return","NULL"); (yyval.ast) = temp;}}
#line 1912 "y.tab.c" /* yacc.c:1646  */
    break;

  case 61:
#line 323 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Return","NULL"); (yyval.ast) = temp; appendChild((yyvsp[-1].ast), temp);}}
#line 1918 "y.tab.c" /* yacc.c:1646  */
    break;

  case 62:
#line 327 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1924 "y.tab.c" /* yacc.c:1646  */
    break;

  case 63:
#line 328 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {if((yyvsp[-1].ast) != NULL){
																											(yyval.ast) = (yyvsp[-1].ast);
																											appendBrother((yyvsp[0].ast), (yyval.ast));
																										}else{(yyval.ast) = (yyvsp[0].ast);}}}
#line 1933 "y.tab.c" /* yacc.c:1646  */
    break;

  case 64:
#line 335 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1939 "y.tab.c" /* yacc.c:1646  */
    break;

  case 65:
#line 336 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1945 "y.tab.c" /* yacc.c:1646  */
    break;

  case 66:
#line 340 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1951 "y.tab.c" /* yacc.c:1646  */
    break;

  case 67:
#line 341 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[0].ast);}}
#line 1957 "y.tab.c" /* yacc.c:1646  */
    break;

  case 68:
#line 342 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Id",(yyvsp[0].str)); free((yyvsp[0].str)); (yyval.ast) = temp;}}
#line 1963 "y.tab.c" /* yacc.c:1646  */
    break;

  case 69:
#line 343 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Length","NULL"); (yyval.ast) = temp; temp1 = createNode("Id",(yyvsp[-1].str)); free((yyvsp[-1].str)); appendChild(temp1, temp);}}
#line 1969 "y.tab.c" /* yacc.c:1646  */
    break;

  case 70:
#line 344 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = (yyvsp[-1].ast);}}
#line 1975 "y.tab.c" /* yacc.c:1646  */
    break;

  case 71:
#line 345 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Plus","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[0].ast), temp);}}
#line 1981 "y.tab.c" /* yacc.c:1646  */
    break;

  case 72:
#line 346 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Minus","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[0].ast), temp);}}
#line 1987 "y.tab.c" /* yacc.c:1646  */
    break;

  case 73:
#line 347 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Not","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[0].ast), temp);}}
#line 1993 "y.tab.c" /* yacc.c:1646  */
    break;

  case 74:
#line 348 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Eq","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 1999 "y.tab.c" /* yacc.c:1646  */
    break;

  case 75:
#line 349 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Geq","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2005 "y.tab.c" /* yacc.c:1646  */
    break;

  case 76:
#line 350 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("And","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2011 "y.tab.c" /* yacc.c:1646  */
    break;

  case 77:
#line 351 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Or","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2017 "y.tab.c" /* yacc.c:1646  */
    break;

  case 78:
#line 352 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Gt","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2023 "y.tab.c" /* yacc.c:1646  */
    break;

  case 79:
#line 353 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Leq","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2029 "y.tab.c" /* yacc.c:1646  */
    break;

  case 80:
#line 354 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Lt","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2035 "y.tab.c" /* yacc.c:1646  */
    break;

  case 81:
#line 355 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Neq","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2041 "y.tab.c" /* yacc.c:1646  */
    break;

  case 82:
#line 356 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Mul","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2047 "y.tab.c" /* yacc.c:1646  */
    break;

  case 83:
#line 357 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Div","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2053 "y.tab.c" /* yacc.c:1646  */
    break;

  case 84:
#line 358 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Mod","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2059 "y.tab.c" /* yacc.c:1646  */
    break;

  case 85:
#line 359 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Add","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2065 "y.tab.c" /* yacc.c:1646  */
    break;

  case 86:
#line 360 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("Sub","NULL"); (yyval.ast) = temp; appendChild( (yyvsp[-2].ast), temp); appendBrother((yyvsp[0].ast), (yyvsp[-2].ast));}}
#line 2071 "y.tab.c" /* yacc.c:1646  */
    break;

  case 87:
#line 361 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("DecLit",(yyvsp[0].str)); free((yyvsp[0].str)); (yyval.ast) = temp;}}
#line 2077 "y.tab.c" /* yacc.c:1646  */
    break;

  case 88:
#line 362 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("BoolLit",(yyvsp[0].str)); free((yyvsp[0].str)); (yyval.ast) = temp;}}
#line 2083 "y.tab.c" /* yacc.c:1646  */
    break;

  case 89:
#line 363 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {temp = createNode("RealLit",(yyvsp[0].str)); free((yyvsp[0].str)); (yyval.ast) = temp;}}
#line 2089 "y.tab.c" /* yacc.c:1646  */
    break;

  case 90:
#line 364 "jac.y" /* yacc.c:1646  */
    {if(erros_sintaxe == 0) {(yyval.ast) = NULL;}}
#line 2095 "y.tab.c" /* yacc.c:1646  */
    break;


#line 2099 "y.tab.c" /* yacc.c:1646  */
      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYTERROR;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined yyoverflow || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  return yyresult;
}
#line 367 "jac.y" /* yacc.c:1906  */



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
