package ADT;

import Model.Exception.myException;
import Model.Value.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class myHeap<Value> implements ImyHeap<Value> {
    private HashMap<Integer, Value> heap;

    public myHeap(){
        this.heap = new HashMap<>();
    }

    @Override
    public Integer generateAddress() {
        while(true){
            int address = new Random().nextInt(9999);
            if(!this.heap.containsKey(address))
                return address;
        }
    }

    @Override
    public void setContent(HashMap<Integer, Value> newHeap) {
        this.heap = newHeap;
    }

    @Override
    public HashMap<Integer, Value> getContent() {
        return this.heap;
    }

    @Override
    public int size() {
        return this.heap.size();
    }

    @Override
    public Value put(Integer key, Value value) {
        return this.heap.put(key, value);
    }

    @Override
    public Value get(Integer key) throws myException {
        return this.heap.get(key);
    }

    @Override
    public Value remove(Integer key) {
        return this.heap.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return this.heap.isEmpty();
    }

    @Override
    public Set<Integer> keys() {
        return this.heap.keySet();
    }

    @Override
    public Collection<Value> values() {
        return this.heap.values();
    }

    @Override
    public boolean containsKey(Integer key) {
        return this.heap.containsKey(key);
    }

    @Override
    public String toString(){
        String values = "[ ";
        for(Integer key: this.heap.keySet()){
            values += key.toString() + " -> " + this.heap.get(key) + " ,";
        }
        values += " ]";
        return values;
    }
}
