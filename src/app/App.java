package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import libraries.*;

public class App {
    public static void main(String[] args) throws Exception {
        char[] abecedario = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                             'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                             '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                             'á', 'é', 'í', 'ó', 'ú',
                             'ä', 'ë', 'ï', 'ö', 'ü', 
                             'ñ', 'Ñ', 'ç', 
                             'à', 'è', 'ì', 'ò', 'ù',
                             'ã', 'ê'};
        
        String completo;
        String[] palabras = new String[50000];

        try {
            Scanner lee = new Scanner(new File(System.getProperty("user.dir") + "/Trie/palabras.txt"));
            completo = lee.nextLine();
            palabras = completo.split(" ");
            lee.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error:" + fnfe.toString());
            System.exit(-1);
        }

        String[] palabras1000 = new String[1000];
        Trie<String> trie1000 = new Trie<String>(abecedario);
        String[] palabras10000 = new String[10000];
        Trie<String> trie10000 = new Trie<String>(abecedario);
        String[] palabras20000 = new String[20000];
        Trie<String> trie20000 = new Trie<String>(abecedario);
        String[] palabras25000 = new String[25000];
        Trie<String> trie25000 = new Trie<String>(abecedario);
        String[] palabras50000 = new String[50000]; 
        Trie<String> trie50000 = new Trie<String>(abecedario);

        long start, end;

        // ! Usamos 1000 palabras
        for(int i = 0; i < 1000; i++){
            palabras1000[i] = palabras[i];
            trie1000.add(palabras1000[i]);
        }

        start = System.nanoTime();
        String ordenado1000 = trie1000.toString();
        end = System.nanoTime();
        System.out.println("Tiempo en Trie1000 nano: " + (end - start));
        System.out.println(ordenado1000);

        start = System.nanoTime();
        Sort.mergeSort(palabras1000);
        end = System.nanoTime();
        System.out.println("Tiempo en merge1000 nano: " + (end - start));

        // ! Usamos 10000 palabras
        for (int i = 0; i < 10000; i++){
            palabras10000[i] = palabras[i];
            trie10000.add(palabras10000[i]);
        }

        start = System.nanoTime();
        String ordenado10000 = trie10000.toString();
        end = System.nanoTime();
        System.out.println("Tiempo en Trie10000 nano: " + (end - start));

        start = System.nanoTime();
        Sort.mergeSort(palabras10000);
        end = System.nanoTime();
        System.out.println("Tiempo en merge10000 nano: " + (end - start));

        // ! Usamos 20000 palabras
        for (int i = 0; i < 20000; i++){
            palabras20000[i] = palabras[i];
            trie20000.add(palabras20000[i]);
        }

        start = System.nanoTime();
        String ordenado20000 = trie20000.toString();
        end = System.nanoTime();
        System.out.println("Tiempo en Trie20000 nano: " + (end - start));

        start = System.nanoTime();
        Sort.mergeSort(palabras20000);
        end = System.nanoTime();
        System.out.println("Tiempo en merge20000 nano: " + (end - start));

        // ! Usamos 25000 palabras
        for (int i = 0; i < 25000; i++){
            palabras25000[i] = palabras[i];
            trie25000.add(palabras25000[i]);
        }

        start = System.nanoTime();
        String ordenado25000 = trie25000.toString();
        end = System.nanoTime();
        System.out.println("Tiempo en Trie25000 nano: " + (end - start));

        start = System.nanoTime();
        Sort.mergeSort(palabras25000);
        end = System.nanoTime();
        System.out.println("Tiempo en merge25000 nano: " + (end - start));

        // ! Usamos 50000 palabras
        for (int i = 0; i < 50000; i++){
            palabras50000[i] = palabras[i];
            trie50000.add(palabras50000[i]);
        }

        start = System.nanoTime();
        String ordenado50000 = trie50000.toString();
        end = System.nanoTime();
        System.out.println("Tiempo en Trie50000 nano: " + (end - start));

        start = System.nanoTime();
        Sort.mergeSort(palabras50000);
        end = System.nanoTime();
        System.out.println("Tiempo en merge50000 nano: " + (end - start));

    }
}

class Sort {
    public static void mergeSort(String a[]) {
        mergeSort(a, 0, a.length - 1);
    }

    private static void mergeSort(String a[], int min, int max) {
        String[] temp;
        int izq, der;
        if (min >= max - 1)
            return;
        int tam = max - min + 1, mitad = (max + min) / 2;
        temp = new String[tam];
        mergeSort(a, min, mitad);
        mergeSort(a, mitad + 1, max);

        izq = min;
        der = mitad + 1;
        for (int i = 0; i < tam; i++) {
            if (izq <= mitad && der <= max) {
                if (a[izq].compareTo(a[der]) < 0)
                    temp[i] = a[izq++];
                else
                    temp[i] = a[der++];
            } else {
                if (izq <= mitad)
                    temp[i] = a[izq++];
                else
                    temp[i] = a[der++];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            a[min + i] = temp[i];
        }
    }
}