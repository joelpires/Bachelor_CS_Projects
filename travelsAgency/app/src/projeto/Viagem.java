
package projeto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Viagem implements Serializable{
    private boolean realizada;         //valor booleano que nos permite saber se a data de uma viagem que se encontra na lista de viagens já ocorreu ou não
    private int codigo;
    private String origem;
    private String destino;
    private double preco;
    private Date data;
    private int duracao;
    private ArrayList<Autocarro> autos = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<Comentario> coments = new ArrayList<>();
    private int vagas;
    private double rating = 0;          //variável qcujo valor é calculado pelo método geraRating();
    static final long serialVersionUID = 1086140242027004010L;      //variavel fundamental para a implementação da interface Serializable
    
    /**
     * CONSTRUTOR VIAGEM
     */
    public Viagem(){}
    
    /**
     * CONSTRUTOR VIAGEM
     * @param c CODIGO DA VIAGEM
     * @param o ORIGEM
     * @param d DESTINO
     * @param p PRECO DA VIAGEM
     * @param data DATA DA PARTIDA
     * @param dura DURAÇÃO
     * @param v NÚMERO DE VAGAS
     */
    public Viagem(int c, String o, String d,double p,Date data,int dura,int v){
        realizada=false;
        codigo=c;
        origem=o;
        destino=d;
        preco=p;
        this.data=data;
        duracao=dura;
        vagas=v;
    }

    /**
     * PERMITE AO ADMINISTRADOR CRIAR UMA NOVA VIAGEM QUE NAO CONSTA DO CATÁLOGO DE VIAGENS
     * @param listaV
     * @param listaA
     * @return v1 VIAGEM RECÉM-CRIADA
     * @throws ParseException
     */
    public static Viagem criaViagem(ArrayList<Viagem> listaV, ArrayList<Autocarro> listaA) throws ParseException{
        boolean num=true,help=true;
        int a, n=0;
        int m = 0;
        int[] array = new int[listaA.size()];
        String b;
        boolean l = true;
        int j;
        Random rand = new Random();
        Viagem v1 = new Viagem();
        Scanner sc = new Scanner(System.in);
        Projeto.clear();
        
        System.out.println("\n-- CRIAR VIAGEM NOVA --\n");
        while(num){
            n = rand.nextInt(100000) + 1;
            if(listaV.isEmpty())
                num=false;
            for(int i=0;i<listaV.size();i++){
                if(listaV.get(i).getCodigo()==n){
                    num=true;
                    break;
                }
                else
                    num=false;
            }
        }
        v1.codigo = n;
        System.out.println("Código da Nova Viagem Gerado: " + n);
        System.out.print("\nInsira a Origem da Viagem: ");
        while(true){ 
            b = sc.nextLine();
            if(b.equals("-1"))
                return null;
            if(b.isEmpty() || Projeto.checkString(b, 0) == false)
                System.out.print("Origem Inválida. Por favor insira a Origem: ");
            else{
                v1.origem = b;
                break;
            }
        }
        System.out.print("\nInsira o Destino da Viagem: ");         
        while(true){                               
            if((b = sc.nextLine()).isEmpty()|| Projeto.checkString(b, 0) == false)            
               System.out.print("Destino Inválido. Por favor insira o Destino: ");
            else{
                v1.destino = b;
                break;
            }
        }
        System.out.print("\nInsira o Preço da Viagem: ");           
        try{                                                        
            while(true){
                if((b = sc.nextLine()).isEmpty() || Float.parseFloat(b)<=0)
                    System.out.println("Insira um Preço Válido: ");
                else{
                    v1.preco = Float.parseFloat(b);
                    break;
                }
            } 
        } catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um Preço Válido: ");
        }
        System.out.print("\nInsira a Data da Partida (dia/mês/ano): ");         
        while(true){
            String dateFormat = "dd/MM/yyyy";
            if((b = sc.nextLine()).isEmpty())                               
                System.out.print("Data Inválida. Por favor insira a Data da Partida (dia/mês/ano): ");
            else{
                if((v1.data = new SimpleDateFormat(dateFormat).parse(b)).compareTo(new Date())>0)
                    break;
                else
                    System.out.println("Data Inválida. Por favor insira a Data da Partida (dia/mês/ano): ");
            }
        }
        System.out.print("\nInsira a Duração da Viagem (número de dias): ");        
        try{                                                                        
            while(true){
                if((b = sc.nextLine()).isEmpty() || Integer.parseInt(b)<1)
                    System.out.println("Insira uma Duração Válida: ");
                else {
                    v1.duracao = Integer.parseInt(b);
                    break;
                }
            } 
        } catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um Preço Válido: ");
        }
        System.out.println("\nAutocarros disponíveis para associar:");              
        for(j=0;j<listaA.size();j++)
            help=true;
            for (Viagem listaV1 : listaV) 
                for( Autocarro A1 : listaV1.getAutos())
                    if(!(listaA.get(j).equals(A1)) && help){
                        System.out.println("\t[" + (++m) + "]" + listaA.get(j).toString());
                        help=false;
                    }
            
        System.out.println("\nSelecione o Número do Autocarro que Quer Associar à Viagem:");
        System.out.println("         (Para Parar a Associação selecione 0)");                   
        for (int i = 0; i<listaA.size(); i++) {
            l = true;
            while ((a = sc.nextInt()) > m || a < 0)
                System.out.println("Digite uma Opção Válida.");
            if(a == 0){
                if(i == 0)
                    System.out.println("Tem que associar pelo menos um autocarro");
                else
                    break;
            }
            for (int k : array) {
                if (k == a){
                    System.out.println("O autocarro em questão já foi associado!");
                    i--;
                    l = false;
                    break;
                }
            }
            if (l == true){
                v1.autos.add(listaA.get(a-1));
                v1.vagas += listaA.get(i).getLotacao();
                System.out.println("Autocarro Associado com Sucesso.");
                array[i] = a;
            }
        }
        System.out.println("Viagem Criada com Sucesso.");
        return v1;
    }
   
    /**
     * PERMITE AO ADMINISTRADOR CRIAR UMA NOVA VIAGEM QUE NAO CONSTA NA LISTA DE VIAGENS
     * @param listaU
     * @param listaV
     * @param listaR
     * @param listaE
     * @param listaC
     */
    public static void removeViagem(ArrayList <Utilizador> listaU,ArrayList<Viagem> listaV, ArrayList<Reserva> listaR, ArrayList<Reserva> listaE, ArrayList<Reserva> listaC){
        Scanner sc = new Scanner(System.in);
        String b;
        int a;
        int k;
        if (listaV.isEmpty()){
            System.out.println("Não Existem Viagens para Eliminar.");
            return;
        }
        System.out.println("   (Código pode ser Consultado no Menu Anterior -> Opção 4)");
        System.out.print("Insira o Código Identificativo da Viagem que Pretende Eliminar:");
        while(true){
            try{
                b = sc.nextLine();
                a = Integer.parseInt(b);      
                if(a==-1)
                    return;
                for(int i=0;i<listaV.size();i++){
                    if(listaV.get(i).getCodigo() == a){
                        if(listaV.get(i).isRealizada()){       //só é possivel eliminar viagens que se vão concretizar, ficando sempre com o registo das que já ocorreram
                            System.out.println("Esse Código é relativo a Uma Viagem que Já Ocorreu");
                            return;
                        }
                        if(!listaV.get(i).getReservas().isEmpty()){
                            System.out.println("A Viagem que Pretende Eliminar Foi Reservada por Clientes.");
                            for (Reserva R1 : listaV.get(i).getReservas()) {
                                String notificacao = "A Seguinte Viagem Foi Cancelada:\n" + listaV.get(i).toString(0) + "\nEm breve devolveremos o dinheiro.\n";
                                for(int l=0;l<listaU.size();l++){
                                if(listaU.get(l).getUsername().equals(listaR.get(i).getUser().getUsername())){
                                    listaU.get(l).getNotificacoes().add(notificacao);
                                    listaU.get(l).setDinheiroReceber(listaR.get(i).getPagar().getQuantiaPagar());
                                }
                            }                                                            
                                if (R1.getPagar() != null)               //por fim retira-se a reserva da lista de reservas                                           
                                    listaR.remove(R1);  
                                else                                            
                                    listaE.remove(R1);
                            }
                            System.out.println("Notificações Enviadas aos Clientes.");
                            System.out.println("Reservas dessa Viagem Eliminadas com Sucesso.");
                        }
                        listaV.remove(i);                   
                        System.out.println("Viagem Removida com Sucesso.");
                        return;
                    }
                }
                System.out.print("O código Inserido Não Pertence a Nenhuma Viagem. Por favor Insira um Código Válido: ");
            } catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um Código Válido: ");
            }
        }
    }
    
    /**
     * PERMITE AO ADMINISTRADOR ALTERAR UMA VIAGEM QUE IRÁ OCORRER AINDA. NÃO PERMITE ALTERAR O CODIGO/DESTINO OU ORIGEM POIS ISSO SERIA COMO QUE CRIAR OUTRA VIAGEM DIFERENTE
     * @param listaU
     * @param listaV
     * @param listaR
     * @param listaE
     * @param listaA
     * @throws ParseException
     */
    public static void alteraViagem(ArrayList <Utilizador> listaU,ArrayList<Viagem> listaV, ArrayList<Reserva> listaR, ArrayList<Reserva> listaE, ArrayList<Autocarro> listaA) throws ParseException{
        Scanner sc = new Scanner(System.in);
        String b;
        int a;
        double m;
        boolean l;
        int k;
        
        if (listaV.isEmpty()){
            System.out.println("Não Existem Viagens para Eliminar.");
            return;
        }
        System.out.println("   (Código pode ser Consultado no Menu Anterior -> Opção 4)");
        System.out.print("Insira o Código Identificativo da Viagem que Pretende Alterar:");
        while(true){
            try{
                b = sc.nextLine();
                a = Integer.parseInt(b);      
                if(a == -1)
                    return;            
                for(int i=0;i<listaV.size();i++){
                    if(listaV.get(i).getCodigo() == a){ 
                        l = listaV.get(i).getReservas().isEmpty();
                        if (l == false)
                            System.out.println("A Viagem que Pretende Alterar Foi Reservada por Clientes.\n");
                        while(true){
                            System.out.println("[1] MODIFICAR PRECO");
                            System.out.println("[2] MODIFICAR DATA");
                            System.out.println("[3] MODIFICAR DURACAO");
                            System.out.println("[4] MODIFICAR AUTOCARROS UTILIZADOS\n");
                            System.out.println("[5] MENU ANTERIOR\n");
                            System.out.print("SELECIONE A SUA OPÇÃO: ");
                            while(true){
                                try{
                                    a = sc.nextInt();
                                    if(a > 4 || a < 0)
                                        switch(a){
                                            case 1:
                                                System.out.print("Insira a Nova Quantia: ");
                                                try{
                                                    m = sc.nextDouble();
                                                    if(m==-1)
                                                        return;
                                                }
                                                catch(Exception e){
                                                    return;
                                                }
                                                listaV.get(i).setPreco(m);
                                                System.out.println("Preço alterado com Sucesso!");
                                                if (l == false){                                    //Alterações devido ao facto de essa viagem ter reservas
                                                    for (Reserva R1 : listaV.get(i).getReservas()) {
                                                        if (m > listaV.get(i).getPreco() && R1.getPagar() != null) {                    //o novo preço é maior? Então quem já tinha pago terá de pagar a diferença e terá de ir para lista de espera até pagar a diferença              
                                                                R1.getPagar().setQuantiaPagar(m - listaV.get(i).getPreco());
                                                                R1.setPagar(null);
                                                                listaE.add(R1);
                                                            }
                                                        else if (R1.getPagar() != null){ //o novo preço é menor? Então quem já tinha pago terá que receber reembolso
                                                            k = listaU.indexOf(R1.getUser());
                                                            listaU.get(k).setDinheiroReceber(listaV.get(i).getPreco()-m);
                                                        }
                                                        String notificacao = "Consulte o Novo Preço Viagem Que Reservou: " + listaV.get(i).getCodigo() + "\nVerifique os Seus Pagamentos Pendentes\n";
                                                        for(int h=0;h<listaU.size();h++){
                                                            if(listaU.get(h).getUsername().equals(R1.getUser().getUsername())){
                                                                listaU.get(h).getNotificacoes().add(notificacao);
                                                                listaU.get(h).setDinheiroReceber(R1.getPagar().getQuantiaPagar());
                                                            }
                                                        }          
                                                    }
                                                    System.out.println("Notificações Enviadas para os Clientes.");
                                                }
                                                break;
                                            case 2:
                                                System.out.print("Insira a Nova Data Da Viagem: ");             
                                                while(true){
                                                    String dateFormat = "dd/MM/yyyy";
                                                    if((b = sc.nextLine()).isEmpty() || b.equals("-1") ||new SimpleDateFormat(dateFormat).parse(b).before(new Date())){
                                                        if(b.equals("-1"))
                                                            return;
                                                        System.out.print("Data Inválida. Por favor insira a Data da Partida (dia/mês/ano): ");
                                                    }
                                                    else{
                                                        listaV.get(i).setData(new SimpleDateFormat(dateFormat).parse(b));
                                                        break;
                                                    }
                                                }
                                                System.out.println("Data alterada com Sucesso!");
                                                if (l == false){                                    //Alterações devido ao facto de essa viagem ter reservas
                                                    for (Reserva R1 : listaV.get(i).getReservas()) {
                                                        String notificacao = "Consulte a Data da Viagem Que Reservou Cujo Código é: " + listaV.get(i).getCodigo() + "\n";
                                                        for(int h=0;h<listaU.size();h++){
                                                            if(listaU.get(h).getUsername().equals(R1.getUser().getUsername()))
                                                                listaU.get(h).getNotificacoes().add(notificacao);
                                                        }   
                                                    }
                                                    System.out.println("Notificações Enviadas para os Clientes.");
                                                }
                                                break;
                                            case 3:
                                                System.out.print("Insira a Nova Duração: ");                    
                                                try{                                                            
                                                    a = sc.nextInt();
                                                    sc.nextLine();
                                                    if(a<0)
                                                        System.out.println("NUMERO INVALIDO");
                                                    else
                                                        break;
                                                }catch(InputMismatchException e){
                                                    System.out.println("INSERE UM DOS NUMEROS");
                                                    sc.nextLine();
                                                }
                                                listaV.get(i).setDuracao(a);
                                                System.out.println("Duracao alterada com Sucesso!");
                                                if (l == false){                                    //Alterações devido ao facto de essa viagem ter reservas
                                                    for (Reserva R1 : listaV.get(i).getReservas()) {
                                                        String notificacao = "Consulte a Nova Duração da Viagem Que Reservou Cujo Código é: " + listaV.get(i).getCodigo() + "\n";
                                                        for(int h=0;h<listaU.size();h++){
                                                            if(listaU.get(h).getUsername().equals(R1.getUser().getUsername())){
                                                                listaU.get(h).getNotificacoes().add(notificacao);
                                                                listaU.get(h).setDinheiroReceber(R1.getPagar().getQuantiaPagar());
                                                            }
                                                        }
                                                    }
                                                System.out.println("Notificações Enviadas para os Clientes.");
                                                }
                                                break;
                                            case 4:                     
                                                int tmpLugares = 0;         
                                                int j;
                                                boolean f = true;
                                                m=0;
                                                int[] array = new int[listaA.size()];
                                                
                                                System.out.println("\nAutocarros disponíveis para associar:"); //Os autocarros da viagem em questão + os autocarros disponíveis são mostrados
                                                for(j=0;j<listaA.size();j++)
                                                    for (Viagem listaV1 : listaV)
                                                        for( Autocarro A1 : listaV1.getAutos()){
                                                            if (listaV1 == listaV.get(i)){
                                                                tmpLugares += A1.getLotacao();
                                                                System.out.println("\t[" + (++m) + "]" + listaA.get(j).toString());
                                                            }   
                                                            if(!(listaA.get(j).equals(A1)))
                                                                System.out.println("\t[" + (++m) + "]" + listaA.get(j).toString());
                                                        }
                                                int tmpVagas = listaV.get(i).getVagas();
                                                System.out.println("\nSelecione o Número do Autocarro que Quer Associar à Viagem:");
                                                System.out.println("         (Para Parar a Associação selecione 0)");
                                                listaV.get(i).autos.clear();
                                                for (int d = 0; d<listaA.size(); d++) {
                                                    l = true;
                                                    while ((a = sc.nextInt()) > m || a < 0)
                                                        System.out.println("Digite uma Opção Válida.");
                                                    if(a == 0){
                                                        if(i == 0)
                                                            System.out.println("Tem que associar pelo menos um autocarro");
                                                        else
                                                            break;
                                                    }
                                                    for (int o : array) {
                                                        if (o == a){
                                                            System.out.println("O autocarro em questão já foi associado!");
                                                            i--;
                                                            l = false;
                                                            break;
                                                        }
                                                    }
                                                    if (l == true){
                                                        listaV.get(i).vagas = 0;
                                                        listaV.get(i).vagas += listaA.get(i).getLotacao();
                                                        if (listaV.get(i).vagas <= tmpLugares){
                                                            System.out.println("Não Pode Diminuir os Lugares Disponibilizados pelos Autocarros na Viagem em Questão");
                                                            f = false;
                                                            break;
                                                        }
                                                        listaV.get(i).autos.add(listaA.get(a-1));
                                                        System.out.println("Autocarro Associado com Sucesso.");
                                                        array[i] = a;
                                                    }
                                                }
                                                if (f == true)
                                                    listaV.get(i).vagas = (listaV.get(i).vagas - tmpLugares) + tmpVagas;
                                                break;
                                            case 5:
                                                return;
                                        }
                                    else
                                        System.out.print("Escolha uma Opção Válida: ");
                                } catch(InputMismatchException e){
                                    System.out.println("INSERE UM DOS NUMEROS");
                                     sc.nextLine();
                                }
                            }
                        }
                    }
                }
                System.out.print("O código Inserido Não Pertence a Nenhuma Viagem. Por favor Insira um Código Válido: ");
            } catch(InputMismatchException | NumberFormatException e){
                System.out.print("Insira um Código Válido: ");
            }
        }
    }
    
    /**
     * ESTE MÉTODO DEVOLVE UMA STRING COM TODOS OS COMENTÁRIOS LISTADOS
     * @return com  STRING COM TODOS OS COMENTÁRIOS LISTADOS
     */
    public String toStringComents(){
        String com = "";
        for (int i = 0; i < this.coments.size(); i++) {
            com += this.coments.get(i).toString();
        }
        return com;
    }
    
    
     /**
     * GET ORIGEM
     * @return origem
     */
    public String getOrigem() {
        return origem;
    }

     /**
     * SET ORIGEM
     * @param origem
     */
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    /**
     * GET DESTINO
     * @return destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * SET DESTINO
     * @param destino
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * GET PREÇO DA VIAGEM
     * @return preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * SET PREÇO DA VIAGEM
     * @param preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * GET DATA DA PARTIDA
     * @return data
     */
    public Date getData() {
        return data;
    }

    /**
     * SET DATA DA PARTIDA
     * @param data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * GET DURAÇÃO DA VIAGEM
     * @return duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * SET DURAÇÃO DA VIAGEM
     * @param duracao
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * GET LISTA DE AUTOCARROS DA VIAGEM
     * @return autos
     */
    public ArrayList<Autocarro> getAutos() {
        return autos;
    }

    /**
     * SET LISTA DE AUTOCARROS DA VIAGEM
     * @param autos
     */
    public void setAutos(ArrayList<Autocarro> autos) {
        this.autos = autos;
    }

    /**
     * GET LISTA DE RESERVAS DA VIAGEM
     * @return reservas
     */
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    /**
     * SET LISTA DE RESERVAS DA VIAGEM
     * @param reservas
     */
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * GET LISTA DE COMENTÁRIOS DA VIAGEM
     * @return coments
     */
    public ArrayList<Comentario> getComents() {
        return coments;
    }

    /**
     * SET LISTA DE COMENTÁRIOS DA VIAGEM
     * @param coments
     */
    public void setComents(ArrayList<Comentario> coments) {
        this.coments = coments;
    }

    /**
     * GET NÚMERO DE VAGAS DA VIAGEM
     * @return vagas
     */
    public int getVagas() {
        return vagas;
    }

    /**
     * SET NÚMERO DE VAGAS DA VIAGEM
     * @param vagas
     */
    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    /**
     * GET RATING DA VIAGEM
     * @return rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * SET RATING DA VIAGEM
     * @param rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    /**
     * Rating = média das pontuações + 0.2 * número de reservas
     */
    public void geraRating(){       
        double rating=0;
        for(int i=0;i<this.coments.size();i++)
            rating+=this.coments.get(i).getPont();
        rating /= this.coments.size();
        rating += 0.2*this.reservas.size();
        this.rating = rating;
    }

    /**
     * GET CODIGO DA VIAGEM
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * SET CODIGO DA VIAGEM
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    /**
     * GET BOOLEAN QUE DIZ SE JÁ FOI REALIZADA OU NÃO
     * @return realizada
     */
    public boolean isRealizada() {
        return realizada;
    }

    /**
     * SET BOOLEAN QUE DIZ SE JÁ FOI REALIZADA OU NÃO
     * @param realizada
     */
    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
    
    /**
     * MÉTODO toString()
     * @param l INTEIRO QUE DISTINGUE A LISTAGEM DO UTILIZADOR E DO ADMINISTRADOR
     * @return String
     */
    public String toString(int l){ 
        String autoUtil = "";
        String reser = "";
        String retorna = "";
        
        for (int i = 0; i < this.autos.size(); i++) {
            autoUtil += this.autos.get(i).toString();
        }
        for (int i = 0; i < this.reservas.size(); i++) {
            reser = reser + "\t" + "[" + (i+1) + "] Username:" + this.reservas.get(i).getUser().login.getUser() + "\n\t    Lugar:" + this.reservas.get(i).getLugar() + "\n\t    Data da Reserva:" + this.reservas.get(i).getData().toLocaleString() + "\n";
        }
        //Administrador
        if (l>=1)       
            retorna = "\nVIAGEM\n->Código:" + this.codigo + "\n" 
                + "->Origem:" + this.origem + "\n" + "->Destino:" + this.destino 
                + "\n" + "->Preço:" + this.preco + " euros" + "\n" + "->Data:"  +  this.data.getDate() + "/" + (this.data.getMonth()+1) + "/" + (this.data.getYear()+1900) +  "\n" + "->Duração:" + this.duracao 
                + " dias" + "\n" + "->Autocarros Utilizados:" + autoUtil + "\n" + "->Vagas:" 
                + this.vagas + "\n" + "->Rating:" + this.rating + "\n";
        if (l==1)
             retorna += "->Reservas:\n" + reser;
        
        //Cliente
        if (l==0)
            retorna = "\nVIAGEM\n ->Código:" + this.codigo + "\n"+ "->Origem:" + this.origem + "\n" + "->Destino:" + this.destino 
                + "\n" + "->Preço:" + this.preco + " euros" + "\n" + "->Data:"  +  this.data.getDate() + "/" + (this.data.getMonth()+1) + "/" + (this.data.getYear()+1900) +  "\n" + "->Duração:" + this.duracao 
                + " dias" +  "\n" + "->Vagas:" + this.vagas + "\n" + "->Rating:" + this.rating + "\n";
        if (l<=0)
             retorna += "->Reservas:\n" + reser;
        
        return retorna;
    }

    
    
}
