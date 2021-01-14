package Test.Statement;

import ADT.myDictionary;
import ADT.myHeap;
import ADT.myList;
import ADT.myStack;
import ADT.myHeap;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Statement.CompoundStatement;
import Model.Statement.IStatement;
import Model.Statement.PrintStatement;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class PrintStatementTest {
    private PrintStatement test_statement;
    private ProgramState state;

    @BeforeEach
    void setUp() {
        this.test_statement = new PrintStatement(new ValueExpression(new IntValue(6556)));
        this.state = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), this.test_statement);
    }

    @Test
    void execute() {
        this.test_statement.execute(this.state);
        assertEquals(1, this.state.getOut_table().size());
    }
}