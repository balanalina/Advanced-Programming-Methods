package Test.ADT;

import ADT.ImyList;
import ADT.myList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class myListTest {
    private ImyList<Integer> myListTest;

    @BeforeEach
    void setUp() {
        this.myListTest = new myList<Integer>();
        myListTest.add(7);
    }

    @Test
    void add() {
        assertTrue(myListTest.add(170));
    }

    @Test
    void clear() {
        myListTest.clear();
        assertTrue(myListTest.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(myListTest.contains(5464));
        assertTrue(myListTest.contains(7));
    }

    @Test
    void isEmpty() {
        assertFalse(myListTest.isEmpty());
        myListTest.clear();
        assertTrue(myListTest.isEmpty());
    }

    @Test
    void get() {
        assertEquals(7,myListTest.get(0));
        myListTest.add(200);
        assertEquals(200,myListTest.get(1));
    }

    @Test
    void size() {
        assertEquals(1,myListTest.size());
        for (int i = 0; i < 50 ; i++) {
            myListTest.add(90);
        }
        assertEquals(51,myListTest.size());
    }
}