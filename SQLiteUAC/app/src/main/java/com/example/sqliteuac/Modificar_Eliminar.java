package com.example.sqliteuac;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar_Eliminar extends AppCompatActivity  {

    EditText nombre, programa;
    Button modificar, borrar;

    int id;
    String nombre_es;
    String programa_es;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__eliminar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle b = getIntent().getExtras();
        if(b!=null){
            id = b.getInt("Codigo");
            nombre_es=b.getString("Nombre");
            programa_es=b.getString("Programa");
        }



        nombre = findViewById(R.id.edtnombre);
        nombre.setText(nombre_es);

        programa = findViewById(R.id.edtprograma);
        programa.setText(programa_es);

        modificar = findViewById(R.id.btnmodificar);

        borrar = findViewById(R.id.btnborrar);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(id,nombre.getText().toString(),programa.getText().toString());
                onBackPressed();
            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(id);
                onBackPressed();
            }
        });
    }



    private void modificar(int codigo, String nombre, String programa ){
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

                String sql= "update ESTUDIANTES set Nombre='"+nombre+"',Programa='"+programa+"' where Codigo="+codigo;
                db.execSQL(sql);
                db.close();

    }


    private void eliminar(int codigo){
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql= "delete from ESTUDIANTES where Codigo="+codigo;
        db.execSQL(sql);
        db.close();

    }


}