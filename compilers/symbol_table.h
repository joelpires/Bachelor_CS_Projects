#ifndef SYMBOL_TABLE_H
#define SYMBOL_TABLE_H

typedef struct param{
	char* type;
	char* name;
	struct param *next;
} param_types;

typedef struct _t2{
	int is_global_variable;
	char* name;
	param_types *params;
	char* type;
	char* flag;
	struct _t2 *next;
} table_element;

typedef struct _t1{
	char* header;
	param_types *params;
	table_element* first;
} table;

typedef struct _t0 {
	table* t;
	struct _t0* next;
}table_list;

table_list* tableList;

table_list* createTableList();
table_element* createSymbol(char* name, char* type, param_types* params, char* flag,int is_global_variable);
void createTable(char* header, param_types* params);
void newGlobalFieldSymbol(table* myTable, char* name, char* type);
void newGlobalMethodSymbol(table* GlobalTable, char* name, param_types* method_params, char* type);
void newLocalSymbol(char* name, param_types* p, char* type, char* flag);
void insert_tableList(table* t);
void addMethodParamSymbol(param_types* method_params);
void print_TableList ();
void print_Table(table* myTable, int j);
int checkGlobalFieldSymbol(table* GlobalTable, char* name);
int checkLocalVariable(table* GlobalTable, char* name);


#endif
