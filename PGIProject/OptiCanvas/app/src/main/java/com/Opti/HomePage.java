package com.Opti;

/**
 * Created by Catia Mourao on 12/12/2016.
 */


        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import android.widget.TextView;

        import com.Opti.R;

        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;
        import java.util.Random;


public class HomePage extends AppCompatActivity  {

    //Map<String, String> m = new HashMap<>();
    private TextView tipTextView;
    private Spinner menuSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();

        titles.add("Quem nunca ouviu a expressão “A cenoura faz bem aos olhos”?");
        titles.add("Por que devem ser usados os Óculos de Sol pelas crianças?");
        titles.add("Conselho para leitura");
        titles.add("Utilizaçao de Computador");
        titles.add("Iluminação Homogenea");
        titles.add("Protecção");
        titles.add("Alimentação");
        titles.add("Higiene");
        titles.add("Trabalho");
        titles.add("Condução");
        titles.add("Lentes do Contacto");
        titles.add("Cuidados a ter com os seus óculos");
        titles.add("Óculos de  Sol");

        messages.add("Não só a cenoura, mas todos os legumes e frutas de cor laranja são grandes amigos dos olhos. O betacaroteno, um antioxidante natural que confere a cor amarelo/laranja, é uma das formas indiretas de se obter a vitamina A que contribui para que a retina e outras partes do olho funcionem sem problemas.");
        messages.add("O uso de óculos de sol pelas crianças, não é uma questão de moda, é o único método para proteger os seus olhos de um meio ambiente adverso e hostil. O uso de lentes escuras de qualidade, sobretudo se adquiridas numa ótica, com filtros adequados para as radiações ultravioleta é importante para prevenir doenças oculares.");
        messages.add("Durante a leitura a postura é importante para que ler seja uma actividade agradável. Não só focalizamos na retina o que vemos, como também o fazemos com os dois olhos. Só assim temos uma noção de relevo ou de distância. Procure utilizar uma distância de trabalho/leitura adequada.");
        messages.add("Procure sempre que possível manter o écran de frente para si e evite movimentos oblíquos dos olhos que serão sempre mais fatigantes do que os horizontais ou verticais. Evite estar períodos longos ao computador para alterar o plano de focagem. No intervalo desses períodos olhe para planos distintos (distâncias diferentes) diversificando a focalização.");
        messages.add("Procure manter uma iluminação do ambiente onde se encontra homogénea. Evite ler ou trabalhar com a televisão ligada pois diminui a concentração na actividade que está a executar. Tente manter o local de trabalho iluminado sem sombras ou pontos de luz perceptíveis aos nossos olhos.");
        messages.add("Use sempre óculos de sol com protecção UV 100%. lembre-se que os óculos sem protecção/certificação são altamente prejudiciais pois, além de não serem bons filtros, fazem com que a pupila dilate, chegando assim mais radiação ao olho. A qualidade deficiente dos artigos comprados fora das ópticas pode causar igualmente defeitos refractivos.");
        messages.add("Os alimentos ricos em vitamina A melhoram o funcionamento do aparelho visual. Inclua na sua alimentação alimentos como: brócolos, papaia, cenoura, salmão e gema de ovo.");
        messages.add("Lave sempre as mãos antes de tocar nos olhos. tenha atenção à validade dos colírios, pomadas ou maquilhagem.");
        messages.add("Quando estiver ao computador, faça intervalos de hora a hora. Olhe para uma imagem distante do monitor, pelo menos a quatro metros e pisque algumas vezes os olhos.");
        messages.add("Quando conduzir durante uma longa viagem, faça intervalos. Não se esqueça de rever a sua acuidade visual periodicamente pela sua segurança e pela segurança dos que o rodeiam.");
        messages.add("Antes de manipular, colocar ou retirar as lentes de contacto, lave as mãos com um sabão de pH neutro que não contenha óleos, creme ou perfume e seque as mãos com uma toalha limpa.");
        messages.add("- Para limpeza das lentes dos seus óculos utilize o spray indicado ou água corrente;\n" +
                "- Não deixe os seus óculos expostos a fontes de calor;\n" +
                "- Evite que os seus óculos entrem em contacto com água salgada.");
        messages.add("Os óculos de sol devem ter lentes com filtros para proteger dos efeitos nocivos da radiação solar que devem ser adequados ao tipo de uso que têm. A indicação de proteção UV não chega, há vários graus de proteção a que deve estar atento e muitas vezes essa proteção é de fraca qualidade.");

        tipTextView = (TextView) findViewById(R.id.tip_text);

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(13);

        tipTextView.setText(titles.get(randomInt) + "\n\n" + messages.get(randomInt));

