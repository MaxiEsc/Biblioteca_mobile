package com.maxescobar.biblioteca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.maxescobar.biblioteca.controladores.implementacion.ImpLibroBD;
import com.maxescobar.biblioteca.controladores.interfaz.SelectListener;
import com.maxescobar.biblioteca.modelos.Libro;
import com.maxescobar.biblioteca.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ListadoLibrosActivity extends AppCompatActivity implements SelectListener {

    ListView listaLibro;
    ArrayList<String> nombresLibros;
    ArrayList<Integer> idenficadoresLibros;
    ImpLibroBD libroDB;
    Context entorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);
        inicio();
    }

    private void inicio() {
        entorno = this.getApplicationContext();
        libroDB = new ImpLibroBD(entorno, Utils.NOMBRE_TABLA_DB,null,1);
        listaLibro = (ListView) findViewById(R.id.lista_libros);
        llenarListalibro();
    }

    private void llenarListalibro() {
        nombresLibros = new ArrayList<>();
        idenficadoresLibros = new ArrayList<>();

        List<Libro> libros = libroDB.listaLibros();
        for (int i = 0; i < libros.size(); i++){
            Libro l = libros.get(i);
            nombresLibros.add(l.getTitulo());
            idenficadoresLibros.add(l.getIdLibro());
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                entorno, android.R.layout.simple_list_item_1,
                nombresLibros);
        listaLibro.setAdapter(adaptador);
        listaLibro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro libro = libroDB.obtenerLibro(idenficadoresLibros.get(position));
                Bundle bolsa = new Bundle();
                bolsa.putInt(Utils.KEY_IDLIBRO, libro.getIdLibro());
                bolsa.putInt(Utils.KEY_ANIO_PUBLICACION, libro.getAnio_publicacion());
                bolsa.putDouble(Utils.KEY_PRECIO, libro.getPrecio());
                bolsa.putString(Utils.KEY_TITULO, libro.getTitulo());
                bolsa.putString(Utils.KEY_SUBTITULO, libro.getSubtitulo());
                bolsa.putString(Utils.KEY_AUTOR, libro.getAutor());
                bolsa.putString(Utils.KEY_ISBN, libro.getIsbn());

                Intent lista = new Intent(entorno, GestionarLibroActivity.class);
                lista.putExtras(bolsa);
                startActivity(lista);
            }
        });
    }
    @Override
    public void onItemClick(String titulo) {

    }
}