**Este trabajo esta hecho por Karen Daniela y Camila u**

**Sistema de Biblioteca Municipal San Rafael**

**Descripción**

Este proyecto consiste en el desarrollo de un sistema de gestión para la Biblioteca Municipal San Rafael, realizado en Java utilizando una interfaz gráfica con Java Swing.
El sistema permite administrar información de diferentes libros de una biblioteca de una manera sencilla. El usuario puede registrar nuevos libros, consultar los libros registrados, eliminarlos y filtrarlos por autor.
Además, el programa cuenta con validaciones para evitar datos incorrectos y códigos de libros repetidos.
El proyecto está organizado principalmente en tres clases: Libro, Biblioteca y VentanaPrincipal. Cada una tiene una función específica dentro del funcionamiento del sistema.

 **Clase Libro**

La clase Libro representa cada libro que se registra en el sistema. Contiene los datos principales de un libro:

* Título.
* Autor.
* Código o ISBN.
* Género.
* Año de publicación.
* Cantidad de copias disponibles.

Los atributos de la clase son privados (private), por lo que se utilizan getters y setters para consultar y modificar la información de cada libro.
También cuenta con un constructor que permite crear un nuevo objeto Libro utilizando todos sus datos.
Además, se utiliza el método toString() para definir una forma organizada de mostrar la información de un libro.

 **Clase Biblioteca**

La clase Biblioteca se encarga de administrar toda la información y la lógica relacionada con los libros.
Para almacenar y organizar los datos se utilizan tres estructuras:

* ArrayList: almacena la lista completa de libros registrados.
* HashSet: almacena los códigos de los libros para evitar que se registren códigos duplicados.
* HashMap: organiza los libros utilizando el autor como clave, permitiendo realizar búsquedas y filtros por autor.

La clase permite:

* Agregar libros.
* Eliminar libros mediante su código.
* Filtrar libros por autor.
* Obtener todos los libros registrados.
* Contar la cantidad de libros.

Al agregar un libro, primero se verifica que su código no exista. Si el código ya está registrado, el sistema genera un error y evita que se duplique.
Cuando se elimina un libro, también se actualiza el ArrayList, el HashSet y el HashMap, manteniendo la información organizada y actualizada.

 **Clase VentanaPrincipal**

La clase VentanaPrincipal corresponde a la interfaz gráfica del programa y hereda de JFrame, utilizando componentes de Java Swing.
La ventana contiene diferentes campos para ingresar la información de los libros, como:

* Título.
* Autor.
* Código.
* Género.
* Año de publicación.
* Número de copias.

También cuenta con una lista desplegable (JComboBox) para seleccionar el género del libro y una tabla (JTable) donde se muestran los libros registrados.
El usuario dispone de botones para realizar diferentes acciones:

* Agregar libro: registra un nuevo libro después de validar la información.
* Eliminar seleccionado: permite eliminar el libro seleccionado en la tabla.
* Filtrar por autor: muestra únicamente los libros pertenecientes al autor indicado.
* Mostrar todos: vuelve a mostrar todos los libros registrados.

 **Validaciones**

El sistema cuenta con diferentes validaciones para evitar errores en los datos ingresados.
Antes de registrar un libro se verifica que los campos obligatorios no estén vacíos. También se comprueba que el año y la cantidad de copias sean valores numéricos.

Además:

* El año de publicación no puede ser mayor al año actual.
* La cantidad de copias no puede ser negativa.
* No se permiten códigos de libros duplicados.

Para controlar errores durante la conversión de los datos se utiliza try-catch, especialmente cuando se convierten los valores de texto a números mediante Integer.parseInt().

**Búsqueda y filtrado**

El sistema permite buscar libros por autor. Para facilitar las búsquedas, los nombres de los autores se organizan utilizando trim() y toLowerCase().
trim() permite eliminar espacios innecesarios al principio y al final del texto, mientras que toLowerCase() convierte el texto a minúsculas para que las búsquedas sean más uniformes.
También se utiliza equalsIgnoreCase() en la eliminación para comparar códigos sin tener en cuenta si están escritos en mayúsculas o minúsculas.

 **Organización del proyecto**

El funcionamiento general del programa se puede resumir de la siguiente manera:

text
Usuario
   ↓
VentanaPrincipal
   ↓
Ingresa y valida los datos
   ↓
Se crea un objeto Libro
   ↓
Biblioteca administra el libro
   ↓
ArrayList + HashSet + HashMap
   ↓
La información se muestra en la JTable


De esta manera, cada clase tiene una responsabilidad diferente:

*Libro: representa la información de cada libro.
*Biblioteca: administra los libros y contiene la lógica del sistema.
*VentanaPrincipal: permite al usuario interactuar con el sistema mediante una interfaz gráfica.

**utilizamos:**

* Java.
* Java Swing.
* ArrayList.
* HashMap.
* HashSet.
* JFrame.
* JTable.
* JTextField.
* JComboBox.
* JButton.
* JOptionPane.

## 🎯 Objetivo

El objetivo del proyecto es desarrollar una aplicación que permita gestionar de forma organizada los libros de una biblioteca, aplicando conceptos de *programación orientada a objetos, estructuras de datos, encapsulamiento, validaciones, manejo de errores e interfaces gráficas en Java
