package com.maxescobar.biblioteca.modelos;

import java.util.Objects;

public class Libro {

    private int idLibro;
    private int anio_publicacion;
    private double precio;
    private String titulo, subtitulo, autor, isbn;

    public Libro() {
    }

    public Libro(int idLibro, int anio_publicacion, double precio, String titulo, String subtitulo, String autor, String isbn) {
        this.idLibro = idLibro;
        this.anio_publicacion = anio_publicacion;
        this.precio = precio;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.autor = autor;
        this.isbn = isbn;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return idLibro == libro.idLibro && anio_publicacion == libro.anio_publicacion && Double.compare(libro.precio, precio) == 0 && Objects.equals(titulo, libro.titulo) && Objects.equals(subtitulo, libro.subtitulo) && Objects.equals(autor, libro.autor) && Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro, anio_publicacion, precio, titulo, subtitulo, autor, isbn);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", anio_publicacion=" + anio_publicacion +
                ", precio=" + precio +
                ", titulo='" + titulo + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
