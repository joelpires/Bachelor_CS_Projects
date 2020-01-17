
#include "symbol_table.h"
#include "parsingTree.h"

#include<malloc.h>
#include<string.h>
#include<stdio.h>

table_list* createTableList() {
	table_list* new = (table_list*)malloc(sizeof(table_list));
	new->t = NULL;
	new->next = NULL;
	return new;
}

table_element* createSymbol(char* name, char* type, param_types* params, char* flag, int is_global_variable) {
	table_element* new = (table_element*)malloc(sizeof(table_element));
	new->name = name;
	new->type = type;
	new->flag = flag;
	new->params = params;
	new->next = NULL;
	new->is_global_variable = is_global_variable;
	return new;
}

void createTable(char* header, param_types* params) {
	table* new = (table*)malloc(sizeof(table));
	new->header = strdup(header);
	new->params = params;
	new->first = NULL;
	insert_tableList(new);
}

//cria um novo simbolo de variavel e adiciona-o a tabela global
void newGlobalFieldSymbol(table* GlobalTable, char* name, char* type) {
	/*if(checkGlobalFieldSymbol(GlobalTable, name) == 1){
		return;
	}*/
	table_element* first = GlobalTable->first;
	if (first == NULL) {
		GlobalTable->first = createSymbol(name, type, NULL, NULL,1);
		return;
	}
	while(first->next != NULL) {
		first = first->next;
	}
	first->next = createSymbol(name, type, NULL, NULL,1);
}

void newCurrentMethodSymbol(Method* MethodTable, char* name, char* type) {
	/*if(checkGlobalFieldSymbol(GlobalTable, name) == 1){
		return;
	}*/
	table_element* first = MethodTable->first;
	if (first == NULL) {
		MethodTable->first = createSymbol(name, type, NULL, NULL,1);
		return;
	}
	while(first->next != NULL) {
		first = first->next;
	}
	first->next = createSymbol(name, type, NULL, NULL,1);
}

//cria um novo simbolo de metodo e adiciona-o a tabela global
void newGlobalMethodSymbol(table* GlobalTable, char* name, param_types* method_params, char* type){
	table_element* symbol = GlobalTable->first;
	if(method_params == NULL){
		method_params = (param_types*) malloc(sizeof(param_types));
		method_params->name = "";
		method_params->type = "";
		method_params->next = NULL;
	}
	if(symbol == NULL){
		GlobalTable->first = createSymbol(name, type, method_params, NULL,0);
		return;
	}
	while(symbol->next != NULL){
		symbol = symbol->next;
	}
	symbol->next = createSymbol(name, type, method_params, NULL,0);
}

//cria um novo simbolo local e adiciona-o na ultima tabela criada (que corresponde ao seu metodo)
void newLocalSymbol(char* name, param_types* p, char* type, char* flag){
	/*if(checkLocalVariable(tableList->t, name) == 1){
		return;
	}*/

	table_list* last_table = tableList;
	if(last_table->t != NULL){
		while(last_table->next != NULL)
			last_table = last_table->next;

		table_element* symbol = last_table->t->first;
		if(symbol == NULL){
			last_table->t->first = createSymbol(name, type, p, flag,0);
			return;
		}
		while(symbol->next != NULL){
			symbol = symbol->next;
		}
		symbol->next = createSymbol(name, type, p, flag,0);
	}
}

void addMethodParamSymbol(param_types* method_params){
	table_list* last_table = tableList;
	if(last_table->t != NULL){
		while(last_table->next != NULL)
			last_table = last_table->next;

		table_element* symbol = last_table->t->first;
		while(symbol->next != NULL){
			symbol = symbol->next;
		}
		param_types* temp = method_params;
		while(temp != NULL){
			symbol->next = createSymbol(temp->name, temp->type, NULL, "param",0);
			symbol = symbol->next;
			temp = temp->next;
		}
	}
}

void insert_tableList(table* t){
	table_list* aux = tableList;
	table_list* temp = (table_list*) malloc(sizeof(table_list));
	if(aux != NULL){
		if(aux->t == NULL){
			aux->t = t;
		}
		else{
			if(aux->next == NULL){
				temp->t = t;
				temp->next = NULL;
				aux->next = temp;
			}
			else{
				while(aux->next != NULL){
					aux = aux->next;
				}
				temp->t = t;
				temp->next = NULL;
				aux->next = temp;
			}
		}
	}
}

