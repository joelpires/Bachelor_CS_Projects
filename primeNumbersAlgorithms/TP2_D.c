#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv){

	int i, j;
	long n;					/*O Uso do Long server para poder testar para numeros muito grandes*/
	long *array_int; 

	scanf("%ld", &n);

	array_int = (long*) malloc ((n-1)* sizeof(long));

	if(n > 2){
		printf("%d\n", 2);
	}


	for (i = 0; i < (n-1); i++) {							/*preencher array com inteiros*/
		array_int[i] = i + 1;
	}

	/*********  Crivo de Sundaram  **********/
	for (i = 1; (i+i+2*i*i)+1 < (n-1); i++) {					
		for(j = i; (i+j+2*i*j)+1 < (n-1); j++){
			if(array_int[i+j+2*i*j-1] != 0){
				array_int[i+j+2*i*j-1] = 0;
				
			}
		}
	}

	for (i = 0; (array_int[i]*2 +1) < n; i++) 		/*À medida que encontramos os verdadeiros primos imprimimos logo, para nao gastar mais memoria nem tempo a */	
		if (array_int[i] != 0)							/*armazenar os valores noutro array e ter de imprimir depois*/
			printf("%ld\n", array_int[i]*2 +1);

	free(array_int);


	return 0;

}