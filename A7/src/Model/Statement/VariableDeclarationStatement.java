package Model.Statement;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Type.*;
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
                    if(this.id_type.equals(new StringType()))
                        sym_table.put(this.identifier, new StringValue());
                    else {
                        Type inner = ((ReferenceType)this.id_type).getInner();
                        sym_table.put(this.identifier, new ReferenceType(inner).defaultValue());
                    }
        }
        else
            throw new myException("Variable " + this.identifier + " has already been declared!");
        return null;
    }

    @Override
    public String toString(){
        return this.id_type.toString() + " " + this.identifier;
    }

    @Override
    public ImyDictionary<String, Type> typeCheck(ImyDictionary<String, Type> typeEnv) throws myException {
        typeEnv.put(this.identifier, this.id_type);
        return typeEnv;
    }
}
