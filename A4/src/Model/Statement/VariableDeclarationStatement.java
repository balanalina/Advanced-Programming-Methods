package Model.Statement;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.*;

public class VariableDeclarationStatement implements IStatement{
    private String identifier;
    private Type id_type;

    public VariableDeclarationStatement(String id, Type type){
        this.identifier = id;
        this.id_type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        ImyDictionary<String, Value> sym_table = state.getSymbol_table();
        //check if the variable was already declared
        if(!sym_table.containsKey(this.identifier)){
            //add the corresponding entry for the variable in the symbol table
            if(this.id_type.equals(new BoolType()))
                sym_table.put(this.identifier, new BoolValue());
            else
                if(this.id_type.equals(new IntType()))
                    sym_table.put(this.identifier, new IntValue());
                else
                    sym_table.put(this.identifier, new StringValue());
        }
        else
            throw new myException("Variable " + this.identifier + " has already been declared!");
        return state;
    }

    @Override
    public String toString(){
        return this.id_type.toString() + " " + this.identifier;
    }
}
