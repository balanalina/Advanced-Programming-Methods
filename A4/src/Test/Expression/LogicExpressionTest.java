package Test.Expression;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import ADT.myDictionary;
import ADT.myHeap;
import Model.Exception.myException;
import Model.Expression.*;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class LogicExpressionTest {
    private ImyDictionary<String, Value> sym_table;
    private ImyHeap<Value> heapTable;

    @BeforeEach
    void setUp() {
        this.heapTable = new myHeap<>();
        this.sym_table = new myDictionary<String, Value>();
    }

    @Test
    void eval() {
        assertThrows(myException.class, () -> { new LogicExpression(new ValueExpression(new BoolValue()),new VariableExpression("a"),"and3");});
        assertThrows(myException.class,() -> {
            LogicExpression wrong_1_operand = new LogicExpression(new ValueExpression(new IntValue()),new VariableExpression("a"), "or");
            sym_table = new myDictionary<String, Value>();
            sym_table.put("a",new BoolValue(true));
            wrong_1_operand.eval(sym_table, heapTable);
        });
        assertThrows(myException.class,() -> {
            IExpression wrong_2_operand = new LogicExpression(new ValueExpression(new BoolValue()),new VariableExpression("b"), "and");
            sym_table = new myDictionary<String, Value>();
            sym_table.put("b",new IntValue(52));
            wrong_2_operand.eval(sym_table, heapTable);
        });
        IExpression logic_expr = new LogicExpression(new ValueExpression(new BoolValue()),new VariableExpression("a"),"and");
        sym_table.put("a",new BoolValue(true));
        BoolValue expr = (BoolValue) logic_expr.eval(sym_table, heapTable);
        assertEquals(new BoolValue().getValue(), expr.getValue());
    }

    @Test
    void getType() {
        sym_table.put("a",new BoolValue(true));
        IExpression logic_expr = new LogicExpression(new ValueExpression(new BoolValue()),new VariableExpression("a"),"and");
        assertEquals(new BoolType(), logic_expr.eval(sym_table, heapTable).getType());
    }
}