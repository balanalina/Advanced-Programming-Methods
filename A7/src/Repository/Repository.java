package Repository;

import ADT.ImyList;
import ADT.myList;
import Model.Exception.myException;
import Model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private ImyList<ProgramState> program;
    private String log_file;

    public Repository(ProgramState state, String file_name){
        this.program = new myList<ProgramState>();
        this.program.add(state);
        this.log_file = file_name;
        try (PrintWriter writer = new PrintWriter(file_name)) {
            writer.print("");
            writer.close();
        }
        catch(IOException e){
            throw new myException("Something went wrong! Cannot open file "+ this.log_file+"!");
        }
    }

    @Override
    public ProgramState getCurrentProgram() {
        return this.program.get(0); //we assume to have just one program
    }

    @Override
    public void logProgramStateExecution(ProgramState state) throws myException {
        try{
            PrintWriter log_file= new PrintWriter(new BufferedWriter(new FileWriter(this.log_file, true)));
            log_file.write("----Start of the execution---- ");
            log_file.write(System.getProperty("line.separator"));
            log_file.write(state.toString());
            log_file.write("----End of execution---- ");
            log_file.write(System.getProperty("line.separator"));
            log_file.write(System.getProperty("line.separator"));
            log_file.close();
        }
        catch (IOException e){
            throw new myException("Something went wrong! Cannot write to file!");
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.program.getList();
    }

    @Override
    public void setProgramList(List<ProgramState> newList) {
        this.program.setList(newList);
    }
}