        menuSpin = (Spinner) findViewById(R.id.menuSpinner);
        populateMenu();

/*
        String text1 = "Quem nunca ouviu a expressão “A cenoura faz bem aos olhos”?";
        String message1 = "Não só a cenoura, mas todos os legumes e frutas de cor laranja são grandes amigos dos olhos. O betacaroteno, um antioxidante natural que confere a cor amarelo/laranja, é uma das formas indiretas de se obter a vitamina A que contribui para que a retina e outras partes do olho funcionem sem problemas.";
        String text2 = "Por que devem ser usados os Óculos de Sol pelas crianças?";
        String message2 = "O uso de óculos de sol pelas crianças, não é uma questão de moda, é o único método para proteger os seus olhos de um meio ambiente adverso e hostil. O uso de lentes escuras de qualidade, sobretudo se adquiridas numa ótica, com filtros adequados para as radiações ultravioleta é importante para prevenir doenças oculares.";
        String text3 = "Conselho para leitura";
        String message3 = "Durante a leitura a postura é importante para que ler seja uma actividade agradável. Não só focalizamos na retina o que vemos, como também o fazemos com os dois olhos. Só assim temos uma noção de relevo ou de distância. Procure utilizar uma distância de trabalho/leitura adequada.";
        String text4 = "Utilizaçao de Computador";
        String message4 = "Procure sempre que possível manter o écran de frente para si e evite movimentos oblíquos dos olhos que serão sempre mais fatigantes do que os horizontais ou verticais. Evite estar períodos longos ao computador para alterar o plano de focagem. No intervalo desses períodos olhe para planos distintos (distâncias diferentes) diversificando a focalização.";
        String text5 = "Iluminação Homogenea";
        String message5 = "Procure manter uma iluminação do ambiente onde se encontra homogénea. Evite ler ou trabalhar com a televisão ligada pois diminui a concentração na actividade que está a executar. Tente manter o local de trabalho iluminado sem sombras ou pontos de luz perceptíveis aos nossos olhos.";
        String text6 = "Protecção";
        String message6 = "Use sempre óculos de sol com protecção UV 100%. lembre-se que os óculos sem protecção/certificação são altamente prejudiciais pois, além de não serem bons filtros, fazem com que a pupila dilate, chegando assim mais radiação ao olho. A qualidade deficiente dos artigos comprados fora das ópticas pode causar igualmente defeitos refractivos.";
        String text7 = "Alimentação";
        String message7 = "Os alimentos ricos em vitamina A melhoram o funcionamento do aparelho visual. Inclua na sua alimentação alimentos como: brócolos, papaia, cenoura, salmão e gema de ovo.";
        String text8 = "Higiene";
        String message8 = "Lave sempre as mãos antes de tocar nos olhos. tenha atenção à validade dos colírios, pomadas ou maquilhagem.";
        String text9 = "Trabalho";
        String message9 = "Quando estiver ao computador, faça intervalos de hora a hora. Olhe para uma imagem distante do monitor, pelo menos a quatro metros e pisque algumas vezes os olhos.";
        String text10 = "Condução";
        String message10 = "Quando conduzir durante uma longa viagem, faça intervalos. Não se esqueça de rever a sua acuidade visual periodicamente pela sua segurança e pela segurança dos que o rodeiam.";
        String text11 = "Lentes do Contacto";
        String message11 = "Antes de manipular, colocar ou retirar as lentes de contacto, lave as mãos com um sabão de pH neutro que não contenha óleos, creme ou perfume e seque as mãos com uma toalha limpa.";
        String text12 = "Cuidados a ter com os seus óculos";
        String message12 = "- Para limpeza das lentes dos seus óculos utilize o spray indicado ou água corrente;\n" +
                "- Não deixe os seus óculos expostos a fontes de calor;\n" +
                "- Evite que os seus óculos entrem em contacto com água salgada.";
        String text13 = "Óculos de  Sol";
        String message13 = "Os óculos de sol devem ter lentes com filtros para proteger dos efeitos nocivos da radiação solar que devem ser adequados ao tipo de uso que têm. A indicação de proteção UV não chega, há vários graus de proteção a que deve estar atento e muitas vezes essa proteção é de fraca qualidade.";
*/



/*
        String key = "";
        int j = 0;
        Set keys = m.keySet();
        for (Iterator i = keys.iterator(); i.hasNext(); ) {
            if (j == randomInt) {
                key = (String) i.next();
            }
        }
*/
        //tipTextView.setText(key + "\n" + m.get(key));

