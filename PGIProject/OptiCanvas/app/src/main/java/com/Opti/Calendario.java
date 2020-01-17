package com.Opti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.Opti.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Catia Mourao on 21/11/2016.
 */

public class Calendario extends AppCompatActivity{
    private static Ficheiro ficheiro;
    final Context context = this;

    CalendarView calendarView;
    ImageButton novoEventoBtn;

    long cal_ID;
    private static ArrayList<Evento> eventos;

    private PopupWindow mPopUp;
    LinearLayout mLinearLayout;
    private Spinner menuSpin;
    TextView naoEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar);

        ficheiro = new Ficheiro("opticanvas_events",context);
        eventos = iniciaArrayEventos();
        sortEvents();

        calendarView = (CalendarView) findViewById(R.id.calendar);
        novoEventoBtn = (ImageButton) findViewById(R.id.novoEventoBtn);
        menuSpin = (Spinner) findViewById(R.id.menuSpinner);
        populateMenu();

        /* Criar um evento novo */
        if(novoEventoBtn!=null){
            novoEventoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    criarNovoEvento();
                    finish();
                }
            });
        }

        /*Calendario*/
        if(calendarView!=null){
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                    showEventsDay(year,month,dayOfMonth);
                }
            });
        }


        TextView tituloEvento1 = (TextView) findViewById(R.id.nomeEvento1);
        TextView dataEvento1 = (TextView) findViewById(R.id.dataEvento1);

        TextView tituloEvento2 = (TextView) findViewById(R.id.nomeEvento2);
        TextView dataEvento2 = (TextView) findViewById(R.id.dataEvento2);

        /* Eventos Próximos*/
        Date now = new Date();
        now.getTime();
        int i=0;
        final Evento[] ev = new Evento[2];
        Date aux;
        Evento e;

        Iterator<Evento> it = eventos.iterator();

        while (it.hasNext()){
            e = it.next();

            aux = new Date(e.dtStart);

            System.out.println(e.descricao);

            if(aux.after(now) && i<2){
                System.out.println(e.descricao+"-"+aux);
                ev[i]=e;
                i++;
            }
        }

        if(ev[0]==null){
            tituloEvento1.setText("O seu Próximo Evento Aqui.");
            dataEvento1.setText("");

        }
        if(ev[1]==null){
            tituloEvento2.setText("O seu Próximo Evento Aqui.");
            dataEvento2.setText("");
        }

        if(ev[0]!=null) {

            String titulo = ev[0].tipo + " - " + ev[0].descricao;
            Date data = new Date(ev[0].dtStart);
            String dataS = data.toString();

            tituloEvento1.setText(titulo);
            dataEvento1.setText(dataS);

            ImageButton more1 = (ImageButton) findViewById(R.id.more1);

            more1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoEvento(ev[0]);

                }
            });
        }

        if(ev[1]!=null){

            String titulo = ev[1].tipo + " - " + ev[1].descricao;
            Date data = new Date(ev[1].dtStart);
            String dataS = data.toString();

            tituloEvento2.setText(titulo);
            dataEvento2.setText(dataS);

            ImageButton more2 = (ImageButton) findViewById(R.id.more2);

            more2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showInfoEvento(ev[1]);

                }
            });
        }
    }

    private void populateMenu() {
        List<String> opcoesMenu = new ArrayList<>();
        opcoesMenu.add("Calendário");
        opcoesMenu.add("Home");
        opcoesMenu.add("Inventário");

        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcoesMenu);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpin.setAdapter(menuAdapter);

        menuSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String op = menuSpin.getSelectedItem().toString();
                switch (op){
                    case "Home":
                        Intent cal = new Intent(Calendario.this,HomePage.class);
                        startActivity(cal);
                        finish();
                        break;

                    case "Inventário":
                        Intent inv = new Intent(Calendario.this,Inventario.class);
                        startActivity(inv);
                        finish();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                menuSpin.setSelection(1);
            }
        });
    }

    private void showInfoEvento(Evento ev){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        View customView = inflater.inflate(R.layout.popup_eventos,null);

        mPopUp = new PopupWindow(
                customView,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        mLinearLayout = (LinearLayout) findViewById(R.id.ll);
        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.close);
        LinearLayout mScrollView = (LinearLayout) customView.findViewById(R.id.scrollEvents);

        final TextView titulo = new TextView(this);

        final LinearLayout l1 = new LinearLayout(this);
        final TextView tipoTxt = new TextView(this);
        final TextView tipoEdit = new TextView(this);

        final LinearLayout l2 = new LinearLayout(this);
        final TextView descricaoTxt = new TextView(this);
        final TextView descricaoEdit = new TextView(this);

        final LinearLayout l3 = new LinearLayout(this);
        final TextView dtStartTxt = new TextView(this);
        final TextView dtStartEdit = new TextView(this);

        final LinearLayout l4 = new LinearLayout(this);
        final TextView dtEndTxt = new TextView(this);
        final TextView dtEndEdit = new TextView(this);

        titulo.setText("Detalhes");
        titulo.setBackgroundResource(R.color.blueTitle);
        titulo.setTextSize(20);
        titulo.setHeight(80);
        titulo.setGravity(Gravity.CENTER);
        mScrollView.addView(titulo);


        tipoTxt.setText("Tipo de Evento:    ");
        tipoTxt.setTextSize(18);
        tipoTxt.setWidth(350);
        tipoTxt.setTextColor(getResources().getColor(R.color.black));
        tipoEdit.setText(ev.getTipo());
        tipoEdit.setTextSize(15);
        l1.setOrientation(LinearLayout.HORIZONTAL);
        l1.addView(tipoTxt);
        l1.addView(tipoEdit);
        mScrollView.addView(l1);


        descricaoTxt.setText("Descrição:    ");
        descricaoTxt.setTextSize(18);
        descricaoTxt.setWidth(350);
        descricaoTxt.setTextColor(getResources().getColor(R.color.black));
        descricaoEdit.setText(ev.getDescricao());
        descricaoEdit.setTextSize(15);
        l2.setOrientation(LinearLayout.HORIZONTAL);
        l2.addView(descricaoTxt);
        l2.addView(descricaoEdit);
        mScrollView.addView(l2);

        dtStartTxt.setText("Data de Inicio:    ");
        dtStartTxt.setTextSize(18);
        dtStartTxt.setWidth(350);
        dtStartTxt.setTextColor(getResources().getColor(R.color.black));
        dtStartEdit.setText(ev.getDtStartToString());
        dtStartEdit.setTextSize(15);
        l3.setOrientation(LinearLayout.HORIZONTAL);
        l3.addView(dtStartTxt);
        l3.addView(dtStartEdit);
        mScrollView.addView(l3);

        dtEndTxt.setText("Data de Fim:    ");
        dtEndTxt.setTextSize(18);
        dtEndTxt.setWidth(350);
        dtEndTxt.setTextColor(getResources().getColor(R.color.black));
        dtEndEdit.setText(ev.getDtEndToString());
        dtEndEdit.setTextSize(15);
        l4.setOrientation(LinearLayout.HORIZONTAL);
        l4.addView(dtEndTxt);
        l4.addView(dtEndEdit);
        mScrollView.addView(l4);


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopUp.dismiss();
            }
        });

        mPopUp.showAtLocation(mLinearLayout, Gravity.CENTER,0,0);
    }

    private void showEventsDay(int year, int month, int dayOfMonth){
        int y,m,d;
        y=year;
        m=month;
        d = dayOfMonth;

        Calendar day= Calendar.getInstance();
        day.set(year,month,dayOfMonth-1,23,59);
        Date dia = new Date();
        dia.setTime(day.getTimeInMillis());

        Calendar dayAfter= Calendar.getInstance();
        dayAfter.set(y,m,d,23,59);
        Date diaD = new Date();
        diaD.setTime(dayAfter.getTimeInMillis());

        Date date;
        long dt;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        View customView = inflater.inflate(R.layout.popup_eventos,null);

        mLinearLayout = (LinearLayout) findViewById(R.id.ll);

        LinearLayout mScrollView = (LinearLayout) customView.findViewById(R.id.scrollEvents);

        mPopUp = new PopupWindow(
                customView,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        ArrayList<Evento> eventosDia = new ArrayList<>();

        for(Evento e : eventos){
            dt=e.dtStart;
            date=new Date(dt);

            if(date.after(dia) && date.before(diaD)){
                eventosDia.add(e);
            }
        }

        if(eventosDia.size()>0){
            final int N = (eventosDia.size()*5);
            int nE = 1;

            final TextView[] myTextViews = new TextView[N]; // create an empty array;

            while (nE<=eventosDia.size()){
                // create a new textview

                final TextView separarView = new TextView(this);

                final LinearLayout l1 = new LinearLayout(this);
                final TextView tipoTxt = new TextView(this);
                final TextView tipoEdit = new TextView(this);

                final LinearLayout l2 = new LinearLayout(this);
                final TextView descricaoTxt = new TextView(this);
                final TextView descricaoEdit = new TextView(this);

                final LinearLayout l3 = new LinearLayout(this);
                final TextView dtStartTxt = new TextView(this);
                final TextView dtStartEdit = new TextView(this);

                final LinearLayout l4 = new LinearLayout(this);
                final TextView dtEndTxt = new TextView(this);
                final TextView dtEndEdit = new TextView(this);


                // set some properties of rowTextView or something
                separarView.setText("Evento " + nE);
                separarView.setBackgroundResource(R.color.blueTitle);
                separarView.setTextSize(20);
                separarView.setHeight(80);
                separarView.setGravity(Gravity.CENTER);
                mScrollView.addView(separarView);


                tipoTxt.setText("Tipo de Evento:    ");
                tipoTxt.setTextSize(18);
                tipoTxt.setWidth(350);
                tipoTxt.setTextColor(getResources().getColor(R.color.black));
                tipoEdit.setText(eventosDia.get(nE-1).getTipo());
                tipoEdit.setTextSize(15);
                l1.setOrientation(LinearLayout.HORIZONTAL);
                l1.addView(tipoTxt);
                l1.addView(tipoEdit);
                mScrollView.addView(l1);


                descricaoTxt.setText("Descrição:    ");
                descricaoTxt.setTextSize(18);
                descricaoTxt.setWidth(350);
                descricaoTxt.setTextColor(getResources().getColor(R.color.black));
                descricaoEdit.setText(eventosDia.get(nE-1).getDescricao());
                descricaoEdit.setTextSize(15);
                l2.setOrientation(LinearLayout.HORIZONTAL);
                l2.addView(descricaoTxt);
                l2.addView(descricaoEdit);
                mScrollView.addView(l2);

                dtStartTxt.setText("Data de Inicio:    ");
                dtStartTxt.setTextSize(18);
                dtStartTxt.setWidth(350);
                dtStartTxt.setTextColor(getResources().getColor(R.color.black));
                dtStartEdit.setText(eventosDia.get(nE-1).getDtStartToString());
                dtStartEdit.setTextSize(15);
                l3.setOrientation(LinearLayout.HORIZONTAL);
                l3.addView(dtStartTxt);
                l3.addView(dtStartEdit);
                mScrollView.addView(l3);

                dtEndTxt.setText("Data de Fim:    ");
                dtEndTxt.setTextSize(18);
                dtEndTxt.setWidth(350);
                dtEndTxt.setTextColor(getResources().getColor(R.color.black));
                dtEndEdit.setText(eventosDia.get(nE-1).getDtEndToString());
                dtEndEdit.setTextSize(15);
                l4.setOrientation(LinearLayout.HORIZONTAL);
                l4.addView(dtEndTxt);
                l4.addView(dtEndEdit);
                mScrollView.addView(l4);

                nE++;

            }
        }else {

            final TextView naoHaEventosView = new TextView(this);
            naoHaEventosView.setText("Não há eventos.");
            mScrollView.addView(naoHaEventosView);

        }

        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.close);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopUp.dismiss();
            }
        });

        mPopUp.showAtLocation(mLinearLayout, Gravity.CENTER,0,0);
    }

    private ArrayList<Evento> iniciaArrayEventos() {
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

    private static void guardaArrayEventos(){
        try {
            ficheiro.abreEscrita();
            ficheiro.escreveArray(eventos);
            ficheiro.fechaEscrita();
        } catch (IOException e) {
            System.out.println("Erro a guardar informação.");
        }

    }

    public void criarNovoEvento(){
        Intent intent = new Intent(this,NovoEvento.class);
        startActivity(intent);
    }

    private long leCalendarIDFicheiro() {
        String temp = "";
        try {
            FileInputStream fin = openFileInput("opticanvas_cal_id");
            int c;
            try {
                while ((c = fin.read()) != -1) {
                    temp = temp + Character.toString((char) c);
                }
                long calID = Long.parseLong(temp);
                return calID;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }



    /************* getters e setters *********************/

    public static Ficheiro getFicheiro() {
        return ficheiro;
    }

    public static void setFicheiro(Ficheiro ficheiro) {
        Calendario.ficheiro = ficheiro;
    }

    public Context getContext() {
        return context;
    }

    public CalendarView getCalendarView() {
        return calendarView;
    }

    public void setCalendarView(CalendarView calendarView) {
        this.calendarView = calendarView;
    }


    public long getCal_ID() {
        return cal_ID;
    }

    public void setCal_ID(long cal_ID) {
        this.cal_ID = cal_ID;
    }

    public static ArrayList<Evento> getEventos() {
        return eventos;
    }

    public static void setEventos(ArrayList<Evento> eventos) {
        Calendario.eventos = eventos;
    }

    public void sortEvents(){

        Collections.sort(eventos, new Comparator<Evento>() {
            @Override
            public int compare(Evento evento2, Evento evento1)
            {
                Date d1,d2;
                d1= new Date(evento1.dtStart);
                d2= new Date(evento2.dtStart);

                return  d2.compareTo(d1);
            }
        });
    }

}



