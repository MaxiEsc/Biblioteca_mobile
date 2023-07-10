package com.maxescobar.biblioteca.utils;

import com.maxescobar.biblioteca.modelos.Libro;

public class Utils {
    //cadenas para BD
    public static final String NOMBRE_DB = "LibroBaseDatos";
    public static final String NOMBRE_TABLA = "Libros";
    public static final String NOMBRE_TABLA_DB = "Libros.db";
    public static final String DATOS_TABLA = "( idLibro INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "anio_publicacion INTEGER, " +
            "precio REAL, " +
            "titulo TEXT, " +
            "subtitulo TEXT, " +
            "autor TEXT, " +
            "isbn TEXT )";
    public static final String INSERT_PRUEBA = "INSERT INTO libros VALUES (NULL," +
            "1996, " +
            "1500.50," +
            "'ar', " +
            "'Android con ejemplos'," +
            "'Juan Pedos', " +
            "'2345366736'" +
            ")";
    public static final int VERSION = 1;
    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA + DATOS_TABLA;
    public static final String ELIMINAR_TABLA = "DROP TABLE IF EXISTS " + NOMBRE_TABLA;
    public static final String WHERE = " WHERE ";
    public static final String COMILLAS = "'";
    public static final String ORDEN = " ORDER BY ";
    public static final String ASCENDENTE = " ASC ";
    public static final String TITULO = "titulo";
    public static final String SELECT_TABLA_LISTA = "SELECT * FROM " + NOMBRE_TABLA + ORDEN + TITULO + ASCENDENTE;

    public static String selectTabla(int id) {
        String cadena = "SELECT * FROM " + NOMBRE_TABLA + WHERE;
        if (id > 0) {
            cadena = cadena + KEY_IDLIBRO + EQUAL_ONLY + id;
        } else {
            cadena = null;
        }
        return cadena;
    }

    public static String selectTabla(String titulo) {
        String cadena = "SELECT * FROM " + NOMBRE_TABLA + WHERE;
        cadena = cadena + KEY_TITULO + EQUAL_ONLY + COMILLAS + titulo + COMILLAS;
        return cadena;
    }

    public static final String TO_EQUAL =  " = ?";
    public static final String EQUAL_ONLY =  " = ";
    //Mensajes
    public static final String TAG = "tag";
    public static final String REGISTRAR = "Registrando";
    public static final String LISTANDO = "Listando";
    public static final String BUSCAR = "Buscando";
    public static final String GUARDAR = "Libro Guardado Correctamente";
    public static final String BORRAR = "Libro Eliminado Correctamente";
    public static final String BORRAR_INEXISTENTE = "No es posible borrar el libroEl gran lie";
    public static final String ACTUALIZAR = "Libro Actualizado Correctamente";
    public static final String LIBRO_NO_EXISTE = "No Existe un Libro con ese titulo";
    //Nombre de keys bd
    public static final String KEY_IDLIBRO = "idLibro";
    public static final String KEY_ANIO_PUBLICACION = "anio_publicacion";
    public static final String KEY_PRECIO = "precio";
    public static final String KEY_TITULO = "titulo";
    public static final String KEY_SUBTITULO = "subtitulo";
    public static final String KEY_AUTOR = "autor";
    public static final String KEY_ISBN = "isbn";
    //Mensajes de Errores
    public static final String ERROR_LIBRO_ID = "Error al extraer el Libro con el id. 'Detalles' : ";
    public static final String ERROR_LIBRO_TITULO = "Error al extraer el Libro con el Titulo. 'Detalles': ";


}
