package projeto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Reserva implements Serializable{
    private Cliente user;
    private int codigoViagem;   //a referência da viagem que utilizamos em cada reserva é únicamente o seu código identificativo
    private Autocarro auto;
    private int lugar;
    private Pagamento pagar;
    private Date data;
    static final long serialVersionUID = 1732334224880444893L;      //variavel fundamental para a implementação da interface Serializable
    
    /**
     * CONSTRUTOR RESERVA
     */
    public Reserva(){}
    
    /**
     * CONSTRUTOR RESERVA
     * @param c
     * @param v
     * @param a
     * @param l
     * @param p
     * @param d
     */
    public Reserva(Cliente c, int v, Autocarro a, int l, Pagamento p,Date d){
        user = c;
        codigoViagem = v;
        auto =a;
        lugar=l;
        pagar=p;
        data=d;
    }

    /**
     * SET PASSWORD
     * @param user
     */
    public void setUser(Cliente user) {
        this.user = user;
    }

    /**
     * GET USER
     * @return user
     */
    public Cliente getUser() {
        return user;
    }

    /**
     * GET SERIALVERSIONUID
     * @return serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * SET AUTO
     * @param auto
     */
    public void setAuto(Autocarro auto) {
        this.auto = auto;
    }

    /**
     * GET AUTO
     * @return auto
     */
    public Autocarro getAuto() {
        return auto;
    }

    /**
     * GET LUGAR
     * @return lugar
     */
    public int getLugar() {
        return lugar;
    }

    /**
     * SET LUGAR
     * @param lugar
     */
    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    /**
     * GET PAGAR
     * @return pagar
     */
    public Pagamento getPagar() {
        return pagar;
    }

    /**
     * SET PAGAR
     * @param pagar
     */
    public void setPagar(Pagamento pagar) {
        this.pagar = pagar;
    }

    /**
     * GET DATA
     * @return data
     */
    public Date getData() {
        return data;
    }

    /**
     * SET DATA
     * @param data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * MÉTODO toString()
     * param l INTEIRO QUE DISTINGUE A LISTAGEM DO UTILIZADOR E DO ADMINISTRADOR
     * @return String
     */
    public String toString(int l){  
        if (l==1 || l==-1)
            System.out.println("********************** RESERVA **********************");
        
        //ADMINISTRADOR
        if (l>=1)   
            return "UTILIZADOR\n" + "->Username:" 
                + this.user.login.getUser() + "\n" + "->Nome:" + this.user.getNome() + "\n" + "AUTOCARRO" + this.auto.toString() + "NÚMERO DO LUGAR\n" + this.lugar + "\n" + "\n" +  "DATA DA RESERVA" + "\n->" + this.data.toLocaleString() + "\nViagem com o código: " + this.codigoViagem 
                    + "\n" + "\n" + this.pagar.toString(1) + "\n";
        //CLIENTE
        return "DATA DA RESERVA" + "\n->" + this.data.toLocaleString() + "\nViagem Com Código:"+this.codigoViagem+"\n" +
                 this.pagar.toString(0) + "\n";
    }


    /**
     * GET CODIGO DA VIAGEM
     * @return codigoViagem
     */
    public int getCodigoViagem() {
        return codigoViagem;
    }

    /**
     * SET CODIGO DA VIAGEM
     * @param codigoViagem
     */
    public void setCodigoViagem(int codigoViagem) {
        this.codigoViagem = codigoViagem;
    }

}
