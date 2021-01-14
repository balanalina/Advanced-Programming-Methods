package Model.Expression;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import Model.Exception.myException;
import Model.Type.Type;
import Model.Value.Value;

public interface IExpression {

    Value eval(ImyDictionary<String,Value> symTable, ImyHeap<Value> heapTable) throws myException;

    public String toString();

    Type typeCheck(ImyDictionary<String, Type> typeEnv) throws myException;

    Type getType();
}
