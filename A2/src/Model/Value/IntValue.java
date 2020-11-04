package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value{
    private int value;

    //no value constructor
    public IntValue(){
        this.value = 0;
    }

    //value constructor
    public IntValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }
}
