package Model.Statement;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Type.Type;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) throws myException {
        return null;
    }

    @Override
    public String toString() { return "nop"; }

    @Override
    public ImyDictionary<String, Type> typeCheck(ImyDictionary<String, Type> typeEnv) throws myException {
        return typeEnv;
    }
}
