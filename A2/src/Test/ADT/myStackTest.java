package Test.ADT;

import ADT.ImyStack;
import ADT.myStack;
import org.junit.Before;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class myStackTest {
    private ImyStack<Integer> myStackTest;

    @BeforeEach
    void setUp() {
        this.myStackTest = new myStack<Integer>();
        myStackTest.push(7);
    }

    @Test
    void push() {
        assertEquals(73,myStackTest.push(73));
        assertEquals(47,myStackTest.push(47));
        assertNotEquals(3,myStackTest.push(767));
        assertFalse(myStackTest.empty());
    }

    @Test
    void pop() {
        assertEquals(7,myStackTest.pop());
    }

    @Test
    void search() {
        for (int i = 0; i <50 ; i++) {
            myStackTest.push(767);
        }
        myStackTest.push(766);
        assertEquals(52,myStackTest.search(7));
        assertEquals(1,myStackTest.search(766));
    }

    @Test
    void empty() {
        assertFalse(myStackTest.empty());
        myStackTest.pop();
        assertTrue(myStackTest.empty());
    }
}