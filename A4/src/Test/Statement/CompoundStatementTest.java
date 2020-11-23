package Test.Statement;

import ADT.myDictionary;
import ADT.myList;
import ADT.myStack;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Statement.CompoundStatement;
import Model.Statement.IStatement;
import Model.Statement.NopStatement;
import Model.Statement.PrintStatement;
import Model.Value.BoolValue;
import Model.Value.StringValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class CompoundStatementTest {
    private CompoundStatement test_statement;
    private ProgramState state;
    private IStatement first_stmt;
    private IStatement second_stmt;

    @BeforeEach
    void setUp() {
        this.first_stmt = new NopStatement();
        this.second_stmt = new PrintStatement(new ValueExpression(new BoolValue(true)));
        this.test_statement = new CompoundStatement(this.first_stmt, this.second_stmt);
        this.state = new ProgramState(new myStack<IStatement>(), new myDictionary<String,Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), this.test_statement);
        this.state.getExecution_stack().push(this.test_statement);
    }

    @Test
    void execute() {
        this.test_statement.execute(this.state);
        assertFalse(this.state.getExecution_stack().empty());
        assertEquals(this.state.getExecution_stack().pop(), this.first_stmt);
        assertEquals(this.state.getExecution_stack().pop(), this.second_stmt);
    }
}