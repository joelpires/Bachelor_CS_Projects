//
//  main.c
//  ppp.project
//
//  Created by Igor Rodrigues   on 18/03/15         2014212177.
//  Created by Joel Pires       on 18/03/15         2014195242.
//  Created by Luis Ramos       on 18/03/15         2008112685.
//  Copyright (c) 2015 Igor Rodrigues.  All rights reserved.
//  Copyright (c) 2015 Joel Pires.      All rights reserved.
//  Copyright (c) 2015 Luis Ramos.      All rights reserved.
//

#include "main.h"

int main(int argc, const char * argv[]) {
    init();
    
    int choice = 0;
    
    do {
        
        signal(SIGINT, signalhandler);
        
        choice = mainMenu();
        system("clear");
        
        if(choice == 9) {
            term();
            return 9;
        } else redirectToFunction(choice);
    } while(1);
    
    return 404;
}

void init() {
    //INITIALIZES PROGRAM
    
    //CREATES ALL THE LISTS AND CHECKS IF FILES EXIST AND IMPORTS TO THE LIST
    printf("A CRIAR AS LISTAS\n");
    
    plisth = createlist();
    redlh = createlist();
    yellowlh = createlist();
    greenlh = createlist();
    
    printf("AS LISTAS FORAM CRIADAS COM SUCESSO\n");
    
    struct stat st = {0};
    
    if (stat("data", &st) == -1) {
        mkdir("data", 0700);
    }
    
    printf("A IMPORTAR INFORMACAO DO FICHEIRO\n");
    
    if(createfile("data/data.txt") == 1) {
        import("data/data.txt");
    }
    
    printf("INFORMACAO IMPORTADA DO FICHEIRO COM SUCESSO\n");
    
    //AUTOSAVE IS ON BY DEFAULT
    autosave = 1;
}

void term() {
    //FINALIZE PROGRAM
    //EXPORTS EVERYTHING TO THE FILES AND DESTROYS ALL THE LISTS
    
    savefiles();
    
    printf("A ELIMINAR AS LISTAS DA MEMORIA\n");
    destroylist(plisth);
    destroylist(redlh);
    destroylist(yellowlh);
    destroylist(greenlh);
    printf("AS LISTAS FORAM ELIMINADAS DA MEMORIA COM SUCESSO\n");
}

int mainMenu() {
    char * line = NULL;
    size_t len = 0;
    ssize_t read;
    
    printf("[1] ADMITIR UM DOENTE\n");
    printf("[2] ATRIBUIR PRIORIDADE A UM DOENTE\n");
    printf("[3] CANCELAR DOENTE PARA TRIAGEM\n");
    printf("[4] CANCELAR DOENTE PARA CONSULTA\n");
    printf("[5] LISTAR DOENTES POR PRIORIDADE\n");
    printf("[6] LISTAR TODOS OS DOENTES\n");
    printf("[7] DETERMINAR O PACIENTE SEGUINTE\n");
    if(autosave) {
        printf("[8] TURN AUTOSAVE OFF\n");
    } else {
        printf("[8] TURN AUTOSAVE ON\n");
    }
    printf("[9] SAIR\n");
    printf("ESCHOLHA UMA OPCAO: ");
    
    while((read = getline(&line, &len, stdin)) != -1) {
        if(!((int) line[0] == 10)) fflush(stdin); break;
    }
    
    int choice = atoi(line);
    
    if(choice == 0 || choice > 10) return 0;
    else return choice;
}

void redirectToFunction(int choice) {
    switch (choice) {
        case 1:
            admitPatient();
            break;
        case 2:
            screening(plisth);
            break;
        case 3:
            deleteabscreening(0, plisth);
            break;
        case 4:
            deleteabscreening(1, plisth);
            break;
        case 5:
            chooseprint();
            break;
        case 6:
            printltof();
            break;
        case 7:
            patientrules();
            break;
        case 8:
            if(autosave) autosave = 0;
            else autosave = 1;
            break;
    }
}

