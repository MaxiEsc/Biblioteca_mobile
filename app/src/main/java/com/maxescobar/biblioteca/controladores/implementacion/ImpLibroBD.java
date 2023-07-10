package com.maxescobar.biblioteca.controladores.implementacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.maxescobar.biblioteca.controladores.interfaz.IntLibroBD;
import com.maxescobar.biblioteca.modelos.Libro;
import com.maxescobar.biblioteca.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ImpLibroBD extends SQLiteOpenHelper implements IntLibroBD {

    Context entorno;
    private List<Libro> listaLibros = new ArrayList<>();

    public ImpLibroBD(@Nullable Context c, @Nullable String nombre,
                       @Nullable SQLiteDatabase.CursorFactory cursor, int version){
        super(c,nombre,cursor,version);
        this.entorno = c;
    }

    @Override
    public void onCreate(SQLiteDatabase p) {
        p.execSQL(Utils.CREAR_TABLA);
        p.execSQL(Utils.INSERT_PRUEBA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase p, int oldVersion, int newVersion) {
        p.execSQL(Utils.ELIMINAR_TABLA);
        p.execSQL(Utils.CREAR_TABLA);
        p.execSQL(Utils.INSERT_PRUEBA);
    }

    @Override
    public Libro obtenerLibro(int id) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor c = bd.rawQuery(Utils.selectTabla(id), null);
        try {
            if (c.moveToNext()) {
                return extraerLibro(c);
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d(Utils.TAG, Utils.ERROR_LIBRO_ID + e.getMessage());
        } finally {
            if (c != null) {
                c.close();
                bd.close();
            }
        }
        return null;
    }

    @Override
    public Libro obtenerLibro(String titulo) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor c = bd.rawQuery(Utils.selectTabla(titulo), null);
        try {
            if (c.moveToNext()) {
                return extraerLibro(c);
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d(Utils.TAG, Utils.ERROR_LIBRO_TITULO + e.getMessage());
        } finally {
            if (c != null) {
                c.close();
                bd.close();
            }
        }
        return null;
    }

    @Override
    public List<Libro> listaLibros() {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor c = bd.rawQuery(Utils.SELECT_TABLA_LISTA, null);

        if (c.moveToNext()) {
            do {
                listaLibros.add(
                        new Libro(c.getInt(0),
                                c.getInt(1),
                                c.getFloat(2),
                                c.getString(3),
                                c.getString(4),
                                c.getString(5),
                                c.getString(6))
                );

            } while (c.moveToNext());
        }
        c.close();
        bd.close();
        return listaLibros;
    }

    @Override
    public void agregarLibro(Libro libro) {
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Utils.KEY_ANIO_PUBLICACION, libro.getAnio_publicacion());
        valores.put(Utils.KEY_PRECIO, libro.getPrecio());
        valores.put(Utils.KEY_TITULO, libro.getTitulo());
        valores.put(Utils.KEY_SUBTITULO, libro.getSubtitulo());
        valores.put(Utils.KEY_AUTOR, libro.getAutor());
        valores.put(Utils.KEY_ISBN, libro.getIsbn());

        bd.insert(Utils.NOMBRE_TABLA, null, valores);
        bd.close();
    }

    @Override
    public void actualizarLibro(int id, Libro libro) {
        SQLiteDatabase bd = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        ContentValues valores = new ContentValues();

        valores.put(Utils.KEY_ANIO_PUBLICACION, libro.getAnio_publicacion());
        valores.put(Utils.KEY_PRECIO, libro.getPrecio());
        valores.put(Utils.KEY_TITULO, libro.getTitulo());
        valores.put(Utils.KEY_SUBTITULO, libro.getSubtitulo());
        valores.put(Utils.KEY_AUTOR, libro.getAutor());
        valores.put(Utils.KEY_ISBN, libro.getIsbn());

        bd.update(Utils.NOMBRE_TABLA, valores, Utils.KEY_IDLIBRO + Utils.TO_EQUAL , parametros);
        bd.close();
    }

    @Override
    public void borrarLibro(int id) {
        SQLiteDatabase bd = getWritableDatabase();
        String[] parametros = {String.valueOf(id)};
        bd.delete(Utils.NOMBRE_TABLA, Utils.KEY_IDLIBRO + Utils.TO_EQUAL, parametros);
        bd.close();
    }

    //metodos privados
    private Libro extraerLibro(Cursor c) {
        Libro libro = new Libro();
        libro.setIdLibro(c.getInt(0));
        libro.setAnio_publicacion(c.getInt(1));
        libro.setPrecio(c.getFloat(2));
        libro.setTitulo(c.getString(3));
        libro.setSubtitulo(c.getString(4));
        libro.setAutor(c.getString(5));
        libro.setIsbn(c.getString(6));
        return libro;
    }
}
