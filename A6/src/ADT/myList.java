package ADT;

import java.util.ArrayList;
import java.util.List;

public class myList<A> implements ImyList<A> {
    private ArrayList<A> list;

    public myList(){
        this.list = new ArrayList<A>();
    }

    @Override
    public boolean add(A element) {
        return list.add(element);
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public boolean contains(A element) {
        return this.list.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public A get(int index) {
        return this.list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public List<A> getList() {
        return this.list;
    }

    @Override
    public void setList(List<A> newList) {
        this.list = ((ArrayList)newList);
    }

    @Override
    public String toString() { return this.list.toString(); }
}
