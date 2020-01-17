#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "parsingTree.h"

int n_points;
int varDecl = 0, statement = 0;
int call = 0, operations = 0;
int myType = 0;
int firstCall = 1;
char** callTypes;
int callArguments = 0;


Method* currentMethod;

ParseNode* temp;

/*-----------------------------------------------------------------*/
ParseNode* createNode(char* key, char* value) {
	ParseNode* node = (ParseNode*) malloc(sizeof(ParseNode));
	node->key = (char*) malloc(sizeof(char)*(strlen(key)+5));
	node->value = (char*) malloc( sizeof(char)*(strlen(value)+5));

	strcpy(node->key, key);
	strcpy(node->value, value);
	node->child = NULL;
	node->brother = NULL;
	return node;
}


/*-----------------------------------------------------------------*/
void appendChild(ParseNode* child, ParseNode* node) {
	if (node != NULL) {
		if(node->child == NULL){
			node->child = child;
		}
	}
}

/*-----------------------------------------------------------------*/
void appendBrother(ParseNode* brother, ParseNode* node) {
	if (node != NULL) {

		while(node->brother != NULL){
			node = node->brother;
		}
		node->brother = brother;
	}
}

/*----------------------------------s-------------------------------*/
void deleteNode(ParseNode* node){
	free(node->key);
	free(node->value);
	free(node);
}

/*-----------------------------------------------------------------*/
void deleteAll(ParseNode* parseTree) {
    if(parseTree != NULL){
		if(parseTree->brother != NULL){
			deleteAll(parseTree->brother);
		}
    	if(parseTree->child != NULL){
			deleteAll(parseTree->child);
		}
		deleteNode(parseTree);
	}
}

void printNotedParseTree(ParseNode* no, int n_points){
	int i;
	if(no == NULL){
		return;
	}
	char* type = getNodeType(no);
	if(type != NULL){
		if(strcmp(no->value,"NULL") != 0 ){
			for(i=0; i< n_points; i++)
				printf(".");
			printf("%s(%s) - %s\n", getNodeKey(no), no->value,type);
		}
		else{
		    if (strcmp(no->key,"NULL") == 0);
		    else{
			    for(i=0; i< n_points; i++)
						printf(".");
		      printf("%s - %s\n", getNodeKey(no), type);
		    }
	   }
	}
	else{
		if(strcmp(no->value,"NULL") != 0 ){
			for(i=0; i< n_points; i++)
				printf(".");
			printf("%s(%s)\n", no->key, no->value);
		}
		else{
		    if (strcmp(no->key,"NULL") == 0);
		    else{
			    for(i=0; i< n_points; i++)
						printf(".");
		      printf("%s\n", no->key);
		    }
	   }
	}

	printNotedParseTree(no->child, n_points+2);
	printNotedParseTree(no->brother, n_points);
}

void printParseTree (ParseNode * no, int n_points) {
	int i;
	if(no == NULL){
		return;
	}
	if(strcmp(no->value,"NULL") != 0 ){
		for(i=0; i< n_points; i++)
			printf(".");
		printf("%s(%s)\n", no->key, no->value);
	}
	else{
	    if (strcmp(no->key,"NULL") == 0);
	    else{
		    for(i=0; i< n_points; i++)
					printf(".");
	      printf("%s\n", no->key);
	    }
   }

	printParseTree(no->child, n_points+2);
	printParseTree(no->brother, n_points);
}



