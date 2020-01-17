
package projeto;

import java.io.Serializable;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Regular extends Cliente implements Serializable{       
    
    /**
     * CONSTRUTOR CLIENTE REGULAR
     */
    public Regular(){
        penalizacao = 0.5;
        nDiasCancela = 7;
        desconto=0;
    }

    /**
     * CONSTRUTOR CLIENTE REGULAR
     * @param nome
     * @param nif
     * @param morada
     * @param telefone
     * @param email
     * @param pass
     * @param secret
     * @param username
     */
    public Regular(String nome, int nif, String morada, int telefone, String email, String pass, String secret, String username){
        desconto = 0;                                           
        penalizacao = 0.5;
        nDiasCancela = 7;
        this.login = new Login(username,pass);
        this.nome=nome;
        this.nif=nif;
        this.morada=morada;
        this.telefone=telefone;
        this.email=email;
        this.secret =secret;
        
    }
    
    /**
     * MÉTODO toString()
     * @return String
     */
    @Override
    public String toString(){
        return "-- CLIENTE REGULAR --\n" + super.toString();
    }
}
