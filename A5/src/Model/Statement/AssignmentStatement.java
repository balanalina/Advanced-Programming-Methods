package Model.Statement;

import ADT.ImyDictionary;
import ADT.ImyStack;
import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Value.*;

public class AssignmentStatement implements IStatement {
    private String identifier;
    private IExpression assigned_value;

    public AssignmentStatement(IExpression exp, String id){
        this.assigned_value = exp;
        this.identifier = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        ImyDictionary<String, Value> sym_table = state.getSymbol_table();
        //check if the variable was declared
        if(sym_table.containsKey(this.identifier)){
            //eval the expression
            Value val = this.assigned_value.eval(sym_table, state.getHeap_table());
            //check if declaration type and expression type match
            if(val.getType().equals(sym_table.get(this.identifier).getType())){
                //assign the value to the variable
                sym_table.put(this.identifier,val);
            }
            else
                throw new myException("Expression type do not match declared variable type!");
        }
        else
            throw new myException("Variable " + this.identifier + " has not been declared!");
        return state;
    }

    @Override
    public String toString(){
        return this.identifier + "=" + this.assigned_value;
    }
}
