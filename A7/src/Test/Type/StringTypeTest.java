package Test.Type;

import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringTypeTest {
    private StringType string;

    @BeforeEach
    void setUp() {
        this.string = new StringType();
    }

    @Test
    void testEquals() {
        StringType eq_check = new StringType();
        IntType not_eq_check = new IntType();
        assertTrue(this.string.equals(eq_check));
        assertFalse(this.string.equals(not_eq_check));
    }

    @Test
    void testDefaultValue() {
        assertEquals("", ((StringValue) this.string.defaultValue()).getValue());
    }
}