void admitPatient() {
    printf("\t\t----- ADMISSAO DE UM PACIENTE -----\n");
    char * line = NULL;
    size_t len = 0;
    ssize_t read;
    
    printf("INSIRA O NOME DO PACIENTE: ");
    while((read = getline(&line, &len, stdin)) != -1) {
        if(!((int) line[0] == 10)) fflush(stdin); break;
    }
    
    char * name = (char *) calloc((read), sizeof(char));
    
    for(int i = 0; i < read - 1; i++) {
        name[i] = line[i];
    }
    
    do {
        printf("INSIRA O NUMERO DO BI DO PACIENTE: ");
        while((read = getline(&line, &len, stdin)) != -1) {
            if(!((int) line[0] == 10)) fflush(stdin); break;
        }
    } while(verifyid(line, read) == 0);
    
    int newid = atoi(line);
    
    if(validateid(newid) == 0) {
        printf("O ID QUE INSERIU JA EXISTE E NAO PERTENCE A ESTE PACIENTE\n");
        return;
    } else {
        gid[tid] = newid;
        tid++;
    }
    
    time_t now;
    time(&now);
    
    //INSERT PATIENT IN THE LIST WITH ALL PATIENTS
    insertandsort(name, newid, now, 0, plisth);
    if(autosave) savefiles();
}

int verifyid(char * id, ssize_t read) {
    if(read > 11) {
        printf("O NUMERO DE DIGITOS DO BI ULTRAPASSA O LIMITE PERMITIDO\n");
        return 0;
    }
    
    for(int i = 0; i < read - 1; i++) {
        if(!((int) id[i] >= 48 && (int) id[i] <= 57)) {
            printf("BI ACEITA APENAS NUMEROS (0 - 9)\n");
            return 0;
        }
    }
    return 1;
}

int validateid(int id) {
    for(int i = 0; i < tid; i++) {
        if(id == gid[i]) return 0;
    }
    
    return 1;
}

void removeid(int id) {
    
    int found = 0;
    
    for(int i = 0; i < tid; i++) {
        if(id == gid[i]) {
            found = i;
        }
    }
    
    for(int i = found; i < tid; i++) {
        if(i == tid) gid[i] = 0;
        else gid[i] = gid[i + 1];
    }
    
}

void screening(patient list) {
    printf("\t\t----- TRIAGEM -----\n");
    
    char * line = NULL;
    size_t len = 0;
    ssize_t read;
    
    printf("INSIRA O NOME DO PACIENTE: ");
    while((read = getline(&line, &len, stdin)) != -1) {
        if(!((int) line[0] == 10)) fflush(stdin); break;
    }
    
    char * name = (char *) calloc((read - 1), sizeof(char));
    
    for(int i = 0; i < read - 1; i++) {
        name[i] = line[i];
    }
    
    do {
        printf("INSIRA O NUMERO DO BI DO PACIENTE: ");
        while((read = getline(&line, &len, stdin)) != -1) {
            if(!((int) line[0] == 10)) fflush(stdin); break;
        }
    } while(verifyid(line, read) == 0);
    
    int newid = atoi(line);
    
    if(findinlist(name, newid, list) != NULL) {
        int priority = givepriority(0);
        
        patient other = NULL;
        
        if(priority == 1) other = redlh;
        if(priority == 2) other = yellowlh;
        if(priority == 3) other = greenlh;
        
        time_t timestamp = gettimestamp(name, newid, list);
        
        if(changelist(name, newid, timestamp, priority, list, other)) printf("PACIENTE ENCONTRA-SE AGORA EM ESPERA PARA CONSULTA\n");
        else printf("OCORREU UM ERRO A COLOCAR O PACIENTE EM LISTA DE ESPERA PARA CONSULTA\n");
    } else {
        printf("ESTE PACIENTE \"%s\" NAO SE ENCONTRA NA LISTA DE ESPERA PARA TRIAGEM\n", name);
        return;
    }
}

