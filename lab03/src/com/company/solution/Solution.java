package com.company.solution;

import java.util.*;

public class Solution {
    public static int solution(List<Integer> list) {
        Collections.sort(list);
        int i = 1;
        int previous = 0;
        for (Integer integer : list) {
            //System.out.println(integer);
            if (integer > 0) {
                if (integer - previous >= 2) return previous + 1;
                if (integer != previous) i++;
                previous = integer;
            }
        }
        return i;
    }
    public static int[] solution(float[] arr, float target) {
        int[] returnVale= new int[2];
        List<Float> list = new ArrayList<>();
        List<Float> list2 = new ArrayList<>();
        for(int i=0; i<arr.length;i++){
            list.add(arr[i]);
            list2.add(arr[i]);
        }
        list.sort(Float::compareTo);
        for(Float i: list) {
            if(list.contains(target-i)){
                returnVale[0] = list2.indexOf(i);
                returnVale[1] = list2.indexOf(target-i);
                return returnVale;
            }
        }
        throw new NoSuchElementException();
    }
}