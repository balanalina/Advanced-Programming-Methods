package Repo;

import Domain.Vegetable;
import Exception.myException;

public interface Repository {
    public void add(Vegetable v) throws myException;
    public void remove(int pos) throws myException;
    public Vegetable[] getAll();
    public int getLength();
    public String printAll();
}
