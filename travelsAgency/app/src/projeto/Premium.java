
package projeto;

import java.io.Serializable;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Premium extends Cliente implements Serializable{

    /**
     * CONSTRUTOR CLIENTE PREMIUM
     */
    public Premium(){
        nDiasCancela = 2;
        penalizacao = 0;    
        desconto = 0.1;
    }

    /**
     * CONSTRUTOR CLIENTE PREMIUM
     * @param nome
     * @param nif
     * @param morada
     * @param telefone
     * @param email
     * @param pass
     * @param secret
     * @param username
     */
    public Premium(String nome, int nif, String morada, int telefone, String email, String pass, String secret, String username){
        nDiasCancela = 2;
        penalizacao = 0;
        desconto = 0.1;
        this.nome=nome;
        this.nif=nif;
        this.morada=morada;
        this.telefone=telefone;
        this.email=email;
        this.login = new Login(username,pass);
        this.secret =secret;
    }
    
    /**
     * MÉTODO toString()
     * @return String
     */
    @Override
    public String toString(){
        return "-- CLIENTE PREMIUM --\n" + super.toString();
    }
}
