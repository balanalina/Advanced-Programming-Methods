package Model.Statement;

import ADT.ImyDictionary;
import ADT.ImyStack;
import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Value.*;

public class IfStatement implements IStatement{
    private IExpression condition;
    private IStatement then;
    private IStatement otherwise;

    public IfStatement(IExpression exp, IStatement st1, IStatement st2){
        this.condition = exp;
        this.then = st1;
        this.otherwise = st2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        ImyStack<IStatement> stack = state.getExecution_stack();
        ImyDictionary<String, Value> sym_table = state.getSymbol_table();
        //eval the conditional expression
        Value cond_val = this.condition.eval(sym_table, state.getHeap_table());
        //check if condition evaluates to our BoolType
        if(cond_val.getType().equals(new BoolType())){
            //push to the stack the corresponding statement to be executed depending of the condition evaluation
            if(((BoolValue) cond_val).getValue())
                stack.push(this.then);
            else
                stack.push(this.otherwise);
        }
        else
            throw new myException("Condition must be boolean!");
        return state;
    }

    @Override
    public String toString(){
        return "if("+this.condition.toString()+"){ " + this.then.toString() + " } else{ " + this.otherwise.toString() + "}";
    }
}
