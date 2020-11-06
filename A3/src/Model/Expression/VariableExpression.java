package Model.Expression;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

public class VariableExpression implements IExpression {
    private String identifier;
    private Value value;

    public VariableExpression(String variable_name){
        this.identifier = variable_name;
        this.value = null;
    }

    @Override
    public String toString(){
        return this.identifier;
    }

    //look for the identifier in the symbol table
    @Override
    public Value eval(ImyDictionary<String, Value> symTable) throws myException {
        this.value = symTable.get(this.identifier);
        return this.value;
    }

    @Override
    public Type getType() {
        return this.value.getType();
    }
}
