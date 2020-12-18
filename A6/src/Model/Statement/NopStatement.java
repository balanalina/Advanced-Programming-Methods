package Model.Statement;

import Model.Exception.myException;
import Model.ProgramState;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) throws myException {
        return null;
    }

    @Override
    public String toString() { return "nop"; }
}
