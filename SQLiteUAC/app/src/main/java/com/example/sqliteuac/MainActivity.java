package com.example.sqliteuac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, nombre, programa;
    Button guardar, cancelar, listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigo = findViewById(R.id.edtcodigo);
        nombre = findViewById(R.id.edtnombre);
        programa = findViewById(R.id.edtprograma);
        guardar = findViewById(R.id.btnguardar);
        cancelar = findViewById(R.id.btncancelar);
        listado = findViewById(R.id.btnlistado);
        guardar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        listado.setOnClickListener(this);

    }

    private void guardar(int codigo, String nombre, String programa){
        BaseHelper helper = new BaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues c= new ContentValues();
            c.put("Codigo",codigo);
            c.put("Nombre",nombre);
            c.put("Programa",programa);
            db.insert("ESTUDIANTES",null,c);
            db.close();
            Toast.makeText(this,"Estudiante Ingresado",Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            Toast.makeText(this,"Error"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnguardar:
                guardar(Integer.parseInt(codigo.getText().toString()),nombre.getText().toString(),programa.getText().toString());
                codigo.setText("");
                nombre.setText("");
                programa.setText("");
                break;

            case R.id.btnlistado:
                startActivity(new Intent(MainActivity.this,Listado.class));
                break;

            case R.id.btncancelar:
                codigo.setText("");
                nombre.setText("");
                programa.setText("");
                break;
        }
    }
}