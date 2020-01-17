import java.util.Scanner;

public class AED_FINAL_A {
    
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int i, l;
        int [][]myArray;
        int linhas, colunas;
        
        //SCANAR VALORES
        String linha = sc.nextLine().trim();
        String[] temp = new String[2];
        temp = linha.split(" ",2);
        linhas = Integer.parseInt(temp[0]);
        colunas = Integer.parseInt(temp[1]);
        myArray = new int[linhas][colunas]; 
        
        //PROCESSAR COORDENADAS
        for (i = 0; i < linhas; i++){
            //EXTRAI COORDENADAS
            String coords = sc.nextLine().trim();
            String temp2[] = new String[colunas];
            temp2 = coords.split(" ",colunas);
            //INSERE-AS NO ARRAY
            for (l = 0 ; l < colunas; l++){
                myArray[i][l] = Integer.parseInt(temp2[l]);
            }
        }
        
        //ALGORITMO DE INSERCAO
        long startTime = System.nanoTime();
        
        insercao_algortimo(myArray);
        
        long estimatedTime = System.nanoTime() - startTime;
            System.out.println("tempo:"+estimatedTime);

        //IMPRESSAO
        /*for (i = 0; i < linhas ; i++){
            for (l = 0; l < colunas; l++){
                if (l == colunas - 1)
                    System.out.print(myArray[i][l]);
                else
                    System.out.print(myArray[i][l]+" ");
            }
            System.out.println();
        }*/
    }

//ALGORTIMO DE INSERÇÃO -----------------------------    
    public static void insercao_algortimo(int[][] lista){
        int[] aux;
        int k, i = 1, j;
        
        while(i < lista.length){
            aux = lista[i];
            for(j = i - 1; j >= 0; j--){
                k=0;
                if(aux == lista[j])
                    break;
                if(aux[k] == lista[j][k]){
                    while(aux[k] == lista[j][k] && k < aux.length-1)
                        k++;
                }
                if(aux[k] < lista[j][k]){
                    lista[j+1] = lista[j];
                    lista[j] = aux;
                } else
                    break;
            }
        i++;
        }
    }
}
