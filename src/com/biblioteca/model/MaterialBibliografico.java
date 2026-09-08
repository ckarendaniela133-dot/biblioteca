package com.biblioteca.model;

public abstract class MaterialBibliografico {
    protected String titulo;
    protected String autor;
    protected String codigo;
    protected int anioPublicacion;

    public MaterialBibliografico(String titulo, String autor, String codigo, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCodigo() { return codigo; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%d)", getTipo(), titulo, autor, anioPublicacion);
    }

}


