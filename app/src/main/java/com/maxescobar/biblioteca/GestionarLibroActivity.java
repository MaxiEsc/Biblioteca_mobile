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

public class GestionarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText titulo, subtitulo, isbn, anioPublicacion, precio, autor;
    int id;
    ImpLibroBD libroBD;
    Button btnGuardar, btnActualizar, btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_libro);
        inicio();
    }

    private void inicio() {
        context = getApplication();
        titulo = (EditText) findViewById(R.id.ges_texto_titulo);
        subtitulo = (EditText) findViewById(R.id.ges_texto_subtitulo);
        isbn = (EditText) findViewById(R.id.ges_texto_isbn);
        anioPublicacion = (EditText) findViewById(R.id.ges_texto_anio_publicacion);
        precio = (EditText) findViewById(R.id.ges_texto_precio);
        autor = (EditText) findViewById(R.id.ges_texto_autor);
        btnGuardar = (Button) findViewById(R.id.ge_btnguardar);
        btnBorrar = (Button) findViewById(R.id.ge_btnborrar);
        btnActualizar = (Button) findViewById(R.id.ge_btnactualizar);

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        id = bolsa.getInt(Utils.KEY_IDLIBRO);
        if (id != 0){
            titulo.setText(bolsa.getString(Utils.KEY_TITULO));
            subtitulo.setText(bolsa.getString(Utils.KEY_SUBTITULO));
            autor.setText(bolsa.getString(Utils.KEY_AUTOR));
            isbn.setText(bolsa.getString(Utils.KEY_ISBN));
            anioPublicacion.setText(bolsa.getInt(Utils.KEY_ANIO_PUBLICACION) + "");
            precio.setText(bolsa.getDouble(Utils.KEY_PRECIO) + "");
            btnGuardar.setEnabled(false);
        }else {
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
        }
    }

    private void limpiarCampos(){
        id = 0;
        titulo.setText("");
        subtitulo.setText("");
        autor.setText("");
        isbn.setText("");
        anioPublicacion.setText("");
        precio.setText("");
    }

    private Libro llenarDatosLibro(){
        Libro libro = new Libro();
        String t_titulo = titulo.getText().toString();
        String t_subtitulo = subtitulo.getText().toString();
        String t_autor = autor.getText().toString();
        String t_isbn = isbn.getText().toString();
        String t_anioPublicacion = anioPublicacion.getText().toString();
        String t_precio = precio.getText().toString();

        libro.setIsbn(t_isbn);
        libro.setTitulo(t_titulo);
        libro.setSubtitulo(t_subtitulo);
        libro.setAutor(t_autor);
        libro.setAnio_publicacion(Integer.parseInt(t_anioPublicacion));
        libro.setPrecio(Float.parseFloat(t_precio));

        return libro;
    }

    private void guardar(){
        libroBD = new ImpLibroBD(context,Utils.NOMBRE_TABLA_DB,null,1);
        Libro libro = llenarDatosLibro();
        if (id == 0){
            libroBD.agregarLibro(libro);
            limpiarCampos();
            Toast.makeText(context,Utils.GUARDAR,Toast.LENGTH_SHORT).show();
        }else {
            libroBD.actualizarLibro(id,libro);
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            limpiarCampos();
            Toast.makeText(context,Utils.ACTUALIZAR,Toast.LENGTH_SHORT).show();
        }
    }

    private void borrar(){
        libroBD = new ImpLibroBD(context,Utils.NOMBRE_TABLA_DB,null,1);
        Libro libro = llenarDatosLibro();
        if (id == 0){
            Toast.makeText(context,Utils.BORRAR_INEXISTENTE,Toast.LENGTH_SHORT).show();
        }else {
            libroBD.borrarLibro(id);
            limpiarCampos();
            btnGuardar.setEnabled(true);
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            Toast.makeText(context,Utils.BORRAR,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ge_btnborrar){
            borrar();
        }
        if (v.getId() == R.id.ge_btnactualizar){
            guardar();
        }
        if (v.getId() == R.id.ge_btnguardar){
            guardar();
        }
    }
}