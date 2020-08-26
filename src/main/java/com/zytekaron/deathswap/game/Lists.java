package com.zytekaron.deathswap.game;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lists {
    private static final Random random = new Random();
    
    public static <E> void shuffle(List<E> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            int index = random.nextInt(i);
            swap(list, i, index);
        }
    }
    
    public static <E> void rotate(List<E> list) {
        Collections.rotate(list, 1);
    }
    
    private static <E> void swap(List<E> list, int a, int b) {
        E temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }
}