void buildTables(ParseNode* root) {
	ParseNode* temp = root, *aux, *aux2, *field_decl;
	ParseNode* method_header, *method_body;
	table_element *return_symbol;
	param_types* method_params;
	if(strcmp(temp->key, "Program") == 0){
		//cria a tabela global
		temp = temp->child;
		createTable(temp->value, NULL);
		temp = temp->brother;

		while(temp != NULL){
			if(strcmp(temp->key, "NULL") != 0){
				if(strcmp(temp->key, "FieldDecl") == 0){
					field_decl = temp->child;
					aux = getNextBrother(field_decl);	//representa tipo da variavel global
					field_decl = aux->brother;
					aux2 = getNextBrother(field_decl); //representa nome da variavel global

					newGlobalFieldSymbol(tableList->t, aux2->value,aux->key);
				}
				else if(strcmp(temp->key, "MethodDecl") == 0){
					//analise do method header
					method_header = temp->child;
					aux = method_header->child;
					return_symbol = createSymbol("return", aux->key, NULL, NULL,0);
					aux = aux->brother;		//representa o nome do metodo
					aux2 = aux-> brother;
					method_params = getMethodParams(aux2);
					if(method_params != NULL){
						createTable(aux->value, method_params->next);
						newGlobalMethodSymbol(tableList->t, aux->value, method_params->next, return_symbol->type);
						newLocalSymbol(return_symbol->name, NULL, return_symbol->type, NULL);
						addMethodParamSymbol(method_params->next);
					}
					else {
						createTable(aux->value, NULL);
						newGlobalMethodSymbol(tableList->t, aux->value, NULL, return_symbol->type);
						newLocalSymbol(return_symbol->name, NULL, return_symbol->type, NULL);
					}

					//analise do method body
					method_body = method_header -> brother;
					aux = method_body->child;
					while(aux != NULL){
						if(strcmp(aux->key, "VarDecl")==0){
							newLocalSymbol(aux->child->brother->value, NULL, aux->child->key, NULL);
						}
						aux = aux->brother;
					}
				}
			}
			temp = temp->brother;
		}
	}
}


