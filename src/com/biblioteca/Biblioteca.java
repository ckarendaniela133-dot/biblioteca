package com.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Biblioteca {
    public static final String[] GENEROS_PERMITIDOS = {
            "Novela", "Ciencia", "Historia", "Infantil", "Técnico"
    };

    private ArrayList<Libro> libros;
    private HashMap<String, ArrayList<Libro>> indicePorAutor;
    private HashSet<String> codigosRegistrados;

    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.indicePorAutor = new HashMap<>();
        this.codigosRegistrados = new HashSet<>();
    }

    public void agregarLibro(Libro libro) {
        if (codigosRegistrados.contains(libro.getCodigo())) {
            throw new IllegalArgumentException(
                    "Ya existe un libro con ese código."
            );
        }

        libros.add(libro);

        codigosRegistrados.add(libro.getCodigo());
        indexarPorAutor(libro);
    }

    private void indexarPorAutor(Libro libro) {
        String autorKey = libro.getAutor().trim().toLowerCase();
        ArrayList<Libro> listaAutor = indicePorAutor.get(autorKey);
        if (listaAutor == null) {
            listaAutor = new ArrayList<>();
            indicePorAutor.put(autorKey, listaAutor);
        }
        listaAutor.add(libro);
    }

    public boolean eliminarLibro(String codigo) {
        Libro aEliminar = null;
        for (Libro libro : libros) {
            if (libro.getCodigo().equalsIgnoreCase(codigo)) {
                aEliminar = libro;
                break;
            }
        }

        if (aEliminar == null) {
            return false;
        }

        libros.remove(aEliminar);
        codigosRegistrados.remove(aEliminar.getCodigo());

        String autorKey = aEliminar.getAutor().trim().toLowerCase();
        ArrayList<Libro> listaAutor = indicePorAutor.get(autorKey);
        if (listaAutor != null) {
            listaAutor.remove(aEliminar);
            if (listaAutor.isEmpty()) {
                indicePorAutor.remove(autorKey);
            }
        }

        return true;
    }

    public ArrayList<Libro> filtrarPorAutor(String autor) {
        ArrayList<Libro> resultado = indicePorAutor.get(autor.trim().toLowerCase());
        return resultado != null ? new ArrayList<>(resultado) : new ArrayList<>();
    }

    public ArrayList<Libro> obtenerTodos() {
        return new ArrayList<>(libros);
    }

    public int contarLibros() {
        int total = 0;
        int i = 0;
        while (i < libros.size()) {
            total++;
            i++;
        }
        return total;
    }
}
