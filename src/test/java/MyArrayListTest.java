import org.example.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyArrayListTest {
    private MyArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void addElementTest() {
        list.add(1);

        assertEquals(1, list.size());
    }

    @Test
    void addAtIndexTest() {
        list.add(1);
        list.add(3);
        list.add(1, 2);

        assertEquals(Integer.valueOf(2), list.get(1));
    }

    @Test
    void removeTest() {
        list.add(1);
        list.remove(0);

        assertEquals(0, list.size());
    }

    @Test
    void clearTest() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    void setTest() {
        list.add(1);
        list.set(0, 2);

        assertEquals(2, list.get(0));
    }

    @Test
    void sortTest() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.sort(Comparator.naturalOrder());

        assertEquals(3, list.get(2));
    }

    @Test
    void addThousandAndRemoveHalfTest(){
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        for (int i = 1000 - 1; i > 500 - 1; i--) {
            list.remove(i);
        }

        assertEquals(500, list.size());
    }

    @Test
    public void iteratorHasNextReturnsFalseWhenEmpty() {
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iteratorRemoveAndCheckNext() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < 3; i++) {
            iterator.next();
            iterator.remove();
        }
        Integer nextElement = iterator.next();

        assertEquals(Integer.valueOf(3), nextElement);
    }

    @Test
    public void iteratorRemoveTest() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        int count = 0;
        while (iterator.hasNext() && count < 750) {
            iterator.next();
            iterator.remove();
            count++;
        }

        assertEquals(250, list.size());
    }

    @Test
    public void iteratorRemoveCorrectElement() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        assertEquals(2, list.get(1));
    }

//    @Test
//    void trimToSizeAndCapacityTest(){
//        for (int i = 0; i < 22; i++) {
//            intList.add(i);
//        }
//        assertEquals(40, intList.capacity());
//
//        intList.trimToSize();
//        assertEquals(22, intList.capacity());
//    }
}
