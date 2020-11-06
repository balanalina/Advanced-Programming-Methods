package Test.Type;

import Model.Type.BoolType;
import Model.Type.IntType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolTypeTest {
    private BoolType bool;

    @BeforeEach
    void setUp() {
        this.bool = new BoolType();
    }

    @Test
    void testEquals() {
        BoolType eq_check = new BoolType();
        IntType not_eq_check = new IntType();
        assertTrue(bool.equals(eq_check));
        assertFalse(bool.equals(not_eq_check));
    }
}