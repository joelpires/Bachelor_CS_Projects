package com.Opti;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Catia Mourao on 10/12/2016.
 */

public class Evento implements Serializable{
    String tipo;
    Boolean allDay;
    long dtStart, dtEnd;
    String descricao;
    boolean notificar;

    public Evento(String tipo, Boolean allDay, long dtStart, long dtEnd, String descricao, boolean notificar) {
        this.tipo = tipo;
        this.allDay = allDay;
        this.dtStart = dtStart;
        this.dtEnd = dtEnd;
        this.descricao = descricao;
        this.notificar = notificar;
        //this.calID = calID;
        //this.eventID = eventID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public long getDtStart() {
        return dtStart;
    }

    public String getDtStartToString() {
        Date d = new Date(dtStart);
        return d.toString();
    }

    public void setDtStart(long dtStart) {
        this.dtStart = dtStart;
    }

    public long getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(long dtEnd) {
        this.dtEnd = dtEnd;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isNotificar() {
        return notificar;
    }

    public void setNotificar(boolean notificar) {
        this.notificar = notificar;
    }

    public String getDtEndToString() {
        Date d = new Date(dtEnd);
        return d.toString();
    }
}
