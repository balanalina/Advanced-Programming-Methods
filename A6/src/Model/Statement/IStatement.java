package Model.Statement;

import Model.Exception.myException;
import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws myException;
    public String toString();
}
