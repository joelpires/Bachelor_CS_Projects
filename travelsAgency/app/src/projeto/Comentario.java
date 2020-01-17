package projeto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Comentario implements Serializable{
    private int pont;
    private String comentario;
    private String nomeC;
    private Date data;
    
    /**
     * CONSTRUTOR COMENTÁRIO
     */
    public Comentario(){}
    
    /**
     * CONSTRUTOR COMENTÁRIO
     * @param p PONTUAÇÃO
     * @param c TEXTO DO COMENTÁRIO
     * @param n NOME DE QUEM COMENTA
     * @param d DATA DO COMENTÁRIO
     */
    public Comentario(int p, String c,String n, Date d){
        pont=p;
        comentario = c;
        nomeC = n;
        data=d;
    }
    
    /**
     * SET PONTUAÇÃO DO COMENTÁRIO
     * @param p PONTUAÇÃO
     */
    public void setPont(int p){
        pont=p;
    }
    
    /**
     * SET TEXTO DO COMENTÁRIO
     * @param c STRING COM O TEXTO DO COMENTÁRIO
     */
    public void setComentario(String c){
        comentario = c;
    }
    
    /**
     * SET NOME DE QUEM COMENTA
     * @param n NOME DE QUEM COMENTA
     */
    public void setNomeC(String n){
        nomeC=n;
    }
    
    /**
     * SET DATA DO COMENTÁRIO
     * @param d DATA DO COMENTÁRIOA
     */
    public void setData(Date d){
        data=d;
    }
    
    /**
     * GET PONTUAÇÃO DO COMENTÁRIO
     * @return p PONTUAÇÃO
     */
    public int getPont(){
        return pont;
    }
    
    /**
     * GET TEXTO PROPRIAMENTE DITO DO COMENTÁRIO
     * @return comentario 
     */
    public String getComentario(){
        return comentario;
    }
    
    /**
     * GET NOME DE QUEM COMENTA
     * @return nomeC NOME DE QUEM COMENTA
     */
    public String getNomeC(){
        return nomeC;
    }
    
    /**
     * GET DATA DO COMENTÁRIO
     * @return data DATA DO COMENTÁRIO
     */
    public Date getData(){
        return data;
    }
    
    /**
     * MÉTODO toString()
     * @return String
     */
    @Override
    public String toString(){
        return "->Comentário:\n\tNome:" + this.nomeC + "\n" + "\tData: " + this.data.toLocaleString()+ "\n" + "\tTexto: " + this.comentario + "\n" + "\tPontuacao: " + this.pont + "\n";
    }
}
