package Model.Expression;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import Model.Exception.myException;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;

enum arithmeticOperand{
    PLUS("+"),
    MINUS("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private String value;
    arithmeticOperand(String s) {
        this.value = s;
    }

    public String toString()
    {
        return this.value;
    }
}

public class ArithmeticExpression implements IExpression {
    private IExpression e1;
    private IExpression e2;
    private arithmeticOperand op;

    public ArithmeticExpression(IExpression e1, IExpression e2, String op){
        this.e1 = e1;
        this.e2 = e2;
        this.op = this.checkOp(op);
    }

    private arithmeticOperand checkOp(String op) throws myException{
        try{
            return arithmeticOperand.valueOf(op);
        }
        catch (Exception e){
            throw new myException("Unexpected operand!");
        }
    }

    @Override
    public Value eval(ImyDictionary<String, Value> symTable, ImyHeap<Value> heapTable) throws myException {
        Value v1,v2;
        //get the value of the first operand
        v1 = this.e1.eval(symTable, heapTable);
        //if the value it is not our defined IntType throw an exception
        if(v1.getType().equals(new IntType())){
            //get the value of the second operand
            v2 = this.e2.eval(symTable, heapTable);
            //we check the type for the second value and throw an exception if needed
            if(v2.getType().equals(new IntType())){
                //cast the expressions' values to BoolValue
                IntValue val1,val2;
                val1 = (IntValue)v1;
                val2 = (IntValue)v2;
                //return the result of the expression as a IntValue
                switch (op){
                    case PLUS:
                        return new IntValue(val1.getValue() + val2.getValue());
                    case MINUS:
                        return new IntValue(val1.getValue() - val2.getValue());
                    case MULTIPLICATION:
                        return new IntValue(val1.getValue() * val2.getValue());
                    case DIVISION:
                        if(val2.getValue() == 0)
                            throw new myException("Cannot divide by zero!");
                        else
                            return new IntValue(val1.getValue() / val2.getValue());
                    default:
                        throw new myException("Unexpected operand!");
                }
            }
            else
                throw new myException("First operand " + this.e2.toString() + " must be of bool type!");
        }
        else
            throw new myException("First operand " + this.e1.toString() + " must be of int type!");
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString() { return this.e1 + this.op.toString() + this.e2;}

    @Override
    public Type typeCheck(ImyDictionary<String, Type> typeEnv) throws myException {
        Type type1;
        Type type2;
        type1 = this.e1.typeCheck(typeEnv);
        type2 = this.e2.typeCheck(typeEnv);
        if(type1.equals(new IntType()))
            if(type2.equals(new IntType()))
                return new IntType();
            else
                throw new myException("The second operand is not an integer!");
            else
                throw new myException("The first operand is not an integer!");
    }
}