        //tipTextView.setText("ola");
    }

    private void populateMenu() {
        List<String> opcoesMenu = new ArrayList<>();
        opcoesMenu.add("Home");
        opcoesMenu.add("Calendário");
        opcoesMenu.add("Inventário");

        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcoesMenu);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpin.setAdapter(menuAdapter);

        menuSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String op = menuSpin.getSelectedItem().toString();
                switch (op){
                    case "Calendário":
                        Intent cal = new Intent(HomePage.this,Calendario.class);
                        startActivity(cal);
                        finish();
                        break;

                    case "Inventário":
                        Intent inv = new Intent(HomePage.this,Inventario.class);
                        startActivity(inv);
                        finish();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                menuSpin.setSelection(0);
            }
        });

    }

    private void startArrayList() {
        /*
        m.put("", "");
        m.put("", "");
        m.put("Conselho para leitura", "Durante a leitura a postura é importante para que ler seja uma actividade agradável. Não só focalizamos na retina o que vemos, como também o fazemos com os dois olhos. Só assim temos uma noção de relevo ou de distância. Procure utilizar uma distância de trabalho/leitura adequada.");
        m.put("Utilizaçao de Computador", "Procure sempre que possível manter o écran de frente para si e evite movimentos oblíquos dos olhos que serão sempre mais fatigantes do que os horizontais ou verticais. Evite estar períodos longos ao computador para alterar o plano de focagem. No intervalo desses períodos olhe para planos distintos (distâncias diferentes) diversificando a focalização.");
        m.put("Iluminação Homogenea", "Procure manter uma iluminação do ambiente onde se encontra homogénea. Evite ler ou trabalhar com a televisão ligada pois diminui a concentração na actividade que está a executar. Tente manter o local de trabalho iluminado sem sombras ou pontos de luz perceptíveis aos nossos olhos.");
        m.put("Protecção", "Use sempre óculos de sol com protecção UV 100%. lembre-se que os óculos sem protecção/certificação são altamente prejudiciais pois, além de não serem bons filtros, fazem com que a pupila dilate, chegando assim mais radiação ao olho. A qualidade deficiente dos artigos comprados fora das ópticas pode causar igualmente defeitos refractivos.");
        m.put("Alimentação", "Os alimentos ricos em vitamina A melhoram o funcionamento do aparelho visual. Inclua na sua alimentação alimentos como: brócolos, papaia, cenoura, salmão e gema de ovo.");
        m.put("Higiene", "Lave sempre as mãos antes de tocar nos olhos. tenha atenção à validade dos colírios, pomadas ou maquilhagem.");
        m.put("Trabalho", "Quando estiver ao computador, faça intervalos de hora a hora. Olhe para uma imagem distante do monitor, pelo menos a quatro metros e pisque algumas vezes os olhos.");
        m.put("Condução", "Quando conduzir durante uma longa viagem, faça intervalos. Não se esqueça de rever a sua acuidade visual periodicamente pela sua segurança e pela segurança dos que o rodeiam.");
        m.put("Lentes do Contacto", "Antes de manipular, colocar ou retirar as lentes de contacto, lave as mãos com um sabão de pH neutro que não contenha óleos, creme ou perfume e seque as mãos com uma toalha limpa.");
        m.put("Cuidados a ter com os seus óculos", "- Para limpeza das lentes dos seus óculos utilize o spray indicado ou água corrente;\n" +
                "- Não deixe os seus óculos expostos a fontes de calor;\n" +
                "- Evite que os seus óculos entrem em contacto com água salgada.");
        m.put("Óculos de  Sol", "Os óculos de sol devem ter lentes com filtros para proteger dos efeitos nocivos da radiação solar que devem ser adequados ao tipo de uso que têm. A indicação de proteção UV não chega, há vários graus de proteção a que deve estar atento e muitas vezes essa proteção é de fraca qualidade.");
        */
    }

    private void eventosProximos(){
        ArrayList<Evento> eventos = iniciaArrayEventos();
        int n=0;
        Calendar now = Calendar.getInstance();
        Date nowD = now.getTime();

        Calendar day= Calendar.getInstance();

        int dia1 = day.get(Calendar.DAY_OF_MONTH);
        int mes1 = day.get(Calendar.MONTH);
        int ano1 = day.get(Calendar.YEAR);

        day.set(ano1,mes1,dia1-1,23,59);
        Date day1 = new Date();
        day1.setTime(day.getTimeInMillis());

        Calendar dayAfter= Calendar.getInstance();

        dayAfter.set(ano1,mes1,dia1+3,23,59);
        Date diaD = new Date();
        diaD.setTime(dayAfter.getTimeInMillis());

        Date dE = new Date();

        for (Evento e : eventos){
            dE.setTime(e.dtStart);
            if(dE.after(day1) && dE.before(diaD)){
                n++;
            }
        }

        TextView numeroEventos = (TextView) findViewById(R.id.calendar);

        if(n<0){
           numeroEventos.setText("Não tem eventos nos próximos 3 dias.");
        }else{
            numeroEventos.setText("Tem "+n+" eventos previstos nos próximos 3 dias.");
        }
    }

    private ArrayList<Evento> iniciaArrayEventos() {
        Ficheiro ficheiro = new Ficheiro("opticanvas_events",this);
        if (ficheiro.abreLeitura()){
            try {
                ArrayList array = ficheiro.leArray();
                ficheiro.fechaLeitura();
                return array;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro a ler informação do ficheiro");
            }
        }

        return new ArrayList<>();
    }

}
