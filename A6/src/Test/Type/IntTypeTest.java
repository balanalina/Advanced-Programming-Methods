package Test.Type;

import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntTypeTest {
    private IntType integer;

    @BeforeEach
    void setUp() {
        this.integer = new IntType();
    }

    @Test
    void testEquals() {
        IntType eq_check = new IntType();
        BoolType not_eq_check = new BoolType();
        assertTrue(integer.equals(eq_check));
        assertFalse(integer.equals(not_eq_check));
    }

    @Test
    void testDefaultValue(){
        assertEquals(0 ,((IntValue)this.integer.defaultValue()).getValue());
    }
}