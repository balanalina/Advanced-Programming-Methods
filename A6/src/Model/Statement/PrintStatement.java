package Model.Statement;

import ADT.ImyDictionary;
import ADT.ImyList;
import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.Type;
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
        out.add(this.expression.eval(state.getSymbol_table(), state.getHeap_table()));
        return null;
    }

    @Override
    public String toString(){
        return "print(" + this.expression.toString() + ");";
    }

    @Override
    public ImyDictionary<String, Type> typeCheck(ImyDictionary<String, Type> typeEnv) throws myException {
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