int givepriority(int print) {
    char * line = NULL;
    size_t len = 0;
    ssize_t read;
    
    
    if(print) {
        do {
            printf("[1] SEM TRIAGEM\n[2] VERMELHO\n[3] AMARELO\n[4] VERDE\n[5] TODAS AS LISTAS\nINSIRA UM VALOR PARA A PRIORIDADE: ");
            while((read = getline(&line, &len, stdin)) != -1) {
                if(!((int) line[0] == 10)) fflush(stdin); break;
            }
        } while(verifypriority(print, line, read) == 0);
        
        return atoi(line);
    } else {
        do {
            printf("[1] VERMELHO\n[2] AMARELO\n[3] VERDE\nINSIRA UM VALOR PARA A PRIORIDADE: ");
            while((read = getline(&line, &len, stdin)) != -1) {
                if(!((int) line[0] == 10)) fflush(stdin); break;
            }
        } while(verifypriority(print, line, read) == 0);
        
        return atoi(line);
    }
}

int verifypriority(int print, char * priority, ssize_t read) {
    //CHECKS IF INPUT PRIORITY IS VALID
    
    if(print) {
        for(int i = 0; i < read - 1; i++) {
            if(!((int) priority[i] >= 49 && (int) priority[i] <= 53)) {
                printf("PRIORIDADE ACEITA APENAS NUMEROS (1 - 5)\n");
                return 0;
            }
        }
        return 1;
    } else {
        for(int i = 0; i < read - 1; i++) {
            if(!((int) priority[i] >= 49 && (int) priority[i] <= 51)) {
                printf("PRIORIDADE ACEITA APENAS NUMEROS (1 - 3)\n");
                return 0;
            }
        }
        return 1;
    }
}

void deleteabscreening(int ab, patient list) {
    printf("\t\t----- REMOVER UM PACIENTE -----\n");
    char * line = NULL;
    size_t len = 0;
    ssize_t read;
    
    printf("INSIRA O NOME DO PACIENTE: ");
    while((read = getline(&line, &len, stdin)) != -1) {
        if(!((int) line[0] == 10)) fflush(stdin); break;
    }
    
    char * name = (char *) calloc((read - 1), sizeof(char));
    
    for(int i = 0; i < read - 1; i++) {
        name[i] = line[i];
    }
    
    do {
        printf("INSIRA O NUMERO DO BI DO PACIENTE: ");
        while((read = getline(&line, &len, stdin)) != -1) {
            if(!((int) line[0] == 10)) fflush(stdin); break;
        }
    } while(verifyid(line, read) == 0);
    
    int newid = atoi(line);
    
    patient other = NULL;
    
    if(ab == 0) {
        //BEFORE SCREENING
        if(findinlist(name, newid, list) != NULL) {
            printf("ENCONTROU O PACIENTE\n");
            deletefromlist(name, newid, list);
            removeid(newid);
            if(autosave) savefiles();
        } else {
            printf("O PACIENTE \"%s\" NAO SE ENCONTRA EM ESPERA PARA TRIAGEM\n", name);
        }
    } else {
        //AFTER SCREENING
        for(int i = 1; i < 4; i++) {
            if(i == 1) other = redlh;
            if(i == 2) other = yellowlh;
            if(i == 3) other = greenlh;
            
            if(findinlist(name, newid, other) != NULL) {
                deletefromlist(name, newid, other);
                removeid(newid);
                if(autosave) savefiles();
                return;
            } else {
                char * listname = NULL;
                if(i == 1) listname = "VERMELHO";
                if(i == 2) listname = "AMARELO";
                if(i == 3) listname = "VERDE";
                if(listname != NULL) printf("O PACIENTE \"%s\" NAO SE ENCONTRA EM ESPERA PARA CONSULTA NA LISTA %s\n", name, listname);
                else printf("O PACIENTE \"%s\" NAO SE ENCONTRA EM NEHUMA DAS LISTAS DE ESPERA\n", name);
            }
        }
    }
}

