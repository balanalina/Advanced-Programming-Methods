package Test.Expression;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import ADT.myDictionary;
import ADT.myHeap;
import Model.Exception.myException;
import Model.Expression.ArithmeticExpression;
import Model.Expression.IExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExpressionTest {
    private ImyDictionary<String, Value> sym_table;
    private ImyHeap<Value> heapTable;
    IExpression arithmetic_expr;

    @BeforeEach
    void setUp() {
        this.sym_table = new myDictionary<String, Value>();
        this.sym_table.put("a",new IntValue(5));
        this.heapTable = new myHeap<Value>();
        this.arithmetic_expr = new ArithmeticExpression(new ValueExpression(new IntValue(-5)), new VariableExpression("a"),"PLUS");
    }

    @Test
    void eval() {
        IntValue expr = (IntValue)this.arithmetic_expr.eval(sym_table, this.heapTable);
        assertEquals(new IntValue(0).getValue(), expr.getValue());
        assertThrows(myException.class,() -> {
            ArithmeticExpression wrong_1_operand = new ArithmeticExpression(new ValueExpression(new BoolValue()), new ValueExpression(new IntValue(0)),"PLUS");
                ImyDictionary<String,Value> sym_table = new myDictionary<String, Value>();
                wrong_1_operand.eval(sym_table, this.heapTable);
        });
        assertThrows(myException.class,() -> {
            ArithmeticExpression wrong_2_operand = new ArithmeticExpression(new ValueExpression(new IntValue(0)), new ValueExpression(new BoolValue()), "MINUS");
            ImyDictionary<String,Value> sym_table = new myDictionary<String, Value>();
            wrong_2_operand.eval(sym_table, this.heapTable);
        });
        assertThrows(myException.class,() -> {
            ArithmeticExpression wrong_operation = new ArithmeticExpression(new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(0)), "MULTf3f3");
            ImyDictionary<String,Value> sym_table = new myDictionary<String, Value>();
            wrong_operation.eval(sym_table, this.heapTable);
        });
        assertThrows(myException.class,() -> {
            ArithmeticExpression division_by_zero = new ArithmeticExpression(new ValueExpression(new IntValue(0)), new ValueExpression(new IntValue(0)), "DIVISION");
            ImyDictionary<String,Value> sym_table = new myDictionary<String, Value>();
            division_by_zero.eval(sym_table, this.heapTable);
        });
    }

    @Test
    void getType() {
        assertEquals(new IntType(), this.arithmetic_expr.eval(this.sym_table, this.heapTable).getType());
    }
}