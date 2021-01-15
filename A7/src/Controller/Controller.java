package Controller;

import ADT.ImyHeap;
import ADT.ImyList;
import ADT.myDictionary;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.ReferenceValue;
import Repository.IRepository;
import Model.Value.*;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    private ExecutorService service = null;

    public Controller(IRepository repo){
        this.repo = repo;
    }

    //do oneStep until the stack is empty
    public void allSteps() throws InterruptedException {
        this.service = Executors.newFixedThreadPool(2);
        List<ProgramState> list = removeCompleted(this.repo.getProgramList());
        while(list.size() > 0){
            executeGarbageCollector();
            oneStepForAll(list);
            list = removeCompleted(this.repo.getProgramList());
        }
        this.service.shutdownNow();
        this.repo.setProgramList(list);
    }

    public void typeCheck(){
        this.repo.getCurrentProgram().getOriginal_program().typeCheck(new myDictionary<String, Type>());
    }

    private void oneStepForAll(List<ProgramState> list) throws InterruptedException {
        list.forEach(prg->{repo.logProgramStateExecution(prg);
            System.out.println(prg);});

        List<Callable<ProgramState>> callList = list.stream().filter(p->!p.getExecution_stack().empty()).
                map((ProgramState p)->(Callable<ProgramState>)(p::oneStep)).collect(Collectors.toList());

        List<ProgramState> newProgramList = service.invokeAll(callList).stream().map(future->{
            try{
                return future.get();
            }
            catch (myException | InterruptedException | ExecutionException exc){
                throw new myException(exc.getMessage());
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        list.addAll(newProgramList);
        list.forEach(prg->{
            System.out.println(prg);repo.logProgramStateExecution(prg);});
        repo.setProgramList(list);
    }

    private List<ProgramState> removeCompleted(List<ProgramState> list){
        return list.stream().filter(program -> program.isNotCompleted()).collect(Collectors.toList());
    }

    private void executeGarbageCollector(){
        //firstly we work with the sym table
        List<ProgramState> programs = repo.getProgramList();
        List<List<Integer>> addrSymTableList = programs.stream().map(ProgramState::getSymbol_table).map(p->getAddrFromSymTable(p.values())).collect(Collectors.toList());
        List<Integer> addresses= new ArrayList<Integer>();
        addrSymTableList.forEach(addresses::addAll);
        List<Integer> add2 = getAddrFromHeap(programs.get(0).getHeap_table().values());
        addresses.addAll(add2);
        Map<Integer,Value> garbCollector = unsafeGarbageCollector(addresses,programs.get(0).getHeap_table().getContent());
        programs.forEach(p->p.getHeap_table().setContent((HashMap) garbCollector));
    }
    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> addresses, Map<Integer,Value> heap){
        return heap.entrySet().stream().filter(e->addresses.contains(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream().
                filter(v->v instanceof ReferenceValue).
                map(v->((ReferenceValue)v).getAddress()).
                collect(Collectors.toList());
    }

    private List<Integer> getAddrFromHeap(Collection<Value> heapValues){
        return heapValues.stream().
                filter(v->v instanceof ReferenceValue)
                .map(v->((ReferenceValue)v).getAddress())
                .collect(Collectors.toList());
    }

    public Integer getNumberOfStates(){
        int counter =0;
        for(ProgramState program :this.repo.getProgramList())
            if(!program.getExecution_stack().empty())
                counter++;
        return counter;
    }

    public List<Integer> getListOfThreadID() {
        return this.repo.getProgramList().stream().map(ProgramState::getThread_id).collect(Collectors.toList());
    }

    public void oneStepGUI() throws InterruptedException {
        service = Executors.newFixedThreadPool(5);
        removeCompleted(repo.getProgramList());
        List<ProgramState> programs = repo.getProgramList();

        if(programs.size()>0){
            //executeGarbageCollector();
            oneStepForAll(programs);
            removeCompleted(repo.getProgramList());
            service.shutdownNow();
        }
    }

    public ProgramState getProgramWithID(Integer currentThreadID) {
        if(currentThreadID == null)
            return null;
        for(ProgramState p : repo.getProgramList()){
            if(p.getThread_id() ==  (int) currentThreadID)
                return p;
        }
        return null;
    }

    public ImyHeap<Value> getHeapTable() {
            return repo.getProgramList().get(0).getHeap_table();
    }

    public ImyList<Value> getOutTable() {
        return repo.getProgramList().get(0).getOut_table();
    }
}
