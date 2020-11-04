package Test.Value;

import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolValueTest {
    private BoolValue true_value;
    private BoolValue false_value;

    @BeforeEach
    void setUp() {
        this.true_value = new BoolValue(true);
        this.false_value = new BoolValue();
    }

    @Test
    void getValue() {
        assertTrue( true_value.getValue());
        assertFalse( false_value.getValue());
    }

    @Test
    void getType() {
        assertEquals(new BoolType(), false_value.getType());
        assertNotEquals(new IntType(), true_value.getType());
    }
}