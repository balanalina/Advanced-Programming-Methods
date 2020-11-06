package Model.Expression;

import ADT.ImyDictionary;
import Model.Exception.myException;
import Model.Type.Type;
import Model.Value.Value;

public interface IExpression {

    Value eval(ImyDictionary<String,Value> symTable) throws myException;

    public String toString();

    Type getType();
}
