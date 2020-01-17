package com.Opti;

import android.content.Context;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Catia Mourao on 10/12/2016.
 */

public class Ficheiro{
    private String nome;
    private FileOutputStream fos;
    FileInputStream fis;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    Context context;

    Ficheiro(String nome,Context context) {
        this.nome = nome;
        this.context = context;
    }


    boolean abreLeitura() {
        try{
            fis = context.openFileInput(nome);
            inputStream = new ObjectInputStream(fis);

            return true;
        } catch (IOException e){
            return false;
        }
    }

    void abreEscrita() throws IOException  {
        fos = context.openFileOutput(nome, Context.MODE_PRIVATE);
        outputStream = new ObjectOutputStream(fos);
    }


    void fechaLeitura() throws IOException {

        inputStream.close();
        fis.close();
    }


    void fechaEscrita() throws IOException
    {
        outputStream.close();
        fos.close();
    }


    ArrayList leArray() throws IOException, ClassNotFoundException {
        ArrayList<Evento> objects = new ArrayList<>();
        Evento object;
        boolean run=true;

        while (run){
            try {
                object = (Evento) inputStream.readObject();
                System.out.println(object.descricao);
                objects.add(object);
            }catch (EOFException e){
                run=false;
            }
        }
        return objects;
    }

    void escreveArray(ArrayList objectos){
        try {
            for(Object i : objectos){
                try {
                    outputStream.writeObject(i);

                }catch (NotSerializableException e){
                    System.out.println("Erro: Objecto não é Serializable");
                }
            }
        }catch (IOException e) {
            System.err.println("Erro: Guardar os objectos não teve sucesso.");;
        }
    }

}

