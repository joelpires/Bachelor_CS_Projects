package com.Opti;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.Opti.R;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.Opti.Constantes_colunas.FOURTH_COLUMN;
import static com.Opti.Constantes_colunas.SECOND_COLUMN;
import static com.Opti.Constantes_colunas.THIRD_COLUMN;
import static com.Opti.Constantes_colunas.FIRST_COLUMN;

public class Inventario extends ListActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private Spinner menuSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        ListView listView = (ListView) findViewById(android.R.id.list);

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        menuSpin = (Spinner) findViewById(R.id.menuSpinner);
        populateMenu();

        HashMap<String, String> temp = new HashMap<>();
        temp.put(FIRST_COLUMN, "Óculos Graduados");
        temp.put(SECOND_COLUMN, "1");
        temp.put(THIRD_COLUMN, "100");
        temp.put(FOURTH_COLUMN, "22-10-2018");
        list.add(temp);

        HashMap<String, String> temp2 = new HashMap<>();
        temp2.put(FIRST_COLUMN, "Lentes de Contacto");
        temp2.put(SECOND_COLUMN, "30");
        temp2.put(THIRD_COLUMN, "35");
        temp2.put(FOURTH_COLUMN, "N/A");
        list.add(temp2);

        HashMap<String, String> temp3 = new HashMap<>();
        temp3.put(FIRST_COLUMN, "Óculos de Sol c/ Graduação");
        temp3.put(SECOND_COLUMN, "1");
        temp3.put(THIRD_COLUMN, "110");
        temp3.put(FOURTH_COLUMN, "22-10-2018");
        list.add(temp3);

        Adaptador_Lista adapter = new Adaptador_Lista(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                int pos = position + 1;
                Toast.makeText(Inventario.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
            }

        });


    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/

    private void populateMenu() {
        List<String> opcoesMenu = new ArrayList<>();
        opcoesMenu.add("Inventário");
        opcoesMenu.add("Home");
        opcoesMenu.add("Calendário");


        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, opcoesMenu);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuSpin.setAdapter(menuAdapter);

        menuSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String op = menuSpin.getSelectedItem().toString();
                switch (op){
                    case "Home":
                        Intent cal = new Intent(Inventario.this,HomePage.class);
                        startActivity(cal);
                        break;

                    case "Calendário":
                        Intent inv = new Intent(Inventario.this,Calendario.class);
                        startActivity(inv);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                menuSpin.setSelection(1);
            }
        });
    }
}
