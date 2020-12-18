package Model.Statement;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Type.Type;

public interface IStatement {
    ProgramState execute(ProgramState state) throws myException;
    public String toString();
    ImyDictionary<String, Type> typeCheck(ImyDictionary<String, Type> typeEnv) throws myException;
}
