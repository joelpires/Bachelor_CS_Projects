
package projeto;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Login implements Serializable{
    private String username;
    private String pass;
    
    /**
     * CONSTRUTOR LOGIN
     */
    public Login(){}
    
    /**
     * CONSTRUTOR LOGIN
     * @param u
     * @param p
     */
    public Login(String u, String p){
        username = u;
        pass=p;
    }
    
    /**
     * MÉTODO QUE PERMITE O UTILIZADOR INSERIR OS SEUS DADOS DE LOGIN, E VERIFICA SE ELES SE ENCONTRAM JÁ NA LISTA DE UTILIZADORES
     * @param listaU
     * @return Utilizador
     */
    public Utilizador Logar(ArrayList<Utilizador> listaU){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("-- LOGIN --\n");
        System.out.println("(Cuidado: Sensível a Maísculas)\n");
        System.out.print("Username: ");
        this.username = sc.nextLine();
        if(this.username.equals("-1"))
            return null;
        
        System.out.print("Pass: ");
        this.pass = sc.nextLine();
        for(int i=0;i<listaU.size();i++){
            if(this.username.equals(listaU.get(i).login.getUser()) && this.pass.equals(listaU.get(i).login.getPass()))
                return listaU.get(i);
        }
        return null;
    }
    
    /**
     * SET USERNAME
     * @param user
     */
    public void setUser(String user){
        username = user;
    }
    
    /**
     * GET USERNAME
     * @return username
     */
    public String getUser(){
        return username;
    }
    
    /**
     * SET PASSWORD
     * @param password
     */
    public void setPass(String password){
        pass = password;
    }
    
    /**
     * GET PASSWORD
     * @return pass
     */
    public String getPass(){
        return pass;
    }
    
    /**
     * MÉTODO toString()
     * @return String
     */
    @Override
    public String toString(){
        return "Username: " + this.username + "\n" + "Password: " + this.pass + "\n";
    }
}
