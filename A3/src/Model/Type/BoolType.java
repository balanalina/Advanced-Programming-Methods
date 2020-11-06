package Model.Type;

public class BoolType implements Type {

    @Override
    public boolean equals(Object bool){
        if(bool instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "Bool \n";
    }
}
