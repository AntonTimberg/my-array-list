package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

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
public class MyArrayList<T> implements MyList<T>, Iterable<T> {
    private Object[] elements; //Хранит элементы списка, может содержать пустые ячейки
    private int size; //Отражает количество элементов в списке, без пустых ячеек
    private int modCount = 0; // Счетчик модификаций коллекции

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
        modCount++;
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
        modCount++;
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
        modCount++;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elements[i] = null;
        size = 0;
        modCount++;
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
        modCount++;
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
        modCount++;
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
            modCount++;
        }
    }

    /**
     * Возвращает количество элементов в списке.
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
//     */
//    public int capacity() {
//        return elements.length;
//    }

    /**
     * Возвращает итератор для обхода элементов списка.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    /**
     * Внутренний класс - реализация итератора для MyArrayList.
     */
    private class MyArrayListIterator implements Iterator<T>{
        private int currentIndex = 0; // Текущий индекс итератора
        private final int expectedModCount = modCount;
        private boolean canRemove = false;

        /**
         * Проверяет, существует ли следующий элемент в списке.
         *
         * @return true, если следующий элемент существует, иначе false.
         */
        @Override
        public boolean hasNext() {
            checkForComodification();
            return currentIndex + 1 < size;
        }

        /**
         * Возвращает следующий элемент в списке, сдвигает итератор вперёд.
         *
         * @return следующий элемент списка
         * @throws NoSuchElementException если следующего элемента не существует
         */
        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true;
            return (T) elements[currentIndex++];
        }

        /**
         * Удаляет последний возвращенный элемент.
         *
         * @throws IllegalStateException если метод {@code remove()} вызван некорректно
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            checkForComodification();

            currentIndex--;
            System.arraycopy(elements, currentIndex + 1, elements, currentIndex, size - currentIndex - 1);
            elements[--size] = null;
            canRemove = false;
        }

        /**
         * Выполняет заданное действие для каждого оставшегося элемента в списке.
         *
         * @param action действие, которое будет выполнено для каждого элемента
         */
        @Override
        public void forEachRemaining(Consumer action) {
            Iterator.super.forEachRemaining(action);
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}