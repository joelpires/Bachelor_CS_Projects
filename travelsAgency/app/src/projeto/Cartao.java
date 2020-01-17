
package projeto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Cartao extends Pagamento implements Serializable{     
    private int num;
    private Date validade;
    
    /**
     * CONSTRUTOR CARTÃO
     */
    public Cartao(){}
    
    /**
     * CONSTRUTOR CARTÃO
     * @param qtpagar QUANTIDADE QUE O CLIENTE TEM DE PAGAR
     * @param n NUMERO DO CARTÃO
     * @param v DATA DE VALIDADE
     */
    public Cartao(double qtpagar, int n, Date v){
        super(qtpagar);
        num = n;
        validade = v;
    }

    /**
     * SET NÚMERO DO CARTÃO
     * @param n NÚMERO DO CARTÃO
     */
    public void setNum(int n){
        num = n;
    }
    
    /**
     * SET DATA DE VALIDADE DO CARTÃO
     * @param v DATA DE VALIDADE DO CARTÃO
     */
    public void setValidade(Date v){
        validade = v;
    }

    /**
     * GET NÚMERO DO CARTÃO
     * @return num NÚMERO DO CARTÃO
     */
    public int getNum() {
        return num;
    }

    /**
     * GET DATA DE VALIDADE DO CARTÃO
     * @return validade DATA DE VALIDADE DO CARTÃO
     */
    public Date getValidade() {
        return validade;
    }
    
    /**
     * MÉTODO toString()
     * @param l INTEIRO QUE DISTINGUE A LISTAGEM DO UTILIZADOR E DO ADMINISTRADOR
     * @return String
     */
    @Override
    public String toString(int l){
        //ADMINISTRADOR
        if(l==1)    
            return "PAGAMENTO\n" + "->Preço da Reserva:" + this.quantiaPagar + " euros" + "\n" + "->Pago? Sim"
                    + "\n" + "->Método: Cartão\n" + "->Número do Cartão:" + this.num 
                    + "\n" + "->Validade:" +  this.validade.getDate() + "/" + (this.validade.getMonth()+1) + "/" + this.validade.getYear()+ "\n"; 
        //CLIENTE
       return "PAGAMENTO\n" + "->Preço da Reserva:" + this.quantiaPagar + " euros" + "\n" + "->Pago? Sim" + "\n" 
               + "->Método: Cartão\n"; 
    }
}
