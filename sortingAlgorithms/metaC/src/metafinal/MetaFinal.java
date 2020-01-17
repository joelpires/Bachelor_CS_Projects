import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MetaFinal {
    
    //VARIAVEIS ESTÁTICAS   ----------------------------------------------------
    
    public static final ArrayList <VectorColunas> vector = new ArrayList<VectorColunas>();
    public static final Scanner sc1 = new Scanner(System.in);
    public static final int LIMIT = 999;
    public static final int VALUE = 10;
    
    //CLASSE QUE ARMAZENA O NUMERO DE COLUNAS  ---------------------------------
    static class VectorColunas{

        public VectorColunas(int first) {
            this.first = first;
        }

        int first,second,third;
    }
        
    //MAIN ---------------------------------------------------------------------
    public static void main(String[] args) {

        String[] words = sc1.nextLine().split ("\\s+");
        
        //SCANAR VALORES ---------------------
        int linhas = Integer.parseInt(words[0]);
        int colunas = Integer.parseInt(words[1]);
        int coordFirst = 0, coordSecond = 0, coordThird = 0;
        VectorColunas coordinates = null;
        
        for (int i = 0; i < linhas; i++){
            String[] myInput = sc1.nextLine().split ("\\s+");
     
            if (colunas > 0){
                coordFirst = Integer.parseInt(myInput[0]);
                coordinates = new VectorColunas(coordFirst + LIMIT);                
            }
            
            if(colunas > 1) {
                coordSecond = Integer.parseInt(myInput[1]);
                coordinates.second = coordSecond + LIMIT;
            }
            
            if(colunas > 2) {
                coordThird = Integer.parseInt(myInput[2]);
                coordinates.third = coordThird + LIMIT;
            }
            vector.add(coordinates);
        }
        
        //ALGORITMO DE COMPARAÇÃO DE RADICAIS ---------------------
        radixSort(linhas,colunas);
        
        //IMPRESSAO
        for (VectorColunas coordenada : vector){
            if (colunas == 1)
                System.out.println(coordenada.first - LIMIT);
                        
            else if (colunas == 2)
                System.out.println((coordenada.first - LIMIT) + " " + (coordenada.second - LIMIT));
            
            else 
                System.out.println((coordenada.first-LIMIT) + " " + (coordenada.second - LIMIT) + " " + (coordenada.third - LIMIT));
        }
    }
    
    //RADIX SORT ALGORITMO -----------------------------------------------------
    private static void radixSort(int n, int d){
        VectorColunas[] temp = new VectorColunas[n];
        int[] counter = new int[VALUE];
        int max = 0, k, i, expoente, aux = 0;
        
        k = d;
        while (k > 0){
            if(k < 2){
                max = vector.get(0).first;
                for(i = 0; i < n; i++)
                    if(vector.get(i).first > max) 
                        max = vector.get(i).first;
            }
            else if(k < 3){
                max = vector.get(0).second;
                for(i = 0; i < n; i++)
                    if(vector.get(i).second > max)
                        max=vector.get(i).second;
            }
            else {
                max = vector.get(0).third;
                for(i = 0; i < n; i++)
                    if(vector.get(i).third > max)
                        max = vector.get(i).third;
            }
            
            expoente = 1;
            while (max/expoente > 0){
                if(k < 2){
                    Arrays.fill(counter,0);
                    for (i=0; i < n; i++)
                        counter[(vector.get(i).first / expoente) % VALUE]++;

                    for (i = 1; i < VALUE; i++)
                        counter[i] += counter[i-1];

                    for (i = n-1; i >= 0; i--){
                        aux = counter[(vector.get(i).first / expoente) % VALUE] -1;
                        temp[aux] = vector.get(i);
                        counter[(vector.get(i).first / expoente) % VALUE]--;
                    }
                    for (i = 0; i < n; i++)
                        vector.set(i , temp[i]);
                }
                else if(k < 3){
                    Arrays.fill(counter,0);
                    for (i = 0; i < n; i++)
                        counter[(vector.get(i).second/expoente)%VALUE]++;
                        
                    for (i = 1; i < VALUE; i++)
                        counter[i] += counter[i-1];
                    
                    for (i = n-1; i >= 0; i--){
                        aux = counter[(vector.get(i).second / expoente) % VALUE] -1;
                        temp[aux] = vector.get(i);
                        counter[(vector.get(i).second / expoente) % VALUE]--;
                    }
                    for (i = 0; i < n; i++)
                        vector.set(i,temp[i]);
                }
                
                else{
                    Arrays.fill(counter,0);
                    for (i = 0; i < n; i++)
                        counter[(vector.get(i).third / expoente) % VALUE]++;
                    
                    for (i = 1; i < VALUE; i++)
                        counter[i] += counter[i-1];
                    
                    for (i = n-1; i >= 0; i--){
                        aux = counter[(vector.get(i).third / expoente) % VALUE] -1;
                        temp[aux] = vector.get(i);
                        counter[(vector.get(i).third / expoente) % VALUE]--;
                    }
                    for (i = 0; i < n; i++)
                        vector.set(i,temp[i]);
                }
            expoente *= VALUE;
            }            
        k--;
        }
    }   
   
}