package Model.Type;

import Model.Value.BoolValue;
import Model.Value.Value;

public class BoolType implements Type {

    @Override
    public boolean equals(Object bool){
        if(bool instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public Value defaultValue() {
        return new BoolValue();
    }

    @Override
    public String toString(){ return "Bool "; }
}
