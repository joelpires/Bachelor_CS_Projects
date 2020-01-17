//
//  main.h
//  ppp.project
//
//  Created by Igor Rodrigues   on 18/03/15         2014212177.
//  Created by Joel Pires       on 18/03/15         2014195242.
//  Created by Luis Ramos       on 18/03/15         2008112685.
//  Copyright (c) 2015 Igor Rodrigues.  All rights reserved.
//  Copyright (c) 2015 Joel Pires.      All rights reserved.
//  Copyright (c) 2015 Luis Ramos.      All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#include <signal.h>

#define MAXIDS 10000
#define SIZE 1024

typedef struct pnode * patient;
typedef struct unode * user;

typedef struct pnode {
    user person;
    time_t timestamp;
    int priority;
    patient next;
    patient gprev;
} patientnode;

typedef struct unode {
    char * name;
    int id;
}usernode;

//PATIENTS ORDERED WITH THE MOST RECENT FIRST
patient absolutefirst;

//LIST WITH ALL PATIENTS BEFORE SCREENING
static patient plisth;

//LIST WITH ALL PATIENTS AFTER SCREENING
static patient redlh;
static patient yellowlh;
static patient greenlh;

//AUTOSAVE FEATURE
int autosave;

//ARRAY WITH ALL IDS
int gid[MAXIDS];
int tid = 0;

//GLOBAL VALUES FOR SCRRENING
int red = 3;
int yellow = 5;

//DECLARATION OF FUNCTIONS

void init();
void term();

int mainMenu();
void redirectToFunction(int choice);

void admitPatient();
int verifyid(char * id, ssize_t read);
int validateid(int id);
void removeid(int id);

int isempty(patient list);

void screening(patient list);
int givepriority(int print);
int verifypriority(int print, char * priority, ssize_t read);

void deleteabscreening(int ab, patient list);

void chooseprint();

void patientrules();
void determinenextpatient(int idl);

//LIST OPERATIONS

patient createlist();
void destroylist(patient list);
int isheader(patient list);

//void insertandsort(char * name, int id, time_t timestamp, int priority, int attended, patient list);
void insertandsort(char * name, int id, time_t timestamp, int priority, patient list);
void insertprev(patient new);

int changelist(char * name, int id, time_t timestamp, int priority, patient list, patient other);

patient findinlist(char * name, int id, patient list);
patient findnext(patient list);

time_t gettimestamp(char * name, int id, patient list);

int deletefromlist(char * name, int id, patient list);

void printftol(patient list);
void printltof();

//FILE OPERATIONS

int createfile(char * filename);
void deletefile(char * filename);

void import(char * filename);
time_t convertto(char * date);
char * convertfrom(time_t timestamp);
void export(char * filename);

void savefiles();
void signalhandler();