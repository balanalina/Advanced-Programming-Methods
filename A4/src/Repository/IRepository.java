package Repository;

import Model.Exception.myException;
import Model.ProgramState;

public interface IRepository {
    ProgramState getCurrentProgram();
    void logProgramStateExecution() throws myException;
}
