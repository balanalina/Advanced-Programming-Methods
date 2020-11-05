package Model.Expression;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

enum logicOperand{
    and,or;
}

public class LogicExpression implements IExpression{
    IExpression e1;
    IExpression e2;
    logicOperand op;


    public LogicExpression(IExpression e1, IExpression e2,String op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = this.checkOp(op);
    }

    private logicOperand checkOp(String op) throws myException{
        try{
            return logicOperand.valueOf(op);
        }
        catch (Exception e){
            throw new myException(e.getMessage());
        }
    }

    @Override
    public Value eval(ImyDictionary<String, Value> symTable) throws myException {
        Value v1,v2;
        //get the value of the first operand
        v1 = e1.eval(symTable);
        //if the value it is not our defined BoolType throw an exception
        if(v1.getType().equals(new BoolType())) {
            //get the value of the second operand
            v2 = e2.eval(symTable);
            //we check the type for the second value and throw an exception if needed
            if(v2.getType().equals(new BoolType())){
                //cast the expressions' values to BoolValue
                BoolValue val1 = (BoolValue)v1;
                BoolValue val2 = (BoolValue)v2;
                //return the result of the expression as a BoolValue
                switch(op){
                    case and:
                        return new BoolValue(val1.getValue() && val2.getValue());
                    case or:
                        return new BoolValue(val1.getValue() || val2.getValue());
                    default:
                        throw new myException("Unexpected operand!");
                }
            }
            else
                throw new myException("Second operand " + this.e2.toString() + " must be of bool type!");
        }
        else
            throw new myException("First operand " + this.e1.toString() + " must be of bool type!");
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

}
