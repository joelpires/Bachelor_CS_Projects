
package projeto;
import java.io.*;
/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public class FicheiroDeTexto {
    private BufferedReader  fR;
    private BufferedWriter fW;

    /**
     * ABRE O FICHEIRO DAS ESTATISTICAS PARA LEITURA
     * @param nomeFich
     * @throws IOException
     * 
     */
    public void abreLeitura(String nomeFich) throws IOException{
        fR = new BufferedReader(new FileReader(nomeFich));
    }
    
    /**
     * ABRE O FICHEIRO DAS ESTATISTICAS PARA ESCRITA
     * @param nomeFich
     * @throws IOException
     * 
     */
    public void abreEscrita(String nomeFich) throws IOException{
        fW = new BufferedWriter(new FileWriter(nomeFich));
    }
    
    /**
     * Lê UMA LINHA DE CADA VEZ DO FICHEIRO DAS ESTATÍSTICAS
     * @return fr.readline() UMA LINHA(STRING) DO FICHEIRO DAS ESTATISTICAS
     * @throws IOException
     */
    public String leLinha() throws IOException{
        return fR.readLine();
    }
    
    public int[] leNumeroInt()throws IOException{   //TO DO: ISTO NÃO É PARA ELIMINAR????
        int[]result = new int [2];
        String st = fR.readLine();
        if (st!= null){
            result[0] = 0;
            result[1] = Integer.parseInt(st);
        }
        else
            result[0] = -1;
        return result;
    }
    
    /**
     * Lê UMA LINHA DE CADA VEZ DO FICHEIRO DAS ESTATÍSTICAS
     * @param linha 
     * @throws IOException
     */
    public void escreveLinha(String linha)throws IOException{
        fW.write(linha,0,linha.length());
        fW.newLine();
    }
    
    public void escreveNumero(int n)throws IOException{ //TO DO: ISTO NÃO É PARA ELIMINAR????
        String st = "";
        st = st.valueOf(n);
        escreveLinha(st);
    }
    
    /**
     * FECHA O FICHEIRO DAS ESTATISTICAS PARA LEITURA
     * @throws IOException
     */
    public void fechaLeitura()throws IOException{
        fR.close();
    }
    
    /**
     * FECHA O FICHEIRO DAS ESTATISTICAS PARA ESCRITA
     * @throws IOException
     */
    public void fechaEscrita()throws IOException{
        fW.close();
    }
}
