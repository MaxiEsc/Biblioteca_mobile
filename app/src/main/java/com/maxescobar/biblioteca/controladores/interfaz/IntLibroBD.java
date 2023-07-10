package com.maxescobar.biblioteca.controladores.interfaz;

import com.maxescobar.biblioteca.modelos.Libro;

import java.util.List;

public interface IntLibroBD {

    Libro obtenerLibro(int id);
    Libro obtenerLibro(String titulo);

    List<Libro> listaLibros();

    void agregarLibro(Libro libro);
    void actualizarLibro(int id, Libro libro);

    void borrarLibro(int id);
}
