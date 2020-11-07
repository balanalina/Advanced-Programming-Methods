package Model.Type;

public class StringType implements Type {
    @Override
    public boolean equals(Object string){
        if(string instanceof StringType)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){ return "String "; }
}
