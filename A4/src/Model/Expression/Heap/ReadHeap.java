package Model.Expression.Heap;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Value.*;

public class ReadHeap implements IExpression {
    private IExpression expression;

    public ReadHeap(IExpression exp){
        this.expression = exp;
    }

    @Override
    public Value eval(ImyDictionary<String, Value> symTable, ImyHeap<Value> heapTable) throws myException {
        Value val_expr = this.expression.eval(symTable, heapTable);
        if(val_expr.getType() instanceof ReferenceType){
            Integer address = ((ReferenceValue)val_expr).getAddress();
            if(heapTable.containsKey(address)){
                return heapTable.get(address);
            }
            else
                throw new myException("Address " + Integer.toString(address) + " is not defined in the heap table!");
        }
        else
            throw new myException("Expression must be of reference type!");
    }

    @Override
    public String toString(){
        return "ReadHeap(" + this.expression.toString() + ") ";
    }

    @Override
    public Type getType() {
        return new ReferenceType();
    }
}