void chooseprint() {
    printf("\t\t----- ESCOLHER UMA LISTA DE PACIENTES PARA IMPRIMIR -----\n");
    int toprint = givepriority(1);
    
    if(toprint == 1) printftol(plisth);
    else if(toprint == 2) printftol(redlh);
    else if(toprint == 3) printftol(yellowlh);
    else if(toprint == 4) printftol(greenlh);
    else if(toprint == 5) {
        printf("PACIENTES SEM PRIORIDADE ATRIBUIDA\n");
        printftol(plisth);
        printf("PACIENTES QUE PERTENCEM A PRIORIDADE VERMELHO\n");
        printftol(redlh);
        printf("PACIENTES QUE PERTENCEM A PRIORIDADE AMARELO\n");
        printftol(yellowlh);
        printf("PACIENTES QUE PERTENCEM A PRIORIDADE VERDE\n");
        printftol(greenlh);
    }
}

void patientrules() {
    printf("\t\t----- PROXIMO PACIENTE -----\n");
    
    if(red == 0 && yellow == 0) {
        determinenextpatient(3);
        red = 3;
        yellow = 5;
    } else if(red == 0) {
        determinenextpatient(2);
        yellow -= 1;
        red = 3;
    } else {
        determinenextpatient(1);
        red -= 1;
    }
}

void determinenextpatient(int idl) {
    //COMPLY OR OVERRIDE THE RULES SET TO ATTEND PATIENTS
    patient list = NULL;
    patient aux = NULL;
    
    int r = 0, y = 0, g = 0;
    char * listname = NULL;
    
    if(idl == 1) {
        listname = "VERMELHO";
        list = redlh;
        r = 1;
    } else if(idl == 2) {
        listname = "AMARELO";
        list = yellowlh;
        y = 1;
    } else if(idl == 3) {
        listname = "VERDE";
        list = greenlh;
        g = 1;
    }
    
    int tar = 0, tay = 0, tag = 0;
    
    
    while(tar == 0 || tay == 0 || tag == 0) {
        aux = findnext(list);
        if(strcmp(listname, "VERMELHO") == 0) tar = 1;
        else if(strcmp(listname, "AMARELO") == 0) tay = 1;
        else if(strcmp(listname, "VERDE") == 0) tag = 1;
        
        if(aux != NULL) {
            printf("O PACIENTE \"%s\" QUE PERTENCE A PRIORIDADE \"%s\" E O PROXIMO PACIENTE A SER ATENDIDO\n", aux -> person -> name, listname);
            deletefromlist(aux -> person -> name, aux -> person -> id, list);
            if(autosave) savefiles();
            return;
        } else {
            if(r == 0) {
                list = redlh;
                r = 1;
                listname = "VERMELHO";
            } else if(y == 0) {
                list = yellowlh;
                y = 1;
                listname = "AMARELO";
            } else if(g == 0) {
                list = greenlh;
                g = 1;
                listname = "VERDE";
            }
        }
    }
    
    printf("NAO HA PACIENTES EM LISTA DE ESPERA PARA SEREM ATENDIDOS\n");
}

// --------------------------------------------- LISTS --------------------------------------------- \\
// CREATE / DESTROY / INSERT / DELETE / FIND / PRINT / OTHER FUNCTIONS

patient createlist() {
    patient aux = (patient) calloc(1, sizeof(patientnode));
    aux -> person = (user) calloc(1, sizeof(usernode));
    
    if(aux != NULL) {
        aux -> person -> name = "HEADER";
        aux -> person -> id = -1;
        aux -> timestamp = -1;
        aux -> priority = -1;
        aux -> next = NULL;
        aux -> gprev = NULL;
    }
    
    return aux;
}

void destroylist(patient list) {
    patient temp;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(list -> next == NULL) {
        printf("ESTA LISTA ESTA VAZIA\n");
        
        free(list -> person);
        free(list);
        
        return;
    }
    
    //JUMP THE HEADER
    list = list -> next;
    
    while(!(isheader(list))) {
        temp = list;
        list = list -> next;
        
        free(temp -> person);
        free(temp);
    }
    
    free(list);
}

