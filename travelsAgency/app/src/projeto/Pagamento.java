
package projeto;

import java.io.Serializable;


/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public abstract class Pagamento implements Serializable{
    
    protected double quantiaPagar;

    /**
     * CONSTRUTOR PAGAMENTO
     */
    public Pagamento(){}
    
    /**
     * CONSTRUTOR PAGAMENTO
     * @param qtpagar
     */
    public Pagamento(double qtpagar){
        this.quantiaPagar = qtpagar;
    }

    /**
     * GET QUANTIA A PAGAR
     * @return quantiaPagar
     */
    public double getQuantiaPagar(){
        return quantiaPagar;
    }
    
    /**
     * SET QUANTIA A PAGAR
     * @param qtpagar
     */
    public void setQuantiaPagar(double qtpagar){
        quantiaPagar = qtpagar;
    }
    
    /**
     * MÉTODO toString()
     * @param l INTEIRO QUE DISTINGUE A LISTAGEM DO UTILIZADOR E DO ADMINISTRADOR
     * @return String
     */
    public String toString(int l){  
        return "";
    }
    
}
