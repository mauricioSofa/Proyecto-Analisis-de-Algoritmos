/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * En esta Clase esta diseñada para la implementacion de los algoritmos no para
 * la resolucion de los problemas.
 *
 * @author Soporte
 */
public class Algoritmos {
    int tipoOrdenamiento = 0;
    private ArrayList<Rodal> salidaMersort = new ArrayList<>();

    public ArrayList<Rodal> getSalidaMersort() {
        return salidaMersort;
    }
    /* Este algoritmo de ordenamieto tiene una complejida de O(n) */
    /**
     * Counting Sort function de ordenamieto de arboles adaptada Mauro Castillo
     * Este algoritmo lo hize basado en el webSite
     * http://www.sanfoundry.com/java-program-implement-counting-sort/
     *
     *
     * @param entrada
     */
    /*Complejida O(n^2)
    InsertionSort  Implementado para rodales partiendo de la caracteristica numero arboles*/
    
    public ArrayList<Rodal> InsertionSort(ArrayList<Rodal> entrada) {
        ArrayList<Rodal> temporal = entrada;
        Rodal key = new Rodal();
        temporal.add(0, key);

        int i = 0;
        for (int j = 0; j < temporal.size(); j++) {
            key = temporal.get(j);
            i = j - 1;

            while (i > 0 && (temporal.get(i).fecha_fin.compareTo(key.fecha_fin) > 0)) {
                temporal.set(i + 1, temporal.get(i));
                i -= 1;
            }
            temporal.set(i + 1, key);
        }
        temporal.remove(0);
        return temporal;
    }
/*Complejida O(nlgn) diseñado para ordenar las fechas de finalizacion de menor a Mayor
    La modificaion de coparacion esta en la clase rodal*/
public ArrayList<Rodal> mergeSort(ArrayList<Rodal> a, int tipo) {
         this.tipoOrdenamiento = tipo;
        ArrayList<Rodal> tmpArray = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            tmpArray.add(a.get(i));
        }
        mergeSort(a, tmpArray, 0, a.size() - 1);
        return salidaMersort;
    }

    private void mergeSort(ArrayList<Rodal> a, ArrayList<Rodal> tmpArray,
            int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    private void merge(ArrayList<Rodal> a, ArrayList<Rodal> tmpArray,
            int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (tipoOrdenamiento == 0) {
                if (a.get(leftPos).compareTo(a.get(rightPos)) <= 0) {
                    tmpArray.set(tmpPos++, a.get(leftPos++));
                } else {
                    tmpArray.set(tmpPos++, a.get(rightPos++));
                }
            } else if (tipoOrdenamiento == 1) {
                if (a.get(leftPos).compareTo1(a.get(rightPos)) <= 0) {
                    tmpArray.set(tmpPos++, a.get(leftPos++));
                } else {
                    tmpArray.set(tmpPos++, a.get(rightPos++));
                }

            }
        }

        while (leftPos <= leftEnd) // Copy rest of first half
        {
            tmpArray.set(tmpPos++, a.get(leftPos++));
        }

        while (rightPos <= rightEnd) // Copy rest of right half
        {
            tmpArray.set(tmpPos++, a.get(rightPos++));
        }

        // Copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            a.set(rightEnd, tmpArray.get(rightEnd));
        }
        salidaMersort = a;

    }
    
}
