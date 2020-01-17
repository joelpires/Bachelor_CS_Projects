
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "symbol_table.h"

typedef struct parseNode {
	char *key;
	char *value;
	struct parseNode *brother;
	struct parseNode *child;
} ParseNode;


typedef struct method{
	char *header;
	param_types *parameters;
	table_element* first;
} Method;

ParseNode* createNode(char* key, char* value);
void appendChild(ParseNode* child, ParseNode* node);
void appendBrother(ParseNode* brother, ParseNode* node);
void deleteNode(ParseNode* node);
void deleteAll(ParseNode* root);
void printParseTree (ParseNode * no, int n_points);
void printNotedParseTree(ParseNode* no, int n_points);
void buildTables(ParseNode* root);
void buildNotedTree(ParseNode* no);
void insert_currMethodParams(param_types* param, char* type);
ParseNode* getNextBrother(ParseNode* node);
param_types* getMethodParams(ParseNode* node);
char* getNodeKey(ParseNode* node);
char* getNodeType(ParseNode* node);
char* convertTypes(char* type);
char* searchTypeSymbol(char* name);
int checkOperations(char* key);
char* searchReturn (char* name);
void newCurrentMethodSymbol(Method* MethodTable, char* name, char* type);
char* searchInScope(char* name);
void insert_param_types(param_types* method_params, char* type, char* name);
