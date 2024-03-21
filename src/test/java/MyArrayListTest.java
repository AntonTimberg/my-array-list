import org.example.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyArrayListTest {
    private MyArrayList<Integer> intList;
//    private MyArrayList<String> stringList;

    @BeforeEach
    void setUp() {
        intList = new MyArrayList<>();
//        stringList = new MyArrayList<>();
    }

    @Test
    void addAndGetTest() {
        intList.add(1);
        intList.add(2);
        assertEquals(1, intList.get(0));
        assertEquals(2, intList.get(1));
    }

    @Test
    void addAtIndexTest() {
        intList.add(1);
        intList.add(2);
        intList.add(1, 3);
        assertEquals(1, intList.get(0));
        assertEquals(3, intList.get(1));
        assertEquals(2, intList.get(2));
    }

    @Test
    void removeTest() {
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.remove(1);
        assertEquals(2, intList.size());
        assertEquals(3, intList.get(1));
    }

    @Test
    void clearTest() {
        intList.add(1);
        intList.add(2);
        intList.clear();
        assertEquals(0, intList.size());
    }

    @Test
    void setTest() {
        intList.add(1);
        intList.set(0, 2);
        assertEquals(2, intList.get(0));
    }

    @Test
    void sortTest() {
        intList.add(3);
        intList.add(1);
        intList.add(2);
        intList.sort(Comparator.naturalOrder());
        assertEquals(1, intList.get(0));
        assertEquals(2, intList.get(1));
        assertEquals(3, intList.get(2));
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
