package org.example;

import java.util.Comparator;

/**
 * Определяет основные методы для списка, поддерживающего элементы обобщенного типа {@code T}.
 *
 * @param <T> тип элементов, хранящихся в списке
 */
public interface MyList<T> {
    void add(T element);
    void add(int index, T element);
    T get(int index);
    void remove(int index);
    void clear();
    void sort(Comparator<? super T> c);
    void set(int index, T element);
    void trimToSize();
    int size();
}
