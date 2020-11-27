package Model.Type;

import Model.Value.ReferenceValue;
import Model.Value.Value;

public class ReferenceType implements Type {
    private Type inner;

    public ReferenceType(){
        this.inner = null;
    }

    public ReferenceType(Type inner){
         this.inner = inner;
    }

    public Type getInner(){ return this.inner; }

    @Override
    public String toString(){ return "Ref( " + this.inner.toString() + " )"; }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof ReferenceType)
            if(this.inner.equals(((ReferenceType) obj).inner))
                return true;
        return false;
    }

    @Override
    public Value defaultValue() {
        return new ReferenceValue(0, this.inner);
    }
}
