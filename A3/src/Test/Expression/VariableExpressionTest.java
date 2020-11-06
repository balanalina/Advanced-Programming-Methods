package Test.Expression;

import ADT.ImyDictionary;
import ADT.myDictionary;
import Model.Expression.VariableExpression;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableExpressionTest {
    private ImyDictionary<String, Value> sym_table;
    private VariableExpression var_expr;

    @BeforeEach
    void setUp() {
        this.sym_table = new myDictionary<String, Value>();
        this.var_expr = new VariableExpression("sum");
        sym_table.put("sum", new IntValue(50));
        var_expr.eval(sym_table);
    }

    @Test
    void eval() {
        IntValue var_expr_eval = (IntValue) var_expr.eval(sym_table);
        assertEquals(50,var_expr_eval.getValue());
    }

    @Test
    void getType() {
        assertEquals(var_expr.getType(), new IntType());
        assertNotEquals(var_expr.getType(), new BoolType());
    }
}