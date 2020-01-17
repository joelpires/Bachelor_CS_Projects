
package projeto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public abstract class Cliente extends Utilizador implements Serializable{      
    protected double desconto;
    protected double penalizacao;
    protected int nDiasCancela;
    protected double DinheiroReceber;        //Dinheiro que o utilizador tem de receber de reservas canceladas/viagens apagadas pelos administradores e quando os utilizadores dão mais dinheiro do que necessário para pagar as reservas
    
    /**
     * MÉTODO QUE PERMITE AO CLIENTE CRIAR UMA RESERVA RELATIVAMENTE A UMA VIAGEM ESPECÍFICA
     * @param listaV
     * @param listaR
     * @param listaE
     * @param rEspera
     * @param flag
     * @throws InterruptedException
     * @throws ParseException
     */
    public void criaReserva(ArrayList<Viagem> listaV, ArrayList<Reserva> listaR, ArrayList<Reserva> listaE, Reserva rEspera, int flag) throws InterruptedException, ParseException{
        int a;
        double pagar;
        String b;
        Date d2;
        Pagamento p1 = null;
        Autocarro auto = new Autocarro();
        Reserva r1;
        int lugar = 0;
        Scanner sc = new Scanner(System.in);
        Projeto.clear();
        
        if (flag != 1){//User tenta finalizar uma reserva que estava em espera
            System.out.println("\n-- CRIAR RESERVA --\n");
            System.out.println("   (Código pode ser Consultado no Menu Anterior -> Opção 1)");
            System.out.print("Insira o Código Identificativo da Viagem que Pretende Reservar:"); //TO DO:implementar -1
        }
        while(true){
            try{
                b = sc.nextLine();
                a = Integer.parseInt(b);                                             //TO DO:proteções não inserir nada, inserir uma letra, inserir negativo            
                for(int i=0;i<listaV.size();i++){               
                    if(listaV.get(i).getCodigo() == a){
                        if(listaV.get(i).isRealizada()){
                            System.out.println("Esse Código é relativo a Uma Viagem que Já Ocorreu");
                            return;
                        }
                        Date d1 = new Date();
                        if (listaV.get(i).getVagas() == 0){
                            if (flag == 1){                      //User tenta finalizar uma reserva que estava em espera
                                System.out.println("Ainda não é Possível Reservar");
                                return;
                            }else{
                                System.out.println("A Viagem que Deseja Reservar já Não Possui Vagas, Vai Ter de Ficar em Lista de Espera.");
                                r1 = new Reserva(this, a, null, 0, null, d1 );
                                listaE.add(r1);
                                System.out.println("A Sua Reserva Encontra-se em Lista de Espera. Informá-lo-emos Assim que Houver Vagas.");
                            }
                        } else{
                            listaV.get(i).setVagas(listaV.get(i).getVagas()-1);
                            for (Autocarro A1 : listaV.get(i).getAutos()){  //o primeiro autocarro a ter lugar é o que vai para a reserva
                                if (A1.getLugares() != 0){
                                    auto = A1;
                                    lugar = A1.getLugares();            //lugares são preenchidos dos lugares finais para os iniciais
                                    A1.setLugares(A1.getLugares() - 1);
                                    break;
                                }
                            }

                            pagar = listaV.get(i).getPreco()*(1 - this.getDesconto());
                            System.out.println("\nQUANTIA A PAGAR: " + pagar);

                            System.out.println("DE QUE FORMA QUER PAGAR?\n");
                            System.out.println("[1] DINHEIRO");
                            System.out.println("[2] CARTÃO");
                            System.out.println("[3] CHEQUE\n");
                            System.out.print("SELECIONE A SUA OPÇÃO: ");
                            a = sc.nextInt();
                            if(a < 1 || a>3)
                                System.out.println("NUMERO INVALIDO");
                            else{
                                Projeto.clear();
                                if (a == 1){
                                    sc.nextLine();
                                    while(true){
                                        System.out.print("\nInserir Quantia a Pagar: ");   
                                        try{
                                            a = sc.nextInt();
                                            if(a==-1)
                                                return;
                                        if(a >= listaV.get(i).getPreco()){
                                            p1 = new Dinheiro(pagar, a);
                                            System.out.println("\nQuantia a Devolver: " + ((Dinheiro)p1).getQuantiaDevolver());
                                            this.DinheiroReceber += ((Dinheiro)p1).getQuantiaDevolver();
                                            break;
                                        } else
                                            System.out.println("Para Completar a Reserva Tem que Inserir uma Quantia Igual ou Superior à do Preço da Viagem.");
                                                                            }
                                        catch(Exception e){
                                            System.out.println("Por favor introduza um valor válido");
                                        }
                                    }
                                }
                                if (a == 2){
                                    sc.nextLine();                                               
                                    System.out.print("\nInserir Número do Cartão: "); //TO DO: Proteção para prevernir se se trata de um numero com mais de 15 digitos (acho eu que é 15)
                                    a = sc.nextInt();  
                                    if(a==-1)
                                        return; //TO DO:proteções não inserir nada, inserir uma letra, inserir negativo
                                    System.out.print("\nInserir Data de Validade do Cartão (dia/mês/ano): "); 
                                    while(true){
                                        String dateFormat = "dd/MM/yyyy";
                                        if((b = sc.nextLine()).isEmpty())                              
                                            System.out.print("Data Inválida. Por favor insira a Data Corretamente (dia/mês/ano): ");
                                        else{
                                            d2 =  new SimpleDateFormat(dateFormat).parse(b);
                                            if(d2.before(new Date()))
                                                System.out.println("Data Inválida. Por favor insira a Data Corretamente (dia/mês/ano): ");
                                            else
                                                break;
                                        }
                                    }
                                    System.out.println("Pocessando Pagamento...");
                                    p1 = new Cartao(pagar, a, d2);
                                    TimeUnit.SECONDS.sleep(2);
                                    System.out.println("Pagamento Aceite!");
                                }
                                if (a == 3){
                                    System.out.print("\nInsira a Origem da Viagem: ");
                                    while(true){ 
                                        if((b = sc.nextLine()).isEmpty() || Projeto.checkString(b, 0) == false)
                                            System.out.print("Origem Inválida. Por favor insira a Origem: ");
                                        else
                                            break;
                                        }
                                    System.out.print("\nInserir ID do Banco: ");    
                                    a = sc.nextInt();
                                    System.out.println("Pocessando Pagamento...");
                                    p1 = new Cheque(pagar, b, a);
                                    TimeUnit.SECONDS.sleep(2);
                                    System.out.println("Pagamento Aceite!");
                                }
                            }
                            r1 = new Reserva(this, a, auto, lugar, p1, d1 );
                            listaR.add(r1);
                            if (flag == 1)//User tenta finalizar uma reserva que estava em espera
                                listaE.remove(rEspera);
                            System.out.println("Reserva Bem Sucedida!");
                        }
                        listaV.get(i).getReservas().add(r1);
                        return;
                    }      
                }
                System.out.print("Codigo Inserido Não Está Associado a Nenhuma Viagem. Insira Novamente: ");
            } catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um Código Válido: ");
            }
        }
    }
    
    /**
     * MÉTODO QUE PERMITE AO CLIENTE CANCELAR UMA RESERVA RELATIVAMENTE A UMA VIAGEM ESPECÍFICA
     * @param listaV
     * @param listaR
     * @param listaE
     * @param listaC
     */
    public void cancelaReserva(ArrayList<Utilizador> listaU,ArrayList<Viagem> listaV, ArrayList<Reserva> listaR, ArrayList<Reserva> listaE, ArrayList<Reserva> listaC){
        int a;
        double preco = 0;
        String b;
        Reserva rFinal = null;
        Scanner sc = new Scanner(System.in);
        Date d1 = new Date();
        Date d3 = new Date();
        String notificacao;
        long diff; 
        long diffDays;
        boolean l = true;
        
        Projeto.clear();
        
        System.out.println("\n-- CANCELAR UMA RESERVA --\n");
        System.out.println("   (As Reservas Podem ser Consultadas no Menu Anterior -> Opção 3)");
        System.out.print("Insira o Código Identificativo da Viagem que Pertence à Reserva Que Pretende Cancelar:");
        while(true){                        
            try{
                b = sc.nextLine();
                a = Integer.parseInt(b);
                if(a==-1)
                    return;
                for(Reserva R1 : listaR)
                    if (R1.getCodigoViagem() == a && R1.getUser() == this){
                       rFinal = R1;
                       l=false;
                       break;
                    }
                for(Reserva R2 : listaE)
                    if (R2.getCodigoViagem() == a  && R2.getUser() == this){
                        rFinal = R2;
                        l=false;
                        break;
                    }
                if (l == true){
                        System.out.println("Não Existem Reservas Suas no Sistema Relativas a Essa Viagem.");
                        return;
                    }
                for (Viagem V1 : listaV) 
                    if(V1.getCodigo() == a){
                        if(!V1.isRealizada()){
                            System.out.println("Esse Código é relativo a Uma Viagem que Já Ocorreu");
                            return;
                        }
                        d3 = V1.getData();
                        preco = V1.getPreco();
                        diff = Math.abs(d1.getTime() - d3.getTime());
                        diffDays = diff / (24 * 60 * 60 * 1000);
                        if (diffDays > this.getnDiasCancela()){
                            System.out.println("\nA esta Altura, já Não é Possível Cancelar a Reserva"); 
                            return;
                        }
                        V1.getReservas().remove(rFinal);                        //Atualizando dados da viagem
                        V1.setVagas(V1.getVagas() + 1);
                        V1.getAutos().get(0).setLugares(V1.getAutos().get(0).getLugares() + 1);                                                             //TO DO: Se um cliente pode ter mais que uma reserva, então não pode fazer break aqui
                    }
                
                //NOTIFICAR TODOS OS USERS
                int k;
                for(Reserva R1 : listaE)
                    if (R1.getCodigoViagem() == a){
                        notificacao = "\nAcabou de Abrir uma Vaga para Reservar a Viagem com o Seguinte Código: " + a + "\nApresse-se a Pagar a Reserva que Tinha em Espera.\n";
                        k = listaU.indexOf(R1.getUser());
                        listaU.get(k).getNotificacoes().add(notificacao);                
                    }
                //RESTITUIR O DINHEIRO
                this.DinheiroReceber = preco*(1 - this.penalizacao);
                
                listaC.add(rFinal);
                //Remove propriamente
                if (rFinal.getPagar() == null)
                    listaE.remove(rFinal);
                else
                    listaR.remove(rFinal);
                return;
            } catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um Código Válido: ");
            }
        }       
    }
    
    /**
     * TODAS AS RESERVAS EFETUADAS PELO CLIENTE, QUER ATIVAS QUER EM ESPERA, SÃO LISTADAS PARA O CLIENTE
     * @param listaR
     * @param listaE
     */
    public void consultaReservas(ArrayList<Reserva> listaR, ArrayList<Reserva> listaE){
        if (!listaR.isEmpty())
            for(Reserva R1 : listaR)
                if (R1.getUser().equals(this))
                    System.out.println(R1.toString(-1));
        else if (!listaE.isEmpty())
            for(Reserva R2 : listaE)
                if (R2.getUser().equals(this))
                    System.out.println(R2.toString(-1));
        else
            System.out.println("Não Existem Reservas Suas no Sistema.");
    }

    /**
     * AS VIAGENS QUE AINDA IRÃO OCORRER SÃO LISTADAS PARA O CLIENTE
     * @param listaV
     */
    public void consultarViagens(ArrayList<Viagem> listaV){
        if (!listaV.isEmpty()){
            for(Viagem V1 : listaV)
                if(!V1.isRealizada())
                    System.out.println(V1.toString(0));
        }
        else
            System.out.println("Não Existem Viagens no Catálogo.");
    }
    
        /**
     * METODO QUE REDIRECIONA PARA MENUS ESPECIFICOS DE ACORDO COM A OPÇÃO INSERIDA NO METODO clientMenu()
     * @param d DATA
     * @param listaU
     * @param listaV
     * @param listaA
     * @param listaR
     * @param listaC
     * @param listaE
     * 
     */
    @Override
    public void options(Date d, ArrayList<Utilizador> listaU,ArrayList<Viagem> listaV,  ArrayList<Autocarro> listaA, ArrayList<Reserva> listaR, ArrayList<Reserva> listaE, ArrayList<Reserva> listaC){
        int choice;

        while(true){
            choice = clientMenu();
            Projeto.clear();

            switch (choice){
                case 1:
                    consultarViagens(listaV);
                    break;
                case 2:
                    try {
                        this.criaReserva(listaV, listaR, listaE, null, 0);
                    } catch (InterruptedException | ParseException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 3:
                    consultaReservas(listaR, listaE);
                    break;
                case 4:
                    cancelaReserva(listaU,listaV, listaR, listaE, listaC);
                    break;
                case 5:
                    inserirComentario(listaV,d);
                    break;
                case 6:
                    try {
                        reservasPendentes(listaV, listaR, listaE);
                    } catch (InterruptedException | ParseException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 7:
                    consultaNotificacoes();
                    break;
                case 8:
                    alteraDados(listaU);
                    break;
                case 9:
                    return;
            }
        }          
    }
    
    /**
     * MENU QUE DISPONIBILIZA AS OPERAÇÕES QUE UM CLIENTE PODE REALIZAR
     * @return choice ESCOLHA DO CLIENTE
     */
    public static int clientMenu (){
       int choice;

        while(true){
            Scanner sc = new Scanner(System.in); 
            System.out.println("SELECIONE A SUA OPÇÃO:\n");
            System.out.println("[1]CATALOGO DE VIAGENS");
            System.out.println("[2]CRIAR RESERVA");
            System.out.println("[3]MINHAS RESERVAS");
            System.out.println("[4]CANCELAR RESERVA");
            System.out.println("[5]INSERIR COMENTARIO");
            System.out.println("[6]RESERVAS PENDENTES");
            System.out.println("[7]CONSULTAR NOTIFICAÇÕES");
            System.out.println("[8]EDITAR DADOS");
            System.out.println("[9]TERMINAR SESSAO");
       
            try{ 
                choice = sc.nextInt();
                if(choice<1 || choice>9)
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
     * PERMITE AO CLIENTE CONCLUIR UMA RESERVA QUE SE ENCONTRAVA EM FILA DE ESPERA POR NÃO HAVER VAGAS SUFICIENTES
     * @param listaV
     * @param listaR
     * @param listaE
     * @throws InterruptedException
     * @throws ParseException
     */
    public void reservasPendentes(ArrayList<Viagem> listaV, ArrayList<Reserva> listaR, ArrayList<Reserva> listaE) throws InterruptedException, ParseException{
        int a;
        String b;
        Reserva rFinal = null;
        Scanner sc = new Scanner(System.in);
        boolean l = true;
        
        Projeto.clear();
        
        System.out.println("\n-- RESERVAR UMA RESERVA EM FILA DE ESPERA --\n");
        System.out.println("(As Reservas Podem ser Consultadas no Menu Anterior -> Opção 3)");
        System.out.print("Insira o Código Identificativo da Viagem que Pretende Reservar:");
        while(true){
            try{
                b = sc.nextLine();
                a = Integer.parseInt(b);                           
                if (!listaE.isEmpty()){
                    for(Reserva R2 : listaE)
                        if (R2.getCodigoViagem() == a && R2.getUser() == this){
                            rFinal = R2;
                            l = false;
                            break;
                        }
                    if (l == true){
                        System.out.println("Não Existem Reservas Suas no Sistema Relativas a Essa Viagem.");
                        return;
                    }
                }
                else{
                    System.out.println("Não Existem Reservas Suas em Espera.");
                    break;
                }
                criaReserva(listaV,listaR,listaE, rFinal, 1);
                
            } catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um Código Válido: ");
            }
        }  
    }
    
    /**
     * PERMITE AO UTILIZAR VER TODAS AS SUAS NOTIFICAÇÕES NO ECRÃ
     */
    public void consultaNotificacoes(){
        if(!this.getNotificacoes().isEmpty())
            for (String N1 : this.getNotificacoes()) {
                System.out.println("---- NOTIFICAÇÕES ----\n");
                System.out.println("*********************************************");
                System.out.println(N1);
                System.out.println("*********************************************");
            }
        else
            System.out.println("Não Possui Notificações.");
    }
    
    /**
     * PERMITE AO CLIENTE QUE EFETUOU UMA VIAGEM, INSERIR UM COMENTÁRIO
     * @param listaV
     * @param d DATA
     */
    public void inserirComentario(ArrayList<Viagem>listaV,Date d){
        int cod,pont;
        String com;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o código da Viagem, a qual quer comentar: ");
        while(true){
            try{
            cod = sc.nextInt();
            sc.nextLine();
            if(cod == -1)
                return;
                break;
            }
            catch(Exception e){
                System.out.println("Introduza um código válido!");   
            }
        }
        for(int i=0;i<listaV.size();i++){
            if(listaV.get(i).getCodigo()==cod){
                if(listaV.get(i).isRealizada()){
                    System.out.println("Introduza o seu Comentário: ");
                    while(true){ 
                        if((com = sc.nextLine()).isEmpty())
                            System.out.print("Comentário não Aceite. Por favor insira outro Comentário: ");
                        else
                            break;
                        }
                    System.out.println("Introduza a pontuação que deseja dar à viagem(0-5)");
                    while(true){
                        try{ 
                            pont = sc.nextInt();
                            if(pont<0 || pont>5)
                                System.out.println("NUMERO INVALIDO");
                            else
                                break;
                        }
                        catch(InputMismatchException e){
                            System.out.println("INSERE UM DOS NUMEROS");
                            sc.nextLine();
                        }
                    }
                    listaV.get(i).getComents().add(new Comentario(pont,com,this.nome,d));
                }
                else
                    System.out.println("Viagem ainda não foi realizada, não pode comentar!");
            }
        }
    }
    
    /**
     * SET FRAÇÃO DE DESCONTO AO CRIAR UMA RESERVA
     * @param d DESCONTO
     */
    public void setDesconto(double d){
        desconto=d;
    }
    /**
     * SET FRAÇÃO DE PENALIZAÇÃO AO CANCELAR UMA RESERVA
     * @param p penalização
     */
    public void setPenalizacao(double p){
        penalizacao = p;
    }
    
    /**
     * GET FRAÇÃO DE DESCONTO AO CRIAR UMA RESERVA
     * @return desconto
     */
    public double getDesconto(){
        return desconto;
    }
    
    /**
     * GET FRAÇÃO DE PENALIZAÇÃO AO CANCELAR UMA RESERVA
     * @return penalizacao
     */
    public double getPenalizacao(){
        return penalizacao;
    }
    
    /**
     * GET NUMERO DE DIAS ATÉ OCCORER A VIAGEM QUE O UTILIZADOR TEM PARA CANCELAR A SUA RESERVA
     * @return nDiasCancela
     */
    public int getnDiasCancela() {
        return nDiasCancela;
    }

    /**
     * SET NUMERO DE DIAS ATÉ OCCORER A VIAGEM QUE O UTILIZADOR TEM PARA CANCELAR A SUA RESERVA
     * @param nDiasCancela
     */
    public void setnDiasCancela(int nDiasCancela) {
        this.nDiasCancela = nDiasCancela;
    }

}
