package Test.Statement;

import ADT.myDictionary;
import ADT.myList;
import ADT.myStack;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Statement.CompoundStatement;
import Model.Statement.IStatement;
import Model.Statement.VariableDeclarationStatement;
import Model.Type.IntType;
import Model.Value.StringValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class VariableDeclarationStatementTest {
    private VariableDeclarationStatement test_statement;
    private ProgramState state;

    @BeforeEach
    void setUp() {
        this.test_statement = new VariableDeclarationStatement("sum",new IntType());
        this.state = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(),this.test_statement);
    }

    @Test
    void execute() {
        this.test_statement.execute(this.state);
        assertTrue(this.state.getSymbol_table().containsKey("sum"));
        assertThrows(myException.class, () -> {
            this.test_statement.execute(this.state);
        });
    }
}