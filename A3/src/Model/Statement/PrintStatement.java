package Model.Statement;

import ADT.ImyList;
import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Value.*;

public class PrintStatement implements IStatement {
    private IExpression expression;

    public PrintStatement(IExpression exp){
        this.expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        ImyList<Value> out = state.getOut_table();
        //add the string to be printed to the out table
        out.add(this.expression.eval(state.getSymbol_table()));
        return state;
    }

    @Override
    public String toString(){
        return "print(" + this.expression.toString() + ");";
    }
}
