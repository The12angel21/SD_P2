package servidor;

import biblioteca.TLibro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Prieto
 */
public class A_MergeSort {
    
 public void merge(List<TLibro> Biblioteca, int izq, int m, int dcha, int campo) {
        int i, j, k;
        int n1 = m - izq + 1;
        int n2 = dcha - m;

        List<TLibro> L = new ArrayList<>(Biblioteca.subList(izq, m + 1));
        List<TLibro> R = new ArrayList<>(Biblioteca.subList(m + 1, dcha + 1));

        i = 0;
        j = 0;
        k = izq;
        while (i < n1 && j < n2) {
            if (compararLibros(L.get(i), R.get(j), campo) <= 0) {
                Biblioteca.set(k, L.get(i));
                i++;
            } else {
                Biblioteca.set(k, R.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            Biblioteca.set(k, L.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            Biblioteca.set(k, R.get(j));
            j++;
            k++;
        }
    }

    public void mergeSort(List<TLibro> Biblioteca, int izq, int dcha, int campo) {
        if (izq < dcha) {
            int m = izq + (dcha - izq) / 2;

            mergeSort(Biblioteca, izq, m, campo);
            mergeSort(Biblioteca, m + 1, dcha, campo);

            merge(Biblioteca, izq, m, dcha, campo);
        }
    }

    public int compararLibros(TLibro o1, TLibro o2, int campo) {
        switch (campo) {
            case 0:
                return o1.getIsbn().compareTo(o2.getIsbn());
            case 1:
                return o1.getTitulo().compareTo(o2.getTitulo());
            case 2:
                return o1.getAutor().compareTo(o2.getAutor());
            case 3:
                return Integer.compare(o1.getAnio(), o2.getAnio());
            case 4:
                return o1.getPais().compareTo(o2.getPais());
            case 5:
                return Integer.compare(o1.getNoLibros(), o2.getNoLibros());
            case 6:
                return Integer.compare(o1.getNoPrestados(), o2.getNoPrestados());
            case 7:
                return Integer.compare(o1.getNoListaEspera(), o2.getNoListaEspera());
            default:
                // Si el campo de comparación no es válido, devuelve 0
                return 0;
        }
    }
    
}
