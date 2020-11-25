package Model;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import ADT.ImyList;
import ADT.ImyStack;
import Model.Statement.IStatement;
import Model.Value.*;

import java.io.BufferedReader;

public class ProgramState {
    private ImyStack<IStatement> execution_stack;
    private ImyDictionary<String, Value> symbol_table;
    private ImyList<Value> out_table;
    private ImyDictionary<StringValue, BufferedReader> file_table;
    public ImyHeap<Value> heap_table;
    private IStatement original_program;

    public ProgramState(ImyStack<IStatement> stack, ImyDictionary<String, Value> sym, ImyList<Value> out,
                        ImyDictionary<StringValue, BufferedReader> file, ImyHeap<Value> heap,  IStatement prg){
        this.execution_stack = stack;
        this.symbol_table = sym;
        this.out_table = out;
        this.file_table = file;
        this.heap_table = heap;
        this.original_program = prg;
        this.execution_stack.push(prg);
    }

    //getters
    public ImyStack<IStatement> getExecution_stack(){ return this.execution_stack; }
    public ImyDictionary<String, Model.Value.Value> getSymbol_table() { return this.symbol_table; }
    public ImyList<Value> getOut_table() { return this.out_table; }
    public ImyDictionary<StringValue, BufferedReader> getFile_table(){ return this.file_table; }
    public ImyHeap<Value> getHeap_table(){ return this.heap_table; }
    public IStatement getOriginal_program() { return this.original_program; }

    //setters
    public void setExecution_stack(ImyStack<IStatement> new_stack) { this.execution_stack = new_stack; }
    public void setSymbol_table(ImyDictionary<String, Value> new_symbol_table){ this.symbol_table = new_symbol_table; }
    public void setOut_table(ImyList<Value> new_out_table){ this.out_table = new_out_table; }
    public void setFile_table(ImyDictionary<StringValue, BufferedReader> new_file_table){ this.file_table = new_file_table; }
    public void setHeap_table(ImyHeap<Value> new_heap_table){ this.heap_table = new_heap_table; }
    public void setOriginal_program(IStatement new_program){ this.original_program = new_program; }

    //to string
    @Override
    public String toString(){
        return "Execution Stack:\n"+ this.execution_stack+"\nSymbol Table:\n"+ this.symbol_table+"\nOut Table:\n"+ this.out_table+
                "\nFile Table:\n" + this.file_table + "\nHeap Table:\n" + this.heap_table.toString() + "\n";
    }
}
