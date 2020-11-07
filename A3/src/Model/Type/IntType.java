package Model.Type;

import Model.Value.IntValue;
import Model.Value.Value;

public class IntType implements Type{
    @Override
    public boolean equals(Object integer){
        if(integer instanceof IntType)
            return true;
        else
            return false;
    }

    @Override
    public Value defaultValue() {
        return new IntValue();
    }

    @Override
    public String toString(){
        return "Int";
    }
}