void buildNotedTree(ParseNode* no){
	char* str;

	if(no == NULL){
		return;
	}
	if (strcmp(no->key, "MethodBody") == 0) {
		statement = 1;
	}
	else if(strcmp(no->key, "VarDecl") == 0){
		varDecl = 1;
		newCurrentMethodSymbol(currentMethod, no->child->brother->value, convertTypes(no->child->key));
	} else if(strcmp(no->key, "MethodDecl") == 0){
		statement = 0;
	}
	else if(strcmp(no->key, "MethodHeader") == 0){
		currentMethod = (Method*) malloc(sizeof(Method));

		currentMethod->parameters = (param_types*) malloc(sizeof(param_types));
		currentMethod->parameters->name = NULL;
		currentMethod->parameters->type = NULL;
		currentMethod->parameters->next = NULL;
		currentMethod->first = NULL;
		currentMethod->header = (char*) malloc(sizeof(char)*(strlen(no->child->brother->value))+5);
		strcpy(currentMethod->header, no->child->brother->value);
	}
	else if(strcmp(no->key, "ParamDecl") == 0){
		newCurrentMethodSymbol(currentMethod, no->child->brother->value, convertTypes(no->child->key));

		if(strcmp(no->child->key, "Bool") == 0){
			/*INSERT CURRENT METHOD*/
			param_types* new = (param_types*) malloc(sizeof(param_types));
			new->name = NULL;
			new->type = (char*) malloc(sizeof(char)*7+5);;
			strcpy(new->type, "boolean");
			new->next = NULL;

			if(currentMethod->parameters == NULL)
				currentMethod->parameters->next = new;
			else{
				while(currentMethod->parameters->next != NULL){
					currentMethod->parameters = currentMethod->parameters->next;
				}
				currentMethod->parameters->next = new;
			}

		}
		else if(strcmp(no->child->key, "Int") == 0){

			/*INSERT CURRENT METHOD*/
			param_types* new = (param_types*) malloc(sizeof(param_types));
			new->name = NULL;
			new->type = (char*) malloc(sizeof(char)*3+5);;
			strcpy(new->type, "int");
			new->next = NULL;

			if(currentMethod->parameters == NULL)
				currentMethod->parameters->next = new;
			else{
				while(currentMethod->parameters->next != NULL){
					currentMethod->parameters = currentMethod->parameters->next;
				}
				currentMethod->parameters->next = new;
			}

		}
		else if(strcmp(no->child->key, "Double") == 0){
			/*INSERT CURRENT METHOD*/
			param_types* new = (param_types*) malloc(sizeof(param_types));
			new->name = NULL;
			new->type = (char*) malloc(sizeof(char)*6+5);;
			strcpy(new->type, "double");
			new->next = NULL;

			if(currentMethod->parameters == NULL)
				currentMethod->parameters->next = new;
			else{
				while(currentMethod->parameters->next != NULL){
					currentMethod->parameters = currentMethod->parameters->next;
				}
				currentMethod->parameters->next = new;
			}
		}
		else {
			/*INSERT CURRENT METHOD*/
			param_types* new = (param_types*) malloc(sizeof(param_types));
			new->name = NULL;
			new->type = (char*) malloc(sizeof(char)*11+5);;
			strcpy(new->type, "StringArray");
			new->next = NULL;

			if(currentMethod->parameters == NULL)
				currentMethod->parameters->next = new;
			else{
				while(currentMethod->parameters->next != NULL){
					currentMethod->parameters = currentMethod->parameters->next;
				}
				currentMethod->parameters->next = new;
			}
		}
	}
	else if(strcmp(no->key, "Not") == 0 || strcmp(no->key, "And") == 0 || strcmp(no->key, "Or") == 0 || strcmp(no->key, "Geq") == 0 || strcmp(no->key, "Leq") == 0 || strcmp(no->key, "Neq") == 0 || strcmp(no->key, "Eq") == 0 || strcmp(no->key, "Lt") == 0 || strcmp(no->key, "Gt") == 0){
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+15));
		strcat(no->key, " - boolean");

	}
	else if(strcmp(no->key, "Length") == 0 || strcmp(no->key, "ParseArgs") == 0){
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+11));
		strcat(no->key, " - int");

	} else if (strcmp(no->key, "ParseArgs") == 0 ){
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+11));
		strcat(no->key, " - int");
	} else if (strcmp(no->key, "BoolLit") == 0 ){
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+15));
		strcat(no->key, " - boolean");
	} else if(strcmp(no->key, "DecLit") == 0 ) {
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+11));
		strcat(no->key, " - int");
		if (call) {
			if (firstCall != 1) {
				callTypes[callArguments] = strdup("int");
				callArguments++;
			}
			firstCall++;
		}
	} else if (strcmp(no->key, "Id") == 0  && statement == 1 && varDecl == 0){
		if (call) {
			if (firstCall != 1) {
				str = strdup(searchInScope(no->value));
				callTypes[callArguments] = strdup(str);
				callArguments++;
				no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+8+strlen(str)));
				strcat(no->key, " - ");
				strcat(no->key, str);
			}
			firstCall++;
		} else if(operations) {
			str = strdup(searchInScope(no->value));
			no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+8+strlen(str)));
			strcat(no->key, " - ");
			strcat(no->key, str);
		} else {
			str = strdup(searchInScope(no->value));
			no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+8+strlen(str)));
			strcat(no->key, " - ");
			strcat(no->key, str);
		}
	}
	else if(strcmp(no->key, "RealLit") == 0){
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+14));
		strcat(no->key," - double");
		if (call) {
			if (firstCall != 1) {
				callTypes[callArguments] = strdup("double");
				callArguments++;
			}
			firstCall++;
		}
	}
	else if(strcmp(no->key, "StrLit") == 0){
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+15));
		strcat(no->key, " - String[]");
	}
	else if(strcmp(no->key, "Assign") == 0){

		str = strdup(searchInScope(no->child->value));
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+8+strlen(str)));
		strcat(no->key, " - ");
		strcat(no->key, str);
	} else if(strcmp(no->key, "Call") == 0){
		call = 1;
		callTypes = (char**)malloc(sizeof(char*)*1000);
		firstCall = 1;
	} else if(checkOperations(no->key)){
		operations = 1;
	}

	buildNotedTree(no->child);
	buildNotedTree(no->brother);





	if (varDecl) {
		varDecl = 0;
	}

	/*atribuir o type Às operações*/
	/*if(checkOperations(no->key)){
		//encontrei um double
		if (myType) {
			str = " - double";
		} else {
			str = " - int";
		}
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+5+strlen(str)));
		strcat(no->key, str);
		operations = 0;
   	}*/

	/*atribuir os parametros ao primeiro filho do call*/
	if (strcmp(no->key, "Call") == 0) {
		int i;
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+9));
		strcat(no->child->key, " - (");
		for (i = 0; i < callArguments; i++) {
			no->child->key = (char*) realloc(no->child->key, sizeof(char)*(strlen(callTypes[i])+6));
			strcat(no->child->key, callTypes[i]);
			if (i != callArguments-1) {
				no->child->key = (char*) realloc(no->child->key, sizeof(char)*6);
				strcat(no->child->key, ",");
			}
		}
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+6));
		strcat(no->child->key, ")");


		str = strdup(searchReturn(no->child->value));
		no->key = (char*) realloc(no->key, sizeof(char)*(strlen(no->key)+8+strlen(str)));
		strcat(no->key, " - ");
		strcat(no->key, str);

		call = 0;
		firstCall = 1;
		callArguments = 0;

	}

}


