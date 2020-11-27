package Test.Expression;

import ADT.ImyHeap;
import ADT.myDictionary;
import ADT.myHeap;
import Model.Expression.ValueExpression;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueExpressionTest {
    private ValueExpression val_expr;

    @BeforeEach
    void setUp() {
        this.val_expr = new ValueExpression(new IntValue());
    }

    @Test
    void getType() {
        assertEquals(new IntType(), val_expr.getType());
        assertNotEquals(new BoolType(), val_expr.getType());
    }

    @Test
    void eval() {
        IntValue val_expr_eval = (IntValue)val_expr.eval(new myDictionary<String,Value>(), new myHeap<Value>());
        assertEquals(0, val_expr_eval.getValue());
    }
}