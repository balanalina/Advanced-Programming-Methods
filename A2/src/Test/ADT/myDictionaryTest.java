package Test.ADT;

import ADT.ImyDictionary;
import ADT.myDictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class myDictionaryTest {
    private ImyDictionary<String,Integer> myDictionaryTest;

    @BeforeEach
    void setUp() {
        this.myDictionaryTest = new myDictionary<String,Integer>();
        myDictionaryTest.put("a",1);
        myDictionaryTest.put("b",5346);
    }

    @Test
    void size() {
        assertEquals(2,myDictionaryTest.size());
        myDictionaryTest.remove("a");
        assertEquals(1,myDictionaryTest.size());
        myDictionaryTest.remove("b");
        assertEquals(0, myDictionaryTest.size());
    }

    @Test
    void put() {
        myDictionaryTest.put("ok",3);
        myDictionaryTest.put("54",54);
        assertEquals(4,myDictionaryTest.size());
    }

    @Test
    void get() {
        assertEquals(5346,myDictionaryTest.get("b"));
        assertEquals(null, myDictionaryTest.get("okok"));
    }

    @Test
    void remove() {
        assertEquals(1, myDictionaryTest.remove("a"));
        assertEquals(1,myDictionaryTest.size());
    }

    @Test
    void isEmpty() {
        assertFalse(myDictionaryTest.isEmpty());
        myDictionaryTest.remove("a");
        myDictionaryTest.remove("b");
        assertTrue(myDictionaryTest.isEmpty());
    }

    @Test
    void keys() {
        Set<String> key_set = myDictionaryTest.keys();
        assertEquals(2,key_set.size());
        assertTrue(key_set.contains("a"));
        assertTrue(key_set.contains("b"));
    }

    @Test
    void values() {
        Collection<Integer> values = myDictionaryTest.values();
        assertEquals(2, values.size());
        assertTrue(values.contains(1));
    }
}