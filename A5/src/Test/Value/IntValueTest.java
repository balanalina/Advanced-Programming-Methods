package Test.Value;

import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntValueTest {
    private IntValue value_0;
    private IntValue value_17;

    @BeforeEach
    void setUp() {
        this.value_0 = new IntValue();
        this.value_17 = new IntValue(17);
    }

    @Test
    void getValue() {
        assertEquals(0, value_0.getValue());
        assertEquals(17, value_17.getValue());
        assertNotEquals(177, value_17.getValue());
    }

    @Test
    void getType() {
        assertEquals(new IntType(), value_0.getType());
        assertNotEquals(new BoolType(), value_0.getType());
    }

    @Test
    void testEquals(){
        assertTrue(this.value_0.equals(new IntValue()));
        assertFalse(this.value_0.equals(54));
    }
}