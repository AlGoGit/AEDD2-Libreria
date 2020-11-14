/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UT0607_2020;

/**
 *
 * @author AlGo
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;
    public static final int METODO_CLASIFICACION_HEAPSORT = 5;
    public static final int METODO_CLASIFICACION_SELECCION = 6;
    public static final int METODO_CLASIFICACION_BIN = 7;
    public static final int METODO_CLASIFICACION_RADIX = 8;
    public static final int METODO_CLASIFICACION_CUENTAS = 9;

    /**
     * Punto de entrada al clasificador
     *
     * @param datosParaClasificar
     * @param metodoClasificacion
     * @param cascara
     * @return Un vector del tam. solicitado, ordenado por el algoritmo
     * solicitado
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion, boolean cascara) {
        if (cascara) {
            return null;
        }
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELL:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_BURBUJA:
                return ordenarPorBurbuja(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            case METODO_CLASIFICACION_HEAPSORT:
                return ordenarPorHeapSort(datosParaClasificar);
            case METODO_CLASIFICACION_SELECCION:
                return ordenarPorHeapSort(datosParaClasificar);
            case METODO_CLASIFICACION_BIN:
                return ordenarPorHeapSort(datosParaClasificar);
            case METODO_CLASIFICACION_RADIX:
                return ordenarPorHeapSort(datosParaClasificar);
            case METODO_CLASIFICACION_CUENTAS:
                return ordenarPorHeapSort(datosParaClasificar);
            default:
                System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * Verifia que el vector esté efectivamente ordenado en orden Ascendente
     *
     * @param vectorAVerificar
     * @return boolean - true if sorted, false if not.
     */
    public boolean estaOrdenado(int[] vectorAVerificar) {
        for (int i = 0; i < vectorAVerificar.length - 1; i++) {
            if (vectorAVerificar[i] > vectorAVerificar[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1 - Ordenar por Inserción Directa
     *
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
                    intercambiar(datosParaClasificar, j, j + 1);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }

    /**
     * 2 - Ordenar por Shellsort
     *
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 11, 3, 1};
        for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                        }
                        j -= inc;
                    }
                }
            }
        }
        return datosParaClasificar;
    }

    /**
     * 3 - Ordenar por Burbuja
     *
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    /**
     * 4 - Ordenar por Quicksort
     *
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;
        int posicionPivote = encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            int pivote = entrada[posicionPivote];
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda++;
                }

                while ((pivote <= entrada[derecha]) && (derecha > i)) {
                    derecha--;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            if (i < derecha) {
                quicksort(entrada, i, derecha);
            }
            if (izquierda < j) {
                quicksort(entrada, izquierda, j);
            }
        }
    }

    // Pivote - Mayor de los dos primeros valores
    public int encuentraPivote2(int i, int j, int[] arr) {
        if (arr[i] < arr[i + 1]) {
            return i + 1;
        } else {
            return i;
        }
    }

    // Pivote - Valor en la posición del medio del array
    public int encuentraPivote(int i, int j, int[] arr) {
        int vMedio = i + (j - i) / 2;
        if (arr[i] < arr[vMedio]) {
            if (arr[vMedio] < arr[j]) {
                return vMedio;
            } else if (arr[i] < arr[j]) {
                return j;
            } else {
                return i;
            }
        } else {
            if (arr[i] < arr[j]) {
                return i;
            } else if (arr[vMedio] < arr[j]) {
                return j;
            } else {
                return vMedio;
            }
        }
    }

    /**
     * 5 - Ordenar pot Heapsort
     *
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
        for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
            armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
        }
        for (int i = datosParaClasificar.length - 1; i > 0; i--) {
            intercambiar(datosParaClasificar, 0, i);
            armaHeap(datosParaClasificar, 0, i - 1);
        }
        return datosParaClasificar;
    }

    private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
        if (primero < ultimo) {
            int r = primero;
            while (r <= ultimo / 2) {
                if (ultimo == 2 * r) { //r tiene un hijo solo
                    if (datosParaClasificar[r] < datosParaClasificar[r * 2]) {
                        intercambiar(datosParaClasificar, r, 2 * r);
                    }
                    r = ultimo;
                } else { //r tiene 2 hijos
                    int posicionIntercambio = 0;
                    if (datosParaClasificar[2 * r] > datosParaClasificar[2 * r + 1]) {
                        posicionIntercambio = 2 * r;
                    } else {
                        posicionIntercambio = 2 * r + 1;
                    }
                    if (datosParaClasificar[r] < datosParaClasificar[posicionIntercambio]) {
                        intercambiar(datosParaClasificar, r, posicionIntercambio);
                        r = posicionIntercambio;
                    } else {
                        r = ultimo;
                    }
                }
            }
        }
    }

    /**
     * 6 - Ordenar por Selección Directa
     *
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorSeleccion(int[] datosParaClasificar) {
        for (int i = 0; i < datosParaClasificar.length - 1; i++) {
            int indiceMenor = i;
            int claveMenor = datosParaClasificar[i];
            for (int j = i + 1; j < datosParaClasificar.length; j++) {
                if (datosParaClasificar[j] < claveMenor) {
                    indiceMenor = j;
                    claveMenor = datosParaClasificar[j];
                }
            }
            intercambiar(datosParaClasificar, i, indiceMenor);
        }
        return datosParaClasificar;
    }

    /**
     * 7 - Ordenar por Binsort
     *
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorBinsort(int[] datosParaClasificar) {
        int max = maximo(datosParaClasificar);
        int cifrasMax = numeroDeCifras(max);
        return binsort(datosParaClasificar, cifrasMax, false);
    }

    private int[] binsort(int[] datosParaClasificar, int cifrasMax, boolean radix) {
        ArrayList<Integer>[] urnas = new ArrayList[10];
        for (int i = 0; i < urnas.length; i++) {
            urnas[i] = new ArrayList<>();
        }
        for (int i = 0; i < datosParaClasificar.length; i++) {
            urnas[digitoEnPosicion(datosParaClasificar[i], cifrasMax)].add(datosParaClasificar[i]);
        }
        int ultimaPosicion = 0;
        for (int i = 0; i < 10; i++) {
            Integer[] urna = urnas[i].toArray(new Integer[urnas[i].size()]);
            int[] urnaOrdenada = new int[urna.length];
            for (int k = 0; k < urna.length; k++) {
                urnaOrdenada[k] = urna[k];
            }
            if (!radix) {
                urnaOrdenada = ordenarPorInsercion(urnaOrdenada);
            }
            urnas[i].clear();
            for (int j = 0; j < urnaOrdenada.length; j++) {
                datosParaClasificar[ultimaPosicion] = urnaOrdenada[j];
                ultimaPosicion++;
            }
        }
        return datosParaClasificar;
    }

    // 
    private int digitoEnPosicion(int n, int pos) {
        int a = n % (int) Math.pow(10, pos);
        int x = (int) Math.pow(10, pos - 1);
        if (a < x) {
            return 0;
        }
        while (a >= 10) {
            a = a / 10;
        }
        return a;
    }

    // Busca y retorna el índice que contiene el valor más alto del conjunto.
    private int maximo(int[] datos) {
        int max = Integer.MIN_VALUE;
        for (int i : datos) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    // 
    private int numeroDeCifras(int i) {
        return (int) (Math.log10(i) + 1);
    }

    /**
     * 8 - Ordenar por Radixsort
     *
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorRadixsort(int[] datosParaClasificar) {
        int max = maximo(datosParaClasificar);
        int cifrasMax = numeroDeCifras(max);
        for (int i = 1; i <= cifrasMax; i++) {
            datosParaClasificar = binsort(datosParaClasificar, i, true);
        }
        return datosParaClasificar;
    }

    /**
     * 9 - Ordenar por Cuentas
     *
     * @param datosParaClasificar
     * @param maximo
     * @return
     */
    protected int[] ordenarPorCuenta(int[] datosParaClasificar, int maximo) {
        int[] cuenta = new int[maximo + 1];
        for (int i = 0; i < datosParaClasificar.length; i++) {
            cuenta[datosParaClasificar[i]]++;
        }
        for (int i = 1; i < maximo + 1; i++) {
            cuenta[i] += cuenta[i - 1];
        }
        int[] salida = new int[datosParaClasificar.length];
        for (int i = datosParaClasificar.length - 1; i >= 0; i--) {
            int j = cuenta[datosParaClasificar[i]] - 1;
            salida[j] = datosParaClasificar[i];
            cuenta[datosParaClasificar[i]]--;
        }
        return salida;
    }

    /**
     * MAIN
     *
     * @param args
     */
    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        boolean runAll = false;
        boolean quitarCascara = false;
        int[] rangos = new int[]{300, 3000, 30000};
        long segundo = 100000000;
        int testValue = 9;

        // CORRER PARA TODOS
        if (runAll) {
            for (int r = 0; r < rangos.length; r++) {
                for (int i = 1; i <= 9; i++) {
                    GeneradorDatosGenericos gdg = new GeneradorDatosGenericos(rangos[r]);
                    int[] vectorAleatorio = gdg.generarDatosAleatorios();
                    int[] vectorAscendente = gdg.generarDatosAscendentes();
                    int[] vectorDescendente = gdg.generarDatosDescendentes();

                    int[] aleatorioCopia = vectorAleatorio.clone();
                    int[] ascendenteCopia = vectorAscendente.clone();
                    int[] descendenteCopia = vectorDescendente.clone();

                    String cascaraMsg = quitarCascara ? " S/ CASCARA" : "";
                    System.out.println(getAlgorithmName(i) + cascaraMsg + " " + rangos[r] + " ELEMENTOS");

                    calcularTiempo("Tiempo Medio Ascendente", clasif, ascendenteCopia, i, segundo, quitarCascara);

                    calcularTiempo("Tiempo Medio Descendente", clasif, descendenteCopia, i, segundo, quitarCascara);

                    calcularTiempo("Tiempo Medio Aleatorio", clasif, aleatorioCopia, i, segundo, quitarCascara);
                    System.out.println("");
                }
            }
        } else {
            // CORRER UNA PRUEBA
            if (testValue > 0) {
                int[] testItems = new int[]{5, 25, 40, 30, 33, 55, 75, 73, 83, 10};
                String cascaraMsg = quitarCascara ? " S/ CASCARA" : "";
                
                System.out.println(getAlgorithmName(testValue) + cascaraMsg + " " + testItems.length + " ELEMENTOS");

                calcularTiempo("Tiempo Medio Ascendente", clasif, testItems, testValue, segundo, quitarCascara);
                System.out.println(clasif.estaOrdenado(testItems));
            }
        }

    }

    /**
     * Ejecuta el algoritmo de ordenación seleccionado para el conjunto de
     * elementos ingresado e imprime en pantalla el tiempo transcurrido
     *
     * @param textoSalida
     * @param clasif
     * @param elementos
     * @param algoritmo
     * @param tiempoResolucion
     * @param quitarCascara
     */
    private static void calcularTiempo(String textoSalida, TClasificador clasif, int[] elementos, int algoritmo, long tiempoResolucion, boolean quitarCascara) {
        long totalTime = 0;
        int llamadas = 0;

        long init = System.nanoTime();
        while (totalTime < tiempoResolucion) {
            clasif.clasificar(elementos, algoritmo, false);
            long fin = System.nanoTime();
            llamadas++;
            totalTime = fin - init;
        }
        long tiempoEjecucion = totalTime / llamadas;

        if (quitarCascara) {
            init = System.nanoTime();
            totalTime = 0;
            llamadas = 0;
            while (totalTime < tiempoResolucion) {
                clasif.clasificar(elementos, algoritmo, true);
                long fin = System.nanoTime();
                llamadas++;
                totalTime = fin - init;
            }
            long tiempoCascara = totalTime / llamadas;
            tiempoEjecucion -= tiempoCascara;
        }
        System.out.println(textoSalida + " = " + (tiempoEjecucion));
    }

    // Mapa de nombres de algoritmos.
    private static String getAlgorithmName(int i) {
        switch (i) {
            case 1:
                return "METODO_CLASIFICACION_INSERCION";
            case 2:
                return "METODO_CLASIFICACION_SHELL";
            case 3:
                return "METODO_CLASIFICACION_BURBUJA";
            case 4:
                return "METODO_CLASIFICACION_QUICKSORT";
            case 5:
                return "METODO_CLASIFICACION_HEAPSORT";
            case 6:
                return "METODO_CLASIFICACION_SELECCION";
            case 7:
                return "METODO_CLASIFICACION_BIN";
            case 8:
                return "METODO_CLASIFICACION_RADIX";
            case 9:
                return "METODO_CLASIFICACION_CUENTAS";
            default:
                return "???";
        }

    }
}
