/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut06.pkg07;

/**
 *
 * @author AlGo
 */
import java.lang.reflect.Array;
import java.util.Arrays;

public class TClasificador {

    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;

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

    public int encuentraPivote2(int i, int j, int[] arr) {
        if (arr[i] < arr[i + 1]) {
            return i + 1;
        } else {
            return i;
        }
    }

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

    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg300 = new GeneradorDatosGenericos(300);
        GeneradorDatosGenericos gdg10000 = new GeneradorDatosGenericos(10000);
        GeneradorDatosGenericos gdg30000 = new GeneradorDatosGenericos(30000);
        
        int[] vectorAleatorio300 = gdg300.generarDatosAleatorios();
        int[] vectorAscendente300 = gdg300.generarDatosAscendentes();
        int[] vectorDescendente300 = gdg300.generarDatosDescendentes();

        int[] vectorAleatorio10000 = gdg10000.generarDatosAleatorios();
        int[] vectorAscendente10000 = gdg10000.generarDatosAscendentes();
        int[] vectorDescendente10000 = gdg10000.generarDatosDescendentes();

        int[] vectorAleatorio30000 = gdg30000.generarDatosAleatorios();
        int[] vectorAscendente30000 = gdg30000.generarDatosAscendentes();
        int[] vectorDescendente30000 = gdg30000.generarDatosDescendentes();

        boolean quitarCascara = true;
        long segundo = 1000000000;

        // 300 elementos
        for (int i = 1; i <= 4; i++) {
            int[] aleatorioCopia = vectorAleatorio300.clone();
            int[] ascendenteCopia = vectorAscendente300.clone();
            int[] descendenteCopia = vectorDescendente300.clone();

            String cascaraMsg = quitarCascara ? " S/ CASCARA" : "";
            System.out.println(getAlgorithmName(i) + cascaraMsg + " 300 ELEMENTOS");

            calcularTiempo("Tiempo Medio Ascendente", clasif, ascendenteCopia, i, segundo, quitarCascara);

            calcularTiempo("Tiempo Medio Descendente", clasif, descendenteCopia, i, segundo, quitarCascara);

            calcularTiempo("Tiempo Medio Aleatorio", clasif, aleatorioCopia, i, segundo, quitarCascara);

            System.out.println("");
        }

        // 10.000 elementos
        for (int i = 1; i <= 4; i++) {
            int[] aleatorioCopia = vectorAleatorio10000.clone();
            int[] ascendenteCopia = vectorAscendente10000.clone();
            int[] descendenteCopia = vectorDescendente10000.clone();

            String cascaraMsg = quitarCascara ? " S/ CASCARA" : "";
            System.out.println(getAlgorithmName(i) + cascaraMsg + " 10.000 ELEMENTOS");

            calcularTiempo("Tiempo Medio Ascendente", clasif, ascendenteCopia, i, segundo, quitarCascara);

            calcularTiempo("Tiempo Medio Descendente", clasif, descendenteCopia, i, segundo, quitarCascara);

            calcularTiempo("Tiempo Medio Aleatorio", clasif, aleatorioCopia, i, segundo, quitarCascara);

            System.out.println("");
        }

        // 30.000 elementos
        for (int i = 1; i <= 4; i++) {
            int[] aleatorioCopia = vectorAleatorio30000.clone();
            int[] ascendenteCopia = vectorAscendente30000.clone();
            int[] descendenteCopia = vectorDescendente30000.clone();

            String cascaraMsg = quitarCascara ? " S/ CASCARA" : "";
            System.out.println(getAlgorithmName(i) + cascaraMsg + " 30.000 ELEMENTOS");

            calcularTiempo("Tiempo Medio Ascendente", clasif, ascendenteCopia, i, segundo, quitarCascara);

            calcularTiempo("Tiempo Medio Descendente", clasif, descendenteCopia, i, segundo, quitarCascara);

            calcularTiempo("Tiempo Medio Aleatorio", clasif, aleatorioCopia, i, segundo, quitarCascara);

            System.out.println("");
        }
    }

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
            default:
                return "???";
        }

    }
}
