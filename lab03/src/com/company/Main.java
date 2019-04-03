package com.company;

import com.company.Sorting.*;
import com.company.matrix.Matrix;
import com.company.matrix.WrongSizeException;
import com.company.solution.Solution;
import com.company.substring.Substring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Matrix<String> m1 = new Matrix<>(2, 3);
        Matrix<String> m2 = new Matrix<>(2, 3);
        Matrix<Float> m1f = new Matrix<>(2, 2);
        Matrix<Float> m2f = new Matrix<>(2, 2);
        List<Integer> listOfInteger = new ArrayList<>();
        Integer[] intTab = {-1, -3, 3, 3, 1, 2, 0};
        Integer[] intTab2 = {1, 2, 3};
        Integer[] intTab3 = {-1, -3};
        for (int i = 0; i < intTab3.length; i++) {
            listOfInteger.add(intTab3[i]);
        }
        System.out.println("Najmniejszy całk. el: " + Solution.solution(listOfInteger));
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                m1f.setElement(i, j, (float) (i + j));
                m2f.setElement(i, j, (float) ((i - 1) * j));
            }
        }
        try {
            m1.add(m2, ((first, second) -> first + second));
        } catch (WrongSizeException ex) {
            System.out.println(ex.toString());
        }
        Iterator iterator = m1f.iterator();
        while (iterator.hasNext()) {
            System.out.println("Value: " + iterator.next());
        }
        System.out.println("Nalezy powtorzyc "+Substring.substring("abab", "abababa"));

        float[] asd = {2, 7, 3, 4, 5, 6, 7, 8, 9, 1};
        int[] wynik = Solution.solution(asd, 4);
        System.out.println("Indeks [0] = " + wynik[0] + " Indeks [1] = " + wynik[1]);

        int arr1[] = new int[1000];
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            arr1[i] = rand.nextInt(750);
        }
        //SelectSort
        long tStart = System.currentTimeMillis();
        SelectionSort.sort(arr1.clone());
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        System.out.println("Select sort: " + tDelta / 1000.0);
        //BubbleSort
        tStart = System.currentTimeMillis();
        BubbleSort.bubbleSort(arr1.clone());
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        System.out.println("Bubble sort: " + tDelta / 1000.0);
        //RecursiveBubbleSort
        tStart = System.currentTimeMillis();
        RecursiveBubbleSort.bubbleSort(arr1.clone(), arr1.length);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        System.out.println("RecursiveBubble sort: " + tDelta / 1000.0);
        //InsertionSort
        tStart = System.currentTimeMillis();
        InsertionSort.sort(arr1.clone());
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        System.out.println("Insertion sort: " + tDelta / 1000.0);
        //RecursiveInsertionSort
        tStart = System.currentTimeMillis();
        RecursiveInsertionSort.insertionSortRecursive(arr1.clone(), arr1.length);
        tEnd = System.currentTimeMillis();
        tDelta = tEnd - tStart;
        System.out.println("RecursiveInsertion sort: " + tDelta / 1000.0);
    }
}
