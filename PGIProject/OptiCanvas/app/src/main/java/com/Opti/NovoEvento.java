package com.Opti;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import com.Opti.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class NovoEvento extends AppCompatActivity {


    RadioButton tipoConsulta;
    RadioButton tipoRastreio;
    RadioGroup tipoEvento;

    CheckBox todoDiaCheck;
    Spinner diaDeSpinner;
    Spinner mesDeSpinner;
    Spinner anoDeSpinner;
    Spinner horaDeSpinner;
    Spinner minDeSpinner;
    Spinner diaAteSpinner;
    Spinner mesAteSpinner;
    Spinner anoAteSpinner;
    Spinner horaAteSpinner;
    Spinner minAteSpinner;

    ImageButton confirmBtn;
    ImageButton cancelBtn;

    EditText descricaoEdit;
    Switch notificacaoSwitch;

    /*Titulo*/
    String tipo;
    Boolean allDay;
    String descricao;

    private Context context = this;

    Ficheiro ficheiroEventos = new Ficheiro("opticanvas_events",context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_evento);

        java.util.Calendar now = java.util.Calendar.getInstance();

        tipoEvento = (RadioGroup) findViewById(R.id.tipoEventoRadio);
        tipoConsulta = (RadioButton) findViewById(R.id.tipoConsulta);
        tipoRastreio = (RadioButton) findViewById(R.id.tipoRastreio);

        todoDiaCheck = (CheckBox) findViewById(R.id.todoDiaCheck);
        diaDeSpinner = (Spinner) findViewById(R.id.diaSpinner);
        mesDeSpinner = (Spinner) findViewById(R.id.mesSpinner);
        anoDeSpinner = (Spinner) findViewById(R.id.anoSpinner);
        horaDeSpinner = (Spinner) findViewById(R.id.horaSpinner);
        minDeSpinner = (Spinner) findViewById(R.id.minSpinner);

        diaAteSpinner = (Spinner) findViewById(R.id.diaAteSpinner);
        mesAteSpinner = (Spinner) findViewById(R.id.mesAteSpinner);
        anoAteSpinner = (Spinner) findViewById(R.id.anoAteSpinner);
        horaAteSpinner = (Spinner) findViewById(R.id.horaAteSpinner);
        minAteSpinner = (Spinner) findViewById(R.id.minAteSpinner);

        descricaoEdit = (EditText) findViewById(R.id.descricaoEdit);

        notificacaoSwitch = (Switch) findViewById(R.id.notificacaoSwitch);

        confirmBtn = (ImageButton) findViewById(R.id.confirmBtn);
        cancelBtn = (ImageButton) findViewById(R.id.cancelBtn);

        allDay = false;

        iniciarSpinners();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarEventoCalendario();
                finish();
            }
        });
    }

    private void iniciarSpinners() {
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        List<String> anos = new ArrayList<>();
        List<String> dias = new ArrayList<>();
        List<String> horas = new ArrayList<>();
        List<String> minutos = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            dias.add("" + i);
        }

        for (int i = 2016; i < (2016 + 20); i++) {
            anos.add("" + i);
        }

        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                horas.add("0" + i);
            } else {
                horas.add("" + i);
            }

        }

        for (int i = 0; i < 60; i += 5) {
            if (i < 10) {
                minutos.add("0" + i);
            } else {
                minutos.add("" + i);
            }
        }

        ArrayAdapter<String> diaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dias);
        ArrayAdapter<String> mesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, meses);
        ArrayAdapter<String> anoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, anos);
        ArrayAdapter<String> horaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, horas);
        ArrayAdapter<String> minAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, minutos);

        diaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        horaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        diaDeSpinner.setAdapter(diaAdapter);
        mesDeSpinner.setAdapter(mesAdapter);
        anoDeSpinner.setAdapter(anoAdapter);
        horaDeSpinner.setAdapter(horaAdapter);
        minDeSpinner.setAdapter(minAdapter);

        diaAteSpinner.setAdapter(diaAdapter);
        mesAteSpinner.setAdapter(mesAdapter);
        anoAteSpinner.setAdapter(anoAdapter);
        horaAteSpinner.setAdapter(horaAdapter);
        minAteSpinner.setAdapter(minAdapter);
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.tipoConsulta:
                if (checked) {
                    tipo = "Consulta Ótica";
                }
                break;
            case R.id.tipoRastreio:
                if (checked) {
                    tipo = "Consulta Ótica";
                }
                break;
        }
    }

    public void onCheckboxAllDayClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        if (checked) {
            allDay = true;
            ((Spinner) horaDeSpinner).getSelectedView().setEnabled(false);
            horaDeSpinner.setEnabled(false);
            ((Spinner) minDeSpinner).getSelectedView().setEnabled(false);
            minDeSpinner.setEnabled(false);
            ((Spinner) horaAteSpinner).getSelectedView().setEnabled(false);
            horaAteSpinner.setEnabled(false);
            ((Spinner) minAteSpinner).getSelectedView().setEnabled(false);
            minAteSpinner.setEnabled(false);
        } else {
            allDay = false;
            ((Spinner) horaDeSpinner).getSelectedView().setEnabled(true);
            horaDeSpinner.setEnabled(true);
            ((Spinner) minDeSpinner).getSelectedView().setEnabled(true);
            minDeSpinner.setEnabled(true);
            ((Spinner) horaAteSpinner).getSelectedView().setEnabled(true);
            horaAteSpinner.setEnabled(true);
            ((Spinner) minAteSpinner).getSelectedView().setEnabled(true);
            minAteSpinner.setEnabled(true);
        }
    }

    private void adicionarEventoCalendario() {

        int tipoChecked = tipoEvento.getCheckedRadioButtonId();
        String titulo;
        if (tipoChecked == 0) {
            titulo = "Consulta Ótica";
        } else {
            titulo = "Rastreio Óptica";
        }
        /* Data e Hora */
        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        String diaDe = diaDeSpinner.getSelectedItem().toString();
        String mesDeSelected = mesDeSpinner.getSelectedItem().toString();
        String anoDe = anoDeSpinner.getSelectedItem().toString();
        int mesDe = -1;

        String diaAte = diaAteSpinner.getSelectedItem().toString();
        String mesAteSelected = mesAteSpinner.getSelectedItem().toString();
        String anoAte = anoAteSpinner.getSelectedItem().toString();
        int mesAte = -1;

        GregorianCalendar dataStart;
        GregorianCalendar dataEnd;

        for (int i = 0; i < 11; i++) {
            if (meses[i].equals(mesDeSelected)) {
                mesDe = i;
            }
        }

        for (int i = 0; i < 11; i++) {
            if (meses[i].equals(mesAteSelected)) {
                mesAte = i;
            }
        }

        if (!todoDiaCheck.isChecked()) {
            String horaDe = horaDeSpinner.getSelectedItem().toString();
            String minDe = minDeSpinner.getSelectedItem().toString();
            String horaAte = horaAteSpinner.getSelectedItem().toString();
            String minAte = minAteSpinner.getSelectedItem().toString();

            dataStart = new GregorianCalendar(Integer.parseInt(anoDe), mesDe, Integer.parseInt(diaDe), Integer.parseInt(horaDe), Integer.parseInt(minDe));
            dataEnd = new GregorianCalendar(Integer.parseInt(anoAte), mesAte, Integer.parseInt(diaAte), Integer.parseInt(horaAte), Integer.parseInt(minAte));

        } else {
            dataStart = new GregorianCalendar(Integer.parseInt(anoDe), mesDe, Integer.parseInt(diaDe));
            dataEnd = new GregorianCalendar(Integer.parseInt(anoAte), mesAte, Integer.parseInt(diaAte));
        }


        String notas = descricaoEdit.getText().toString();
        Boolean notificacoes = notificacaoSwitch.isChecked();


        Evento novo = new Evento(titulo,allDay,dataStart.getTimeInMillis(),dataEnd.getTimeInMillis(),notas,notificacoes);

        guardaArrayEventos(novo);

        Intent intent = new Intent(this,Calendario.class);
        startActivity(intent);
        finish();
    }


    private void guardaArrayEventos(Evento evento){
        ArrayList<Evento> eventos = getArrayEventos();
        eventos.add(evento);

        for(Evento e : eventos){
            System.out.println(e.descricao);
        }

        try {
            ficheiroEventos.abreEscrita();
            ficheiroEventos.escreveArray(eventos);
            ficheiroEventos.fechaEscrita();
        } catch (IOException e) {
            System.out.println("Erro a guardar informação.");
        }

    }

    private ArrayList<Evento> getArrayEventos() {
        if (ficheiroEventos.abreLeitura()){
            try {
                ArrayList array = ficheiroEventos.leArray();
                ficheiroEventos.fechaLeitura();
                return array;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro a ler informação do ficheiro");

            }
        }
        return new ArrayList<>();
    }
}
