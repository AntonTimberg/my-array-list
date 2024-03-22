package org.example;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        Iterator<Integer> iterator = myArrayList.iterator();

        for (int i = 0; i < 1000; i++) {
            myArrayList.add(i);
        }

        while (iterator.hasNext()) {
            Integer current = iterator.next();
            if (current % 2 == 0 || current % 3 == 0 || current % 5 == 0) {
                iterator.remove();
            }
        }

        myArrayList.iterator().forEachRemaining(System.out::println);
    }
}