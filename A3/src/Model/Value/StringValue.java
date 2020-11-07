package Model.Value;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.*;

public class StringValue implements Value {
    private String value;

    //no value constructor
    public StringValue(){ this.value = ""; }

    //value constructor
    public StringValue(String value){
        this.value = value;
    }

    public String getValue(){ return this.value; }

    @Override
    public boolean equals(Object o){
        if(o instanceof StringValue)
            return true;
        else
            return false;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString(){ return this.value;  }
}