int isheader(patient list) {
    if(strcmp(list -> person -> name, "HEADER") == 0) return 1;
    else return 0;
}

void insertandsort(char * name, int id, time_t timestamp, int priority, patient list) {
    patient aux = list;
    patient new = (patient) calloc(1, sizeof(patientnode));
    new -> person = (user) calloc(1, sizeof(usernode));
    
    if(aux -> next == NULL) {
        new -> person -> name = name;
        new -> person -> id = id;
        new -> timestamp = timestamp;
        new -> priority = priority;
        new -> next = aux;
        new -> gprev = NULL;
        
        aux -> next = new;
        
        insertprev(new);
        
        return;
    }
    
    //JUMP THE HEADER
    aux = aux -> next;
    
    //SET THE VARIABLES FOR THE NEW ELEMENT
    new -> person -> name = name;
    new -> person -> id = id;
    new -> timestamp = timestamp;
    new -> priority = priority;
    
    while(1) {
        if(difftime(aux -> timestamp, timestamp) <= 0) {
            if(strcmp(aux -> next -> person -> name, "HEADER") == 0) {
                new -> next = aux -> next;
                aux -> next = new;
                
                insertprev(new);
                
                return;
            } else {
                if(difftime(aux -> next -> timestamp, timestamp) >= 0) {
                    new -> next = aux -> next;
                    aux -> next = new;
                    
                    insertprev(new);
                    
                    return;
                } else {
                    aux = aux -> next;
                }
            }
        } else {
            if(strcmp(aux -> person -> name, list -> next -> person -> name) == 0) {
                list -> next = new;
                new -> next = aux;
                
                insertprev(new);
                
                return;
            }
        }
    }
    
}

void insertprev(patient new) {
    
    patient aux = absolutefirst;
    
    //THERE IS NO ABSOLUTE FIRST ATM
    if(absolutefirst == NULL) {
        absolutefirst = new;
        
        return;
    }
    
    while(1) {
        if(difftime(aux -> timestamp, new -> timestamp) >= 0) {
            if(aux -> gprev == NULL) {
                aux -> gprev = new;
                
                return;
            } else {
                if(difftime(aux -> gprev -> timestamp, new -> timestamp) >= 0) {
                    aux = aux -> gprev;
                } else {
                    new -> gprev = aux -> gprev;
                    aux -> gprev = new;
                    
                    return;
                }
            }
        } else {
            new -> gprev = aux;
            absolutefirst = new;
            
            return;
        }
    }
}

patient findinlist(char * name, int id, patient list) {
    patient aux = list;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(aux == NULL || aux -> next == NULL) {
        return NULL;
    }
    
    //JUMP THE HEADER
    aux = aux -> next;
    
    while(!(isheader(aux))) {
        if(strcmp(aux -> person -> name, name) == 0 && aux -> person -> id == id) {
            return aux;
        } else aux = aux -> next;
    }
    
    return NULL;
}

patient findnext(patient list) {
    //PRINT FROM FIRST TO LAST
    patient aux = list;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(aux -> next == NULL || strcmp(aux -> next -> person -> name, "HEADER") == 0) {
        printf("ESTA LISTA ESTA VAZIA\n");
        return NULL;
    }
    
    //JUMP THE HEADER
    aux = aux -> next;
    
    return aux;
}

time_t gettimestamp(char * name, int id, patient list) {
    patient aux = list;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(aux -> next == NULL) {
        return 0;
    }
    
    //JUMP THE HEADER
    aux = aux -> next;
    
    while(!(isheader(aux))) {
        if(strcmp(aux -> person -> name, name) == 0 && aux -> person -> id == id) {
            return aux -> timestamp;
        } else aux = aux -> next;
    }
    
    return 0;
}

