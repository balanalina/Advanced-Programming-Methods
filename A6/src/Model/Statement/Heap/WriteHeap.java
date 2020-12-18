package Model.Statement.Heap;

import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Type.ReferenceType;
import Model.Value.*;

public class WriteHeap implements IStatement {
    String variable_name;
    IExpression expression;

    public WriteHeap(String name,IExpression exp){
        this.variable_name = name;
        this.expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        if(state.getSymbol_table().containsKey(this.variable_name)){
            Value var_eval = state.getSymbol_table().get(this.variable_name);
            if(var_eval.getType() instanceof ReferenceType){
                Integer address = ((ReferenceValue)var_eval).getAddress();
                if(state.getHeap_table().containsKey(address)){
                    Value expr_eval = this.expression.eval(state.getSymbol_table(), state.getHeap_table());
                    if(expr_eval.getType().equals(((ReferenceValue)var_eval).getLocationType())){
                        state.getHeap_table().put(address, expr_eval);
                    }
                    else
                        throw new myException("Expression " + this.expression.toString() + " must have type " + ((ReferenceValue)var_eval).getLocationType().toString());
                }
                else
                    throw new myException("Variable " + this.variable_name + "does not point to a valid heap address!");
            }
            else
                throw new myException("Variable " + this.variable_name + " must be of reference type!");
        }
        else
            throw new myException("Variable " + this.variable_name + " was not declared!");
        return null;
    }

    @Override
    public String toString(){
        return "WriteHeap(" + this.variable_name + ", "+this.expression.toString() +" );";
    }
}
