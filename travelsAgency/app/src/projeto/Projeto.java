/* 
 * PROGRAMA PARA A CADEIRA DE PROGRAMAÇÃO ORIENTADA A OBJETOS (POO)
 * DOCENTE: PROFESSORA MARÍLIA PASCOAL CURADO
 * TURMA: TP4
 * ALUNOS: JOEL FILIPE ROGÃO PIRES -> Nr: 2014195242
 *         FRANCISCO NOGUEIRA FERNANDES -> Nr: 2014200243 
 */
package projeto;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public class Projeto {
    
    /**
     * MAIN
     * @param args
     */
    public static void main(String[] args){ 
        ArrayList<Utilizador> listaU = new ArrayList<>();   //LISTA COM TODOS OS UTILIZADORES
        ArrayList<Viagem> listaV = new ArrayList<>();       //LISTA COM TODAS AS VIAGENS (AS QUE CUJA DATA DE PARTIDA JÁ PASSOU E AS QUE AINDA ESTÃO EM VIGOR)
        ArrayList<Autocarro> listaA = new ArrayList<>();    //LISTA COM TODAS OS AUTOCARROS QUE A AGÊNCIA DE VIAGENS POSSUI
        ArrayList<Reserva> listaR = new ArrayList<>();      //LISTA COM TODAS AS RESERVAS ATIVAS
        ArrayList<Reserva> listaC = new ArrayList<>();      //LISTA COM TODAS AS RESERVAS CANCELADAS
        ArrayList<Reserva> listaE = new ArrayList<>();      //LISTA COM TODAS AS RESERVAS EM ESPERA

        Date d = criaData();  
        System.out.println("DATA: "+d);
        
        try {
            init(d,listaU,listaV,listaA,listaR,listaC,listaE);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Projeto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("OCORREU UM ERRO: " + ex);
        }
        
        verificaPartidas(listaU,listaV,listaE,d);
        
        try {
            mainLoop(d,listaU,listaV,listaA,listaR,listaC,listaE);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Projeto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("OCORREU UM ERRO: " + ex);
        }
    }
    
    /**
     * FUNÇÃO PRINCIPAL QUE, ATRAVÉS DE UM CICLO, ESCREVE O MENU PRINCIPAL E REDIRECIONA O UTILIZADOR PARA OS SUBMENUS E MÉTODOS CONSEQUENTES
     * @param d DATA
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @throws IOException
     * @throws InterruptedException
     */
    public static void mainLoop(Date d,ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws IOException, InterruptedException{
        int choice ;
        while(true){
           choice = mainMenu();
           Projeto.clear();
           redirectToMainFunctions(d,listaU, listaV, listaA, listaR, listaC, listaE,choice);
        }
    }
    
    /**
     * MÉTODO EXECUTADO NO ARRANQUE DO SISTEMA QUE SE ENCARREGA DE CRIAR O DIRETÓRIO PARA OS FICHEIROS E CARREGAR O CONTEUDO DOS FICHEIROS PARA AS LISTAS
     * @param d DATA EM QUE O PROGRAMA INICIALIZOU
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @throws IOException
     * @throws InterruptedException
     */
    public static void init(Date d,ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws IOException, InterruptedException{
        createDir(d,listaU, listaV, listaA, listaR, listaC, listaE);
        fileToList(d,listaU,listaV,listaA,listaR,listaC,listaE);      
    }
    
    /**
     * FUNCAO AUXILIAR QUE PERMITE CRIAR UMA DATA NO SISTEMA ADAPTADA AO UTILIZADOR
     * @return new Date(ano-1900,mes-1,dia) OBJETO DO TIPO DATE
     */
    public static Date criaData(){
        Scanner sc = new Scanner(System.in);
        int ano,mes,dia;
        System.out.println("Introduza a data a verificar: (escreva 0 no primeiro campo caso queira a data de hoje): ");
        System.out.print("Ano: ");
        while(true){
            try{ 
                ano = sc.nextInt();
                sc.nextLine();
                if(ano ==0)
                    return new Date();
                if(ano<2000)
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
                }catch(InputMismatchException e){
                    System.out.println("INSERE UM DOS NUMEROS");
                        sc.nextLine();
                }
        }
        System.out.print("\nMês: ");
        while(true){
            try{ 
                mes = sc.nextInt();
                sc.nextLine();
                if(mes<1 || mes>12)
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        System.out.print("\nDia: ");
        while(true){
            try{ 
                dia = sc.nextInt();
                sc.nextLine();
                if(dia<1 || dia>31)
                    System.out.println("NUMERO INVALIDO");
                else
                    break;
            }catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
                sc.nextLine();
            }
        }
        return new Date(ano-1900,mes-1,dia);
    }
    
    /**
     * MÉTODO QUE PERMITE VERIFICAR SE A DATA DA VIAGEM JÁ OCORREU, SINALIZANDO ISSO NO OBJETO VIAGEM E CANCELANDO AS SUAS RESERVAS
     * @param listaU
     * @param listaV
     * @param listaE
     * @param d DATA EM QUE O PROGRAMA INICIALIZOU
     */
    public static void verificaPartidas(ArrayList<Utilizador> listaU,ArrayList <Viagem> listaV,ArrayList<Reserva> listaE,Date d){
        int k=0;
        for(int i=0;i<listaV.size();i++){      
            if(listaV.get(i).getData().compareTo(d)<0 && !listaV.get(i).isRealizada()){
                listaV.get(i).setRealizada(true);
                for (int j=0;j<listaE.size();j++){
                    if(listaE.get(j).getCodigoViagem() == listaV.get(i).getCodigo())
                        listaE.remove(j);
                }
                for(int j=0;j<listaV.get(i).getReservas().size();j++){
                    for(int l=0;l<listaU.size();l++){
                        if(listaU.get(l).getUsername().equals(listaV.get(i).getReservas().get(j).getUser().getUsername()))
                            listaU.get(l).getNotificacoes().add("Viagem "+listaV.get(i).getCodigo()+" foi realizada. Por Favor, vá comentar a sua experiência.");
                    }
                 
                }
            }
        }
    }
                     
    /**
     * ESCREVE NO ECRÃ O MENU PRINCIPAL COM AS OPERAÇÕES BÁSICAS QUE UM UTILIZADOR NÃO AUTENTICADO PODE EFETUAR
     * @return opcao OPÇÃO ESCOLHIDA PELO UTILIZADOR
     */
    public static int mainMenu(){
        int opcao;
        System.out.println("\n*********************************");
        System.out.println("* FasTrip -- Agência de Viagens *");
        System.out.println("*********************************");
        System.out.println("*           BEM VINDO           *\n");
        System.out.println("(a qualquer altura, caso deseje retroceder escreva '-1')");
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("[1]LOGIN");
            System.out.println("[2]SIGN UP");
            System.out.println("[3]CATALOGO DE VIAGENS");
            System.out.println("[4]ESQUECI-ME DA PASSWORD");
            System.out.println("[5]DESLIGAR\n");
            System.out.print("SELECIONE A SUA OPÇÃO: ");

            try{ 
                opcao = sc.nextInt();
                if(opcao<1 || opcao>5)
                    System.out.println("NUMERO INVALIDO");
                else
                    return opcao;
            }
            catch(InputMismatchException e){
                System.out.println("INSERE UM DOS NUMEROS");
            }
        }
    }
    
    /**
     * REDIRECIONA O UTILIZADOR, QUE ACABOU DE ESCOLHER UMA OPERAÇÃO DO MENU PRINCIPAL, PARA OS MÉTODOS ADEQUADOS
     * @param d DATA
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @param choice OPÇÃO ESCOLHIDA PELO UTILIZADOR
     * @throws IOException
     * @throws InterruptedException
     */
    public static void redirectToMainFunctions(Date d,ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,  ArrayList<Reserva> listaR,  ArrayList<Reserva> listaC, ArrayList<Reserva> listaE, int choice) throws IOException, InterruptedException{
        Login l1 = new Login();
         switch (choice){
                case 1:
                    Utilizador user = l1.Logar(listaU);
                    if(user == null){
                        System.out.println("UTILIZADOR INEXISTENTE");
                        TimeUnit.SECONDS.sleep(2);
                    }    
                    else
                        user.options(d, listaU, listaV, listaA, listaR, listaC, listaE);  
                    break;
                case 2:
                    signup(d,listaU, listaV, listaA, listaR, listaC, listaE);
                    break;
                case 3:
                    consulta(listaV);
                    break;
                case 4:
                    passRecover(listaU);
                    break;
                case 5:
                    term(d,listaU,listaV,listaA,listaR,listaC,listaE);
                    break;
            }
        }
    
    /**
     * METODO PRINCIPAL QUE PERMITE O UTILIZADOR REGISTAR-SE COMO ADMINISTRADOR OU CLIENTE, CONCLUINDO DEPOIS A TAREFA COM AJUDA DO MÉTODO join()
     * @param d DATA
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @throws InterruptedException
     * @throws IOException
     */
    public static void signup(Date d,ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws InterruptedException, IOException{
        int opcao;
        String pass;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("-- SIGN UP --\n");
        System.out.println("[1] ADMINISTRADOR");
        System.out.println("[2] CLIENTE");
        System.out.println("[3] VOLTAR\n");
        System.out.print("SELECIONE A SUA OPÇÃO: ");
        try{ 
            opcao = sc.nextInt();
            if(opcao<1 || opcao>3)
                System.out.println("NUMERO INVALIDO");
            else{
                Projeto.clear();
                if (opcao == 1){
                    sc.nextLine();
                    while(true){
                        System.out.println("\nInsira a password de Administrador:");
                        System.out.println("(Cuidado: Sensível a Maísculas)\n");
                        if((pass=sc.nextLine()).equals(Administrador.getPassword())){
                            Utilizador ad1 = new Administrador();
                            ad1.join(d,listaU, listaV, listaA, listaR, listaC, listaE);
                            break;
                        }
                        else{
                            if (pass.equals("-1"))
                                return;
                            System.out.println("A Password Inserida Está Errada");
                        }
                    }
                } else if (opcao == 2){
                    Utilizador cl1 = new Regular();         //a primeira vez que um cliente faz uma compra tem de ser regular podendo depois ser promovido
                    cl1.join(d,listaU, listaV, listaA, listaR, listaC, listaE);
                }
            }
        }catch(InputMismatchException e){
            System.out.println("INSERE UM DOS NUMEROS");
        }
    }
    
    /**
     * É CRIADO O DIRETÓRIO QUE IRÁ CONTER TODOS OS FICHEIROS NECESSÁRIOS (PARA LISTAS E ESTATÍSTICAS)
     * @param d DATA
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @throws IOException
     * @throws InterruptedException
     */
    public static void createDir(Date d,ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws IOException, InterruptedException{
        File theDir = new File("files");
        if (!theDir.exists()) {
            boolean result = false;
            try{
                theDir.mkdir();
                result = true;
            } 
            catch(SecurityException e){
                System.out.println("Erro a criar Diretorio.");
                term(d,listaU,listaV,listaA,listaR,listaC,listaE);
            }        
            if(result) {    
                System.out.println("Diretorio criado");
            }
        }
    }
    
    /**
     * CRIA O FICHEIRO COM O NOME ESPECIFICADO, CASO NÃO EXISTA JÁ
     * @param d DATA
     * @param nome
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @return myFile
     * @throws IOException
     * @throws InterruptedException
     */
    public static File createFile(Date d, String nome, ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws IOException, InterruptedException{
        File myFile = new File(nome);
        try{
            if(!myFile.exists()) {
                myFile.createNewFile();
        }
        }catch (Exception e){
            System.out.println("Não foi possível criar ou abrir o ficheiro: " + myFile.getName());
            term(d, listaU, listaV, listaA, listaR, listaC, listaE);
        }
        return myFile; 
    }
    
    /**
     * TODOS OS OBJETOS QUE CONSTAM NOS FICHEIROS NO DIRETÓRIO SÃO CARREGADOS NAS LISTAS RESPETIVAS
     * @param d DATA
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @throws IOException
     * @throws InterruptedException
     */
    public static void fileToList(Date d,ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws IOException, InterruptedException{
        boolean j=true;
        FicheiroDeTexto f2 = new FicheiroDeTexto();
        String aux="";
        f2.abreLeitura("files/estatisticas.txt");
        while(j){
            try{
                aux=f2.leLinha();
                if(aux==null)
                    break;
                System.out.println(aux);
            } catch (Exception e){
                j=false;
            }
        }
        f2.fechaLeitura();
        FicheiroDeObjetos f1 = new FicheiroDeObjetos();
        createFile(d,"files/listaUtilizadores.txt",listaU, listaV, listaA, listaR, listaC, listaE); 
        f1.abreLeitura("files/listaUtilizadores.txt");   
        j=true;
        while(j){
            try{
                listaU.add((Utilizador)f1.leObjeto());
            } catch (Exception e){
                j=false;
            }
        }
        j=true;
        createFile(d,"files/listaViagens.txt", listaU, listaV, listaA, listaR, listaC, listaE);
        f1.abreLeitura("files/listaViagens.txt");
        while(j){
            try{
                listaV.add((Viagem)f1.leObjeto());
            } catch (Exception e){
                j=false;
            }
        }
        j=true;
        createFile(d,"files/listaAutocarros.txt", listaU, listaV, listaA, listaR, listaC, listaE);
        f1.abreLeitura("files/listaAutocarros.txt");
        while(j){
            try{
                listaA.add((Autocarro)f1.leObjeto());
            } catch (Exception e){                  
                j=false;
            }
        }
        f1.fechaLer();
        j=true;
        createFile(d,"files/listaReservas.txt", listaU, listaV, listaA, listaR, listaC, listaE);
        f1.abreLeitura("files/listaReservas.txt");
        while(j){
            try{
                listaR.add((Reserva)f1.leObjeto());
            } catch (Exception e){ 
                j=false;
            }
        }
        j=true;
        createFile(d,"files/listaReservasC.txt", listaU, listaV, listaA, listaR, listaC, listaE);
        f1.abreLeitura("files/listaReservasC.txt");
        while(j){
            try{
                listaC.add((Reserva)f1.leObjeto());
            } catch (Exception e){          
                j=false;
            }
        }
        j=true;
        createFile(d,"files/listaReservasE.txt", listaU, listaV, listaA, listaR, listaC, listaE);
        f1.abreLeitura("files/listaReservasE.txt");
        while(j){
            try{
                listaE.add((Reserva)f1.leObjeto());
            } catch (Exception e){          
                j=false;
            }
        }
        f1.fechaLer();
    }

    /**
     * MÉTODO QUE PERMITE TERMINAR O PROGRAMA EM SEGURANÇA, GUARDANDO TODAS AS ALTERAÇÕES E TODA A INFORMAÇÃO PRESENTE NAS LISTAS PARA OS FICHEIROS RESPETIVOS
     * @param d DATA
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * @throws IOException
     * @throws InterruptedException
     */
    public static void term(Date d,ArrayList<Utilizador> listaU, ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA,ArrayList<Reserva> listaR,ArrayList<Reserva> listaC,ArrayList<Reserva> listaE) throws IOException, InterruptedException{
        FicheiroDeObjetos f1 = new FicheiroDeObjetos();
        FicheiroDeTexto f2 = new FicheiroDeTexto();
        f1.abreEscrita("files/listaReservasC.txt");
        for (int i = 0; i < listaC.size(); i++) {
            f1.escreveObjeto(listaC.get(i));
        }
        f1.abreEscrita("files/listaReservasE.txt");
        for (int i = 0; i < listaE.size(); i++) {
            f1.escreveObjeto(listaE.get(i));
        }
        f1.abreEscrita("files/listaReservas.txt");
        for (int i = 0; i < listaR.size(); i++) {
            f1.escreveObjeto(listaR.get(i));
        }
        f1.abreEscrita("files/listaAutocarros.txt");
        for (int i = 0; i < listaA.size(); i++) {
            f1.escreveObjeto(listaA.get(i));
        }
        f1.abreEscrita("files/listaViagens.txt");
        for (int i = 0; i < listaV.size(); i++) {
            f1.escreveObjeto(listaV.get(i));
        }
        f1.abreEscrita("files/listaUtilizadores.txt");
        for (int i = 0; i < listaU.size(); i++) {
            f1.escreveObjeto(listaU.get(i));
        }

        String aux;
        int j=0;
        double valor=0;
        f2.abreEscrita("files/estatisticas.txt");
        for(int i=0;i<listaV.size();i++){
            if(listaV.get(i).getData().compareTo(d)<0){
                j++;
                for(int k=0;k<listaV.get(i).getReservas().size();k++)
                    valor+=listaV.get(i).getReservas().get(k).getPagar().getQuantiaPagar();
            }
        }
        aux = "Número de Viagens já realizadas: "+j;
        f2.escreveLinha(aux);
        j=0;
        for(int i=0;i<listaU.size();i++){
            if(listaU.get(i) instanceof Administrador)
                j++;
        }
        aux="Número de Clientes registados: "+(listaU.size()-j);
        f2.escreveLinha(aux);
        aux = "Total Encaixado: "+valor;
        f2.escreveLinha(aux);
        f2.fechaEscrita();
        System.out.println("\nPROGRAMA TERMINADO EM SEGURANÇA");
        TimeUnit.SECONDS.sleep(2);
        Projeto.clear();
        System.exit(0);
    }

    /**
     * LIMPA O DISPLAY
     */
    public static void clear(){
        for(int i=0;i<40;i++)
            System.out.println("");
    }
    
    /**
     * FUNÇÃO AUXILIAR QUE PERMITE VERIFICAR SE DETERMINADA STRING É APROVADA OU NÃO
     * @param word STRING A VERIFICAR
     * @param flag INTEIRO QUE DEFINE O TIPO DE VERIFICAÇÕES
     * @return
     */
    public static boolean checkString(String word, int flag){
        int count = 0;
        char[] chars = word.toCharArray();
        if(word.equals("-1"))
            return true;
        for (int i = 0; i < chars.length; i++) {
            if (flag == 0 && !(chars[i]>=65 && chars[i]<=90 || chars[i]>=97 && chars[i]<=122 || chars[i] == 32)){  //Quando se pretende que a string tenho só caracteres alfanuméricos ex:inserir o nome do utilizador
                System.out.println("(Cuidado: Introduziu caracteres não alfanuméricos.)");
                return false;
            }
            if (flag == 1){                             //Quando a String se trata de um email
                if(chars[i] == 64)
                    count++;
                if(chars[0] == 64 || chars[chars.length - 1] == 64){
                    System.out.println("(Cuidado: Domínio Inválido.)");
                    return false;
                }
            }   //Quando se pretende que a string tenha regras para a localização dos espaços
            if (chars[0] == 32){
                System.out.println("(Cuidado: Inseriu um espaço no início.)");
                return false;
            }
            if (chars[chars.length - 1] == 32){
                System.out.println("(Cuidado: Inseriu um espaço no fim.)");
                return false;
            }
            if (i>0 && (chars[i] == 32 && chars[i-1] == 32)){
                System.out.println("(Cuidado: palavras diferentes devem ser separadas por um só espaço)");
                return false;
            }
        }
        if(flag == 1 && count != 1)
            return false;
        
        return true;
    }
    
    /**
     * PERMITE A UM UTILIZADOR RECUPERAR A SUA PASSWORD
     * @param listaU
     */
    public static void passRecover(ArrayList<Utilizador> listaU){
        String user,secret,pass;
        Scanner sc = new Scanner(System.in);
        boolean ex=false;
        System.out.println("Por Favor introduza o seu username ou o seu email: ");
        user=sc.nextLine();
        if (user.equals("-1"))
            return;
        System.out.println("Introduza agora a sua Secret Answer");
        secret = sc.nextLine();
        for(int i=0;i<listaU.size();i++){
            if((listaU.get(i).getUsername().equals(user) || listaU.get(i).getEmail().equals(user) )&& listaU.get(i).getSecret().equals(secret)){
                ex=true;
                System.out.println("Insira a sua Nova Password: ");
                pass = sc.nextLine();
                listaU.get(i).setPass(pass);
                break;
            }
        }
        if(ex){
            System.out.println("Password Alterada Com Sucesso!");
        }
        else{
            System.out.println("Utilizador Inexistente ou Secret Anwser não aceite");
        }
    }
    
    /**
     * CONSULTA A LISTA DE VIAGENS DISPONÍVEIS QUE AINDA NÃO OCORRERAM 
     * @param listaV
     */
    public static void consulta(ArrayList<Viagem> listaV){
        if(listaV.isEmpty() != true){
            for (Viagem V1 : listaV){
                if(!V1.isRealizada()){
                    System.out.println(V1.toString(1));
                }
            }
        }
        else
            System.out.println("Não Existem Viagens para Consultar.");
    }
}

