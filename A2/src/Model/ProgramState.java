package Model;

import ADT.ImyDictionary;
import ADT.ImyList;
import ADT.ImyStack;
import Model.Statement.IStatement;
import com.sun.jdi.Value;
import org.hamcrest.core.Is;

public class ProgramState {
    private ImyStack<IStatement> execution_stack;
    private ImyDictionary<String, Value> symbol_table;
    private ImyList<String> out_table;
    private IStatement original_program;

    public ProgramState(ImyStack<IStatement> stack, ImyDictionary<String, Value> sym, ImyList<String> out,
                        IStatement prg){
        this.execution_stack = stack;
        this.symbol_table = sym;
        this.out_table = out;
        this.original_program = prg;
        this.execution_stack.push(prg);
    }

    //getters
    public ImyStack<IStatement> getExecution_stack(){ return this.execution_stack; }
    public ImyDictionary<String, Value> getSymbol_table() { return this.symbol_table; }
    public ImyList<String> getOut_table() { return this.out_table; }
    public IStatement getOriginal_program() { return this.original_program; }

    //setters
    public void setExecution_stack(ImyStack<IStatement> new_stack) { this.execution_stack = new_stack; }
    public void setSymbol_table(ImyDictionary<String, Value> new_symbol_table){ this.symbol_table = new_symbol_table; }
    public void setOut_table(ImyList<String> new_out_table){ this.out_table = new_out_table; }
    public void setOriginal_program(IStatement new_program){ this.original_program = new_program; }

    //to string
    @Override
    public String toString(){
        return "Execution Stack:\n"+ this.execution_stack+"\nSymbol Table:\n"+ this.symbol_table+"\nOut Table:\n"+ this.out_table+"\n";
    }
}
