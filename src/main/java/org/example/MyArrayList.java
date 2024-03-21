package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация списка на основе массива Object, поддерживающая элементы обобщённого типа {@code T}.
 * Предоставляет базовые операции для работы со списком:
 * - Добавление элемента
 * - Добавление элемента по индексу
 * - Удаление элемента
 * - Получение элемента
 * - Замена элемента по индексу
 * - Сортировка
 * - Очистка коллекции
 *
 * @param <T> тип элементов, хранящихся в списке
 */
public class MyArrayList<T> implements MyList<T> {
    private Object[] elements; //Хранит элементы списка, может содержать пустые ячейки
    private int size; //Отражает количество элементов в списке, без пустых ячеек

    /**
     * Инициализация начальной емкости списка.
     */
    public MyArrayList() {
        elements = new Object[10];
        size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    @Override
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Вставляет элемент на указанную позицию в списке.
     *
     * @param index   позиция, на которую нужно вставить элемент
     * @param element элемент для добавления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент на указанной позиции в списке.
     *
     * @param index позиция элемента для возврата
     * @return элемент на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    /**
     * Удаляет элемент на указанной позиции в списке.
     *
     * @param index позиция элемента для удаления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;
        size = 0;
    }

    /**
     * Сортирует список с использованием заданного компаратора.
     *
     * @param comparator компаратор, используемый для сравнения элементов
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        T[] copyOfElements = (T[]) Arrays.copyOf(elements, size, elements.getClass());
        MyQuickSort.sort(copyOfElements, comparator);
        System.arraycopy(copyOfElements, 0, elements, 0, size);
    }

    /**
     * Заменяет элемент на указанной позиции в списке на указанный элемент.
     *
     * @param index   позиция элемента для замены
     * @param element элемент, который будет храниться на указанной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public void set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = element;
    }

    /**
     * Уменьшает емкость списка до текущего размера списка(количества элементов).
     */
    @Override
    public void trimToSize() {
        if (elements.length > size) {
            Object[] newElements = new Object[size];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return текущее количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Убеждается в том, что в списке достаточно места для добавления новых элементов,
     * и при необходимости увеличивает его емкость.
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

//    /**
//     * Возвращает текущую емкость списка.
//     *
//     * @return емкость массива, используемого для хранения элементов списка
//     */
//    public int capacity() {
//        return elements.length;
//    }
}
