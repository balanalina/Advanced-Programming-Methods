package Repo;

import Exception.myException;
import Domain.Vegetable;

public class Repo implements IRepository {
    private Vegetable[] array;
    private int nrOfElements;
    //Constructor
    public Repo(int dim)
    {
        this.nrOfElements = 0;
        this.array = new Vegetable[dim];
    }
    public int getLength() { return this.nrOfElements;}
    @Override
    public void add(Vegetable v) throws myException{
        if(this.nrOfElements == this.array.length)
            throw new myException("Repository is full. You can't add other vegetables! \n");
        this.array[this.nrOfElements] = v;
        this.nrOfElements ++;
    }
    @Override
    public void remove(int pos) throws myException
    {
        if(pos < 0 || pos >= this.nrOfElements)
            throw new myException("Invalid position. There is no vegetable on that position! \n");
        for(int i = pos;i<this.nrOfElements -1 ;i++)
            this.array[i] = this.array[i+1];
        this.nrOfElements--;
    }
    public Vegetable[] getAll(){
        Vegetable[] copyArray = new Vegetable[this.nrOfElements];
        for(int i = 0;i < this.nrOfElements ; i++)
            copyArray[i] = this.array[i];
        return copyArray;
    }
    public String printAll(){
        String s = "";
        for(int i = 0; i < this.nrOfElements ; i++)
            s += i+1 + "." + this.array[i].toString();
        return s;
    }
}
