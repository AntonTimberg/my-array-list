package org.example;

import java.util.Comparator;

/**
 * Класс, предоставляющий статический метод для сортировки массива с использованием алгоритма быстрой сортировки.
 */
public class MyQuickSort {
    /**
     * Сортирует переданный массив с использованием компаратора.
     *
     * @param array Массив, который необходимо отсортировать.
     * @param comparator Компаратор, используемый для сравнения элементов массива.
     * @param <T> Тип элементов массива.
     */
    public static <T> void sort(T[] array, Comparator<? super T> comparator){
        quickSort(array, 0, array.length - 1, comparator);
    }

    /**
     * Рекурсивно сортирует подмассивы.
     *
     * @param array Массив для сортировки.
     * @param low Нижний индекс диапазона сортировки.
     * @param high Верхний индекс диапазона сортировки.
     * @param comparator Компаратор для сравнения элементов.
     * @param <T> Тип элементов массива.
     */
    private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivot = partition(array, low, high, comparator);
            quickSort(array, low, pivot - 1, comparator);
            quickSort(array, pivot + 1, high, comparator);
        }
    }

    /**
     * Переупорядочивает элементы массива таким образом, что элементы меньше опорного оказываются слева от него,
     * а большие или равные - справа. Возвращает индекс опорного элемента.
     *
     * @param array Массив, элементы которого необходимо переупорядочить.
     * @param low Нижний индекс диапазона переупорядочивания.
     * @param high Верхний индекс диапазона переупорядочивания.
     * @param comparator Компаратор для сравнения элементов.
     * @param <T> Тип элементов массива.
     * @return Индекс опорного элемента после переупорядочивания.
     */
    private static <T> int partition(T[] array, int low, int high, Comparator<? super T> comparator) {
        T pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;

                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