char* searchReturn (char* name) {


	table_list* current_table = (table_list*)malloc(sizeof(table_list));
	current_table = tableList;
	int i;
	param_types* aux = (param_types*)malloc(sizeof(param_types));
	table_element* aux4 = (table_element*)malloc(sizeof(table_element));

	/*LETS FIND THE GLOBAL TABLE*/
	aux4 = current_table->t->first;
	while (aux4 != NULL) {
		/*check name*/
		if (strcmp(aux4->name, name) == 0) {
			/*check parameters*/
			aux = aux4->params;
			i = 0;
			while (aux != NULL && i < callArguments) {
				if (strcmp(convertTypes(aux->type), callTypes[i]) != 0) {
					break;
				}
				aux = aux->next;
				i++;
			}
			if (i == callArguments) {
				return convertTypes(aux4->type);
			}

		}
		aux4 = aux4->next;
	}
	return "undef";
}


int checkOperations(char* key){
	if (strcmp("Add", key) == 0) {
		return 1;
	} else if(strcmp("Sub", key) == 0){
		return 1;
	} else if(strcmp("Mul", key) == 0){
		return 1;
	} else if(strcmp("Div", key) == 0){
		return 1;
	}  else if(strcmp("Mod", key) == 0){
		return 1;
	}	else {
		return 0;
	}
}

char* searchInScope(char* name){
	table_element* aux3 = (table_element*)malloc(sizeof(table_element));
	table_element* aux4 = (table_element*)malloc(sizeof(table_element));

	//lets find the right symbol
	aux3 = currentMethod->first;
	while (aux3 != NULL) {
		if (strcmp(aux3->name, name) == 0) {
			return convertTypes(aux3->type);
		}
		aux3 = aux3->next;
	}

	/*LETS FIND THE GLOBAL TABLE*/
	aux4 = tableList->t->first;
	while (aux4 != NULL) {
		if (strcmp(aux4->name, name) == 0) {
			return convertTypes(aux4->type);
		}
		aux4 = aux4->next;
	}

	return "undef";
}

//retorna proximo irmao nao nulo
ParseNode* getNextBrother(ParseNode* node){
		while(strcmp(node->key, "NULL") == 0)
			node = node->brother;
		return node;
}

//retorna os parametros de um metodo para serem inseridos nas tabelas
param_types* getMethodParams(ParseNode* node){
param_types* method_params;

		if(node->child == NULL){
			return NULL;
		}

		node = node->child; 	//node == ParamDecl

		while(node != NULL){
			if(node->child->key != NULL){
				param_types* new = (param_types*) malloc(sizeof(param_types));
				new->type = (char*) malloc(sizeof(char)*(strlen(node->child->key))+5);
				new->name = (char*) malloc( sizeof(char)*(strlen(node->child->brother->value))+5);
				new->next = NULL;

				method_params = (param_types*) malloc(sizeof(param_types));
				method_params->type = (char*) malloc(sizeof(char)*(strlen(node->child->key))+5);
				method_params->name = (char*) malloc( sizeof(char)*(strlen(node->child->brother->value))+5);
				method_params->next = NULL;

				if(new != NULL){
					strcpy(new->name, node->child->brother->value);
					strcpy(new->type, node->child->key);
					if(method_params->next == NULL){
						method_params->next = new;
					} else {
						while(method_params->next != NULL){
							method_params = method_params->next;
						}
						method_params->next = new;
					}
				}



			}
			node = node->brother;
		}
		return method_params;
}


char* getNodeKey(ParseNode* node){
	char* token;
	token = strtok(node->key, " - ");
	return token;

}

char* getNodeType(ParseNode* node){
	char* token;
	token = strtok(node->key, " - ");
	token = strtok(NULL, " - ");
	return token;
}

char* convertTypes(char* type){
	if(strcmp(type, "Bool") == 0){
		return "boolean";
	}
	else if(strcmp(type, "Int") == 0){
		return "int";
	}
	else if(strcmp(type, "Double") == 0){
		return "double";
	} else if (strcmp(type, "Void") == 0){
		return "void";
	} else if(strcmp(type, "StringArray") == 0) {
		return "String[]";
	} else {
		return type;
	}
}
