
package projeto;
import java.io.*;
/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class FicheiroDeObjetos {
    private ObjectInputStream iS;
    private ObjectOutputStream oS;
    
    /**
     * ABRE FICHEIRO PARA LER DE QUALQUER UMA DAS LISTAS 
     * @param nomeFich NOME DO FICHEIRO DE UMA DAS LISTAS
     * 
     * @throws IOException
     */
    public void abreLeitura(String nomeFich) throws IOException{
        iS = new ObjectInputStream(new FileInputStream(new File(nomeFich)));
    }
    
    /**
     * ABRE FICHEIRO PARA ESCREVER DE QUALQUER UMA DAS LISTAS 
     * @param nomeFich NOME DO FICHEIRO DE UMA DAS LISTAS
     * 
     * @throws IOException
     */
    public void abreEscrita(String nomeFich) throws IOException{
        oS = new ObjectOutputStream(new FileOutputStream(nomeFich));
    }
    
    /**
     * LÊ UM OBJETO DE QUALQUER UM DOS FICHEIROS DAS LISTAS 
     * @return iS.readObject() DEVOLVE OBJETO PROPRIAMENTE DITO
     * @throws IOException
     * @throws java.lang.ClassNotFoundException
     */
    public Object leObjeto() throws IOException,ClassNotFoundException{ 
        return iS.readObject();
    }
    
    /**
     * ESCREVE UM OBJETO NUM DOS FICHEIROS DAS LISTAS
     * @param o OBJETO 
     * 
     * @throws IOException
     */
    public void escreveObjeto(Object o) throws IOException{
        oS.writeObject(o);
    }
    
    /**
     * FECHA FICHEIRO DE UMA DAS LISTAS QUE TINHA SIDO ABERTO PREVIAMENTE PARA LEITURA
     * 
     * @throws IOException
     */
    public void fechaLer() throws IOException{
        iS.close();
    }
    
    /**
     * FECHA FICHEIRO DE UMA DAS LISTAS QUE TINHA SIDO ABERTO PREVIAMENTE PARA ESCRITA
     * 
     * @throws IOException
     */
    public void fechaEscrever() throws IOException{
        oS.close();
    }
}   
