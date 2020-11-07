package Model.Value;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.*;

public class StringValue implements Value {
    private String value;

    public StringValue(String value){
        this.value = value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString(){ return this.value;  }
}
