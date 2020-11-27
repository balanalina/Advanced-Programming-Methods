package Controller;

import ADT.ImyStack;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Value.ReferenceValue;
import Repository.IRepository;
import Model.Value.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        repo.logProgramStateExecution();
        while(!ps.getExecution_stack().empty()){
            try{
                oneStep(ps);
                repo.logProgramStateExecution();
                executeGarbageCollector();
            }
            catch (Exception e){
                throw new myException(e.getMessage());
            }
        }
    }

    private void executeGarbageCollector(){
        ProgramState current = this.repo.getCurrentProgram();
        List<Integer> symadd = getAddrfromSymTable(current.getSymbol_table().values());
        List<Integer> heapadd = getAddrfromHeap(current.getHeap_table().values());
        symadd.addAll(heapadd);
        current.getHeap_table().setContent((HashMap<Integer, Value>) unsafeGarbageCollector(symadd,current.getHeap_table().getContent()));
    }

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> addresses, Map<Integer,Value> heap){
        return heap.entrySet().stream().filter(e->addresses.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    private List<Integer> getAddrfromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream().filter(v->v instanceof ReferenceValue).map(v->((ReferenceValue) v).getAddress()).collect(Collectors.toList());
    }

    private List<Integer> getAddrfromHeap(Collection<Value> heapValues){
        return heapValues.stream().filter(v->v instanceof ReferenceValue).map(v->((ReferenceValue) v).getAddress()).collect(Collectors.toList());
    }
}
