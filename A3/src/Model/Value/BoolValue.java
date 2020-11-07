package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    private boolean value;

    //no value constructor
    public BoolValue(){
        this.value = false;
    }

    //value constructor
    public BoolValue(boolean value){
        this.value = value;
    }

    public boolean getValue(){
        return this.value;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof BoolValue)
            return true;
        else
            return false;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return Boolean.toString(this.value);
    }
}
