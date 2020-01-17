
package projeto;

import java.io.Serializable;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Cheque extends Pagamento implements Serializable{      //perceber o autorizado
    private String nome;
    private int idBanco;
    
    /**
     * CONSTRUTOR CHEQUE
     */
    public Cheque(){}
    
    /**
     * CONSTRUTOR CHEQUE
     * @param qtpagar QUANTIDADE QUE O CLIENTE TEM DE PAGAR
     * @param n NOME DO BANCO
     * @param id ID DO BANCO
     */
    public Cheque(double qtpagar, String n, int id){
        super(qtpagar);
        nome=n;
        idBanco = id;
    }
    
    /**
     * SET NOME DO BANCO
     * @param n NOME DO BANCO
     */
    public void setNome(String n){
        nome=n;
    }
    
    /**
     * SET ID DO BANCO
     * @param id ID DO BANCO
     */
    public void setIdBanco(int id){
        idBanco = id;
    }
    
    /**
     * GET NOME DO BANCO
     * @return n NOME DO BANCO
     */
    public String getNome(){
        return nome;
    }
    
    /**
     * GET NOME DO BANCO
     * @return idBanco ID DO BANCO
     */
    public int getIdBanco(){
        return idBanco;
    }
    
    /**
     * MÉTODO toString()
     * @param l INTEIRO QUE DISTINGUE A LISTAGEM DO UTILIZADOR E DO ADMINISTRADOR
     * @return String
     */
    @Override
    public String toString(int l){
        //Administrador
        if(l==1)    
            return "PAGAMENTO\n" + "->Preço da Reserva:" + this.quantiaPagar + " euros" + "\n" + "->Pago? Sim"
                    + "\n" + "->Método: Cheque"  + "\n" + "->Nome do Banco:" + this.nome + "\n" + "->ID do Banco:" + this.idBanco 
                    + "\n";
       //Cliente 
       return "PAGAMENTO\n" + "->Preço da Reserva:" + this.quantiaPagar + " euros" + "\n" + "->Pago? Sim"
                    + "\n" + "->Método: Cheque" + "\n";
    }
}
