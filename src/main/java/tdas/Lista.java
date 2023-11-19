 /**
 * El paquete estructuras contiene las estructuras de datos
 *
 */
package tdas;

import java.util.Iterator;

/**
 * Clase lista que contiene nodos doblemente enlazados
 *
 * @author luis Gabriel Romero
 * @author Felipe Triviño
 * @author Tomas David Vera
 *
 * @param <Item> Elementos que se almacenaran en la lista.
 */
public class Lista<Item> implements Iterable<Item> {

    //Atributos
    private Node first;
    private Node last;
    int count;

    private class Node {

        Item item;
        Node next;
        Node behind;
    }

    /**
     *
     * Crea una lista vacia
     *
     */
    public Lista() {
        first = null;
        last = null;
        count = 0;
    }

    /**
     * Crea una lista a partir de los elementos de un arreglo
     *
     * @param a Arreglo de elementos agregados a la lista
     */
    public Lista(Item[] a) {
        for (Item a1 : a) {
            adicionarUltimo(a1);
        }
    }

    /**
     * Limpia la lista, eliminando todos los datos de ella.
     */
    public void clear() {
        first = null;
        last = null;
        count = 0;
    }

    /**
     * añade un dato al inicio de la lista
     *
     * @param item dato que se va a añadir
     */
    public void adicionarPrimero(Item item) {
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
        } else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            oldfirst.behind = first;
        }
        count++;
    }

    /**
     * Añade un dato al final de la lista.
     *
     * @param item dato que se va a eliminar
     */
    public void adicionarUltimo(Item item) {
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            oldLast.next = last;
            last.behind = oldLast;
        }
        count++;
    }

    /**
     * Añade en la posicion especificada un dato
     *
     * @param item dato que se va a añadir
     * @param pos Posicion en la que se va a añadir el dato
     */
    public void adicionar(Item item, int pos) {
        if (pos == 0) {
            adicionarPrimero(item);
        } else if (pos >= count) {
            adicionarUltimo(item);
        } else {
            Node x = first;
            for (int i = 0; i < pos; i++) {
                x = x.next;
            }
            Node nuevo = new Node();
            nuevo.item = item;
            nuevo.behind = x.behind;
            nuevo.next = x;
            x.behind.next = nuevo;
            x.behind = nuevo;
            count++;
        }
    }

    /**
     * Elimina el primer dato de la lista
     *
     * @return El dato eliminado.
     */
    public Item eliminarPrimero() {
        if (!isEmpty()) {
            Item temp = first.item;
            first = first.next;
            count--;
            return temp;
        }
        return null;
    }

    /**
     * Elimina el ultimo dato de la lista
     *
     * @return El dato eliminado
     */
    public Item eliminarUltimo() {
        if (!isEmpty()) {
            Item temp = last.item;
            if (last == first) {
                first = null;
                last = null;
            } else {
                last = last.behind;
                last.next = null;

            }
            count--;
            return temp;
        }
        return null;
    }

    /**
     * Elimina de la lista la primera ocurrencia del dato especificado
     *
     * @param item dato que se va a eliminar
     * @return true si encontro el dato y lo elimino, false si no lo encontro
     */
    public boolean remove(Item item) {
        if (!isEmpty()) {
            if (first.item.equals(item)) {
                eliminarPrimero();
                return true;
            } else if (last.item.equals(item)) {
                eliminarUltimo();
                return true;
            } else {
                for (Node x = first; x != null; x = x.next) {
                    if (x.item.equals(item)) {
                        x.behind.next = x.next;
                        x.next.behind = x.behind;
                        count--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Elimina un dato en una posicion especifica
     *
     * @param index posicion del dato a eliminar
     * @return El dato eliminado, si la posicion es invalida retorna null
     */
    public Item remove(int index) {
        if (index == 0) {
            return eliminarPrimero();
        } else if (index >= count - 1) {
            return eliminarUltimo();
        } else {
            int i = 0;
            for (Node x = first; x != null; x = x.next, i++) {
                if (index == i) {
                    Item temp = x.item;
                    x.behind.next = x.next;
                    x.next.behind = x.behind;
                    count--;
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * Encuentra la primera ocurrencia del dato y devuelve su posicion
     *
     * @param item dato a buscar
     * @return la posicion del dato
     */
    public int indexOf(Item item) {
        if (!isEmpty()) {
            int i = 0;
            for (Node x = first; x != null; x = x.next, i++) {
                if (x.item.equals(item)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Encuentra la ultima ocurrencia del dato especificado
     *
     * @param item dato que se va a busacr
     * @return la posicion del dato
     */
    public int lastIndexOf(Item item) {
        if (!isEmpty()) {
            int i = count - 1;
            for (Node x = last; x != null; x = x.behind, i--) {
                if (x.item.equals(item)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Convierte la lista en un array y lo retorna
     *
     * @return El arreglo con los datos de la lista
     */
    public Item[] toArray() {
        Item[] b = (Item[]) new Object[count];
        int i = 0;
        for (Node x = first; x != null; x = x.next, i++) {
            b[i] = x.item;
        }
        return b;
    }

    /**
     * Establece un dato en la posicion especifica
     *
     * @param pos posicion del elemento
     * @param item Nuevo dato
     * @return El elemento almacenado en la posicion especifica
     */
    public Item set(int pos, Item item) {
        if (!isEmpty()) {
            int i = 0;
            for (Node x = first; x != null; x = x.next, i++) {
                if (pos == i) {
                    Item temp = x.item;
                    x.item = item;
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * Comprueba si la lista contiene los datos especificados
     *
     * @param item datos que se va a buscar
     * @return true si la lista contiene el dato especificado, false si sucede
     * lo contrario
     */
    public boolean contains(Item item) {
        if (!isEmpty()) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Obtiene los datos en las posiciones especificadas
     *
     * @param index posicion del dato que se va a obtener
     * @return El dato en la posicion especificada o null si la posicion es
     * invalida
     */
    public Item get(int index) {
        if (!isEmpty()) {
            Node x = first;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    return x.item;
                }
                x = x.next;
            }
        }
        return null;
    }

    /**
     * Actualiza un dato de la lista con el nuevo valor
     *
     * @param item dato que se va a buscar y actualizar
     * @param act El nuevo valor para el dato
     * @return true si se encontro y se actualizo el dato, false si sucede lo
     * contrario
     *
     */
    public boolean actualizarDato(Item item, Item act) {
        for (Node x = first; x != null; x = x.next) {
            if (x.item.equals(item)) {
                x.item = act;
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si la lista está vacía
     *
     * @return true si la lista esta vacía
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Obtiene el tamaño de la lista
     *
     * @return el numero de datos en la lista.
     */
    public int size() {
        return count;
    }

    /**
     * Iterador para recorrer la lista
     *
     * @return un iterador
     */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item temp = current.item;
            current = current.next;
            return temp;
        }
    }
}
