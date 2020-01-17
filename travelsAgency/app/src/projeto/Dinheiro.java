
package projeto;

import java.io.Serializable;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Dinheiro extends Pagamento implements Serializable{
    private double quantiaDada;         //DINHEIRO DADO PELO CLIENTE E QUE PODE SER IGUAL OU SUPERIOR À QUANTIA A PAGAR
    private double quantiaDevolver;
    
    /**
     * CONSTRUTOR DINHEIRO
     */
    public Dinheiro(){}
    
    /**
     * CONSTRUTOR DINHEIRO
     * @param qtpagar
     * @param quantiaDada
     */
    public Dinheiro(double qtpagar,double quantiaDada){
        super(qtpagar);
        this.quantiaDada = quantiaDada;
        this.quantiaDevolver = geraTroco();
    }
    
    /**
     * CALCULA O DINHEIRO QUE DEVE SER DEVOLVIDO AO CLIENTE CASO ESTE INSIRA UMA UQNATIA SUPERIOR À QUANTIA À PAGAR
     * @return quantiaDada - quantiaPagar RETORNA O DINHEIRO QUE DEVE SER DADO DE VOLTA AO CLIENTE
     */
    public final double geraTroco(){
        return quantiaDada - quantiaPagar;
    }

    /**
     * GET QUANTIA A DEVOLVER AO CLIENTE
     * @return quantiaDevolver RETORNA O DINHEIRO QUE DEVE SER DADO DE VOLTA AO CLIENTE
     */
    public double getQuantiaDevolver() {
        return quantiaDevolver;
    }

    /**
     * SET QUANTIA A DEVOLVER AO CLIENTE
     * @param quantiaDevolver  DINHEIRO QUE DEVE SER DADO DE VOLTA AO CLIENTE
     */
    public void setQuantiaDevolver(double quantiaDevolver) {
        this.quantiaDevolver = quantiaDevolver;
    }
    
    /**
     * SET DINHEIRO QUE O CLIENTE DEU PARA PAGAR RESERVA
     * @param quantia
     */
    public void setQuantiaDada(double quantia){
        quantiaDada = quantia;
    }
   
    /**
     * GET DINHEIRO QUE O CLIENTE DEU PARA PAGAR RESERVA
     * @return quantiaDada DINHEIRO QUE O CLIENTE DEU PARA PAGAR RESERVA
     */
    public double getQuantiaDada(){
        return quantiaDada;
    }
    
    /**
     * MÉTODO toString()
     * @param l INTEIRO QUE DISTINGUE A LISTAGEM DO UTILIZADOR E DO ADMINISTRADOR
     * @return String
     */
    @Override
    public String toString(int l){
        return "PAGAMENTO\n" + "->Preço da Reserva:" + this.quantiaPagar + " euros" + "\n" + "->Pago? Sim"+ "\n" 
            + "->Método: Dinheiro" + "\n" + "->Quantia Fornecida:" + this.quantiaDada + " euros" + "\n" + "->Quantia a Devolver:" 
            + this.quantiaDevolver + " euros" + "\n";
    }
}