int changelist(char * name, int id, time_t timestamp, int priority, patient list, patient other) {
    patient aux = list;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(aux -> next == NULL) {
        return 0;
    }
    
    //JUMP THE HEADER
    aux = aux -> next;
    
    while(!(isheader(aux))) {
        if(strcmp(aux -> person -> name, name) == 0 && aux -> person -> id == id) {
            if(deletefromlist(name, id, list)) {
                insertandsort(name, id, timestamp, priority, other);
                if(autosave) savefiles();
            }
            
            return 1;
        } else {
            aux = aux -> next;
        }
    }
    
    return 0;
}

int deletefromlist(char * name, int id, patient list) {
    patient aux = list;
    patient temp;
    patient prev;
    
    patient gaux = absolutefirst;
    patient aprev = absolutefirst;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(aux -> next == NULL) {
        return 0;
    }
    
    if(absolutefirst -> gprev == NULL) {
        //IF THERE IS ONLY ONE ELEMENT IN THE LIST
        absolutefirst = NULL;
    } else {
        //IF THERE ARE MULTIPLE ELEMENTS IN THE LIST
        while(1) {
            if(strcmp(gaux -> person -> name, aprev -> person -> name) == 0) {
                //FIRST ELEMENT OF THE LIST
                
                if(strcmp(gaux -> person -> name, name) == 0 && gaux -> person -> id == id) {
                    absolutefirst = gaux -> gprev;
                    break;
                }
                
                gaux = gaux -> gprev;
            } else {
                //ANY OTHER ELEMENT ON THE LIST
                
                if(strcmp(gaux -> person -> name, name) == 0 && gaux -> person -> id == id) {
                    aprev -> gprev = gaux -> gprev;
                    break;
                }
                
                
                gaux = gaux -> gprev;
                aprev = aprev -> gprev;
            }
            
        }
    }
    
    //JUMP THE HEADER
    aux = aux -> next;
    prev = list;
    
    while(!(isheader(aux))) {
        if(strcmp(aux -> person -> name, name) == 0 && (aux -> person -> id == id)) {
            temp = aux;
            prev -> next = aux -> next;
            
            free(temp -> person);
            free(temp);
            
            return 1;
        }
        
        aux = aux -> next;
        prev = prev -> next;
    }
    
    return 0;
}

void printftol(patient list) {
    //PRINT FROM FIRST TO LAST
    patient aux = list;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(aux == NULL || aux -> next == NULL) {
        printf("ESTA LISTA ESTA VAZIA\n");
        return;
    }
    
    //JUMP THE HEADER
    aux = aux -> next;
    
    while(!(isheader(aux))) {
        char * timestring = convertfrom(aux -> timestamp);
        char * priority;
        
        if(aux -> priority == 1) priority = "vermelho";
        else if(aux -> priority == 2) priority = "amarelo";
        else if(aux -> priority == 3) priority = "verde";
        else priority = "ainda nao passou a triagem";
        
        printf("NOME: %s BI: %d PRIORIDADE: %s CHECK-IN AS: %s\n", aux -> person -> name, aux -> person -> id, priority, timestring);
        
        free(timestring);
        aux = aux -> next;
    }
}

void printltof() {
    //PRINT FROM LAST TO FIRST
    patient aux = absolutefirst;
    
    //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
    if(aux == NULL) {
        printf("ESTA LISTA ESTA VAZIA\n");
        return;
    }
    
    while(aux != NULL) {
        char * timestring = convertfrom(aux -> timestamp);
        char * priority;
        
        if(aux -> priority == 1) priority = "vermelho";
        else if(aux -> priority == 2) priority = "amarelo";
        else if(aux -> priority == 3) priority = "verde";
        else priority = "ainda nao passou a triagem";
        
        printf("NOME: %s BI: %d PRIORIDADE: %s CHECK-IN AS: %s\n", aux -> person -> name, aux -> person -> id, priority, timestring);
        
        free(timestring);
        if(aux -> gprev == NULL) return;
        else aux = aux -> gprev;
    }
}

// --------------------------------------------- FILES --------------------------------------------- \\
//

int createfile(char * filename) {
    FILE * file;
    
    file = fopen(filename, "r");
    
    if(file == NULL) {
        file = fopen(filename, "w");
        return 0;
    }
    
    fclose(file);
    return 1;
}

