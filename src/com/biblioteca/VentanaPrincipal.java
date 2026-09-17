package com.biblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal  extends  JFrame {
    private Biblioteca biblioteca;

    private DefaultTableModel modelTabla;
    private JTable tablaLibros;

    private JTextField txtTitulo, txtAutor, txtCodigo, txtAnio, txtCopias, txtFiltroAutor;
    private JComboBox<String> comboGenero;

    public VentanaPrincipal(){
        biblioteca = new Biblioteca();

        setTitle("Biblioteca Municipal San Rafael");
        setSize(850, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        construirFormulario();
        construirTabla();
        construirBotones();
    }

    private void construirFormulario(){
        JPanel panelFormulario = new JPanel(new GridLayout(2, 6, 5, 5));
        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtCodigo = new JTextField();
        comboGenero = new JComboBox<>(Biblioteca.GENEROS_PERMITIDOS);
        txtAnio = new JTextField();
        txtCopias = new JTextField();

        panelFormulario.add(new JLabel("Titulo"));
        panelFormulario.add(new JLabel("Autor"));
        panelFormulario.add(new JLabel("Codigo/ISBN"));
        panelFormulario.add(new JLabel("Genero"));
        panelFormulario.add(new JLabel("Anio"));
        panelFormulario.add(new JLabel("Copias"));

        panelFormulario.add(txtTitulo);
        panelFormulario.add(txtAutor);
        panelFormulario.add(txtCodigo);
        panelFormulario.add(comboGenero);
        panelFormulario.add(txtAnio);
        panelFormulario.add(txtCopias);

        add(panelFormulario, BorderLayout.NORTH);
    }
    private void construirTabla(){
        String[] columnas = {"Titulo", "Autor", "Codigo", "Genero", "Anio", "Copias"};

        modelTabla = new DefaultTableModel(columnas, 0){
            @Override public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tablaLibros = new JTable(modelTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaLibros);
        add(scrollTabla,BorderLayout.CENTER);
    }
    private void construirBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnAgregar = new JButton("Agregar libro");
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        JButton btnFiltrar = new JButton("Filtrar por autor");
        JButton btnMostrarTodos = new JButton("Mostrar todos");

        txtFiltroAutor = new JTextField(15);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(new JLabel("   Autor a filtrar:"));
        panelBotones.add(txtFiltroAutor);
        panelBotones.add(btnFiltrar);
        panelBotones.add(btnMostrarTodos);

        add(panelBotones, BorderLayout.SOUTH);


        btnAgregar.addActionListener(e -> agregarLibro());
        btnEliminar.addActionListener(e -> eliminarLibroSeleccionado());
        btnFiltrar.addActionListener(e -> filtrarPorAutor());
        btnMostrarTodos.addActionListener(e -> actualizarTabla(biblioteca.obtenerTodos()));
    }

    private void agregarLibro() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();
        String codigo = txtCodigo.getText().trim();
        String genero = (String) comboGenero.getSelectedItem();
        String anioTexto = txtAnio.getText().trim();
        String copiasTexto = txtCopias.getText().trim();


        if (titulo.isEmpty() || autor.isEmpty() || codigo.isEmpty()
                || anioTexto.isEmpty() || copiasTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios.",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int anio;
        int copias;
        try {
            anio = Integer.parseInt(anioTexto);
            copias = Integer.parseInt(copiasTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Año y copias deben ser valores numéricos.",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int anioActual = java.time.Year.now().getValue();
        if (anio > anioActual) {
            JOptionPane.showMessageDialog(this,
                    "El año no puede ser mayor a " + anioActual + ".",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (copias < 0) {
            JOptionPane.showMessageDialog(this,
                    "Las copias disponibles no pueden ser negativas.",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Libro nuevo = new Libro(titulo, autor, codigo, genero, anio, copias);
            biblioteca.agregarLibro(nuevo);
            actualizarTabla(biblioteca.obtenerTodos());
            limpiarFormulario();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Código duplicado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarLibroSeleccionado() {
       // tablaLibros es un objeto de tipo JTable. Esa clase, definida en el paquete javax.swing,
        // ya trae incorporados un montón de métodos listos para usar — entre ellos getSelectedRow().
        int fila = tablaLibros.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona primero un libro en la tabla.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
//   lee el valor de una celda que viene en la variable fila
        String codigo = (String) modelTabla.getValueAt(fila, 0);

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar el libro con código " + codigo + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            biblioteca.eliminarLibro(codigo);
            actualizarTabla(biblioteca.obtenerTodos());
        }
    }

    private void filtrarPorAutor() {
        String autor = txtFiltroAutor.getText().trim();
        if (autor.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Escribe un autor en el campo de filtro.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ArrayList<Libro> filtrados = biblioteca.filtrarPorAutor(autor);
        actualizarTabla(filtrados);
    }

    private void actualizarTabla(ArrayList<Libro> lista) {
        modelTabla.setRowCount(0);
        for (Libro libro : lista) {
            Object[] fila = {
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.getCodigo(),
                    libro.getGenero(),
                    libro.getAnioPublicacion(),
                    libro.getCopiasDisponibles()
            };
            modelTabla.addRow(fila);
        }
    }

    private void limpiarFormulario() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtCodigo.setText("");
        txtAnio.setText("");
        txtCopias.setText("");
    }
}
