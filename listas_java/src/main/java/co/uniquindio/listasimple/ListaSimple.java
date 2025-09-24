package co.uniquindio.listasimple;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Lista simple genérica
 */
public class ListaSimple<T extends Comparable<T>> implements Iterable<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int size;


    // Constructor
    public ListaSimple() {
        this.cabeza = null;
        this.cola = null;
        this.size = 0;
    }

    public int size() {
        int contador = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    public T get(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (actual != null) {
            if (contador == index) {
                return actual.getDato();
            }
            contador++;
            actual = actual.getSiguiente();
        }
        throw new IndexOutOfBoundsException();
    }

    //Agregar elemento al final
    public void agregarFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        
        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
        }
        
        size++;
    }

    //Agregar elemento al inicio
    public void agregarInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        
        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        }
        
        size++;
    }

    //Agregar en posicion específica
    public void agregarEn(T dato, int indice) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        
        if (indice == 0) {
            agregarInicio(dato);
            return;
        }
        
        if (indice == size) {
            agregarFinal(dato);
            return;
        }
        
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.getSiguiente();
        }
        
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevoNodo);
        
        size++;
    }

    // Elimina el primer elemento de la lista

    public T eliminarPrimero() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        
        T dato = cabeza.getDato();
        cabeza = cabeza.getSiguiente();
        
        if (cabeza == null) {
            cola = null;
        }
        
        size--;
        return dato;
    }

    // Elimina el último elemento de la lista

    public T eliminarUltimo() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }
        
        if (cabeza == cola) {
            return eliminarPrimero();
        }
        
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != cola) {
            actual = actual.getSiguiente();
        }
        
        T dato = cola.getDato();
        cola = actual;
        cola.setSiguiente(null);
        
        size--;
        return dato;
    }

    // Elimina un elemento en una posición específica

    public T eliminarEn(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        
        if (indice == 0) {
            return eliminarPrimero();
        }
        
        if (indice == size - 1) {
            return eliminarUltimo();
        }
        
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.getSiguiente();
        }
        
        T dato = actual.getSiguiente().getDato();
        actual.setSiguiente(actual.getSiguiente().getSiguiente());
        
        size--;
        return dato;
    }


    //obtener el valor de un nodo
    public T obtenerValorNodo(int indice) {
        /*
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }

         */
        if(indiceValido(indice)) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        
        return actual.getDato();
    }

    //Obtener un nodo:
    public Nodo<T> obtenerNodo(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    //Obtener posicion del nodo:
    public int obtenerPosicionNodo(T dato) {
        Nodo<T> actual = cabeza;
        int posicion = 0;

        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }

        // No se encontró el nodo
        return -1;
    }

    public boolean indiceValido(int indice) {
        boolean valido = true;
        if (indice < 0 || indice >= size) {
            valido = false;
        }
        return valido;
    }



    public void modificarNodo(int posicion, T nuevoDato) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }

        actual.setDato(nuevoDato);
    }


   //Verificar si la lista está vacia
    public boolean estaVacia() {
        return cabeza == null;
    }

    //tamaño lista
    public int tamanio() {
        return size;
    }

    //Vaciar lista
    public void borrarLista() {
        cabeza = null;
        cola = null;
        size = 0;
    }


    public void ordenarLista() {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return;
        }

        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;

            while (actual.getSiguiente() != null) {
                Nodo<T> siguiente = actual.getSiguiente();

                if (actual.getDato().compareTo(siguiente.getDato()) > 0) {
                    // Intercambio de datos
                    T temp = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temp);

                    cambiado = true;
                }

                actual = actual.getSiguiente();
            }
        } while (cambiado);
    }

    public void imprimirLista() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;
            
            @Override
            public boolean hasNext() {
                return actual != null;
            }
            
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = cabeza;
        
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        
        sb.append("]");
        return sb.toString();
    }
}
