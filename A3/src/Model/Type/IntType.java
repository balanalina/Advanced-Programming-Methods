package Model.Type;

public class IntType implements Type{
    @Override
    public boolean equals(Object integer){
        if(integer instanceof IntType)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "Int";
    }
}
