package Test.Statement;

import ADT.myDictionary;
import ADT.myList;
import ADT.myStack;
import Model.Exception.myException;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Statement.IfStatement;
import Model.Statement.OpenRFile;
import Model.Value.StringValue;
import Model.Value.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class OpenRFileTest {
    private ProgramState state;
    private OpenRFile test_statement;
    private OpenRFile exception_statement;
    private StringValue file;

    @BeforeEach
    void setUp() {
        this.file = new StringValue("ok.txt");
        this.test_statement = new OpenRFile(new ValueExpression(this.file));
        this.exception_statement = new OpenRFile(new ValueExpression(this.file));
        this.state = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), this.test_statement);
    }

    @Test
    void execute() {
        this.state = this.test_statement.execute(this.state);
        assertTrue(this.state.getFile_table().containsKey(this.file));
        assertThrows(myException.class, () -> {
           this.exception_statement.execute(this.state);
        });
    }
}