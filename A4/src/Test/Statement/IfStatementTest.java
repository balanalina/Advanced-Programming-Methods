package Test.Statement;

import ADT.myDictionary;
import ADT.myList;
import ADT.myStack;
import Model.Exception.myException;
import Model.Expression.ArithmeticExpression;
import Model.Expression.LogicExpression;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Statement.IfStatement;
import Model.Statement.NopStatement;
import Model.Statement.PrintStatement;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class IfStatementTest {
    private ProgramState state;
    private IfStatement test_statement;
    private IfStatement int_condition;
    private IStatement first_stmt;
    private IStatement second_stmt;

    @BeforeEach
    void setUp() {
        this.first_stmt = new NopStatement();
        this.second_stmt = new PrintStatement(new ValueExpression(new BoolValue(true)));
        this.test_statement = new IfStatement(new LogicExpression(new ValueExpression(new BoolValue()),new ValueExpression(new BoolValue(true)),
                "or"),this.first_stmt, this.second_stmt);
        this.state = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), this.test_statement);
        this.int_condition = new IfStatement(new ArithmeticExpression(new ValueExpression(new IntValue()),
                new ValueExpression(new IntValue(-5)),"MINUS"),this.first_stmt, this.second_stmt);
    }

    @Test
    void execute() {
        this.state = this.test_statement.execute(this.state);
        assertEquals(this.first_stmt,this.state.getExecution_stack().pop());
        this.test_statement = new IfStatement(new LogicExpression(new ValueExpression(new BoolValue()),new ValueExpression(new BoolValue(true)),
                "and"),this.first_stmt, this.second_stmt);
        this.state = this.test_statement.execute(this.state);
        assertEquals(this.second_stmt,this.state.getExecution_stack().pop());
        assertThrows(myException.class, () -> {
            this.int_condition.execute(this.state);
        });
    }
}