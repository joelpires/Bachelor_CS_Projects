#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){

	int i, j;
	long n;					/*O Uso do Long server para poder testar para numeros muito grandes*/
	long *array_int; 

	scanf("%ld", &n);

	array_int = (long*) malloc ((n-1)* sizeof(long));	/*poder-se-ia ter alocado (n-2) no caso de n ser maior que 2*/

	for (i = 2; i < n; i++) {							/*preencher array com inteiros*/
		array_int[i-2] = i;
	}

	for (i = 0; i < n-2; i++){							/*crivo de eratóstenes*/
		if(array_int[i]  != 0){
			printf("%ld\n", array_int[i]);
			for (j = i + array_int[i]; j < n-2; j += array_int[i])
				array_int[j] = 0;		
		}
	}

	free(array_int);

	return 0;

}