package Model.Type;

import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {
    @Override
    public boolean equals(Object string){
        if(string instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public Value defaultValue() {
        return new StringValue();
    }

    @Override
    public String toString(){ return "String "; }
}
