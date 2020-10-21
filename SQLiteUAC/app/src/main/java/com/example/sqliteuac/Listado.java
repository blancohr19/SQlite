package com.example.sqliteuac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView = findViewById(R.id.ListView);

        CargarListado();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(Listado.this,listado.get(position),Toast.LENGTH_LONG).show();
                int id = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre_es=listado.get(position).split(" ")[1];
                String programa_es=listado.get(position).split(" ")[2];
                Intent intent = new Intent(Listado.this,Modificar_Eliminar.class);
                intent.putExtra("Codigo",id);
                intent.putExtra("Nombre",nombre_es);
                intent.putExtra("Programa",programa_es);
                startActivity(intent);
            }
        });


        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void CargarListado(){
        listado = ListaEstudiantes();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String>ListaEstudiantes(){
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Codigo,Nombre,Programa from ESTUDIANTES";
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToNext()){

            do{
                /*String linea = "Codigo: "+c.getInt(0)+"\nNombre: "+c.getString(1)+"\nPrograma: "+c.getString(2); */
                String linea = c.getInt(0)+" "+c.getString(1)+" "+c.getString(2);
                datos.add(linea);
            }while (c.moveToNext());

            }
        db.close();
        return datos;
        }
    }

