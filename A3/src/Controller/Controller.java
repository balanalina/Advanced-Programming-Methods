package Controller;

import ADT.ImyStack;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Statement.IStatement;
import Repository.IRepository;

public class Controller {
    private IRepository repo;

    public Controller(IRepository repo){
        this.repo = repo;
    }

    public ProgramState oneStep(ProgramState state) throws myException {
        //check if there are still statements to be executed
        if(state.getExecution_stack().empty())
            throw new myException("Execution stack is empty!");
        //get the first statement that needs to be executed
        IStatement current_statement = state.getExecution_stack().pop();
        //execute the statement and return the new program state
        return current_statement.execute(state);
    }

    //do oneStep until the stack is empty
    public void allSteps(){
        ProgramState ps = this.repo.getCurrentProgram();
        System.out.println("Start of execution \n ------------------------------------------------------\n");
        while(!ps.getExecution_stack().empty()){
            System.out.println("\nStep \n ------------------------------------------------------\n");
            try{
                oneStep(ps);
                System.out.println(ps.toString());
            }
            catch (Exception e){
                throw new myException(e.getMessage());
            }
        }
        System.out.println("------------------------------------------------------\nEnd of execution");
    }
}
