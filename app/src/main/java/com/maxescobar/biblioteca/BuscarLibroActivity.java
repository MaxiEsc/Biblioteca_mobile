package com.maxescobar.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maxescobar.biblioteca.controladores.implementacion.ImpLibroBD;
import com.maxescobar.biblioteca.modelos.Libro;
import com.maxescobar.biblioteca.utils.Utils;

public class BuscarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txt_Titulo;
    Button btn_Buscar;
    ImpLibroBD libroBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_libro);

        inicio();
    }

    private void inicio() {
        context = getApplicationContext();

        txt_Titulo = (EditText) findViewById(R.id.bus_texto_titulo);
        btn_Buscar = (Button) findViewById(R.id.btnbuscar);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bus_btnbuscar) {
            String titulo = txt_Titulo.getText().toString();
            Libro libro = buscarLibro(titulo);
            if (libro != null) {
                Bundle bolsa = new Bundle();
                bolsa.putInt(Utils.KEY_IDLIBRO, libro.getIdLibro());
                bolsa.putInt(Utils.KEY_ANIO_PUBLICACION, libro.getAnio_publicacion());
                bolsa.putDouble(Utils.KEY_PRECIO, libro.getPrecio());
                bolsa.putString(Utils.KEY_TITULO, libro.getTitulo());
                bolsa.putString(Utils.KEY_SUBTITULO, libro.getSubtitulo());
                bolsa.putString(Utils.KEY_AUTOR, libro.getAutor());
                bolsa.putString(Utils.KEY_ISBN, libro.getIsbn());

                Intent i = new Intent(context, GestionarLibroActivity.class);
                i.putExtras(bolsa);
                startActivity(i);
            } else {
                Toast.makeText(context, Utils.LIBRO_NO_EXISTE, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Libro buscarLibro(String titulo) {
        libroBD = new ImpLibroBD(context, Utils.NOMBRE_TABLA_DB,null, 1);
        return libroBD.obtenerLibro(titulo);
    }
}