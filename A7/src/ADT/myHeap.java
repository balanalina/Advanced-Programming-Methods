package ADT;

import Model.Exception.myException;
import Model.Value.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class myHeap<Value> implements ImyHeap<Value> {
    private HashMap<Integer, Value> heap;
    private ConcurrentHashMap<Integer, Value> myHeap;
    private Semaphore semaphore;

    public myHeap(){
        this.heap = new HashMap<>();
        this.myHeap = new ConcurrentHashMap<>();
        this.semaphore = new Semaphore(1);
    }

    @Override
    public synchronized Integer generateAddress() {
        try {
            this.semaphore.acquire();
            while (true) {
                int address = new Random().nextInt(9999);
                if (!this.myHeap.containsKey(address))
                    this.semaphore.release();
                    return address;
            }
        }
        catch (InterruptedException e) {
            throw new myException(e.getMessage());
        }
    }

    @Override
    public void setContent(HashMap<Integer, Value> newHeap) {
        this.heap = (HashMap<Integer, Value>) newHeap;
        this.setDictionary((HashMap<Integer, Value>) newHeap);
    }

    @Override
    public Map<Integer, Value> getContent() {
        return this.myHeap;
    }

    @Override
    public int size() {
        return this.myHeap.size();
    }

    @Override
    public Value put(Integer key, Value value) {
        this.myHeap.put(key, value);
        return this.heap.put(key, value);
    }

    @Override
    public Value get(Integer key) throws myException {
        return this.myHeap.get(key);
    }

    @Override
    public Value remove(Integer key) {
        this.myHeap.remove(key);
        return this.heap.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return this.myHeap.isEmpty();
    }

    @Override
    public Set<Integer> keys() {
        return this.myHeap.keySet();
    }

    @Override
    public Collection<Value> values() {
        return this.myHeap.values();
    }

    @Override
    public boolean containsKey(Integer key) {
        return this.myHeap.containsKey(key);
    }

    @Override
    public ImyDictionary<Integer, Value> cloneDictionary() {
        return null;
    }

    @Override
    public void setDictionary(HashMap<Integer, Value> newDictionary) {
        try {
            this.semaphore.acquire();
            this.heap = newDictionary;
            this.myHeap=new ConcurrentHashMap<>();
            for(Integer key: newDictionary.keySet())
                this.heap.put(key,newDictionary.get(key));
            this.semaphore.release();
        }
        catch (InterruptedException e){
            throw new myException(e.getMessage());
        }
    }


    @Override
    public String toString(){
        String values = "[ ";
        for(Integer key: this.myHeap.keySet()){
            values += key.toString() + " -> " + this.heap.get(key) + " ,";
        }
        values += " ]";
        return values;
    }
}
