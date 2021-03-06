package Model.Statement;

import ADT.ImyStack;
import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Value.BoolValue;

public class WhileStatement implements IStatement {
    private IExpression condition;
    private IStatement body;

    public WhileStatement(IExpression exp, IStatement st){
        this.condition = exp;
        this.body = st;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        if(condition.eval(state.getSymbol_table(), state.getHeap_table()).getType().equals(new BoolType())){
            Boolean cond = ((BoolValue)this.condition.eval(state.getSymbol_table(), state.getHeap_table())).getValue();
            ImyStack<IStatement> while_stack;
            if(cond){
                while_stack = state.getExecution_stack();
                while_stack.push(this);
                state.setExecution_stack(while_stack);
                this.body.execute(state);
            }
        }
        else
            throw new myException("Condition must evaluate to a boolean!");
        return null;
    }

    @Override
    public String toString(){
        return "while(" + this.condition.toString() + ") {" + this.body.toString() + " }";
    }
}
