package Model.Expression;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import Model.Exception.myException;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExpression implements IExpression {
    private Value val;

    public ValueExpression(Value value){
        this.val = value;
    }

    @Override
    public String toString(){ return this.val.toString(); }

    @Override
    public Type getType(){
        return this.val.getType();
    }

    //returns the value
    @Override
    public Value eval(ImyDictionary<String, Value> symTable, ImyHeap<Value> heapTable) throws myException {
        return this.val;
    }
}
