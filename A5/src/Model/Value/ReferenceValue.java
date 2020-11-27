package Model.Value;

import Model.Type.ReferenceType;
import Model.Type.Type;
import Model.Value.*;

public class ReferenceValue implements Value {
    private int address;
    private Type locationType;

    public ReferenceValue(){
        this.address = 0;
        this.locationType = null;
    }

    public ReferenceValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new ReferenceType(this.locationType);
    }

    public Type getLocationType(){ return this.locationType; }

    public int getAddress(){ return this.address; }

    @Override
    public String toString(){ return "Ref( " + Integer.toString(this.address) + ", " + this.locationType.toString() + " )" ;}

}
