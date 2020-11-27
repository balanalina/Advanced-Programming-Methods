package Test.Expression;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import ADT.myDictionary;
import ADT.myHeap;
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
    private ImyHeap<Value> heapTable;
    private VariableExpression var_expr;

    @BeforeEach
    void setUp() {
        this.sym_table = new myDictionary<String, Value>();
        this.heapTable = new myHeap<>();
        this.var_expr = new VariableExpression("sum");
        sym_table.put("sum", new IntValue(50));
        var_expr.eval(sym_table, this.heapTable);
    }

    @Test
    void eval() {
        IntValue var_expr_eval = (IntValue) var_expr.eval(sym_table, heapTable);
        assertEquals(50,var_expr_eval.getValue());
    }

    @Test
    void getType() {
        assertEquals(var_expr.getType(), new IntType());
        assertNotEquals(var_expr.getType(), new BoolType());
    }
}