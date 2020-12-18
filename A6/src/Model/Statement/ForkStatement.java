package Model.Statement;

import ADT.ImyDictionary;
import ADT.myStack;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Value.*;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement stmt){
        this.statement = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        return new ProgramState(new myStack<IStatement>(),state.getSymbol_table().cloneDictionary(),state.getOut_table(),state.getFile_table(),state.getHeap_table(),statement);
    }

    @Override
    public String toString(){
        return "Fork( " + this.statement.toString() + " );";
    }
}