void insert_param_types(param_types* method_params, char* type, char* name){
	param_types* temp = method_params;
	param_types* new = (param_types*) malloc(sizeof(param_types));
	if(new != NULL){
		new->name = name;
		new->type = type;
		if(temp->next == NULL){
			temp->next = new;
			return;
		}
		while(temp->next != NULL){
			temp = temp->next;
		}
		temp->next = new;
	}
}

void print_Table(table* myTable, int j) {
	table_element* temp;
	param_types* aux, *temp1;
	int i;

	/*PRINT HEADER*/
	if (j == 0) {
		printf("===== Class %s Symbol Table =====\n", myTable->header);
	} else {
		printf("\n");
		printf("===== Method %s", myTable->header);
		aux = myTable->params;
		printf("(");
		for (i = 0; aux != NULL; i++) {
			if(i!=0)
				printf(",");
			if(strcmp(aux->type, "StringArray") == 0)
				printf("String[]");
			else if(strcmp(aux->type, "Int") == 0)
				printf("int");
			else if(strcmp(aux->type, "Bool") == 0)
				printf("boolean");
			else if(strcmp(aux->type, "Double") == 0)
				printf("double");
			aux = aux->next;
		}
		printf(") " );
		printf("Symbol Table =====\n");
	}

	/*PRINT SYMBOLS*/
	temp = myTable->first;
	while (temp != NULL) {
		printf("%s\t", temp->name);
		temp1 = temp->params;
		for (i = 0; temp1 != NULL; i++) {
			if (i == 0) {
				printf("(");
			} else {
				printf(",");
			}
			if(strcmp(temp1->type, "StringArray") == 0)
				printf("String[]");
			else if(strcmp(temp1->type, "Int") == 0)
				printf("int");
			else if(strcmp(temp1->type, "Bool") == 0)
				printf("boolean");
			else if(strcmp(temp1->type, "Double") == 0)
				printf("double");
			temp1 = temp1->next;
		}
		if (i != 0) {
			printf(")" );
		}
		if(strcmp(temp->type, "StringArray") == 0)
			printf("\tString[]");
		else if(strcmp(temp->type, "Int") == 0)
			printf("\tint");
		else if(strcmp(temp->type, "Bool") == 0)
			printf("\tboolean");
		else if(strcmp(temp->type, "Double") == 0)
			printf("\tdouble");
		else
			printf("\tvoid");

		if (temp->flag != NULL) {
			printf("\t%s", temp->flag);
		}

		printf("\n");
		temp = temp->next;

	}
}

void print_TableList () {
	table_list *aux = tableList;
	if(aux->t != NULL){
		if(aux->next == NULL){
			print_Table(aux->t, 0);
		}
		else{
			print_Table(aux->t, 0);
			aux = aux->next;
			while(aux->next != NULL){
				print_Table(aux->t, 1);
				aux = aux->next;
			}
			print_Table(aux->t, 1);
		}
	}

}


//verifica se uma variavel global ja existe na tabela global
int checkGlobalFieldSymbol(table* GlobalTable, char* name){
	table_element* temp = GlobalTable->first;
	while(temp != NULL){
		//se existir um simbolo com o mesmo nome que nao seja um metodo
		if(strcmp(temp->name, name) == 0 && temp->is_global_variable == 1){
			//printf("Simbolo ja definido na tabela Global: %s\n",name);
			return 1;
		}
		temp = temp->next;
	}
	return 0;
}

//verifica se uma variavel local ja existe
int checkLocalVariable(table* GlobalTable, char* name){
	table_list* last_table = tableList;
	if(last_table->t != NULL){
		while(last_table->next != NULL)
			last_table = last_table->next;
			//verifica na tabela global
			/*if(checkGlobalFieldSymbol(GlobalTable, name) == 1){
				return 1;
			}*/
			//verifica na tabela local
			table_element* temp = last_table->t->first;
			while(temp != NULL){
				if(strcmp(temp->name, name) == 0 && temp->params == NULL){
					//printf("Simbolo ja definido na tabela local: %s\n", name);
					return 1;
				}
				temp = temp->next;
			}

	}
	return 0;
}
