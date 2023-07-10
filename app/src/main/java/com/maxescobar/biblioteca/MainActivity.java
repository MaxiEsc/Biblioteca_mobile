package com.maxescobar.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maxescobar.biblioteca.utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btnListar, btnRegistrar, btnBuscar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicio();
    }

    private void inicio() {
        context = getApplicationContext();
        btnBuscar =(Button) findViewById(R.id.btnbuscar);
        btnRegistrar =(Button) findViewById(R.id.btnregistrar);
        btnListar =(Button) findViewById(R.id.btnlistar);
    }

    //Metodo con la interfaz  View.OnClickListener
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnbuscar) {
            Toast.makeText(context, Utils.BUSCAR,Toast.LENGTH_SHORT).show();
            Intent intBuscar = new Intent(context, BuscarLibroActivity.class);
            startActivity(intBuscar);
        }
        if (v.getId() == R.id.btnlistar) {
            Toast.makeText(context, Utils.LISTANDO,Toast.LENGTH_SHORT).show();
            Intent intListar = new Intent(context, ListadoLibrosActivity.class);
            startActivity(intListar);
        }
        if (v.getId() == R.id.btnregistrar) {
            Toast.makeText(context, Utils.REGISTRAR,Toast.LENGTH_SHORT).show();
            Intent intRegistrar = new Intent(context,GestionarLibroActivity.class);
            Bundle bolsa = new Bundle();
            bolsa.putInt(Utils.KEY_IDLIBRO, 0);
            intRegistrar.putExtras(bolsa);
            startActivity(intRegistrar);
        }

    }
}