void deletefile(char * filename) {
    remove(filename);
}

void import(char * filename) {
    FILE * file;
    
    file = fopen(filename, "r");
    char * line = (char *) calloc(SIZE, sizeof(char));
    time_t timestamp;
    
    while(fgets(line, 100, file) != NULL) {
        
        char * name = strsep(&line, ".");
        char * id = strsep(&line, " ");
        gid[tid] = atoi(id);
        tid++;
        char * priority = strsep(&line, " ");
        timestamp = convertto(line);
        
        if(atoi(priority) == 0) {
            insertandsort(name, atoi(id), timestamp, atoi(priority), plisth);
        } else if(atoi(priority) == 1) {
            insertandsort(name, atoi(id), timestamp, atoi(priority), redlh);
        } else if(atoi(priority) == 2) {
            insertandsort(name, atoi(id), timestamp, atoi(priority), yellowlh);
        } else if(atoi(priority) == 3) {
            insertandsort(name, atoi(id), timestamp, atoi(priority), greenlh);
        }
    }
    
    fclose(file);
}

time_t convertto(char * timestring) {
    int mon = -1, day = -1, hour = -1, min = -1, sec = -1, year = -1;
    
    day = atoi(strsep(&timestring, "/"));
    mon = atoi(strsep(&timestring, "/"));
    year = atoi(strsep(&timestring, " "));
    hour = atoi(strsep(&timestring, ":"));
    min = atoi(strsep(&timestring, ":"));
    sec = atoi(strsep(&timestring, "\n"));
    
    struct tm * timeinfo = (struct tm *) malloc(sizeof(struct tm));
    
    timeinfo -> tm_year = year - 1900;
    timeinfo -> tm_mon = mon - 1;
    timeinfo -> tm_mday = day;
    timeinfo -> tm_hour = hour;
    timeinfo -> tm_min = min;
    timeinfo -> tm_sec = sec;
    timeinfo -> tm_isdst = -1;
    
    time_t newtime;
    newtime = mktime(timeinfo);
    
    free(timeinfo);
    
    return newtime;
    
}

char * convertfrom(time_t timestamp) {
    struct tm * timeinfo;
    
    timeinfo = localtime(&timestamp);
    
    char * timestring = (char *) calloc(21, sizeof(char));
    sprintf(timestring, "%d/%d/%d %d:%d:%d", timeinfo -> tm_mday, timeinfo -> tm_mon + 1, timeinfo -> tm_year + 1900, timeinfo -> tm_hour, timeinfo -> tm_min, timeinfo -> tm_sec);
    
    return timestring;
}

void export(char * filename) {
    FILE * file;
    file = fopen(filename, "w");
    
    int i = 0;
    int empty = 0;
    
    patient aux = plisth;
    
    while(i < 4) {
        if(i == 1) aux = redlh;
        else if(i == 2) aux = yellowlh;
        else if(i == 3) aux = greenlh;
        
        
        
        //CHECK IF THERE ARE ELEMENTS IN THE LIST OTHER THAN THE HEADER
        if(aux -> next == NULL) {
            empty = 1;
        }
        
        if(empty) i++;
        else {
            //JUMP THE HEADER
            aux = aux -> next;
            
            while(!(isheader(aux))) {
                char * timestring = convertfrom(aux -> timestamp);
                fprintf(file, "%s.%d %d %s\n", aux -> person -> name, aux -> person -> id, aux -> priority, timestring);
                free(timestring);
                
                aux = aux -> next;
            }
            
            i++;
        }
    }
    
    fclose(file);
}

void savefiles() {
    
    printf("A GUARDAR O FICHEIRO\n");
    export("data/data.txt");
    
    printf("O FICHEIRO FOI GUARDADO COM SUCESSO\n");
}

void signalhandler() {
    printf("A FORCAR O ENCERRAMENTO\n");
    
    if(autosave) {
        term();
    }
    
    exit(404);
}