package Controller;
import Exception.*;

import Domain.Vegetable;
import Repo.Repo;
import Repo.Repository;

public class Controller{
    private Repository repo;

    public Controller(Repository repository) { this.repo = repository; }

    public void add(Vegetable v) throws myException {
        this.repo.add(v);
    }
    public int getLength() { return this.repo.getLength(); }

    public void remove(int pos) throws myException{
     this.repo.remove(pos);
    }
    public String filterByWeight(double weight){
        String s = "";
        Vegetable[] filter = this.repo.getAll();
        for(Vegetable e: filter)
            if(e.getW() > weight)
                s += e.toString();
        return s;
    }
    public String printAll(){ return this.repo.printAll(); }
}
