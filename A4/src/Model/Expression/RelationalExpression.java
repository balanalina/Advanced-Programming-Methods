package Model.Expression;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

enum relationalOperand{
    SMALLER("<"),
    SMALLEREQUAL("<="),
    EQUAL("=="),
    NOTEQUAL("!="),
    GREATER(">"),
    GREATEREQUAL(">=");

    private String value;
    relationalOperand(String s) {
        this.value = s;
    }

    public String toString()
    {
        return this.value;
    }
}

public class RelationalExpression implements IExpression {
    private IExpression e1;
    private IExpression e2;
    private relationalOperand op;

    public RelationalExpression(IExpression e1, IExpression e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = this.checkOp(op);
    }

    private relationalOperand checkOp(String op) throws myException{
        try{
            return relationalOperand.valueOf(op);
        }
        catch (Exception e){
            throw new myException("Unexpected operand!");
        }
    }

    @Override
    public Value eval(ImyDictionary<String, Value> symTable) throws myException {
        Value v1,v2;
        //get the value of the first operand
        v1 = this.e1.eval(symTable);
        //if the value it is not our defined IntType throw an exception
        if(v1.getType().equals(new IntType())){
            //get the value of the second operand
            v2 = this.e2.eval(symTable);
            //we check the type for the second value and throw an exception if needed
            if(v2.getType().equals(new IntType())){
                //cast the expressions' values to BoolValue
                IntValue val1,val2;
                val1 = (IntValue)v1;
                val2 = (IntValue)v2;
                //return the result of the expression as a IntValue
                switch (op){
                    case SMALLER:
                        return new BoolValue(val1.getValue() < val2.getValue());
                    case SMALLEREQUAL:
                        return new BoolValue(val1.getValue() <= val2.getValue());
                    case EQUAL:
                        return new BoolValue(val1.getValue() == val2.getValue());
                    case NOTEQUAL:
                        return new BoolValue(val1.getValue() != val2.getValue());
                    case GREATER:
                        return new BoolValue(val1.getValue() > val2.getValue());
                    case GREATEREQUAL:
                        return new BoolValue(val1.getValue() >= val2.getValue());
                    default:
                        throw new myException("Unexpected operand!");
                }
            }
            else
                throw new myException("First operand " + this.e2.toString() + " must be of int type!");
        }
        else
            throw new myException("First operand " + this.e1.toString() + " must be of int type!");
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() { return this.e1 + this.op.toString() + this.e2;}
}
