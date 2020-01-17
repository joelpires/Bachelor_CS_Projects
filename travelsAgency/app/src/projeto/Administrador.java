package projeto;

import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Administrador extends Utilizador implements Serializable{
    
    private static String password = "password"; //PASSWORD QUE PERMITE UTILIZADORES REGISTAREM-SE COMO ADMINISTRADORES
    
    /**
     * CONSTRUTOR ADMINISTRADOR
     */
    public Administrador(){}

    /**
     * CONSTRUTOR ADMINISTRADOR
     * @param nome
     * @param nif
     * @param morada
     * @param telefone
     * @param email
     * @param pass
     * @param secret
     * @param username
     */
    public Administrador(String nome, int nif, String morada, int telefone, String email, String pass, String secret, String username){
        this.login = new Login(username,pass);
        this.nome=nome;
        this.nif=nif;
        this.morada=morada;
        this.telefone=telefone;
        this.email=email;
        this.secret =secret;
    }
    
    /**
     * METODO QUE REDIRECIONA PARA MENUS ESPECIFICOS DE ACORDO COM A OPÇÃO INSERIDA NO METODO adminMenu()
     * @param d DATA
     * @param listaU lista Utilizadores
     * @param listaV lista viagens
     * @param listaA lista autocarros
     * @param listaR lista reservas
     * @param listaC lista reservas canceladas
     * @param listaE lista reservas em espera
     * 
     */
    @Override
    public void options (Date d, ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,  ArrayList<Reserva> listaR,  ArrayList<Reserva> listaC, ArrayList<Reserva> listaE){
        int choice;
        while(true){
            try {
                choice = adminMenu();
                Projeto.clear();
                
                switch (choice){
                    case 1:
                        System.out.println("----- OPERACOES VIAGENS -----");
                        adminViagOptions(listaU, listaV, listaA, listaR, listaC, listaE);
                        break;
                    case 2:
                        System.out.println("----- OPERACOES RESERVAS -----");
                        adminReservOptions(listaV,listaU,listaR, listaC, listaE);
                        break;
                    case 3:
                        System.out.println("----- OPERACOES COMENTARIOS -----");
                        adminComOptions(listaV);
                        break;
                    case 4:
                        System.out.println("----- OPERACOES UTILIZADORES -----");
                        adminUtilOptions(listaU,listaE);
                        break;
                    case 5:
                        System.out.println("----- OPERACOES AUTOCARROS -----");
                        adminAutoOptions(listaA,listaV);
                        break;
                    case 6:
                        System.out.println("----- DADOS ESTATÍSTICOS -----");
                        adminStatsOptions(listaR,listaV);
                        break;
                    case 7:
                        System.out.println("----- EDITAR DADOS PESSOAIS -----");
                        this.alteraDados(listaU);
                        break;
                    case 8:
                        return;       
                }
            } catch (ParseException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }          
    }
    
    /**
     * MENU QUE APARECE NO ECRÃ ASSIM QUE O ADMINISTRADOR FAZ LOGIN E QUE LHE PERMITE ENTRAR NOS MENUS DAS PRINCIPAIS OPERAÇOES
     * @return choice OPÇÃO ESCOLHIDA PELO ADMINISTRADOR
     */
    public static int adminMenu (){
       int choice;

        while(true){
            Scanner sc = new Scanner(System.in);
            Projeto.clear();
            System.out.println("-- BEM VINDO ADMINISTRADOR --\n");
            System.out.println("[1]OPERACOES VIAGENS");
            System.out.println("[2]OPERACOES RESERVAS");
            System.out.println("[3]OPERACOES COMENTARIOS");
            System.out.println("[4]OPERACOES UTILIZADORES");
            System.out.println("[5]OPERACOES AUTOCARROS");
            System.out.println("[6]DADOS ESTATISTICOS");
            System.out.println("[7]EDITAR DADOS");
            System.out.println("[8]TERMINAR SESSAO");
            System.out.print("\nSELECIONE A SUA OPÇÃO: ");
       
            try{ 
                choice = sc.nextInt();
                sc.nextLine();
                if(choice<1 || choice>8)
                    System.out.println("NUMERO INVALIDO");
                else
                    return choice;
            }
            catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
    }
    
    /**
     *  MENU QUE ESCREVE NO ECRÃ AS OPERAÇÕES RELATIVAS ÀS VIAGENS E, MEDIANTE A ESCOLHA DO ADMINISTRADOR, REDIRECIONA-O PARA O MÉTODO ADEQUADO
     * @param listaU lista Utilizadores
     * @param listaV lista viagens
     * @param listaA lista autocarros
     * @param listaR lista reservas
     * @param listaC lista reservas canceladas
     * @param listaE lista reservas em espera
     * 
     * @throws ParseException 1
     */
    public void adminViagOptions(ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,  ArrayList<Reserva> listaR,  ArrayList<Reserva> listaC, ArrayList<Reserva> listaE) throws ParseException{
        int ch = 0;
        while(true){
            while(true){
                Scanner sc = new Scanner(System.in);
                
                System.out.println("----- OPERACOES VIAGENS -----");
                System.out.println("[1]CRIAR VIAGEM");
                System.out.println("[2]APAGAR VIAGEM");
                System.out.println("[3]ALTERAR VIAGEM");
                System.out.println("[4]CONSULTAR VIAGENS");
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
                    listaV.add(Viagem.criaViagem(listaV, listaA));
                    System.out.println(listaV.get(0));
                    break;
                case 2:
                    Viagem.removeViagem(listaU,listaV, listaR, listaE,listaC);
                    break;
                case 3:
                    Viagem.alteraViagem(listaU,listaV, listaR, listaE, listaA);
                    break;
                case 4:
                    if(listaV.isEmpty() != true){
                        for (Viagem V1 : listaV) 
                            if(!V1.isRealizada())
                                System.out.println(V1.toString(1));
                    }
                    else
                        System.out.println("Não Existem Viagens para Consultar.");
                    break;
                case 5:
                    return;
            }
        }  
    }    

    /**
     * MENU QUE ESCREVE NO ECRÃ AS OPERAÇÕES RELATIVAS ÀS RESERVAS E, MEDIANTE A ESCOLHA DO ADMINISTRADOR, ELE PODE CANCELAR/CONSULTAR RESERVAS
     * @param listaU lista Utilizadores
     * @param listaV lista viagens
     * @param listaR lista reservas
     * @param listaC lista reservas canceladas
     * @param listaE lista reservas em espera
     * 
     */
    public void adminReservOptions(ArrayList<Viagem> listaV,ArrayList<Utilizador> listaU, ArrayList<Reserva> listaR,  ArrayList<Reserva> listaC, ArrayList<Reserva> listaE){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        String user = "";
        int cod,k;
        
        while(true){
            while(true){
                System.out.println("SELECIONE A SUA OPÇÃO:\n");
                System.out.println("[1]APAGAR RESERVA");
                System.out.println("[2]CONSULTAR RESERVAS ATIVAS");
                System.out.println("[3]CONSULTAR RESERVAS EM FILA DE ESPERA");
                System.out.println("[4]CONSULTAR RESERVAS CANCELADAS");
                System.out.println("[5]MENU ANTERIOR");
           
                try{ 
                    choice = sc.nextInt();
                    sc.nextLine();
                    if(choice<1 || choice>7)
                        System.out.println("NUMERO INVALIDO");
                    else
                        break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                    sc.nextLine();
                }
            }  
            Projeto.clear();

            switch (choice) {
                case 1:
                    System.out.println("Insira o username de quem pretende apagar a reserva: ");
                    user=sc.nextLine();
                    if(user.equals("-1"))
                        break;
                    System.out.println("Insira o código da viagem para a qual a reserva estava feita: ");
                    cod = sc.nextInt();
                    sc.nextLine();
                    if(cod == -1)
                        break;
                    for(int i=0;i<listaR.size();i++){           
                        if(listaR.get(i).getUser().login.getUser().equalsIgnoreCase(user) && listaR.get(i).getCodigoViagem() == cod){
                            String notificacao = "A sua reserva para a viagem "+listaR.get(i).getCodigoViagem()+" foi cancelada.\nEm breve devolveremos o dinheiro.\n";
                            for(int l=0;l<listaU.size();l++){
                                if(listaU.get(l).getUsername().equals(listaR.get(i).getUser().getUsername())){
                                    listaU.get(l).getNotificacoes().add(notificacao);
                                    listaU.get(l).setDinheiroReceber(listaR.get(i).getPagar().getQuantiaPagar());
                                }
                            }
                            for(int j=0;j<listaV.size();j++){
                                if(listaV.get(j).getCodigo() == cod){
                                    for(int l=0;l<listaV.get(j).getReservas().size();l++){
                                        if(listaV.get(j).getReservas().get(l).getUser().login.getUser().equalsIgnoreCase(user)){
                                            Autocarro a1 = listaV.get(j).getReservas().get(l).getAuto();
                                            listaV.get(j).getAutos().get(listaV.get(j).getAutos().indexOf(a1)).setLugares(listaV.get(j).getAutos().get(listaV.get(j).getAutos().indexOf(a1)).getLugares()+1);
                                            listaV.get(j).getReservas().remove(l);
                                        }
                                    }
                                }
                            }
                            listaR.remove(i);
                            for(Reserva R1 : listaE)
                            if (R1.getCodigoViagem() == cod){
                                notificacao = "\nAcabou de Abrir uma Vaga para Reservar a Viagem com o Seguinte Código: " + cod + "\nApresse-se a Pagar a Reserva que Tinha em Espera.\n";
                                k = listaU.indexOf(R1.getUser());
                                for(int l=0;l<listaU.size();l++){
                                    if(listaU.get(l).getUsername().equals(R1.getUser().getUsername()))
                                        listaU.get(l).getNotificacoes().add(notificacao);
                                }                
                            }
                            System.out.println("Notificação Enviada ao Cliente.");
                            System.out.println("Reserva Eliminada com Sucesso.");
                        }
                    }
                    break;
                case 2:
                    for(int i=0;i<listaR.size();i++){
                        System.out.println(listaR.get(i));
                    }
                    break;
                case 3:
                    for(int i=0;i<listaE.size();i++){
                        System.out.println(listaE.get(i));
                    }
                    break;
                case 4:
                    for(int i=0;i<listaC.size();i++){
                        System.out.println(listaC.get(i));
                    }
                    break;
                case 5:
                    return;
            }
        }  
    }
    
    /**
     * MENU QUE ESCREVE NO ECRÃ AS OPERAÇÕES RELATIVAS AOS COMENTARIOS E, MEDIANTE A ESCOLHA DO ADMINISTRADOR, ELE PODE CONSULTAR/ELIMINAR COMENTARIOS
     * @param listaV lista de viagens
     * 
     */
    public void adminComOptions(ArrayList<Viagem> listaV){
        int choice = 0;
        int cod, codC = -1;
        Scanner sc = new Scanner(System.in);
        while(true){
            while(true){
                System.out.println("SELECIONE A SUA OPÇÃO:\n");
                System.out.println("[1]APAGAR COMENTARIO");     //ADMINISTRADOR NAO VAI INSERIR COMENTÁRIOS, MAS SIM APAGÁ-LOS SE ALGUM FOR OFENSIVO
                System.out.println("[2]CONSULTAR COMENTARIOS");
                System.out.println("[3]MENU ANTERIOR");
           
                try{ 
                    choice = sc.nextInt();
                    sc.nextLine();
                    if(choice<1 || choice>3)
                        System.out.println("NUMERO INVALIDO");
                    else
                        break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                    sc.nextLine();
                }
            }  
            Projeto.clear();
            
            switch (choice) { 
                case 1:
                    System.out.println("Insira o código da viagem a consultar:");
                    cod = sc.nextInt();
                    sc.nextLine();
                    if(cod == -1)
                        break;
                    System.out.println("Insira o número do comentário a apagar:");
                    codC = sc.nextInt();
                    sc.nextLine();
                    if(codC == -1)
                        break;
                    for(int i=0;i<listaV.size();i++){
                        if(listaV.get(i).getCodigo() == cod){
                            try{
                                listaV.get(i).getComents().remove(codC);
                            }
                            catch(Exception e){
                                System.out.println("Esse comentário não existe!");
                            }
                            break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Insira o código da viagem a consultar:");
                    cod = sc.nextInt();
                    sc.nextLine();
                    if(cod == -1)
                        break;
                    for(int i=0;i<listaV.size();i++){
                        if(listaV.get(i).getCodigo() == cod){
                            for(int j=0;j<listaV.get(i).getComents().size();j++){
                                System.out.println(j+": "+listaV.get(i).getComents().get(j));
                            }
                            break;
                        }
                    }
                    break;
                case 3:
                    return;
            }
        }  
    }
    
    /**
     * MENU QUE ESCREVE NO ECRÃ AS OPERAÇÕES RELATIVAS AOS UTILIZADORES E, MEDIANTE A ESCOLHA DO ADMINISTRADOR, ELE PODE CONSULTAR/BANIR E ALTERAR PRIVILÉGIOS A UTILIZADORES
     * @param listaU lista utilizadores
     * @param listaE lista reservas em espera
     * 
     */
    public void adminUtilOptions(ArrayList<Utilizador> listaU,ArrayList<Reserva> listaE){
        boolean j = true;
        int choice = 0;
        String user = "";
        Scanner sc = new Scanner(System.in);
        while(true){
            while(true){
                System.out.println("\n[1]CONSULTAR TODOS UTILIZADORES");
                System.out.println("[2]CONSULTAR UTILIZADORES EM FILA DE ESPERA");
                System.out.println("[3]ALTERAR PRIVILEGIOS A UTILIZADORES");
                System.out.println("[4]BANIR UTILIZADOR");
                System.out.println("[5]MENU ANTERIOR");
                System.out.print("SELECIONE A SUA OPÇÃO: ");
           
                try{ 
                    choice = sc.nextInt();
                    sc.nextLine();
                    if(choice<1 || choice>5)
                        System.out.println("NUMERO INVALIDO");
                    else
                        break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                    sc.nextLine();
                }
            }  
            Projeto.clear();

            switch (choice) {
                case 1:
                    for(int i=0;i<listaU.size();i++){
                        System.out.println(listaU.get(i));
                    }
                    break;
                case 2:
                    for(int i=0;i<listaE.size();i++){
                        System.out.print("\nUtilizador: "+listaE.get(i).getUser().login.getUser()+", em espera para a viagem com código "+listaE.get(i).getCodigoViagem());
                    }
                    break;
                case 3:
                    System.out.println("Escreva o username que deseja promover a Premium: ");       
                    user = sc.nextLine();
                    if(user.equals("-1"))
                        break;
                    for(int i=0;i<listaU.size();i++){
                        if(listaU.get(i).getUsername().equalsIgnoreCase(user)){
                            Utilizador u1 = listaU.remove(i);
                            Utilizador u2 = new Premium(u1.getNome(),u1.getNif(),u1.getMorada(),u1.getTelefone(),u1.getEmail(),u1.getPass(),u1.getSecret(),u1.getUsername());
                            listaU.add(u2);
                            j = false;
                            break;
                        }
                    }
                    if(!j){
                        System.out.println("Utilizador Promovido Com Sucesso!\n\n");
                    }
                    else
                        System.out.println("Esse Utilizador Não Existe!\n\n");
                    break;
                case 4:
                    System.out.println("Escreva o username que deseja banir: ");
                    user = sc.nextLine();
                    if(user.equals("-1"))
                        break;
                    for(int i=0;i<listaU.size();i++){
                        if(listaU.get(i).getUsername().equalsIgnoreCase(user)){
                            listaU.remove(i);
                            j=false;
                            break;
                        }
                    }
                    if(!j){
                        System.out.println("Utilizador Removido Com Sucesso!\n\n");
                    }
                    else
                        System.out.println("Esse Utilizador Não Existe!\n\n");
                    break;
                case 5:
                    return;
            }
        }  
    }
    
    /**
     * MENU QUE ESCREVE NO ECRÃ AS OPERAÇÕES RELATIVAS AOS AUTOCARROS E, MEDIANTE A ESCOLHA DO ADMINISTRADOR, ELE PODE CRIAR/APAGAR/CONSULTAR AUTOCARROS
     * @param listaA lista de autocarros
     * @param listaV lista de viagens
     * 
     */
    public void adminAutoOptions(ArrayList<Autocarro> listaA,ArrayList<Viagem> listaV){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        String a ="";
        int l=0;
        boolean j=true;
        
        while(true){
            while(true){
                System.out.println("SELECIONE A SUA OPÇÃO:\n");
                System.out.println("[1]CRIAR AUTOCARRO");
                System.out.println("[2]APAGAR AUTOCARRO");
                System.out.println("[3]CONSULTAR AUTOCARROS");
                System.out.println("[4]MENU ANTERIOR");
           
                try{ 
                    choice = sc.nextInt();
                    sc.nextLine();     
                    if(choice<1 || choice>4)
                        System.out.println("NUMERO INVALIDO");
                    else
                        break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS\n\n");
                    sc.nextLine();     
                }
            }  
            Projeto.clear();

            switch (choice) {
                case 1:
                    j=true;
                    System.out.print("Introduza a matrícula do novo Autocarro:\nFormato: _ _-_ _-_ _\n");
                    while(j){
                        a = sc.nextLine();
                        if(a.equals("-1"))
                            break;
                        if(a.length() != 8)
                            System.out.println("Matrícula Inválida");
                        else{
                            j=false;
                        }
                    }
                    if (j==true)
                        break;
                    j=true;
                    System.out.println("Introduza o número de lugares no autocarro: ");
                    while (j){
                        try{
                            l=sc.nextInt();
                            sc.nextLine();
                            if(l == -1)
                                break;
                            if(l<0 || l>60)
                                System.out.println("Por Favor, insira um número válido!\n");
                            else
                                j=false;
                        }
                        catch(InputMismatchException e){
                            System.out.println("INSIRA UM NÚMERO");
                        }
                    }
                    if(j==true)
                        break;
                    Autocarro a1 = new Autocarro(a,l);
                    listaA.add(a1);
                    System.out.println("Autocarro Introduzido com sucesso!\n");
                    break;
                case 2:
                    j=true;
                    System.out.print("Introduza a matrícula do Autocarro:\nFormato: _ _-_ _-_ _\n");
                    while(j){
                        a = sc.nextLine();
                        if(a.equals("-1"))
                            break;
                        if(a.length() != 8)
                            System.out.println("Matrícula Inválida");
                        else{
                            j=false;
                        }
                    }
                    if(j==true)
                        break;
                    for(int i=0;i<listaA.size();i++){
                        if(listaA.get(i).getMatricula().equals(a)){
                            listaA.remove(i);
                            System.out.println("Autocarro Removido Com Sucesso!\n");
                            break;
                        }    
                    }
                    break;
                    
                case 3:
                    for(int i=0;i<listaA.size();i++){
                        System.out.println(listaA.get(i));
                        for(int k=0;k<listaV.size();k++){
                            if(!listaV.get(k).getAutos().isEmpty()){
                                if((listaV.get(k)).getAutos().contains(listaA.get(i))){
                                    System.out.println("A ser utilizado na Viagem: "+listaV.get(i).getCodigo()+" com partida a "+listaV.get(i).getData()+" e duração de "+listaV.get(i).getDuracao()+" dias.");
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    return;
            }
        }  
    }
    
    /**
     * MENU QUE ESCREVE NO ECRÃ O ACESSO A DETERMINADOS DADOS ESTATÍSTICOS E, MEDIANTE A ESCOLHA DO ADMINISTRADOR, É REDIRECIONADO PARA O MÉTODO ADEQUADO
     * @param listaR lista de reservas
     * @param listaV lista de viagens
     * 
     */
    public void adminStatsOptions(ArrayList<Reserva> listaR,ArrayList<Viagem> listaV){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while(true){
            while(true){
                System.out.println("SELECIONE A SUA OPÇÃO:\n");
                System.out.println("[1]VIAGEM MAIS VENDIDA DE UM DETERMINADO MÊS");
                System.out.println("[2]CLIENTE QUE MAIS VIAGENS COMPROU NUM DETERMINADO MÊS");
                System.out.println("[3]VIAGENS SEM RESERVAS NUM DETERMINADO MÊS");      
                System.out.println("[4]VIAGEM COM MELHOR PONTUACAO NUM DETERMINADO MÊS");
                System.out.println("[5]VOLUME DE VENDAS NUM DETERMINADO ANO");
                System.out.println("[6]DIA DO ANO COM MAIOR NÚMERO VENDAS");
                System.out.println("[7]MENU ANTERIOR");
           
                try{ 
                    choice = sc.nextInt();
                    sc.nextLine();
                    if(choice<1 || choice>7)
                        System.out.println("NUMERO INVALIDO");
                    else
                        break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                    sc.nextLine();
                }
            }  
            Projeto.clear();

            switch (choice) {
                case 1:
                    viagemMaisVend(listaR);
                    break;
                case 2:
                    userMaisComprou(listaR);
                    break;
                case 3:
                    viagemSemReservas(listaV);
                    break;
                case 4:
                    melhorViagem(listaV);
                    break;
                case 5:
                    volumeVendas(listaV);
                    break;
                case 6:
                    melhorDia(listaR);
                    break;
                case 7:
                    return;
            }
        }  
    }
    
    /**
     * MEDIANTE A ESCOLHA DE UM DETERMINADO MÊS DE UM DETERMINADO ANO, É DITO AO ADMINISTRADOR QUAL A VIAGEM QUE MAIS VOLUME DE VENDAS TEVE
     * @param listaR lista de reservas
     * 
     */
    public void viagemMaisVend(ArrayList<Reserva> listaR){
        ArrayList<Integer> auxCod = new ArrayList<>();
        ArrayList<Integer> numV = new ArrayList<>();
        int mes,ano,aux=0,num=0;
        boolean o=true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o número do mês que deseja consultar");
        while(true){
            try{ 
                mes = sc.nextInt();
                sc.nextLine();
                if(mes == -1)
                    return;
                if(mes<1 || mes>12)
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        System.out.println("Introduza o número do ano que deseja consultar");
        while(true){
            try{ 
                ano = sc.nextInt();
                if(ano==-1)
                    return;
                if(ano < 2000 || ano > (new Date().getYear() + 1900))
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                        sc.nextLine();
                }
        }
        for(int i=0;i<listaR.size();i++){
            if(listaR.get(i).getData().getMonth() == mes-1 && listaR.get(i).getData().getYear()==ano-1900){
                for(int j=0;j<auxCod.size();j++){
                    if(listaR.get(i).getCodigoViagem() == auxCod.get(j)){
                        numV.set(j, numV.get(j)+1);
                        o=false;
                    }
                }
                if(o){
                    auxCod.add(listaR.get(i).getCodigoViagem());
                    numV.add(1);
                }    
            }
        }
        for(int i=0;i<numV.size();i++){
            if(numV.get(i) > aux){
                num = i;
                aux = numV.get(i);
            }
        }
        System.out.println("A Viagem mais vendida no mês "+mes+" foi a Viagem com o código: "+auxCod.get(num));
        
    }
    
    /**
     * MEDIANTE A ESCOLHA DE UM DETERMINADO MÊS DE UM DETERMINADO ANO, É DITO AO ADMINISTRADOR QUAL O UTILIZADOR QUE MAIS COMPROU
     * @param listaR lista de reservas
     * 
     */
    public void userMaisComprou(ArrayList<Reserva> listaR){
        ArrayList<String> auxCod = new ArrayList<>();
        ArrayList<Integer> numV = new ArrayList<>();
        int mes,ano,aux=0,num=0;
        boolean o=true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o número do mês que deseja consultar");
        while(true){
            try{ 
                mes = sc.nextInt();
                sc.nextLine();
                if(mes==-1)
                    return;
                if(mes<1 || mes>12)
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                    sc.nextLine();
                }
        }
        System.out.println("Introduza o número do ano que deseja consultar");
        while(true){
            try{ 
                ano = sc.nextInt();
                sc.nextLine();
                if(ano==-1)
                    return;
                if(ano<2000 || ano > (new Date().getYear()+1900))
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                     sc.nextLine();
                }
        }
        for(int i=0;i<listaR.size();i++){
            if(listaR.get(i).getData().getMonth() == mes-1 && listaR.get(i).getData().getYear()==ano-1900){
                for(int j=1;j<auxCod.size();j++){
                    if(listaR.get(i).getUser().getUsername().equals(auxCod.get(j))){
                        numV.set(j, numV.get(j)+1);
                        o=false;
                    }
                }
                if(o){
                    auxCod.add(listaR.get(i).getUser().getUsername());
                    numV.add(1);
                }    
            }
        }
        for(int i=0;i<numV.size();i++){
            if(numV.get(i) > aux){
                num = i;
                aux = numV.get(i);
            }
        }
        System.out.println("O cliente que mais comprou no mês "+mes+" foi o Cliente com o username: "+auxCod.get(num));
        
    }
    
    /**
     * MEDIANTE A ESCOLHA DE UM DETERMINADO MÊS DE UM DETERMINADO ANO, É DITO AO ADMINISTRADOR QUAL A VIAGEM QUE OBTEVE MELHOR PONTUAÇÃO
     * @param listaV lista de viagens
     * 
     */
    public void melhorViagem(ArrayList<Viagem> listaV){
        Scanner sc = new Scanner(System.in);
        double soma=0;
        int v=0, mes, ano, j;
        double aux=0;
        System.out.println("Introduza o número do mês que deseja consultar");
        while(true){
            try{ 
                mes = sc.nextInt();
                sc.nextLine();
                if(mes==-1)
                    return;
                if(mes<1 || mes>12)
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        System.out.println("Introduza o número do ano que deseja consultar");
        while(true){
            try{ 
                ano = sc.nextInt();
                if(ano==-1)
                    return;
                if(ano<2000 || ano > (new Date().getYear()+1900))
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        for(int i=0;i<listaV.size();i++){
            if(mes-1==listaV.get(i).getData().getMonth() && ano-1900 == listaV.get(i).getData().getYear()){
                for(j=0;j<listaV.get(i).getComents().size();j++){
                    soma+=listaV.get(i).getComents().get(j).getPont();
                }
                soma = (soma/j + (listaV.get(i).getReservas().size())*0.2); 
                if(soma > aux){
                    v=i;
                    aux=soma;
                }
            }
        }
        System.out.println("A Viagem melhor pontuada no mês "+mes+" do ano "+ano+" é a viagem com o código "+listaV.get(v).getCodigo());
    }
    
    /**
     * MEDIANTE A ESCOLHA DE UM DETERMINADO ANO, É DITO AO ADMINISTRADOR QUAL FOI O VOLUME DE VENDAS DE TODAS AS VIAGENS NESSE ANO
     * @param listaV lista de viagens
     * 
     */
    public void volumeVendas(ArrayList<Viagem> listaV){
        double soma=0;
        int ano;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o número do ano que deseja consultar");
        while(true){
            try{ 
                ano = sc.nextInt();
                sc.nextLine();
                if(ano==-1)
                    return;
                if(ano<2000 || ano > (new Date().getYear()+1900))
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        for(int i=0;i<listaV.size();i++){
            if(ano-1900 == listaV.get(i).getData().getYear()){
                for(int j=0;j<listaV.get(i).getReservas().size();j++){
                    soma+=listaV.get(i).getReservas().get(j).getPagar().getQuantiaPagar();
                }
            }
        }
        System.out.println("O volume de vendas no ano "+ano+" foi de "+soma+"€.");
    }
    
    /**
     *  MEDIANTE A ESCOLHA DE UM DETERMINADO ANO, É DITO AO ADMINISTRADOR QUAL O DIA EM QUE SE OBTEVE MAIOR VOLUME DE VENDAS
     * @param listaR lista de reservas
     * 
     */
    public void melhorDia(ArrayList<Reserva> listaR){
        int ano,aux=0,num=0;
        boolean o=true;
        String str="";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> auxCod = new ArrayList<>();
        ArrayList<Integer> numV = new ArrayList<>();
        System.out.println("Introduza o número do ano que deseja consultar");
        while(true){
            try{ 
                ano = sc.nextInt();
                sc.nextLine();
                if(ano==-1)
                    return;
                if(ano<2000 || ano > (new Date().getYear()+1900))
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        for(int i=0;i<listaR.size();i++){
            if(listaR.get(i).getData().getYear() == ano-1900){
                for(int j=1;j<auxCod.size();j++){
                    if((str = Integer.toString(listaR.get(i).getData().getDate())+"/"+Integer.toString(listaR.get(i).getData().getMonth()+1)).equals(auxCod.get(j))){
                        numV.set(j, numV.get(j)+1);
                        o=false;
                    }
                }
                if(o){
                    auxCod.add(str);
                    numV.add(1);
                }    
            }
        }
        for(int i=0;i<numV.size();i++){
            if(numV.get(i) > aux){
                num = i;
                aux = numV.get(i);
            }
        }
        System.out.println("O Dia com o Maior Número de Vendas foi "+auxCod.get(num));
    }
    
    /**
     * MEDIANTE A ESCOLHA DE UM DETERMINADO MÊS DE UM DETERMINADO ANO, É DITO AO ADMINISTRADOR QUAIS FORAM AS VIAGENS QUE NÃO OBTIVERAM RESERVAS QUER ATIVAS QUER EM ESPERA
     * @param listaV lista de viagens
     * 
     */
    public void viagemSemReservas(ArrayList<Viagem> listaV){
        int mes,ano;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o número do mês que deseja consultar");
        while(true){
            try{ 
                mes = sc.nextInt();
                sc.nextLine();
                if(mes==-1)
                    return;
                if(mes<1 || mes>12)
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        System.out.println("Introduza o número do ano que deseja consultar");
        while(true){
            try{ 
                ano = sc.nextInt();
                if(ano==-1)
                    return;
                if(ano<2000 || ano > (new Date().getYear()+1900))
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        
        for(int i=0;i<listaV.size();i++){
            if(mes-1==listaV.get(i).getData().getMonth() && ano-1900 == listaV.get(i).getData().getYear()){
                if(listaV.get(i).getReservas().isEmpty())
                    System.out.println(listaV.get(i));
            }
        }
        
    }
    
    /**
     * SET PASSWORD
     * @param password pass secreta para admins
     */
    public static void setPassword(String password) {
        Administrador.password = password;
    }

    /**
     * GET PASSWORD
     * @return password
     */
    public static String getPassword() {
        return password;
    }
    
    /**
     * MÉTODO toString()
     * @return String
     */
    @Override
    public String toString(){
        return "-- ADMINISTRADOR --\n" + super.toString();
    }
    
}
