package Test.Value;

import Model.Type.BoolType;
import Model.Type.StringType;
import Model.Value.StringValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValueTest {
    private StringValue test_value;
    private StringValue default_value;

    @BeforeEach
    void setUp() {
        this.test_value = new StringValue("test");
        this.default_value = new StringValue();
    }

    @Test
    void getValue() {
        assertEquals("",this.default_value.getValue());
        assertEquals("test", this.test_value.getValue());
        assertNotEquals("Fwfwfw",this.default_value.getValue());
        assertNotEquals("", this.test_value.getValue());
    }

    @Test
    void getType() {
        assertEquals(new StringType(), this.test_value.getType());
        assertNotEquals(new BoolType(), this.default_value.getType());
    }

    @Test
    void testEquals(){
        assertTrue(this.default_value.equals(new StringValue()));
        assertFalse(this.default_value.equals(54));
    }
}