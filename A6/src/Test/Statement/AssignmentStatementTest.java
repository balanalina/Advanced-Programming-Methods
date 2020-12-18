package Test.Statement;

import ADT.*;
import Model.Exception.myException;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Statement.AssignmentStatement;
import Model.Statement.IStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.Value.*;
import org.junit.jupiter.api.function.Executable;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentStatementTest {
    private AssignmentStatement test_statement;
    private AssignmentStatement not_match_type;
    private AssignmentStatement id_not_declared;
    private ImyDictionary<String, Value> sym_table;
    private ProgramState state;

    @BeforeEach
    void setUp() {
        this.sym_table = new myDictionary<String, Value>();
        this.test_statement = new AssignmentStatement(new ValueExpression(new IntValue(17)),"a");
        this.sym_table.put("a", new IntValue());
        this.not_match_type = new AssignmentStatement(new ValueExpression(new IntValue()), "b");
        this.sym_table.put("b", new BoolValue());
        this.id_not_declared = new AssignmentStatement(new ValueExpression(new BoolValue()),"c");
        this.state = new ProgramState(new myStack<IStatement>(), this.sym_table, new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), this.id_not_declared);
    }

    @Test
    void execute() {
        ProgramState st = this.test_statement.execute(this.state);
        assertEquals(17,((IntValue)st.getSymbol_table().get("a")).getValue());
        assertThrows(myException.class, () ->{
            this.not_match_type.execute(this.state);
        });
        assertThrows(myException.class, () -> {
            this.id_not_declared.execute(this.state);
        });
    }
}