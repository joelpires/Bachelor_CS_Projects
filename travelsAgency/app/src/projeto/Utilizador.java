
package projeto;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public abstract class Utilizador implements Serializable{     
    protected String nome;
    protected int nif;
    protected String morada;
    protected int telefone;
    protected String email;
    protected Login login;
    protected String secret;                               //Password secreta alternativa, útil para quando o utilizador quer recuperar a sua password principal
    private ArrayList<String> notificacoes = new ArrayList<>();
    private double DinheiroReceber;                      //Dinheiro que o utilizador tem de receber de reservas canceladas/viagens apagadas pelos administradores e quando os utilizadores dão mais dinheiro do que necessário para pagar as reservas
    
    /**
     * MÉTODO QUE SOFRERÁ OVERRIDE PELAS SUAS SUBCLASSES: CLIENTE E ADMINISTRADOR
     * @param d DATA
     * @param listaU lista Utilizadores
     * @param listaV lista viagens
     * @param listaA lista autocarros
     * @param listaR lista reservas
     * @param listaC lista reservas canceladas
     * @param listaE lista reservas em espera
     */
    public void options(Date d, ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,  ArrayList<Reserva> listaR,  ArrayList<Reserva> listaC, ArrayList<Reserva> listaE){}; //no caso do Administrador//no caso do Administrador
    
    /**
     * MÉTODO EVOCADO PELO signup() QUE PERMITE FINALIZAR O REGISTO DE UM NOVO UTILIZADOR NO PROGRAMA
     * @param d DATA
     * @param lista lista Utilizadores
     * @param listaV lista viagens
     * @param listaA lista autocarros
     * @param listaR lista reservas
     * @param listaC lista reservas canceladas
     * @param listaE lista reservas em espera
     * @throws InterruptedException 1
     */
        
    public void join(Date d,ArrayList<Utilizador> lista, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws InterruptedException{
        int a;
        String b;
        boolean l;
        String pass;
        Scanner sc = new Scanner(System.in);
        Projeto.clear();
        System.out.println("\n-- SIGN UP --\n");
        System.out.print("Insira o seu nome: ");
        while(true){                              
            if((b = sc.nextLine()).isEmpty() || Projeto.checkString(b, 0) == false)
                System.out.print("Nome Inválido. Por favor insira o seu nome: ");
            else{
                if(b.equals("-1"))
                    return;
                this.nome = b;
                break;
            }
        }
        System.out.print("\nInsira o seu NIF: ");
        while(true){
            l = true;
            try{
                b = sc.nextLine();
                a = Integer.parseInt(b);         
                if(a<1000000000 && a>99999999){
                   for(int i=0;i<lista.size();i++){
                        if(lista.get(i).getNif() == a){
                            System.out.print("Esse NIF já está a ser utilizado. Insira um NIF Válido: ");
                            l = false;
                            break;
                        }
                   }
                   if (l == true){
                       this.nif = a;
                       break;
                    }
                }else
                    System.out.print("Insira um NIF Válido: ");
            }
            catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um NIF Válido: ");
            }
        }
        System.out.print("\nInsira a sua morada: ");
        while(true){
            if((b = sc.nextLine()).isEmpty() || Projeto.checkString(b, 2) == false)             
                System.out.print("Morada Inválida. Por favor insira a sua morada: ");
            else{
                this.morada = b;
                break;
            }
        }
        System.out.print("\nInsira o seu telefone: ");
        while(true){
            try{
                b = sc.nextLine();
                a = Integer.parseInt(b);          
                if(a<1000000000 && a>99999999){
                    for(int i=0;i<lista.size();i++){
                        if(lista.get(i).getTelefone() == a){
                            System.out.print("Esse telefone já está a ser utilizado. Insira um telefone Válido: ");
                            l = false;
                            break;
                        }
                    }
                    if (l == true){
                        this.telefone = a;
                         break;
                    }
                } else
                    System.out.print("Insira o seu telefone correctamente: ");
            }
            catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um número de telefone válido: ");
            }
        }
        System.out.print("\nInsira o seu email: ");    
        while(true){
            l = true;
            try{
                b = sc.nextLine();             
                if((!b.isEmpty()) && (Projeto.checkString(b, 1) == true)){
                    for(int i=0;i<lista.size();i++){
                        if(lista.get(i).getEmail().equalsIgnoreCase(b)){
                            System.out.println("Esse Email já está a ser utilizado. Insira um email válido: ");
                            l = false;
                            break;
                        }
                   }
                   if (l == true){
                       this.email = b;
                       break;
                   }
                } else
                    System.out.print("O email que inseriu não possui domínio válido. Insira corretamente: ");
            }
            catch(InputMismatchException e){
                System.out.print("Insira um email válido: ");
            }
        }
        System.out.print("\n\n");
        System.out.println("                    INFORMAÇÕES PARA LOGIN");
        System.out.println("(Necessário para fazer início de sessão. Não são permitidos espaços)\n");
        System.out.print("Username: ");
        while (true){
            l = true;
            b = sc.next();
            if(!b.isEmpty() && (Projeto.checkString(b, 2) == true)){
                for(int i=0;i<lista.size();i++){
                    if(lista.get(i).getUsername().equalsIgnoreCase(b)){
                        System.out.println("Esse username já está a ser utilizado.Por Favor escolha outro username: ");
                        l = false;
                        break;
                    }
                }
                if (l == true){
                   break;
                }
            }
            else
                System.out.print("Não inseriu o username!");
        }
        System.out.print("\nEscolha a sua Password: ");
        sc.nextLine();
        while(true){                              
            if((pass = sc.nextLine()).isEmpty())
                System.out.print("Password Inválida. Por favor insira a sua password: ");
            else
                break;
        }
        this.login = new Login(b,pass);
        lista.add(this);
        if (this instanceof Administrador){
            System.out.println("\n-- CONTA DE ADMINISTRADOR CRIADA COM SUCESSO --");
            System.out.println("                ENTRANDO...");
            TimeUnit.SECONDS.sleep(2);
            Projeto.clear();
            this.options(d, lista, listaV, listaA, listaR, listaC, listaE);
        }else{
            System.out.println("-- CONTA DE CLIENTE CRIADA COM SUCESSO --");
            System.out.println("                ENTRANDO...");
            TimeUnit.SECONDS.sleep(2);
            Projeto.clear();
            this.options(d, lista, listaV, listaA, listaR, listaE, listaC);
        }
        
    }

    /**
     * PERMITA AO UTILIZADOR ALTERAR MORADA/TELEFON/EMAIL E PASSWORD. NÃO PERMITE MUDAR DADOS ESSENCIAIS COMO O NOME/NIF OU USERNAME
     * @param lista lista de utilizadores
     */
    public void alteraDados(ArrayList<Utilizador>lista){            
        int ch = 0,a;
        String b;
        boolean l=true;
        Scanner sc = new Scanner(System.in);            
        while(true){
            while(true){  
                Projeto.clear();
                System.out.println("----- EDITAR DADOS -----");
                System.out.println("[1]EDITAR MORADA");
                System.out.println("[2]EDITAR TELEFONE");
                System.out.println("[3]EDITAR EMAIL");         
                System.out.println("[4]EDITAR PASSWORD");
                System.out.println("[5]MENU ANTERIOR\n");
                System.out.print("SELECIONE A SUA OPÇÃO: ");
           
                try{ 
                    ch = sc.nextInt();
                    sc.nextLine();
                    if(ch<1 || ch>5)
                        System.out.println("NUMERO INVALIDO");
                    else
                        break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                    sc.nextLine();
                }
            }  
            Projeto.clear();
            switch (ch) {
                case 1:
                    System.out.print("\nInsira a sua nova morada: ");
                    while(true){
                        if((b = sc.nextLine()).isEmpty() || Projeto.checkString(b, 2) == false)
                            System.out.print("Morada Inválida. Por favor insira a sua morada: ");
                        else{
                            this.morada = b;
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.print("\nInsira o seu novo telefone: ");
                    while(true){
                        try{
                            b = sc.nextLine();
                            a = Integer.parseInt(b);          
                            if(a<1000000000 && a>99999999){
                                for(int i=0;i<lista.size();i++){
                                    if(lista.get(i).getTelefone() == a){
                                        System.out.print("Esse telefone já está a ser utilizado. Insira um telefone Válido: ");
                                        l = false;
                                        break;
                                    }
                                }
                                if (l == true){
                                    this.telefone = a;
                                     break;
                                }
                            } else
                                System.out.print("Insira o seu telefone correctamente: ");
                        }
                        catch(InputMismatchException e){
                            System.out.print("Insira o seu telefone correctamente: ");
                        }
                        catch(NumberFormatException e){
                            System.out.print("Insira um número de telefone válido: ");
                        }
                    }
                    break;
                case 3:
                    System.out.print("\nInsira o seu novo email: ");    
                    while(true){
                        l = true;
                        try{
                            b = sc.nextLine();             
                            if((!b.isEmpty()) && (Projeto.checkString(b, 1) == true)){
                                for(int i=0;i<lista.size();i++){
                                    if(lista.get(i).getEmail().equalsIgnoreCase(b)){
                                        System.out.println("Esse Email já está a ser utilizado. Insira um email válido: ");
                                        l = false;
                                        break;
                                    }
                               }
                               if (l == true){
                                   this.email = b;
                                   break;
                               }
                            } else
                                System.out.print("O email que inseriu não possui domínio válido. Insira corretamente: ");
                        }
                        catch(InputMismatchException e){
                            System.out.print("Insira um email válido: ");
                        }
                    }
                    break;
                case 4:
                    System.out.print("\nEscolha a sua nova Password: ");
                    sc.nextLine();
                    while(true){                              
                        if((b = sc.nextLine()).isEmpty())
                            System.out.print("Password Inválida. Por favor insira a sua password: ");
                        else
                            this.setPass(b);
                            break;
                    }
                    break;
                case 5:
                    return;
            }
        }
    }

    /**
     * GET NOME DO UTILIZADOR
     * @return nome
     */
    public String getNome(){
        return nome;
    }
    
    /**
     * GET NIF DO UTILIZADOR
     * @return nif
     */
    public int getNif(){
        return nif;
    }
    
    /**
     * GET MORADA DO UTILIZADOR
     * @return morada
     */
    public String getMorada(){
        return morada;
    }
    
    /**
     * GET TELEFONE DO UTILIZADOR
     * @return telefone
     */
    public int getTelefone(){
        return telefone;
    }
    
    /**
     * GET EMAIL DO UTILIZADOR
     * @return email
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * GET PASSWORD DO UTILIZADOR
     * @return login.getPass()
     */
    public String getPass(){
        return login.getPass();
    }
    
    /**
     * GET PASSWORD SECRETA ALTERNATIVA DO UTILIZADOR
     * @return secret
     */
    public String getSecret(){
        return secret;
    }
    
    /**
     * GET USERNAME DO UTILIZADOR
     * @return login.getUser()
     */
    public String getUsername(){
        return login.getUser();
    }
    
    /**
     * SET NOME DO UTILIZADOR
     * @param nome nome do utilizador
     */
    public void setName (String nome){
         this.nome = nome;
    }
    
    /**
     * SET NIF DO UTILIZADOR
     * @param nif nif do utilizador
     */
    public void setNif (int nif){
         this.nif = nif;
    }
    
    /**
     * SET MORADA DO UTILIZADOR
     * @param morada morada do utilizador
     */
    public void setMorada (String morada){
        this.morada = morada;
    }
    
    /**
     * SET TELEFONE DO UTILIZADOR
     * @param telefone telefone do utilizador
     */
    public void setTelefone (int telefone){
        this.telefone = telefone;
    }
    
    /**
     * SET EMAIL DO UTILIZADOR
     * @param email email do utilizador
     */
    public void setEmail (String email){
        this.email = email;
    }
    
    /**
     * SET PASSWORD SECRETA ALTERNATIVA DO UTILIZADOR
     * @param secret secreta do utilizador
     */
    public void setSecret (String secret){
        this.secret = secret;
    }
    
    /**
     * SET PASSWORD DO UTILIZADOR
     * @param pass pass do utilizador
     */
    public void setPass (String pass){
        this.login.setPass(pass);
    }
    
    /**
     * SET USERNAME DO UTILIZADOR
     * @param username username do utilizador
     */
    public void setUsername (String username){
        this.login.setUser(username);
    }

    /**
     * GET LISTA DE NOTIFICAÇÕES DO UTILIZADOR
     * @return notificacoes
     */
    public ArrayList<String> getNotificacoes() {
        return notificacoes;
    }

    /**
     * SET LISTA DE NOTIFICAÇÕES DO UTILIZADOR
     * @param notificacoes as notificações enviadas ao utilizador
     */
    public void setNotificacoes(ArrayList<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    /**
     * GET DINHEIRO O UTILIZADOR TEM A RECEBER
     * @return DinheiroReceber
     */
    public double getDinheiroReceber() {
        return DinheiroReceber;
    }

    /**
     * SET DINHEIRO O UTILIZADOR TEM A RECEBER
     * @param DinheiroReceber quando se cancela uma reserva, o utilizador terá dinheiro a receber
     */
    public void setDinheiroReceber(double DinheiroReceber) {
        this.DinheiroReceber = DinheiroReceber;
    }
    
    /**
     * MÉTODO toString()
     * @return String
     */
    @Override
    public String toString(){
        return "Username: "+ this.login.getUser()+"\nNome: " + this.nome + "\nNIF: " + this.nif + "\nMorada: " + this.morada + "\nTelefone: " + this.telefone + "\nEmail: " + this.email;
    }
}
