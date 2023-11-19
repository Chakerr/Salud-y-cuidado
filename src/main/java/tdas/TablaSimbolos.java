/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdas;

/**
 * La clase BinaySearchST implementa una tabla de búsqueda binaria ordenada
 * utilizando una estructura de datos.
 *
 * @author luis Gabriel Romero
 * @author Felipe Triviño
 * @author Tomas David Vera
 * @param <Key> La clave asociada para la tabla de busqueda.
 * @param <Value> El valor asociado a las claves.
 */
public class TablaSimbolos<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;

    /**
     * Constructor que inicializa una nueva tabla de búsqueda ordenada.
     *
     */
    public TablaSimbolos() {
        keys = (Key[]) new Comparable[1];
        vals = (Value[]) new Object[1];
    }

    /**
     * Retorna el número de pares clave con su valor, almacenados en la tabla de
     * busqueda
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Retorna el numero de claves en un rango especifico
     *
     * @param lo Clave mas baja del rango
     * @param hi clave mas alta del rango
     * @return El numero de claves en el rango, incluyendo la clave mas alta y
     * la clave mas baja.
     */
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Comprueba si la tabla de búsqueda esta vacia.
     *
     * @return verdadero si la tabla de busqueda esta vacia, falso de lo
     * contrario.
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Comprueba si la tabla de busqueda contiene una clave en especifico.
     *
     * @param key clave que se desea verificar
     * @return verdadero si se encuentra la clave en la tabla de busqueda, falso
     * de caso contrario.
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Redimensiona ta tabla de busqueda al tamaño especificado
     *
     * @param cap El nuevo tamaño de la tabla de busqueda.
     */
    private void resize(int cap) {
        Key auxK[] = (Key[]) new Comparable[cap];
        Value auxV[] = (Value[]) new Object[cap];
        for (int i = 0; i < N; i++) {
            auxK[i] = keys[i];
            auxV[i] = vals[i];
        }
        keys = auxK;
        vals = auxV;
    }

    /**
     * Obtiene el valor asociado a una clave.
     *
     * @param key clave para la cual se desea obtener el valor
     * @return el valor asociado a la clave.
     */
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    /**
     * Calcula la posicion de una clave en la tabla de busqueda.
     *
     * @param key la clave para la que se desea determinar el rango.
     * @return la posicion de la clave en la tabla de busqueda.
     */
    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        return rank(key, lo, hi);
    }

    /**
     * Calcula el rango de una clave en la tabla de busqueda dentro del rango
     * especificado.
     *
     * @param key La clave para la que se desea determinar el rango
     * @param lo La posicion mas baja del rango.
     * @param hi la posicion mas alta del rango.
     * @return la posicion de la clave en el rango especificado
     */
    private int rank(Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    /**
     * corre una posicion a la izquierda todos los datos a partir de una
     * posicion especifica
     *
     * @param pos la posicion desde donde se quiere correr a la izquierda
     */
    private void toLeft(int index) {
        for (int i = index; i < N; i++) {
            keys[i] = keys[i + 1];
            vals[i] = vals[i + 1];
        }
    }

    /**
     * Mueve todos los datos a la derecha.
     *
     * @param index La posicion desde donde se quiere mover a la derecha.
     */
    public void toRight(int index) {
        for (int i = N; i > index; i--) {
            keys[i] = keys[i - 1];
            vals[i] = vals[i - 1];
        }
    }

    /**
     * Asocia una clave con un valor, Si la clave existe se actualiza el valor.
     *
     * @param key La clave para asociar
     * @param val El valor a asociar con una clave.
     */
    public void put(Key key, Value val) {
        if (N == keys.length) {
            resize(2 * keys.length);
        }
        int i = rank(key);
        //Cuando esta en el arreglo
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        //Cuando no esta en el arreglo
        toRight(i);
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /**
     * Elimina la clave y su valor asociado de la tabla de busqueda.
     *
     * @param key La clave que se desea eliminar
     */
    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        if (N > 0 && N == keys.length / 4) {
            resize(keys.length / 2);
        }
        int i = rank(key);
        if (i == N || !contains(key)) {
            return;
        }
        toLeft(i);
        N--;
        keys[N] = null;
        vals[N] = null;
    }

    /**
     * Elimina la minima clave y su valor asociado en la tabla de busqueda.
     */
    public void deleteMin() {
        delete(min());
    }

    /**
     * Elimina la maxima clave y su valor asociado en la tabla de busqueda.
     */
    public void deleteMax() {
        delete(max());
    }

    /**
     * Obtiene la clave minima en la tabla de busqueda.
     *
     * @return La clave minima de la tabla de busqueda.
     */
    public Key min() {
        return keys[0];
    }

    /**
     * Obtiene la clave maxima en la tabla de busqueda.
     *
     * @return La clave maxima de la tabla de busqueda.
     */
    public Key max() {
        return keys[N - 1];
    }

    /**
     * Obtiene la clave en la posicion K en el arreglo ordenado.
     *
     * @param k Posicion de la clave que se desea obtener.
     * @return La clave en la posicion K
     */
    public Key select(int k) {
        return keys[k];
    }

    /**
     * Encuentra la clave más pequeña en la tabla de busqueda que es mayor o
     * igual a la clave especificada.
     *
     * @param key La clave de referencia
     * @return La clave más pequeña en la tabla de búsqueda que es mayor o igual
     * a la clave especificada.
     */
    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    /**
     * Encuentra la clave mas grande en la tabla de busqueda que es menor o
     * igual a la clave especificada
     *
     * @param key La Clave referencia
     * @return La clave más grande en la tabla de búsqueda que es menor o igual
     * a la clave especificada.
     */
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            return keys[i];
        } else {
            return keys[i - 1];
        }
    }

//    /**
//     * Retorna un iterable de claves en el rango minimo y maximo, incluyendo
//     * ambas claves.
//     *
//     * @param lo La clave mas baja del rango.
//     * @param hi La clave mas alta del rango.
//     * @return Un iterable de claves en el rango especificado.
//     */
//    public Iterable<Key> keys(Key lo, Key hi) {
//        Cola<Key> q = new Cola<>();
//        for (int i = rank(lo); i < rank(hi); i++) {
//            q.enqueue(keys[i]);
//        }
//        if (contains(hi)) {
//            q.enqueue(keys[rank(hi)]);
//        }
//        return q;
//    }
    
    public Iterable<Key> keys(Key lo, Key hi) {
        Lista<Key> q = new Lista<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.adicionarUltimo(keys[i]);
        }
        if (contains(hi)) {
            q.adicionarUltimo(keys[rank(hi)]);
        }
        return q;
    }
}
