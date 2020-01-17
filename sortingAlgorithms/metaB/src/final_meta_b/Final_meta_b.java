package final_meta_b;

import java.util.Scanner;

public class Final_meta_b {

    public static final int CUTOFF = 30;
    public static Scanner sc;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, l;
        int linhas, colunas;
        long [][] myArray;
        
        //SCANAR VALORES
        String linha = sc.nextLine().trim();
        String[] temp = new String[2];
        temp = linha.split(" ",2);
        linhas = Integer.parseInt(temp[0]);
        colunas = Integer.parseInt(temp[1]);
        myArray = new long[linhas][colunas]; 
        
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
        
         long startTime = System.nanoTime();
        //ALGORITMO DE ORDENACAO RAPIDA-------------
        ordenacao_rapida(myArray,0,myArray.length-1);
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
    
    //ALGORITMO DE ORDENACAO RAPIDA E METODOS AUXILIARES -----------------------
    public static void ordenacao_rapida( long [][] myArray , int chao , int teto ) {
        int centro, i, j;
        
        if ( teto - chao + 1 < CUTOFF ) {
            insercao_algoritmo(myArray,chao,teto);
        } else {
            centro = (chao+teto) / 2;
            ordenaTres(myArray, chao, centro, teto);
            
            j = teto - 1;
            i = chao;
            centro  = j;
            
            while(true){
                while(maior(myArray[++i],myArray[centro]) < 0);
                while(maior(myArray[--j],myArray[centro]) > 0);
                if ( i < j )
                    troca(myArray,i,j);
                else
                    break;        
            }
            
            centro = i;
            troca(myArray,centro,teto-1);
            
            ordenacao_rapida(myArray, chao, centro -1);
            ordenacao_rapida(myArray, centro+ 1, teto);
        }
            
    }
    //------------------------------------------------------------------------------
    public static void ordenaTres (long[][] myArray, int chao, int centro, int teto){
        if ( maior(myArray[chao],myArray[centro]) > 0)
            troca(myArray,chao,centro);
        if ( maior(myArray[chao],myArray[teto]) > 0)
            troca(myArray,chao,teto);
        if ( maior(myArray[centro],myArray[teto]) > 0)
            troca(myArray,centro,teto);

        troca(myArray,centro,teto-1);
    }
    //------------------------------------------------------------------------------
    public static int maior( long [] s1 , long [] s2 ){
        int i;
        for ( i = 0; i < s1.length; i++ ){
            if ( s1[i] > s2[i] )
                return 1;
            else if ( s1[i] < s2[i] )
                return -1;
        }
        return 0;
    }
    //------------------------------------------------------------------------------
    public static void troca(long [][] myArray, int chao, int teto ) {
        long [] aux = myArray[chao];
        
        myArray[chao] = myArray[teto];
        myArray[teto] = aux;
    }
    
    //ALGORITMO DE INSERCAO ------------------------------------------------------------
    public static void insercao_algoritmo(long [][] lista, int chao , int teto){
        long[] aux;
        int k, i = chao + 1, j;
        
        while(i <= teto){
            aux = lista[i];
            for(j = i - 1; j >= chao; j--){
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
