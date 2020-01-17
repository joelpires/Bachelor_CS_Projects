package com.Opti;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Opti.R;

public class Adaptador_Lista extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    TextView txtThird;
    TextView txtFourth;
    public Adaptador_Lista(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.colunas, null);

            txtFirst=(TextView) convertView.findViewById(R.id.Produto);
            txtSecond=(TextView) convertView.findViewById(R.id.Quantidade);
            txtThird=(TextView) convertView.findViewById(R.id.Preço);
            txtFourth=(TextView) convertView.findViewById(R.id.Garantia);

        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(Constantes_colunas.FIRST_COLUMN));
        txtSecond.setText(map.get(Constantes_colunas.SECOND_COLUMN));
        txtThird.setText(map.get(Constantes_colunas.THIRD_COLUMN));
        txtFourth.setText(map.get(Constantes_colunas.FOURTH_COLUMN));

        return convertView;
    }

}