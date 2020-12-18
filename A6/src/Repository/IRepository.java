package Repository;

import Model.Exception.myException;
import Model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public interface IRepository {
    ProgramState getCurrentProgram();
    void logProgramStateExecution(ProgramState state) throws myException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> newList);
}
