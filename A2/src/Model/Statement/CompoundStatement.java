package Model.Statement;

import ADT.ImyStack;
import Model.Exception.myException;
import Model.ProgramState;

public class CompoundStatement implements IStatement {
    private IStatement first_statement;
    private IStatement second_statement;

    public CompoundStatement(IStatement first,IStatement second){
        this.first_statement = first;
        this.second_statement = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        ImyStack<IStatement> stack = state.getExecution_stack();
        //split the compound statement and push to the stack the resulting
        stack.push(this.second_statement);
        stack.push(this.first_statement);
        return state;
    }

    @Override
    public String toString(){
        return "(" + this.first_statement.toString() + " ; " + this.second_statement.toString();
    }
}
