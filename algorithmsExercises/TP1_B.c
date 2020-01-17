#include <stdio.h>
#include <stdlib.h>

int search (long val, int* array_int, int len){
	int i, j;
	long sum;						/*O uso do long é util para testar valores grande */

	for (i = 0; i < len; i++) {
		sum = 0;
		for (j = i; j < len; j++) {
			sum += array_int[j];
			if (sum == val)
				return (i+1);
		}
	}
	return 0;
}


int main(int argc, char **argv){
	
	int j = 0, i;
	long comp, val;
	int* array_int = (int*) malloc(1);					/*inputs inseridos podem ser do tipo int uma vez que só variam entre 1 e 1000*/									
	int* array_ans = (int*)calloc(1024, sizeof(int));



	while(scanf("%ld %ld", &comp, &val) && (comp != 0 || val != 0)){		/*Processamento das Séries de Inputs*/

		array_int = (int*)realloc(array_int, comp*sizeof(int));

		for(i=0; i < comp; i++){
			scanf("%d", &(array_int[i]));
		}
		array_ans[j++] = search(val, array_int, comp);					/*Algoritmo de Pesquisa */
	}
	free(array_int);

	for (i = 0; i < j; i++) {											/*Impressão do Resultado*/
		if (array_ans[i] == 0)
			printf("SUBSEQUENCIA NAO ENCONTRADA\n");
		else if (array_ans[i] > 0)
			printf("SUBSEQUENCIA NA POSICAO %d\n", array_ans[i]);
	}
	free(array_ans);

	return 0;
}