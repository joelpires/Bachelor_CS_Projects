#include <stdio.h>
#include <stdlib.h>

long is_prime(long n);


int main(int argc, char **argv){

	int i;
	long n;					/*O uso do Long server para poder testar para numeros muito grandes*/
	long *array_int; 						
									
	scanf("%ld", &n);

	array_int = (long*) malloc ((n-1)* sizeof(long));  
	
	for (i = 2; i < n; i++){ 
		if (is_prime(i) != 0){			/*no enunciado diz "todos os valores inferiores" mas se i = 0 ou 1 */ 
			array_int[i-2] = i;			/*os valores vão para a função is_prime() e não fazem absolutamente nada*/
			printf("%ld\n", array_int[i-2]);
		}			
	}

	free(array_int);

	return 0;
}

long is_prime(long n){
	int i;							
									
	for (i = 2; i < n; i++){		
		if(n % i == 0)				
    		return 0;
	}
	return n;
}