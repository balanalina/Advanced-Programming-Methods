package Repository;

import ADT.ImyList;
import ADT.myList;
import Model.ProgramState;

public class Repository implements IRepository {
    private ImyList<ProgramState> program;

    public Repository(ProgramState state){
        this.program = new myList<ProgramState>();
        this.program.add(state);
    }

    @Override
    public ProgramState getCurrentProgram() {
        return this.program.get(0); //we assume to have just one program
    }
}
