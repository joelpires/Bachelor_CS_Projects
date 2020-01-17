#include	 <stdio.h>
#include	 <stdlib.h>

int main() {

	int* array_int = (int*)malloc(1);
	array_int[0] = 1;
	array_int[2] = 3;

	printf("%d", array_int[2]);
	
	system("pause");


	return 0;

}