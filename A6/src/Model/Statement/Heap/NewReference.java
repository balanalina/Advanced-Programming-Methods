package Model.Statement.Heap;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.*;

public class NewReference implements IStatement {
    private String variable_name;
    IExpression expression;

    public NewReference(String var_name, IExpression expr){
        this.variable_name = var_name;
        this.expression = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        if(state.getSymbol_table().containsKey(this.variable_name)){
            if(state.getSymbol_table().get(this.variable_name).getType() instanceof ReferenceType){
                Type var_type = ((ReferenceValue)state.getSymbol_table().get(this.variable_name)).getLocationType();
                Value exp_value = this.expression.eval(state.getSymbol_table(), state.getHeap_table());
                if(exp_value.getType().equals(var_type)){
                    int address = state.getHeap_table().generateAddress();
                    state.getHeap_table().put(address, exp_value);
                    state.getSymbol_table().put(this.variable_name, new ReferenceValue(address, var_type));
                }
                else
                    throw new myException("Declared variable name must have matching type with expression!");
            }
            else
                throw new myException("Variable " + this.variable_name + " must be of reference type!");
        }
        else
            throw new myException("Variable " + this.variable_name.toString() + " was not defined!");
        return null;
    }

    @Override
    public String toString(){ return "new_reference( " + this.variable_name.toString() + ", " + this.expression.toString() + " );"; }

    @Override
    public ImyDictionary<String, Type> typeCheck(ImyDictionary<String, Type> typeEnv) throws myException {
        Type variableType = typeEnv.get(this.variable_name);
        Type expressionType = this.expression.typeCheck(typeEnv);
        if(variableType.equals(new ReferenceType(expressionType)))
            return typeEnv;
        else
            throw new myException("The operands have different types!");
    }
}
